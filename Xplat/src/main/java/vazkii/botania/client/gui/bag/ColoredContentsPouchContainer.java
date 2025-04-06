/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.client.gui.bag;

import it.unimi.dsi.fastutil.ints.IntList;

import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import vazkii.botania.client.gui.SlotLocked;
import vazkii.botania.common.helper.ColorHelper;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.ColoredContentsPouchItem;

import java.util.List;

public class ColoredContentsPouchContainer extends AbstractContainerMenu {
	private final ItemStack bag;
	public final Container flowerBagInv;

	public ColoredContentsPouchContainer(int windowId, Inventory playerInv, boolean isMainHand) {
		super(BotaniaItems.COLORED_CONTENTS_POUCH_CONTAINER, windowId);

		this.bag = playerInv.player.getItemInHand(isMainHand ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND);
		ColoredContentsPouchItem pouch;
		if (!(bag.getItem() instanceof ColoredContentsPouchItem pouchItem)) {
			flowerBagInv = new SimpleContainer(0);
			pouch = BotaniaItems.flowerBag;
		} else {
			pouch = pouchItem;
			if (!playerInv.player.level().isClientSide) {
				flowerBagInv = pouchItem.getInventory(bag);
			} else {
				flowerBagInv = new SimpleContainer(pouchItem.getInventorySize(bag));
			}
		}

		List<TagKey<Item>> itemTypes = ColoredContentsPouchItem.getStoredItemTypes(bag);
		List<DyeColor> colors = ColorHelper.supportedColors().toList();

		for (int row = 0; row < 2 * itemTypes.size(); ++row) {
			for (int col = 0; col < 8; ++col) {
				int slot = col + row * 8;
				addSlot(new Slot(flowerBagInv, slot, 17 + col * 18, 26 + row * 18) {
					@Override
					public boolean mayPlace(ItemStack stack) {
						return pouch.isValidItemForSlot(slot, stack, colors, itemTypes);
					}
				});
			}
		}

		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 120 + row * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			if (playerInv.getItem(i) == bag) {
				addSlot(new SlotLocked(playerInv, i, 8 + i * 18, 178));
			} else {
				addSlot(new Slot(playerInv, i, 8 + i * 18, 178));
			}
		}

	}

	public ItemStack getPouch() {
		return bag;
	}

	@Override
	public boolean stillValid(Player player) {
		ItemStack main = player.getMainHandItem();
		ItemStack off = player.getOffhandItem();
		return bag.getItem() instanceof ColoredContentsPouchItem && (main == bag || off == bag);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		Slot slot = slots.get(slotIndex);
		if (!slot.hasItem()) {
			return ItemStack.EMPTY;
		}

		ItemStack slotStack = slot.getItem();
		ItemStack copyStack = slotStack.copy();

		int numPouchSlots = flowerBagInv.getContainerSize();
		if (slotIndex < numPouchSlots) {
			if (!moveItemStackTo(slotStack, numPouchSlots, slots.size(), true)) {
				return ItemStack.EMPTY;
			}
		} else if (bag.getItem() instanceof ColoredContentsPouchItem pouch) {
			IntList slotCandidates = pouch.findCandidateSlots(bag, copyStack);
			if (slotCandidates.intStream().anyMatch(slotId -> !moveItemStackTo(slotStack, slotId, slotId + 1, true))) {
				return ItemStack.EMPTY;
			}
		}

		if (slotStack.isEmpty()) {
			slot.setByPlayer(ItemStack.EMPTY);
		} else {
			slot.setChanged();
		}

		return copyStack;
	}

}
