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

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.crafting.RunicAltarRecipe;
import vazkii.botania.common.crafting.recipe.HeadRecipe;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.data.recipes.BotaniaRecipeProvider;

import java.util.concurrent.CompletableFuture;

import static vazkii.botania.api.BotaniaAPI.botaniaRL;

public class RunicAltarProvider extends BotaniaRecipeProvider {
	public RunicAltarProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@Override
	public String getName() {
		return "Botania runic altar recipes";
	}

	@Override
	public void buildRecipes(RecipeOutput consumer) {
		final int costTier1 = 5200;
		final int costTier2 = 8000;
		final int costTier3 = 12000;

		Ingredient manaSteel = Ingredient.of(ConventionalBotaniaTags.Items.MANASTEEL_INGOTS);
		Ingredient manaDiamond = Ingredient.of(ConventionalBotaniaTags.Items.MANA_DIAMOND_GEMS);
		Ingredient manaPowder = Ingredient.of(ConventionalBotaniaTags.Items.MANA_DUSTS);

		Ingredient stone = Ingredient.of(Blocks.STONE);
		Ingredient coalBlock = Ingredient.of(ConventionalItemTags.STORAGE_BLOCKS_COAL);
		defaultReagent(consumer, idFor("water"), new ItemStack(BotaniaItems.runeWater, 2), costTier1,
				manaPowder, manaSteel, Ingredient.of(Items.BONE_MEAL), Ingredient.of(Blocks.SUGAR_CANE), Ingredient.of(ConventionalItemTags.FISHING_ROD_TOOLS));
		defaultReagent(consumer, idFor("fire"), new ItemStack(BotaniaItems.runeFire, 2), costTier1,
				manaPowder, manaSteel, Ingredient.of(ConventionalItemTags.NETHER_BRICKS), Ingredient.of(Items.GUNPOWDER), Ingredient.of(Items.NETHER_WART));
		defaultReagent(consumer, idFor("earth"), new ItemStack(BotaniaItems.runeEarth, 2), costTier1,
				manaPowder, manaSteel, stone, coalBlock, Ingredient.of(Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM));
		defaultReagent(consumer, idFor("air"), new ItemStack(BotaniaItems.runeAir, 2), costTier1,
				manaPowder, manaSteel, Ingredient.of(ItemTags.WOOL_CARPETS), Ingredient.of(Items.FEATHER), Ingredient.of(Items.STRING));

		Ingredient fire = Ingredient.of(BotaniaItems.runeFire);
		Ingredient water = Ingredient.of(BotaniaItems.runeWater);
		Ingredient earth = Ingredient.of(BotaniaItems.runeEarth);
		Ingredient air = Ingredient.of(BotaniaItems.runeAir);

		Ingredient sapling = Ingredient.of(ItemTags.SAPLINGS);
		Ingredient leaves = Ingredient.of(ItemTags.LEAVES);
		Ingredient sand = Ingredient.of(ItemTags.SAND);
		defaultReagent(consumer, idFor("spring"), new ItemStack(BotaniaItems.runeSpring), costTier2,
				new Ingredient[] { sapling, sapling, sapling, Ingredient.of(Items.WHEAT) }, water, fire);
		defaultReagent(consumer, idFor("summer"), new ItemStack(BotaniaItems.runeSummer), costTier2,
				new Ingredient[] { sand, sand, Ingredient.of(Items.SLIME_BALL), Ingredient.of(Items.MELON_SLICE) },
				earth, air);
		defaultReagent(consumer, idFor("autumn"), new ItemStack(BotaniaItems.runeAutumn), costTier2,
				new Ingredient[] { leaves, leaves, leaves, Ingredient.of(Items.SPIDER_EYE) }, fire, air);
		defaultReagent(consumer, idFor("winter"), new ItemStack(BotaniaItems.runeWinter), costTier2,
				new Ingredient[] { Ingredient.of(Blocks.SNOW_BLOCK), Ingredient.of(Blocks.SNOW_BLOCK),
						Ingredient.of(ItemTags.WOOL), Ingredient.of(Blocks.CAKE) },
				water, earth);

		Ingredient spring = Ingredient.of(BotaniaItems.runeSpring);
		Ingredient summer = Ingredient.of(BotaniaItems.runeSummer);
		Ingredient autumn = Ingredient.of(BotaniaItems.runeAutumn);
		Ingredient winter = Ingredient.of(BotaniaItems.runeWinter);

		defaultReagent(consumer, idFor("mana"), new ItemStack(BotaniaItems.runeMana), costTier2,
				manaSteel, manaSteel, manaSteel, manaSteel, manaSteel, Ingredient.of(ConventionalBotaniaTags.Items.MANA_PEARL_GEMS));

		defaultReagent(consumer, idFor("lust"), new ItemStack(BotaniaItems.runeLust), costTier3,
				new Ingredient[] { manaDiamond, manaDiamond }, summer, air);
		defaultReagent(consumer, idFor("gluttony"), new ItemStack(BotaniaItems.runeGluttony), costTier3,
				new Ingredient[] { manaDiamond, manaDiamond }, winter, fire);
		defaultReagent(consumer, idFor("greed"), new ItemStack(BotaniaItems.runeGreed), costTier3,
				new Ingredient[] { manaDiamond, manaDiamond }, spring, water);
		defaultReagent(consumer, idFor("sloth"), new ItemStack(BotaniaItems.runeSloth), costTier3,
				new Ingredient[] { manaDiamond, manaDiamond }, autumn, air);
		defaultReagent(consumer, idFor("wrath"), new ItemStack(BotaniaItems.runeWrath), costTier3,
				new Ingredient[] { manaDiamond, manaDiamond }, winter, earth);
		defaultReagent(consumer, idFor("envy"), new ItemStack(BotaniaItems.runeEnvy), costTier3,
				new Ingredient[] { manaDiamond, manaDiamond }, winter, water);
		defaultReagent(consumer, idFor("pride"), new ItemStack(BotaniaItems.runePride), costTier3,
				new Ingredient[] { manaDiamond, manaDiamond }, summer, fire);

		consumer.accept(idFor("head"), new HeadRecipe(new ItemStack(Items.PLAYER_HEAD), DEFAULT_REAGENT, 22500,
				Ingredient.of(Items.SKELETON_SKULL), Ingredient.of(ConventionalBotaniaTags.Items.PIXIE_DUSTS),
				Ingredient.of(ConventionalItemTags.PRISMARINE_GEMS), Ingredient.of(Items.NAME_TAG), Ingredient.of(Items.GOLDEN_APPLE)), null);
	}

	private static ResourceLocation idFor(String s) {
		return botaniaRL("runic_altar/" + s);
	}

	protected static Ingredient DEFAULT_REAGENT = Ingredient.of(BotaniaBlocks.livingrock);

	protected static void defaultReagent(RecipeOutput consumer, ResourceLocation id, ItemStack output, int mana, Ingredient... ingredients) {
		consumer.accept(id, new RunicAltarRecipe(output, DEFAULT_REAGENT, mana, ingredients, new Ingredient[] {}), null);
	}

	protected static void defaultReagent(RecipeOutput consumer, ResourceLocation id, ItemStack output, int mana, Ingredient[] ingredients, Ingredient... catalysts) {
		consumer.accept(id, new RunicAltarRecipe(output, DEFAULT_REAGENT, mana, ingredients, catalysts), null);
	}
}
