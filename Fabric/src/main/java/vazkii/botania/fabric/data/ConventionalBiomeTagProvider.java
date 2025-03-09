package vazkii.botania.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import vazkii.botania.common.lib.BotaniaTags;
import vazkii.botania.data.util.DummyTagLookup;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ConventionalBiomeTagProvider extends TagsProvider<Biome> {
	private static final Set<TagKey<Biome>> RELEVANT_TAGS = Set.of(
			ConventionalBiomeTags.IS_OVERWORLD,
			ConventionalBiomeTags.IS_NETHER,
			ConventionalBiomeTags.IS_BADLANDS,
			ConventionalBiomeTags.IS_BEACH,
			ConventionalBiomeTags.IS_COLD,
			ConventionalBiomeTags.IS_CONIFEROUS_TREE,
			ConventionalBiomeTags.IS_DESERT,
			ConventionalBiomeTags.IS_FOREST,
			ConventionalBiomeTags.IS_JUNGLE,
			ConventionalBiomeTags.IS_MOUNTAIN,
			ConventionalBiomeTags.IS_MUSHROOM,
			ConventionalBiomeTags.IS_PLAINS,
			ConventionalBiomeTags.IS_SAVANNA,
			ConventionalBiomeTags.IS_SNOWY,
			ConventionalBiomeTags.IS_SWAMP,
			ConventionalBiomeTags.IS_UNDERGROUND
	);

	public ConventionalBiomeTagProvider(FabricDataOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Registries.BIOME, lookupProvider, DummyTagLookup.completedFuture(RELEVANT_TAGS));
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {

		tag(BotaniaTags.Biomes.MYSTICAL_FLOWER_SPAWNLIST)
				.addTag(ConventionalBiomeTags.IS_OVERWORLD);
		tag(BotaniaTags.Biomes.MYSTICAL_FLOWER_BLOCKLIST)
				.addTag(ConventionalBiomeTags.IS_MUSHROOM);

		tag(BotaniaTags.Biomes.MYSTICAL_MUSHROOM_SPAWNLIST)
				.addTag(ConventionalBiomeTags.IS_OVERWORLD)
				.addTag(ConventionalBiomeTags.IS_NETHER);
		tag(BotaniaTags.Biomes.MYSTICAL_MUSHROOM_BLOCKLIST);

		tag(BotaniaTags.Biomes.MARIMORPHOSIS_DESERT_BONUS)
				.addTag(ConventionalBiomeTags.IS_DESERT)
				.addTag(ConventionalBiomeTags.IS_SAVANNA);
		tag(BotaniaTags.Biomes.MARIMORPHOSIS_FOREST_BONUS)
				.addTag(ConventionalBiomeTags.IS_FOREST);
		tag(BotaniaTags.Biomes.MARIMORPHOSIS_FUNGAL_BONUS)
				.addTag(ConventionalBiomeTags.IS_MUSHROOM)
				.addTag(ConventionalBiomeTags.IS_UNDERGROUND);
		tag(BotaniaTags.Biomes.MARIMORPHOSIS_MESA_BONUS)
				.addTag(ConventionalBiomeTags.IS_BADLANDS)
				.addTag(ConventionalBiomeTags.IS_SAVANNA);
		tag(BotaniaTags.Biomes.MARIMORPHOSIS_MOUNTAIN_BONUS)
				.addTag(ConventionalBiomeTags.IS_MOUNTAIN);
		tag(BotaniaTags.Biomes.MARIMORPHOSIS_PLAINS_BONUS)
				.addTag(ConventionalBiomeTags.IS_PLAINS)
				.addTag(ConventionalBiomeTags.IS_BEACH);
		tag(BotaniaTags.Biomes.MARIMORPHOSIS_SWAMP_BONUS)
				.addTag(ConventionalBiomeTags.IS_SWAMP)
				.addTag(ConventionalBiomeTags.IS_JUNGLE);
		tag(BotaniaTags.Biomes.MARIMORPHOSIS_TAIGA_BONUS)
				.addTag(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
				.addTag(ConventionalBiomeTags.IS_COLD)
				.addTag(ConventionalBiomeTags.IS_SNOWY);
	}

	@Override
	public String getName() {
		return "Conventional " + super.getName();
	}

}
