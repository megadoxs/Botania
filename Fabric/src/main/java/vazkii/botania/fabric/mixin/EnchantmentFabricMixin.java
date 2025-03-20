package vazkii.botania.fabric.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentFabricMixin {
	@Inject(method = "canEnchant", cancellable = true, at = @At("HEAD"))
	public void onEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		Enchantment self = (Enchantment) (Object) this;
		/* TODO: figure out how to allow additional item types for enchantments on Fabric
			(looting applies to the swords tag by default)
		if (self == Enchantments.LOOTING && stack.is(BotaniaItems.elementiumAxe)) {
			cir.setReturnValue(true);
		}
		
		 */
	}
}
