/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.common.crafting;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.crafting.recipe.*;
import vazkii.botania.mixin.RecipeManagerAccessor;

import java.util.*;
import java.util.function.BiConsumer;

import static vazkii.botania.api.BotaniaAPI.botaniaRL;

public class BotaniaRecipeTypes {
	private static final Map<ResourceLocation, RecipeType<?>> TYPES = new LinkedHashMap<>();

	public static final RecipeType<vazkii.botania.api.recipe.ManaInfusionRecipe> MANA_INFUSION_TYPE = register(
			vazkii.botania.api.recipe.ManaInfusionRecipe.TYPE_ID);
	public static final RecipeType<vazkii.botania.api.recipe.ElvenTradeRecipe> ELVEN_TRADE_TYPE = register(
			vazkii.botania.api.recipe.ElvenTradeRecipe.TYPE_ID);
	public static final RecipeType<vazkii.botania.api.recipe.PureDaisyRecipe> PURE_DAISY_TYPE = register(
			vazkii.botania.api.recipe.PureDaisyRecipe.TYPE_ID);
	public static final RecipeType<vazkii.botania.api.recipe.BotanicalBreweryRecipe> BREW_TYPE = register(
			vazkii.botania.api.recipe.BotanicalBreweryRecipe.TYPE_ID);
	public static final RecipeType<vazkii.botania.api.recipe.PetalApothecaryRecipe> PETAL_TYPE = register(
			vazkii.botania.api.recipe.PetalApothecaryRecipe.TYPE_ID);
	public static final RecipeType<vazkii.botania.api.recipe.RunicAltarRecipe> RUNE_TYPE = register(
			vazkii.botania.api.recipe.RunicAltarRecipe.TYPE_ID);
	public static final RecipeType<vazkii.botania.api.recipe.TerrestrialAgglomerationRecipe> TERRA_PLATE_TYPE = register(
			vazkii.botania.api.recipe.TerrestrialAgglomerationRecipe.TYPE_ID);
	public static final RecipeType<vazkii.botania.api.recipe.OrechidRecipe> ORECHID_TYPE = register(
			vazkii.botania.api.recipe.OrechidRecipe.TYPE_ID);
	public static final RecipeType<vazkii.botania.api.recipe.OrechidRecipe> ORECHID_IGNEM_TYPE = register(
			vazkii.botania.api.recipe.OrechidRecipe.IGNEM_TYPE_ID);
	public static final RecipeType<vazkii.botania.api.recipe.OrechidRecipe> MARIMORPHOSIS_TYPE = register(
			vazkii.botania.api.recipe.OrechidRecipe.MARIMORPHOSIS_TYPE_ID);

	private static <T extends Recipe<?>> RecipeType<T> register(ResourceLocation id) {
		RecipeType<T> type = new BotaniaRecipeType<>(id.getPath());
		if (TYPES.put(id, type) != null) {
			throw new IllegalArgumentException("Multiple recipe types with ID " + id);
		}
		return type;
	}

	private record BotaniaRecipeType<T extends Recipe<?>>(String name) implements RecipeType<T> {
		@Override
		public String toString() {
			return name;
		}
	}

	public static void submitRecipeTypes(BiConsumer<RecipeType<?>, ResourceLocation> r) {
		TYPES.forEach((resourceLocation, recipeType) -> r.accept(recipeType, resourceLocation));
	}

	public static void submitRecipeSerializers(BiConsumer<RecipeSerializer<?>, ResourceLocation> r) {
		// serializers for our custom recipe types
		r.accept(ManaInfusionRecipe.SERIALIZER, vazkii.botania.api.recipe.ManaInfusionRecipe.TYPE_ID);
		r.accept(ElvenTradeRecipe.SERIALIZER, vazkii.botania.api.recipe.ElvenTradeRecipe.TYPE_ID);
		r.accept(LexiconElvenTradeRecipe.SERIALIZER, vazkii.botania.api.recipe.ElvenTradeRecipe.TYPE_ID_LEXICON);
		r.accept(PureDaisyRecipe.SERIALIZER, vazkii.botania.api.recipe.PureDaisyRecipe.TYPE_ID);
		r.accept(BotanicalBreweryRecipe.SERIALIZER, vazkii.botania.api.recipe.BotanicalBreweryRecipe.TYPE_ID);
		r.accept(PetalApothecaryRecipe.SERIALIZER, vazkii.botania.api.recipe.PetalApothecaryRecipe.TYPE_ID);
		r.accept(RunicAltarRecipe.SERIALIZER, vazkii.botania.api.recipe.RunicAltarRecipe.TYPE_ID);
		r.accept(HeadRecipe.SERIALIZER, vazkii.botania.api.recipe.RunicAltarRecipe.HEAD_TYPE_ID);
		r.accept(TerrestrialAgglomerationRecipe.SERIALIZER, vazkii.botania.api.recipe.TerrestrialAgglomerationRecipe.TYPE_ID);
		r.accept(OrechidRecipe.SERIALIZER, vazkii.botania.api.recipe.OrechidRecipe.TYPE_ID);
		r.accept(OrechidIgnemRecipe.SERIALIZER, vazkii.botania.api.recipe.OrechidRecipe.IGNEM_TYPE_ID);
		r.accept(MarimorphosisRecipe.SERIALIZER, vazkii.botania.api.recipe.OrechidRecipe.MARIMORPHOSIS_TYPE_ID);

		// serializers for crafting recipe variants
		r.accept(AncientWillRecipe.SERIALIZER, botaniaRL("ancient_will_attach"));
		r.accept(ArmorUpgradeRecipe.SERIALIZER, botaniaRL("armor_upgrade"));
		r.accept(BlackHoleTalismanExtractRecipe.SERIALIZER, botaniaRL("black_hole_talisman_extract"));
		r.accept(CompositeLensRecipe.SERIALIZER, botaniaRL("composite_lens"));
		r.accept(CosmeticAttachRecipe.SERIALIZER, botaniaRL("cosmetic_attach"));
		r.accept(CosmeticRemoveRecipe.SERIALIZER, botaniaRL("cosmetic_remove"));
		r.accept(LaputaShardUpgradeRecipe.SERIALIZER, botaniaRL("laputa_shard_upgrade"));
		r.accept(LensDyeingRecipe.SERIALIZER, botaniaRL("lens_dye"));
		r.accept(ManaBlasterClipRecipe.SERIALIZER, botaniaRL("mana_gun_add_clip"));
		r.accept(ManaBlasterLensRecipe.SERIALIZER, botaniaRL("mana_gun_add_lens"));
		r.accept(ManaBlasterRemoveLensRecipe.SERIALIZER, botaniaRL("mana_gun_remove_lens"));
		r.accept(ManaUpgradeRecipe.SERIALIZER, botaniaRL("mana_upgrade"));
		r.accept(MergeVialRecipe.SERIALIZER, botaniaRL("merge_vial"));
		r.accept(PhantomInkRecipe.SERIALIZER, botaniaRL("phantom_ink_apply"));
		r.accept(ResoluteIvyRecipe.SERIALIZER, botaniaRL("keep_ivy"));
		r.accept(ShapelessManaUpgradeRecipe.SERIALIZER, botaniaRL("mana_upgrade_shapeless"));
		r.accept(SpellbindingClothRecipe.SERIALIZER, botaniaRL("spell_cloth_apply"));
		r.accept(SplitLensRecipe.SERIALIZER, botaniaRL("split_lens"));
		r.accept(TerraShattererTippingRecipe.SERIALIZER, botaniaRL("terra_pick_tipping"));
		r.accept(WandOfTheForestRecipe.SERIALIZER, botaniaRL("twig_wand"));
		r.accept(WaterBottleMatchingRecipe.SERIALIZER, botaniaRL("water_bottle_matching_shaped"));

		// wrapper serializers without a fixed recipe type
		r.accept(GogAlternationRecipe.SERIALIZER, botaniaRL("gog_alternation"));
		r.accept(NbtOutputRecipe.SERIALIZER, botaniaRL("nbt_output_wrapper"));
	}

	public static <C extends RecipeInput, T extends Recipe<C>> Collection<RecipeHolder<T>> getRecipes(Level world, RecipeType<T> type) {
		return ((RecipeManagerAccessor) world.getRecipeManager()).botania_getAll(type);
	}

	@SuppressWarnings("unchecked")
	public static <C extends RecipeInput, T extends Recipe<C>> Optional<RecipeHolder<T>> getRecipe(Level world, ResourceLocation id, RecipeType<T> expectedType) {
		var holder = world.getRecipeManager().byKey(id);
		BotaniaAPI.LOGGER.info("getRecipe found {}", holder);
		return holder.isPresent() && holder.get().value().getType() == expectedType
				? holder.map(h -> (RecipeHolder<T>) h)
				: Optional.empty();
	}
}
