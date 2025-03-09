/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 */

package vazkii.botania.fabric.data;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import vazkii.botania.common.helper.ColorHelper;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConventionalBotaniaTags {

	public static <T> TagKey<T> createSuffixedTag(TagKey<T> baseTag, String suffix) {
		return TagKey.create(baseTag.registry(), baseTag.location().withSuffix("/" + suffix));
	}

	public static class Blocks {

		// Cobblestones
		public static final TagKey<Block> METAMORPHIC_COBBLESTONES = createSuffixedTag(ConventionalBlockTags.COBBLESTONES, "metamorphic");

		public static final TagKey<Block> METAMORPHIC_FOREST_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "forest");
		public static final TagKey<Block> METAMORPHIC_PLAINS_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "plains");
		public static final TagKey<Block> METAMORPHIC_MOUNTAIN_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "mountain");
		public static final TagKey<Block> METAMORPHIC_FUNGAL_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "fungal");
		public static final TagKey<Block> METAMORPHIC_SWAMP_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "swamp");
		public static final TagKey<Block> METAMORPHIC_DESERT_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "desert");
		public static final TagKey<Block> METAMORPHIC_TAIGA_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "taiga");
		public static final TagKey<Block> METAMORPHIC_MESA_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "mesa");

		// Glass
		public static final TagKey<Block> MANA_GLASS_BLOCKS = createSuffixedTag(ConventionalBlockTags.GLASS_BLOCKS, "mana");
		public static final TagKey<Block> MANA_GLASS_PANES = createSuffixedTag(ConventionalBlockTags.GLASS_PANES, "mana");

		// Storage blocks
		public static final TagKey<Block> MANASTEEL_STORAGE_BLOCKS = createSuffixedTag(ConventionalBlockTags.STORAGE_BLOCKS, "manasteel");
		public static final TagKey<Block> TERRASTEEL_STORAGE_BLOCKS = createSuffixedTag(ConventionalBlockTags.STORAGE_BLOCKS, "terrasteel");
		public static final TagKey<Block> ELEMENTIUM_STORAGE_BLOCKS = createSuffixedTag(ConventionalBlockTags.STORAGE_BLOCKS, "elementium");
		public static final TagKey<Block> MANA_DIAMOND_STORAGE_BLOCKS = createSuffixedTag(ConventionalBlockTags.STORAGE_BLOCKS, "mana_diamond");
		public static final TagKey<Block> DRAGONSTONE_STORAGE_BLOCKS = createSuffixedTag(ConventionalBlockTags.STORAGE_BLOCKS, "dragonstone");
		public static final TagKey<Block> BLAZE_STORAGE_BLOCKS = createSuffixedTag(ConventionalBlockTags.STORAGE_BLOCKS, "blaze");
		public static final TagKey<Block> PETAL_STORAGE_BLOCKS = createSuffixedTag(ConventionalBlockTags.STORAGE_BLOCKS, "petal");
		public static final Map<DyeColor, TagKey<Block>> PETAL_STORAGE_BLOCKS_BY_COLOR = ColorHelper.supportedColors()
				.collect(Collectors.toUnmodifiableMap(Function.identity(),
						dyeColor -> createSuffixedTag(PETAL_STORAGE_BLOCKS, dyeColor.getSerializedName())));

	}

	public static class Items {

		// Buckets
		public static final TagKey<Item> EXTRAPOLATING_BUCKETS = createSuffixedTag(ConventionalItemTags.BUCKETS, "extrapolating");

		// Cobblestones
		public static final TagKey<Item> METAMORPHIC_COBBLESTONES = createSuffixedTag(ConventionalItemTags.COBBLESTONES, "metamorphic");

		public static final TagKey<Item> METAMORPHIC_FOREST_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "forest");
		public static final TagKey<Item> METAMORPHIC_PLAINS_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "plains");
		public static final TagKey<Item> METAMORPHIC_MOUNTAIN_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "mountain");
		public static final TagKey<Item> METAMORPHIC_FUNGAL_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "fungal");
		public static final TagKey<Item> METAMORPHIC_SWAMP_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "swamp");
		public static final TagKey<Item> METAMORPHIC_DESERT_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "desert");
		public static final TagKey<Item> METAMORPHIC_TAIGA_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "taiga");
		public static final TagKey<Item> METAMORPHIC_MESA_COBBLESTONES = createSuffixedTag(METAMORPHIC_COBBLESTONES, "mesa");

		// Dusts
		public static final TagKey<Item> MANA_DUSTS = createSuffixedTag(ConventionalItemTags.DUSTS, "mana");
		public static final TagKey<Item> PIXIE_DUSTS = createSuffixedTag(ConventionalItemTags.DUSTS, "pixie");

		// Gems
		public static final TagKey<Item> MANA_DIAMOND_GEMS = createSuffixedTag(ConventionalItemTags.GEMS, "mana_diamond");
		public static final TagKey<Item> MANA_PEARL_GEMS = createSuffixedTag(ConventionalItemTags.GEMS, "mana_pearl");
		public static final TagKey<Item> DRAGONSTONE_GEMS = createSuffixedTag(ConventionalItemTags.GEMS, "dragonstone");

		// Glass
		public static final TagKey<Item> MANA_GLASS_BLOCKS = createSuffixedTag(ConventionalItemTags.GLASS_BLOCKS, "mana");
		public static final TagKey<Item> MANA_GLASS_PANES = createSuffixedTag(ConventionalItemTags.GLASS_PANES, "mana");

		// Ingots
		public static final TagKey<Item> MANASTEEL_INGOTS = createSuffixedTag(ConventionalItemTags.INGOTS, "manasteel");
		public static final TagKey<Item> TERRASTEEL_INGOTS = createSuffixedTag(ConventionalItemTags.INGOTS, "terrasteel");
		public static final TagKey<Item> ELEMENTIUM_INGOTS = createSuffixedTag(ConventionalItemTags.INGOTS, "elementium");
		public static final TagKey<Item> GAIA_INGOTS = createSuffixedTag(ConventionalItemTags.INGOTS, "gaia");

		// Nuggets
		public static final TagKey<Item> MANASTEEL_NUGGETS = createSuffixedTag(ConventionalItemTags.NUGGETS, "manasteel");
		public static final TagKey<Item> TERRASTEEL_NUGGETS = createSuffixedTag(ConventionalItemTags.NUGGETS, "terrasteel");
		public static final TagKey<Item> ELEMENTIUM_NUGGETS = createSuffixedTag(ConventionalItemTags.NUGGETS, "elementium");

		// Rods
		public static final TagKey<Item> LIVINGWOOD_RODS = createSuffixedTag(ConventionalItemTags.RODS, "livingwood");
		public static final TagKey<Item> DREAMWOOD_RODS = createSuffixedTag(ConventionalItemTags.RODS, "dreamwood");

		// Storage blocks
		public static final TagKey<Item> MANASTEEL_STORAGE_BLOCKS = createSuffixedTag(ConventionalItemTags.STORAGE_BLOCKS, "manasteel");
		public static final TagKey<Item> TERRASTEEL_STORAGE_BLOCKS = createSuffixedTag(ConventionalItemTags.STORAGE_BLOCKS, "terrasteel");
		public static final TagKey<Item> ELEMENTIUM_STORAGE_BLOCKS = createSuffixedTag(ConventionalItemTags.STORAGE_BLOCKS, "elementium");
		public static final TagKey<Item> MANA_DIAMOND_STORAGE_BLOCKS = createSuffixedTag(ConventionalItemTags.STORAGE_BLOCKS, "mana_diamond");
		public static final TagKey<Item> DRAGONSTONE_STORAGE_BLOCKS = createSuffixedTag(ConventionalItemTags.STORAGE_BLOCKS, "dragonstone");
		public static final TagKey<Item> BLAZE_STORAGE_BLOCKS = createSuffixedTag(ConventionalItemTags.STORAGE_BLOCKS, "blaze");
		public static final TagKey<Item> PETAL_STORAGE_BLOCKS = createSuffixedTag(ConventionalItemTags.STORAGE_BLOCKS, "petal");
		public static final Map<DyeColor, TagKey<Item>> PETAL_STORAGE_BLOCKS_BY_COLOR = ColorHelper.supportedColors()
				.collect(Collectors.toUnmodifiableMap(Function.identity(),
						dyeColor -> createSuffixedTag(PETAL_STORAGE_BLOCKS, dyeColor.getSerializedName())));

		// TODO: maybe tags for rods, horns and similar tools?

	}

}
