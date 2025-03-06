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
import net.minecraft.world.item.component.ItemAttributeModifiers;

import vazkii.botania.common.handler.PixieHandler;

import static vazkii.botania.api.BotaniaAPI.botaniaRL;

public class ElementiumChestItem extends ElementiumArmorItem {

	public ElementiumChestItem(Properties props) {
		super(Type.CHESTPLATE, props.component(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
				.add(PixieHandler.PIXIE_SPAWN_CHANCE,
						PixieHandler.makeModifier(botaniaRL("chest_modifier"), 0.17),
						EquipmentSlotGroup.bySlot(EquipmentSlot.MAINHAND))
				.build()));
	}
}
