/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 */

package vazkii.botania.common.crafting.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.bauble.FlugelTiaraItem;

public class TiaraWingsRecipe extends CustomRecipe {
	public static final RecipeSerializer<TiaraWingsRecipe> SERIALIZER = new Serializer();

	private final Ingredient material;
	private final int variant;

	public TiaraWingsRecipe(CraftingBookCategory category, Ingredient material, int variant) {
		super(category);
		if (material.test(BotaniaItems.flightTiara.getDefaultInstance())) {
			throw new IllegalArgumentException("Material cannot be a Flügel Tiara");
		}
		this.material = material;
		this.variant = variant;
	}

	@Override
	public boolean matches(CraftingInput input, Level level) {
		if (input.ingredientCount() != (material.isEmpty() ? 1 : 2)) {
			return false;
		}

		boolean foundTiara = false;
		boolean foundMaterial = material.isEmpty();

		for (ItemStack stack : input.items()) {
			if (stack.isEmpty()) {
				continue;
			}
			if (!foundTiara && matchesTiara(stack)) {
				foundTiara = true;
			} else if (!foundMaterial && material.test(stack)) {
				foundMaterial = true;
			} else {
				return false;
			}
		}

		return foundMaterial && foundTiara;
	}

	private boolean matchesTiara(ItemStack stack) {
		return stack.is(BotaniaItems.flightTiara) && FlugelTiaraItem.getVariant(stack) != variant;
	}

	@Override
	public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
		ItemStack tiara = input.items().stream().filter(stack -> stack.is(BotaniaItems.flightTiara))
				.findFirst().orElseThrow().copy();
		FlugelTiaraItem.setVariant(tiara, variant);
		return tiara;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return material.isEmpty()
				? NonNullList.of(Ingredient.EMPTY, Ingredient.of(BotaniaItems.flightTiara))
				: NonNullList.of(Ingredient.EMPTY, Ingredient.of(BotaniaItems.flightTiara), material);
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider registries) {
		ItemStack tiara = new ItemStack(BotaniaItems.flightTiara);
		FlugelTiaraItem.setVariant(tiara, variant);
		return tiara;
	}

	@Override
	public String getGroup() {
		return "botania:flight_tiara_wings";
	}

	public Ingredient material() {
		return material;
	}

	public int variant() {
		return variant;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	private static class Serializer implements RecipeSerializer<TiaraWingsRecipe> {
		private static final MapCodec<TiaraWingsRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
				CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.EQUIPMENT).forGetter(TiaraWingsRecipe::category),
				Ingredient.CODEC.fieldOf("material").forGetter(TiaraWingsRecipe::material),
				ExtraCodecs.intRange(0, FlugelTiaraItem.WING_TYPES).fieldOf("variant").forGetter(TiaraWingsRecipe::variant)
		).apply(instance, TiaraWingsRecipe::new));
		private static final StreamCodec<RegistryFriendlyByteBuf, TiaraWingsRecipe> STREAM_CODEC = StreamCodec.composite(
				CraftingBookCategory.STREAM_CODEC, TiaraWingsRecipe::category,
				Ingredient.CONTENTS_STREAM_CODEC, TiaraWingsRecipe::material,
				ByteBufCodecs.VAR_INT, TiaraWingsRecipe::variant,
				TiaraWingsRecipe::new
		);

		@Override
		public MapCodec<TiaraWingsRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, TiaraWingsRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
