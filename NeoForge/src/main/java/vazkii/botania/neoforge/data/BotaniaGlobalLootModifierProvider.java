/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 */

package vazkii.botania.neoforge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.loot.BotaniaLootTables;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public class BotaniaGlobalLootModifierProvider extends GlobalLootModifierProvider {
	public BotaniaGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BotaniaAPI.MODID);
	}

	@Override
	protected void start() {
		for (ResourceKey<LootTable> lootTable : BotaniaLootTables.all()) {
			ResourceKey<LootTable> targetTable = BotaniaLootTables.getInjectionTargetLootTable(lootTable);
			if (targetTable != null) {
				add(lootTable.location().getPath(), new AddTableLootModifier(
						new LootItemCondition[] { LootTableIdCondition.builder(targetTable.location()).build() },
						lootTable)
				);
			}
		}
/* Not for Botania itself, but useful for producing the GoG extra seeds injection:
		add("extra_seeds", new AddTableLootModifier(new LootItemCondition[] {
				AnyOfCondition.anyOf(
						LootTableIdCondition.builder(Blocks.SHORT_GRASS.getLootTable().location()),
						LootTableIdCondition.builder(Blocks.TALL_GRASS.getLootTable().location())
				).build()
		}, ResourceKey.create(Registries.LOOT_TABLE,
				ResourceLocation.fromNamespaceAndPath(BotaniaAPI.GOG_MODID, "extra_seeds"))));
*/
	}
}
