/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.api.corporea;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;

import vazkii.botania.api.ServiceUtil;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiFunction;

public interface CorporeaHelper {
	CorporeaHelper INSTANCE = ServiceUtil.findService(CorporeaHelper.class, () -> new CorporeaHelper() {});

	static CorporeaHelper instance() {
		return INSTANCE;
	}

	/**
	 * Gets all the nodes on this spark network. This is memoized for use once every tick.
	 * The order of the nodes in this set are unspecified.
	 */
	default Set<CorporeaNode> getNodesOnNetwork(CorporeaSpark spark) {
		return Collections.emptySet();
	}

	/**
	 * Create a CorporeaRequestMatcher from an ItemStack and NBT-checkness.
	 */
	default CorporeaRequestMatcher createMatcher(ItemStack stack, boolean checkNBT) {
		return CorporeaRequestMatcher.Dummy.INSTANCE;
	}

	/**
	 * Create a CorporeaRequestMatcher from a String.
	 */
	default CorporeaRequestMatcher createMatcher(String name) {
		return CorporeaRequestMatcher.Dummy.INSTANCE;
	}

	/**
	 * Requests items from the network associated with {@code spark}.
	 * 
	 * @param matcher   Specifies what you want to request. See {@link #createMatcher} to create one.
	 * @param itemCount Specifies the maximum amount you want to request. If -1, the amount is unlimited.
	 * @param requestor The entity that initiated this request, if there is one.
	 * @param doit      If false, only counts the items instead of actually extracting
	 */
	default CorporeaResult requestItem(CorporeaRequestMatcher matcher, int itemCount, CorporeaSpark spark, @Nullable LivingEntity requestor, boolean doit) {
		return CorporeaResult.Dummy.INSTANCE;
	}

	/**
	 * Gets the spark attached to the block in the coords passed in. Note that the coords passed
	 * in are for the block that the spark will be on, not the coords of the spark itself.
	 */
	@Nullable
	default CorporeaSpark getSparkForBlock(Level world, BlockPos pos) {
		return null;
	}

	/**
	 * Gets if the block in the coords passed in has a spark attached. Note that the coords passed
	 * in are for the block that the spark will be on, not the coords of the spark itself.
	 */
	default boolean doesBlockHaveSpark(Level world, BlockPos pos) {
		return getSparkForBlock(world, pos) != null;
	}

	/**
	 * Returns the comparator strength for a corporea request that corporea crystal cubes and retainers use, following
	 * the usual "each step up requires double the items" formula.
	 */
	default int signalStrengthForRequestSize(int requestSize) {
		return 0;
	}

	default <T extends CorporeaRequestMatcher> void registerRequestMatcher(ResourceLocation id, Class<T> clazz, BiFunction<CompoundTag, HolderLookup.Provider, T> deserializer) {}
}
