/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.common.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.function.Consumer;

public final class LootHandler {
	public static final ResourceLocation GOG_SEEDS_TABLE = ResourceLocation.fromNamespaceAndPath(BotaniaAPI.GOG_MODID, "extra_seeds");

	public static void lootLoad(ResourceLocation id, Consumer<LootPool.Builder> addPool) {

		ResourceKey<LootTable> injectedLootTable = BotaniaLootTables.getInjectedLootTable(id);
		if (BotaniaLootTables.all().contains(injectedLootTable)) {
			addPool.accept(LootPool.lootPool().add(NestedLootTable.lootTableReference(injectedLootTable)));
		}
		if (XplatAbstractions.INSTANCE.gogLoaded() && (Blocks.SHORT_GRASS.getLootTable().location().equals(id)
				|| Blocks.TALL_GRASS.getLootTable().location().equals(id))) {
			ResourceKey<LootTable> gogSeedsKey = ResourceKey.create(Registries.LOOT_TABLE, GOG_SEEDS_TABLE);
			addPool.accept(LootPool.lootPool().add(NestedLootTable.lootTableReference(gogSeedsKey)));
		}
	}
}
