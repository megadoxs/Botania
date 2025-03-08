/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.common.helper;

import com.mojang.datafixers.util.Either;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RegistryHelper {
	@SuppressWarnings("unchecked")
	public static <T> Registry<T> getRegistry(ResourceKey<Registry<T>> resourceKey) {
		return (Registry<T>) BuiltInRegistries.REGISTRY.get(resourceKey.location());
	}

	public static <T> HolderProxy<T> holderProxy(ResourceKey<Registry<T>> registryKey, ResourceLocation id, T value) {
		return new HolderProxy<>(ResourceKey.create(registryKey, id), value);
	}

	public static class HolderProxy<T> implements Holder<T> {
		private final ResourceKey<T> resourceKey;
		private final T value;
		@Nullable
		private Reference<T> reference = null;

		public HolderProxy(ResourceKey<T> resourceKey, T value) {
			this.resourceKey = resourceKey;
			this.value = value;
		}

		public void register(Registry<T> registry) {
			if (!resourceKey.isFor(registry.key())) {
				throw new IllegalArgumentException("Mismatched registry: expected %s, got %s"
						.formatted(resourceKey.registry(), registry.key().location()));
			}
			reference = Registry.registerForHolder(registry, resourceKey, value);
		}

		@Override
		public T value() {
			return value;
		}

		@Override
		public boolean isBound() {
			return reference != null && reference.isBound();
		}

		@Override
		public boolean is(ResourceLocation location) {
			return reference != null ? reference.is(location) : resourceKey.location().equals(location);
		}

		@Override
		public boolean is(ResourceKey<T> resourceKey) {
			return this.resourceKey.equals(resourceKey);
		}

		@Override
		public boolean is(Predicate<ResourceKey<T>> predicate) {
			return predicate.test(resourceKey);
		}

		@Override
		public boolean is(TagKey<T> tagKey) {
			return reference != null && reference.is(tagKey);
		}

		@Override
		public boolean is(Holder<T> holder) {
			return holder.kind() == Kind.DIRECT ? value().equals(holder.value()) : holder.is(resourceKey);
		}

		@Override
		public Stream<TagKey<T>> tags() {
			return reference != null ? reference.tags() : Stream.empty();
		}

		@Override
		public Either<ResourceKey<T>, T> unwrap() {
			return Either.left(resourceKey);
		}

		@Override
		public Optional<ResourceKey<T>> unwrapKey() {
			return Optional.of(resourceKey);
		}

		@Override
		public Kind kind() {
			return Kind.REFERENCE;
		}

		@Override
		public boolean canSerializeIn(HolderOwner<T> owner) {
			return reference != null && reference.canSerializeIn(owner);
		}
	}
}
