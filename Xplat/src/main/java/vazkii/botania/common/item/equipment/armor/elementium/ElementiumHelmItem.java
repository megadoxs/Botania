/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.common.item.equipment.armor.elementium;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import org.jetbrains.annotations.Nullable;

import vazkii.botania.api.mana.ManaDiscountArmor;
import vazkii.botania.common.handler.PixieHandler;

import static vazkii.botania.api.BotaniaAPI.botaniaRL;

public class ElementiumHelmItem extends ElementiumArmorItem implements ManaDiscountArmor {
	public ElementiumHelmItem(Properties props) {
		super(Type.HELMET, props.component(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
				.add(PixieHandler.PIXIE_SPAWN_CHANCE,
						PixieHandler.makeModifier(botaniaRL("helmet_modifier"), 0.11),
						EquipmentSlotGroup.bySlot(EquipmentSlot.MAINHAND))
				.build()));
	}

	@Override
	public float getDiscount(ItemStack stack, int slot, Player player, @Nullable ItemStack tool) {
		return hasArmorSet(player) ? 0.1F : 0F;
	}

}
