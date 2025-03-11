/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 */
package vazkii.botania.fabric.data.xplat;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.data.recipes.BotaniaRecipeProvider;

import java.util.concurrent.CompletableFuture;

import static vazkii.botania.data.util.BotaniaRecipeHelper.deriveRecipeId;

public class SmeltingProvider extends BotaniaRecipeProvider {
	public SmeltingProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	private static Criterion<InventoryChangeTrigger.TriggerInstance> conditionsFromItem(ItemLike item) {
		return CraftingRecipeProvider.conditionsFromItem(item);
	}

	@Override
	public void buildRecipes(RecipeOutput consumer) {
		makeDefaultSmeltingRecipe(consumer, BotaniaBlocks.biomeCobblestoneForest, BotaniaBlocks.biomeStoneForest);
		makeDefaultSmeltingRecipe(consumer, BotaniaBlocks.biomeCobblestonePlains, BotaniaBlocks.biomeStonePlains);
		makeDefaultSmeltingRecipe(consumer, BotaniaBlocks.biomeCobblestoneMountain, BotaniaBlocks.biomeStoneMountain);
		makeDefaultSmeltingRecipe(consumer, BotaniaBlocks.biomeCobblestoneFungal, BotaniaBlocks.biomeStoneFungal);
		makeDefaultSmeltingRecipe(consumer, BotaniaBlocks.biomeCobblestoneSwamp, BotaniaBlocks.biomeStoneSwamp);
		makeDefaultSmeltingRecipe(consumer, BotaniaBlocks.biomeCobblestoneDesert, BotaniaBlocks.biomeStoneDesert);
		makeDefaultSmeltingRecipe(consumer, BotaniaBlocks.biomeCobblestoneTaiga, BotaniaBlocks.biomeStoneTaiga);
		makeDefaultSmeltingRecipe(consumer, BotaniaBlocks.biomeCobblestoneMesa, BotaniaBlocks.biomeStoneMesa);
		makeDefaultSmeltingRecipe(consumer, BotaniaBlocks.livingrockBrick, BotaniaBlocks.livingrockBrickCracked);
	}

	private static void makeDefaultSmeltingRecipe(RecipeOutput consumer, Block biomeCobblestone, Block biomeStone) {
		SimpleCookingRecipeBuilder
				.smelting(Ingredient.of(biomeCobblestone), RecipeCategory.BUILDING_BLOCKS, biomeStone, 0.1f, 200)
				.unlockedBy("has_item", conditionsFromItem(biomeCobblestone))
				.save(consumer, deriveRecipeId(RecipeType.SMELTING, biomeStone));
	}

	@Override
	public String getName() {
		return "Botania smelting recipes";
	}
}
