/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 */

package vazkii.botania.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.BotaniaFlowerBlocks;
import vazkii.botania.common.block.BotaniaGrassBlock;
import vazkii.botania.common.component.BotaniaDataComponents;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.lib.LibMisc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BotaniaBlockLoot extends BlockLootSubProvider {
	// TODO: which other blocks should never be deleted by explosions? (i.e. always drop as item)
	// (vanilla default: heads, shulker boxes, dragon egg, beacon, conduit)
	private static final Set<Item> EXPLOSION_RESISTANT = Stream
			.of(
					BotaniaBlocks.gaiaHead
			)
			.map(Block::asItem)
			.collect(Collectors.toSet());

	private final Set<Block> specialHandling = new HashSet<>();

	public BotaniaBlockLoot(HolderLookup.Provider registries) {
		super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), registries);
	}

	@Override
	public void generate() {
		Map<Block, LootTable.Builder> specialCases = new HashMap<>();

		// Empty
		Stream.of(
				BotaniaBlocks.bifrost,
				BotaniaBlocks.cocoon,
				BotaniaBlocks.fakeAir,
				BotaniaBlocks.manaFlame
		).forEach(b -> specialCases.put(b, noDrop()));

		// Redirects
		specialCases.put(BotaniaBlocks.cacophonium, createSingleItemTable(Blocks.NOTE_BLOCK));
		specialCases.put(BotaniaBlocks.enchantedSoil, createSingleItemTable(Blocks.DIRT));
		specialCases.put(BotaniaBlocks.enchanter, createSingleItemTable(Blocks.LAPIS_BLOCK));

		// Special
		dropWhenSilkTouch(BotaniaBlocks.cellBlock);
		specialCases.put(BotaniaBlocks.root, createSingleItemTable(BotaniaItems.livingroot, ConstantValue.exactly(4)));
		specialCases.put(BotaniaBlocks.solidVines, BotaniaLootTableProvider.copyReferencedLootTable(Blocks.VINE.getLootTable()));
		specialCases.put(BotaniaBlocks.tinyPotato, createNameableBlockEntityTable(BotaniaBlocks.tinyPotato));

		// Flower component saving
		saveSpecialFlowerState(specialCases, BotaniaFlowerBlocks.gourmaryllis, BotaniaFlowerBlocks.gourmaryllisFloating,
				BotaniaDataComponents.STREAK_LENGTH, BotaniaDataComponents.LAST_REPEATS, BotaniaDataComponents.LAST_FOODS);
		saveSpecialFlowerState(specialCases, BotaniaFlowerBlocks.hydroangeas, BotaniaFlowerBlocks.hydroangeasFloating,
				BotaniaDataComponents.COOLDOWN, BotaniaDataComponents.DECAY_TICKS);
		saveSpecialFlowerState(specialCases, BotaniaFlowerBlocks.munchdew, BotaniaFlowerBlocks.munchdewFloating,
				BotaniaDataComponents.COOLDOWN, BotaniaDataComponents.ACTIVE);
		saveSpecialFlowerState(specialCases, BotaniaFlowerBlocks.rafflowsia, BotaniaFlowerBlocks.rafflowsiaFloating,
				BotaniaDataComponents.LAST_REPEATS, BotaniaDataComponents.LAST_FLOWERS);
		saveSpecialFlowerState(specialCases, BotaniaFlowerBlocks.spectrolus, BotaniaFlowerBlocks.spectrolusFloating,
				BotaniaDataComponents.NEXT_COLOR);
		saveSpecialFlowerState(specialCases, BotaniaFlowerBlocks.thermalily, BotaniaFlowerBlocks.thermalilyFloating,
				BotaniaDataComponents.COOLDOWN);

		// TODO: move spreader attachments (wool, scaffolding) to loot table

		Map.of(
				BotaniaBlocks.biomeStoneDesert, BotaniaBlocks.biomeCobblestoneDesert,
				BotaniaBlocks.biomeStoneForest, BotaniaBlocks.biomeCobblestoneForest,
				BotaniaBlocks.biomeStoneFungal, BotaniaBlocks.biomeCobblestoneFungal,
				BotaniaBlocks.biomeStoneMesa, BotaniaBlocks.biomeCobblestoneMesa,
				BotaniaBlocks.biomeStoneMountain, BotaniaBlocks.biomeCobblestoneMountain,
				BotaniaBlocks.biomeStonePlains, BotaniaBlocks.biomeCobblestonePlains,
				BotaniaBlocks.biomeStoneSwamp, BotaniaBlocks.biomeCobblestoneSwamp,
				BotaniaBlocks.biomeStoneTaiga, BotaniaBlocks.biomeCobblestoneTaiga
		).forEach((stone, cobble) -> specialCases.put(stone, createSingleItemTableWithSilkTouch(stone, cobble)));

		for (Block block : BuiltInRegistries.BLOCK) {
			ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
			if (!LibMisc.MOD_ID.equals(blockId.getNamespace()) || specialHandling.contains(block)) {
				continue;
			}
			if (specialCases.containsKey(block)) {
				add(block, specialCases.get(block));
			} else if (block instanceof SlabBlock) {
				add(block, createSlabItemTable(block));
			} else if (block instanceof TallFlowerBlock) {
				add(block, createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
			} else if (block instanceof BotaniaGrassBlock) {
				add(block, createSingleItemTableWithSilkTouch(block, Blocks.DIRT));
			} else if (block instanceof FlowerPotBlock flowerPot) {
				dropPottedContents(flowerPot);
			} else {
				dropSelf(block);
			}
		}
	}

	private void saveSpecialFlowerState(Map<Block, LootTable.Builder> specialCases, Block flower, Block floatingFlower,
			DataComponentType<?>... components) {
		specialCases.put(flower, createBlockEntityTableWithComponents(flower, components));
		specialCases.put(floatingFlower, createBlockEntityTableWithComponents(floatingFlower, components));
	}

	@Override
	public void otherWhenSilkTouch(Block block, Block other) {
		super.otherWhenSilkTouch(block, other);
		specialHandling.add(block);
	}

	protected LootTable.Builder createBlockEntityTableWithComponents(Block block, DataComponentType<?>... componentsToInclude) {
		CopyComponentsFunction.Builder copyComponents = CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY);
		for (DataComponentType<?> component : componentsToInclude) {
			copyComponents.include(component);
		}
		return LootTable.lootTable().withPool(this.applyExplosionCondition(block, LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(block).apply(copyComponents))));
	}

	public Map<? extends ResourceKey<LootTable>, ? extends LootTable.Builder> getMap() {
		return Collections.unmodifiableMap(this.map);
	}
}
