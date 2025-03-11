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

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.helper.ColorHelper;
import vazkii.botania.data.BlockTagProvider;

import java.util.concurrent.CompletableFuture;

public class ConventionalBlockTagProvider extends BlockTagProvider {

	public ConventionalBlockTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {

		// Cobblestones
		// TODO: switch over biome block/item IDs to match their in-game names?
		tag(ConventionalBotaniaTags.Blocks.METAMORPHIC_FOREST_COBBLESTONES).add(BotaniaBlocks.biomeCobblestoneForest);
		tag(ConventionalBotaniaTags.Blocks.METAMORPHIC_PLAINS_COBBLESTONES).add(BotaniaBlocks.biomeCobblestonePlains);
		tag(ConventionalBotaniaTags.Blocks.METAMORPHIC_MOUNTAIN_COBBLESTONES).add(BotaniaBlocks.biomeCobblestoneMountain);
		tag(ConventionalBotaniaTags.Blocks.METAMORPHIC_FUNGAL_COBBLESTONES).add(BotaniaBlocks.biomeCobblestoneFungal);
		tag(ConventionalBotaniaTags.Blocks.METAMORPHIC_SWAMP_COBBLESTONES).add(BotaniaBlocks.biomeCobblestoneSwamp);
		tag(ConventionalBotaniaTags.Blocks.METAMORPHIC_DESERT_COBBLESTONES).add(BotaniaBlocks.biomeCobblestoneDesert);
		tag(ConventionalBotaniaTags.Blocks.METAMORPHIC_TAIGA_COBBLESTONES).add(BotaniaBlocks.biomeCobblestoneTaiga);
		tag(ConventionalBotaniaTags.Blocks.METAMORPHIC_MESA_COBBLESTONES).add(BotaniaBlocks.biomeCobblestoneMesa);

		tag(ConventionalBotaniaTags.Blocks.METAMORPHIC_COBBLESTONES)
				.addTag(ConventionalBotaniaTags.Blocks.METAMORPHIC_FOREST_COBBLESTONES)
				.addTag(ConventionalBotaniaTags.Blocks.METAMORPHIC_PLAINS_COBBLESTONES)
				.addTag(ConventionalBotaniaTags.Blocks.METAMORPHIC_MOUNTAIN_COBBLESTONES)
				.addTag(ConventionalBotaniaTags.Blocks.METAMORPHIC_FUNGAL_COBBLESTONES)
				.addTag(ConventionalBotaniaTags.Blocks.METAMORPHIC_SWAMP_COBBLESTONES)
				.addTag(ConventionalBotaniaTags.Blocks.METAMORPHIC_DESERT_COBBLESTONES)
				.addTag(ConventionalBotaniaTags.Blocks.METAMORPHIC_TAIGA_COBBLESTONES)
				.addTag(ConventionalBotaniaTags.Blocks.METAMORPHIC_MESA_COBBLESTONES);
		tag(ConventionalBlockTags.COBBLESTONES).addTag(ConventionalBotaniaTags.Blocks.METAMORPHIC_COBBLESTONES);
		tag(ConventionalBlockTags.STONES).add(
				BotaniaBlocks.biomeStoneForest, BotaniaBlocks.biomeStonePlains,
				BotaniaBlocks.biomeStoneMountain, BotaniaBlocks.biomeStoneFungal,
				BotaniaBlocks.biomeStoneSwamp, BotaniaBlocks.biomeStoneDesert,
				BotaniaBlocks.biomeStoneTaiga, BotaniaBlocks.biomeStoneMesa
		);

		// Dyed blocks
		// TODO: generate for covered spreaders, once those are flattened

		// Glass blocks and panes
		tag(ConventionalBotaniaTags.Blocks.MANA_GLASS_BLOCKS).add(BotaniaBlocks.manaGlass, BotaniaBlocks.elfGlass, BotaniaBlocks.bifrostPerm);
		tag(ConventionalBlockTags.GLASS_BLOCKS).addTag(ConventionalBotaniaTags.Blocks.MANA_GLASS_BLOCKS);

		tag(ConventionalBotaniaTags.Blocks.MANA_GLASS_PANES).add(BotaniaBlocks.managlassPane, BotaniaBlocks.alfglassPane, BotaniaBlocks.bifrostPane);
		tag(ConventionalBlockTags.GLASS_PANES).addTag(ConventionalBotaniaTags.Blocks.MANA_GLASS_PANES);

		// Storage blocks
		tag(ConventionalBotaniaTags.Blocks.MANASTEEL_STORAGE_BLOCKS).add(BotaniaBlocks.manasteelBlock);
		tag(ConventionalBotaniaTags.Blocks.TERRASTEEL_STORAGE_BLOCKS).add(BotaniaBlocks.terrasteelBlock);
		tag(ConventionalBotaniaTags.Blocks.ELEMENTIUM_STORAGE_BLOCKS).add(BotaniaBlocks.elementiumBlock);
		tag(ConventionalBotaniaTags.Blocks.MANA_DIAMOND_STORAGE_BLOCKS).add(BotaniaBlocks.manaDiamondBlock);
		tag(ConventionalBotaniaTags.Blocks.DRAGONSTONE_STORAGE_BLOCKS).add(BotaniaBlocks.dragonstoneBlock);
		tag(ConventionalBotaniaTags.Blocks.BLAZE_STORAGE_BLOCKS).add(BotaniaBlocks.blazeBlock);

		ColorHelper.supportedColors().forEach(dyeColor -> {
			var tag = ConventionalBotaniaTags.Blocks.PETAL_STORAGE_BLOCKS_BY_COLOR.get(dyeColor);
			tag(tag).add(BotaniaBlocks.getPetalBlock(dyeColor));
			tag(ConventionalBotaniaTags.Blocks.PETAL_STORAGE_BLOCKS).addTag(tag);
		});
		tag(ConventionalBlockTags.STORAGE_BLOCKS)
				.addTag(ConventionalBotaniaTags.Blocks.MANASTEEL_STORAGE_BLOCKS)
				.addTag(ConventionalBotaniaTags.Blocks.TERRASTEEL_STORAGE_BLOCKS)
				.addTag(ConventionalBotaniaTags.Blocks.ELEMENTIUM_STORAGE_BLOCKS)
				.addTag(ConventionalBotaniaTags.Blocks.MANA_DIAMOND_STORAGE_BLOCKS)
				.addTag(ConventionalBotaniaTags.Blocks.DRAGONSTONE_STORAGE_BLOCKS)
				.addTag(ConventionalBotaniaTags.Blocks.BLAZE_STORAGE_BLOCKS)
				.addTag(ConventionalBotaniaTags.Blocks.PETAL_STORAGE_BLOCKS);

		// Miscellaneous
		tag(ConventionalBlockTags.SKULLS).add(BotaniaBlocks.gaiaHead, BotaniaBlocks.gaiaHeadWall);
		tag(ConventionalBlockTags.STRIPPED_LOGS).add(
				BotaniaBlocks.livingwoodLogStripped, BotaniaBlocks.livingwoodLogStrippedGlimmering,
				BotaniaBlocks.dreamwoodLogStripped, BotaniaBlocks.dreamwoodLogStrippedGlimmering
		);
		tag(ConventionalBlockTags.STRIPPED_WOODS).add(
				BotaniaBlocks.livingwoodStripped, BotaniaBlocks.livingwoodStrippedGlimmering,
				BotaniaBlocks.dreamwoodStripped, BotaniaBlocks.dreamwoodStrippedGlimmering
		);
	}

	@Override
	public String getName() {
		return "Conventional " + super.getName();
	}
}
