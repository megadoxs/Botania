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

import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.FillPlayerHead;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.entity.BotaniaEntities;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.lib.BotaniaTags;
import vazkii.botania.common.loot.BindUuid;
import vazkii.botania.common.loot.BotaniaLootTables;
import vazkii.botania.common.loot.EnableRelics;
import vazkii.botania.common.loot.TrueGuardianKiller;

import java.util.function.BiConsumer;
import java.util.stream.Stream;

// not extending EntityLootSubProvider, as that assumes 1:1 match with EntityTypes
public class BotaniaEntityLoot implements LootTableSubProvider {
	private final HolderLookup.Provider registries;

	public BotaniaEntityLoot(HolderLookup.Provider registries) {
		this.registries = registries;
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
		defineElementiumAxeBeheadingLoot(output);

		output.accept(BotaniaLootTables.FEL_BLAZE, LootTable.lootTable().withPool(LootPool.lootPool()
				.add(LootItem.lootTableItem(Items.BLAZE_POWDER)
						.apply(SetItemCountFunction.setCount(ConstantValue.exactly(6)))
						.when(InvertedLootItemCondition.invert(LootItemKilledByPlayerCondition.killedByPlayer()))
				)
				.add(LootItem.lootTableItem(Items.BLAZE_POWDER)
						.apply(SetItemCountFunction.setCount(ConstantValue.exactly(10)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 6)))
						.when(LootItemKilledByPlayerCondition.killedByPlayer())
				)
		));

		output.accept(BotaniaLootTables.GHAST_LOOT_TABLE, LootTable.lootTable().withPool(LootPool.lootPool()
				.add(LootItem.lootTableItem(Items.GHAST_TEAR))
				.add(EmptyLootItem.emptyItem().setWeight(7))
		));

		output.accept(BotaniaLootTables.GAIA_GUARDIAN_REWARD, LootTable.lootTable()
				.withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer())
						.add(LootItem.lootTableItem(BotaniaItems.lifeEssence)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(6)))
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(8))
										.when(TrueGuardianKiller.builder())
								)
						)
				)
				.withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer())
						.when(LootItemRandomChanceCondition.randomChance(0.2f))
						.add(LootItem.lootTableItem(BotaniaItems.recordGaia1))
				)
		);

		output.accept(BotaniaLootTables.GAIA_GUARDIAN_REWARD_HARD, LootTable.lootTable()
				.withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer())
						.add(LootItem.lootTableItem(BotaniaItems.lifeEssence)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(10)))
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(16))
										.when(TrueGuardianKiller.builder())
								)
						)
				)
				.withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer())
						.when(LootItemRandomChanceCondition.randomChance(0.5f))
						.add(NestedLootTable.lootTableReference(BotaniaLootTables.GAIA_GUARDIAN_LOTUSES))
				)
				.withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer())
						.add(TagEntry.expandTag(BotaniaTags.Items.ANCIENT_WILLS))
				)
				.withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer())
						.when(LootItemRandomChanceCondition.randomChance(0.25f))
						.add(LootItem.lootTableItem(BotaniaItems.overgrowthSeed)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
						)
				)
				.withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer())
						.add(NestedLootTable.lootTableReference(BotaniaLootTables.GAIA_GUARDIAN_MATERIALS))
				)
				.withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer())
						.setRolls(UniformGenerator.between(1, 6))
						.add(NestedLootTable.lootTableReference(BotaniaLootTables.GAIA_GUARDIAN_RUNES))
				)
				.withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer())
						.when(LootItemRandomChanceCondition.randomChance(0.2f))
						.add(LootItem.lootTableItem(BotaniaItems.pinkinator))
				)
				.withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer())
						.when(LootItemRandomChanceCondition.randomChance(0.44f))
						.add(LootItem.lootTableItem(BotaniaItems.recordGaia2).setWeight(14))
						.add(LootItem.lootTableItem(Items.MUSIC_DISC_13).setWeight(15))
						.add(LootItem.lootTableItem(Items.MUSIC_DISC_WAIT).setWeight(15))
				)
				.withPool(LootPool.lootPool().when(LootItemKilledByPlayerCondition.killedByPlayer())
						.when(EnableRelics.builder())
						.add(LootItem.lootTableItem(BotaniaItems.dice)
								.apply(BindUuid.builder())
						)
				)
		);
	}

	// Note: keep in sync with entity list in BotaniaLootTables
	private void defineElementiumAxeBeheadingLoot(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
		// TODO: this can technically cause a double drop if injected as additional pool
		output.accept(BotaniaLootTables.getInjectedLootTable(EntityType.WITHER_SKELETON),
				defineAxeBeheadingDropTable(Items.WITHER_SKELETON_SKULL, 0.1154f, 0.0385f));

		// Note: other skeletons don't actually drop heads from charged creepers
		Stream.of(EntityType.SKELETON, EntityType.STRAY, EntityType.BOGGED).forEach(
				entityType -> output.accept(BotaniaLootTables.getInjectedLootTable(entityType),
						defineAxeBeheadingDropTable(Items.SKELETON_SKULL, 0.1154f, 0.0385f)));

		// technically zombified piglins also drop a zombie head from charged creepers
		Stream.of(EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.HUSK, EntityType.DROWNED).forEach(
				entityType -> output.accept(BotaniaLootTables.getInjectedLootTable(entityType),
						defineAxeBeheadingDropTable(Items.ZOMBIE_HEAD, 0.0769f, 0.0769f)));

		// Note: piglin brutes don't actually drop a head from charged creepers
		Stream.of(EntityType.PIGLIN, EntityType.PIGLIN_BRUTE).forEach(
				entityType -> output.accept(BotaniaLootTables.getInjectedLootTable(entityType),
						defineAxeBeheadingDropTable(Items.PIGLIN_HEAD, 0.0769f, 0.0769f)));

		output.accept(BotaniaLootTables.getInjectedLootTable(EntityType.CREEPER),
				defineAxeBeheadingDropTable(Items.CREEPER_HEAD, 0.0769f, 0.0769f));

		output.accept(BotaniaLootTables.getInjectedLootTable(EntityType.PLAYER),
				defineAxeBeheadingDropTable(Items.PLAYER_HEAD, 0.0909f, 0.0909f)
						.apply(FillPlayerHead.fillPlayerHead(LootContext.EntityTarget.THIS)));

		output.accept(BotaniaEntities.DOPPLEGANGER.getDefaultLootTable(),
				defineAxeBeheadingDropTable(BotaniaBlocks.gaiaHead, 0.0769f, 0.0769f));
	}

	private LootTable.Builder defineAxeBeheadingDropTable(ItemLike head, float chance, float lootingMultiplier) {
		return LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(head))
				.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKING_PLAYER,
						EntityPredicate.Builder.entity().equipment(
								EntityEquipmentPredicate.Builder.equipment().mainhand(
										ItemPredicate.Builder.item().of(BotaniaItems.elementiumAxe)))))
				.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries,
						chance, lootingMultiplier))
		);
	}
}
