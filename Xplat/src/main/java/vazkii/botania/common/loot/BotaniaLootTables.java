package vazkii.botania.common.loot;

import com.google.common.collect.Sets;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static vazkii.botania.api.BotaniaAPI.botaniaRL;

public class BotaniaLootTables {
	private static final Set<ResourceKey<LootTable>> LOCATIONS = Sets.newHashSet();
	private static final Set<ResourceKey<LootTable>> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);

	public static final ResourceKey<LootTable> BEHEADING_LOOT_TABLE = register("elementium_axe_beheading");
	public static final List<ResourceKey<LootTable>> DICE_ROLL_LOOT_TABLES = IntStream.rangeClosed(1, 6)
			.mapToObj(i -> register("dice/roll_" + i)).toList();
	public static final ResourceKey<LootTable> GHAST_LOOT_TABLE = register("ghast_ender_air_crying");
	public static final ResourceKey<LootTable> LOONIUM_DEFAULT_LOOT = register("loonium/default");

	public static final ResourceKey<LootTable> LOONIUM_ARMOR_ANCIENT_CITY = register("equipment/loonium/armor_ancient_city");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_BASTION_REMNANT = register("equipment/loonium/armor_bastion_remnant");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_DESERT_PYRAMID = register("equipment/loonium/armor_desert_pyramid");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_END_CITY = register("equipment/loonium/armor_end_city");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_FORTRESS = register("equipment/loonium/armor_fortress");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_JUNGLE_TEMPLE = register("equipment/loonium/armor_jungle_temple");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_MANSION = register("equipment/loonium/armor_mansion");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_MONUMENT = register("equipment/loonium/armor_monument");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_OUTPOST = register("equipment/loonium/armor_outpost");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_PORTAL = register("equipment/loonium/armor_portal");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_SHIPWRECK = register("equipment/loonium/armor_shipwreck");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_STRONGHOLD = register("equipment/loonium/armor_stronghold");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_TRAIL_RUINS = register("equipment/loonium/armor_trail_ruins");
	public static final ResourceKey<LootTable> LOONIUM_ARMOR_TRIAL_CHAMBER = register("equipment/loonium/armor_trial_chamber");

	public static final ResourceKey<LootTable> LOONIUM_DROWNED_DEFAULT = register("equipment/loonium/drowned");
	public static final ResourceKey<LootTable> LOONIUM_DROWNED_ANCIENT_CITY = register("equipment/loonium/drowned_ancient_city");
	public static final ResourceKey<LootTable> LOONIUM_DROWNED_JUNGLE_TEMPLE = register("equipment/loonium/drowned_jungle_temple");
	public static final ResourceKey<LootTable> LOONIUM_DROWNED_MONUMENT = register("equipment/loonium/drowned_monument");
	public static final ResourceKey<LootTable> LOONIUM_DROWNED_PORTAL = register("equipment/loonium/drowned_portal");
	public static final ResourceKey<LootTable> LOONIUM_DROWNED_SHIPWRECK = register("equipment/loonium/drowned_shipwreck");
	public static final ResourceKey<LootTable> LOONIUM_DROWNED_STRONGHOLD = register("equipment/loonium/drowned_stronghold");
	public static final ResourceKey<LootTable> LOONIUM_DROWNED_TRAIL_RUINS = register("equipment/loonium/drowned_trail_ruins");
	public static final ResourceKey<LootTable> LOONIUM_DROWNED_TRIAL_CHAMBER = register("equipment/loonium/drowned_trial_chamber");

	public static final ResourceKey<LootTable> LOONIUM_PIGLIN_BRUTE_DEFAULT = register("equipment/loonium/piglin_brute");
	public static final ResourceKey<LootTable> LOONIUM_PIGLIN_BASTION_REMNANT = register("equipment/loonium/piglin_bastion_remnant");
	public static final ResourceKey<LootTable> LOONIUM_PIGLIN_PORTAL = register("equipment/loonium/piglin_ruined_portal");

	public static final ResourceKey<LootTable> LOONIUM_PILLAGER_DEFAULT = register("equipment/loonium/pillager");

	public static final ResourceKey<LootTable> LOONIUM_SKELETON_DEFAULT = register("equipment/loonium/skeleton");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_ANCIENT_CITY = register("equipment/loonium/skeleton_ancient_city");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_DESERT_PYRAMID = register("equipment/loonium/skeleton_desert_pyramid");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_JUNGLE_TEMPLE = register("equipment/loonium/skeleton_jungle_temple");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_END_CITY = register("equipment/loonium/skeleton_end_city");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_FORTRESS = register("equipment/loonium/skeleton_fortress");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_MONUMENT = register("equipment/loonium/skeleton_monument");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_OUTPOST = register("equipment/loonium/skeleton_outpost");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_PORTAL = register("equipment/loonium/skeleton_portal");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_SHIPWRECK = register("equipment/loonium/skeleton_shipwreck");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_STRONGHOLD = register("equipment/loonium/skeleton_stronghold");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_TRAIL_RUINS = register("equipment/loonium/skeleton_trail_ruins");
	public static final ResourceKey<LootTable> LOONIUM_SKELETON_TRIAL_CHAMBER = register("equipment/loonium/skeleton_trial_chamber");

	public static final ResourceKey<LootTable> LOONIUM_VINDICATOR_DEFAULT = register("equipment/loonium/vindicator");

	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_DEFAULT = register("equipment/loonium/zombie");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_ANCIENT_CITY = register("equipment/loonium/zombie_ancient_city");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_DESERT_PYRAMID = register("equipment/loonium/zombie_desert_pyramid");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_END_CITY = register("equipment/loonium/zombie_end_city");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_FORTRESS = register("equipment/loonium/zombie_fortress");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_JUNGLE_TEMPLE = register("equipment/loonium/zombie_jungle_temple");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_MONUMENT = register("equipment/loonium/zombie_monument");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_OUTPOST = register("equipment/loonium/zombie_outpost");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_PORTAL = register("equipment/loonium/zombie_portal");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_SHIPWRECK = register("equipment/loonium/zombie_shipwreck");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_STRONGHOLD = register("equipment/loonium/zombie_stronghold");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_TRAIL_RUINS = register("equipment/loonium/zombie_trail_ruins");
	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_TRIAL_CHAMBER = register("equipment/loonium/zombie_trial_chamber");

	public static final ResourceKey<LootTable> LOONIUM_ZOMBIE_VILLAGER = register("equipment/loonium/zombie_villager");

	private static ResourceKey<LootTable> register(String path) {
		return register(ResourceKey.create(Registries.LOOT_TABLE, botaniaRL(path)));
	}

	private static ResourceKey<LootTable> register(ResourceKey<LootTable> location) {
		if (LOCATIONS.add(location)) {
			return location;
		} else {
			throw new IllegalArgumentException(location + " is already a registered built-in loot table");
		}
	}

	public static Set<ResourceKey<LootTable>> all() {
		return IMMUTABLE_LOCATIONS;
	}

	/**
	 * Gets the resource key for the dice loot table for the specified number.
	 * 
	 * @param roll The rolled number. Must be between 1 and 6, inclusive.
	 * @return Loot table resource key for the dice roll.
	 */
	public static ResourceKey<LootTable> getDiceRollTable(int roll) {
		return DICE_ROLL_LOOT_TABLES.get(roll - 1);
	}
}
