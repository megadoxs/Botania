package vazkii.botania.forge.data;

import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import vazkii.botania.common.lib.LibMisc;

import java.util.Collections;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeDatagenInitializer {
	@SubscribeEvent
	public static void configureForgeDatagen(GatherDataEvent evt) {
		var generator = evt.getGenerator();
		var output = generator.getPackOutput();
		var disabledHelper = new ExistingFileHelper(Collections.emptyList(), Collections.emptySet(), false, null, null);
		var blockTagProvider = new ForgeBlockTagProvider(output, evt.getLookupProvider(), disabledHelper);
		generator.addProvider(evt.includeServer(), blockTagProvider);
		generator.addProvider(evt.includeServer(), new ForgeItemTagProvider(output, evt.getLookupProvider(),
				blockTagProvider.contentsGetter(), disabledHelper));
		generator.addProvider(evt.includeServer(), new ForgeRecipeProvider(output));
		generator.addProvider(evt.includeServer(), new ForgeBlockLootProvider(generator));
		generator.addProvider(evt.includeServer(), new ForgeBiomeTagProvider(output, evt.getLookupProvider(), disabledHelper));
		generator.addProvider(evt.includeServer(), new ForgeEntityTagProvider(output, evt.getLookupProvider(), disabledHelper));
	}
}
