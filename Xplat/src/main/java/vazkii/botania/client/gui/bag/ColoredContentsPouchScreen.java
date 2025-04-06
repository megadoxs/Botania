/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.client.gui.bag;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import vazkii.botania.api.internal.Colored;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.lib.ResourcesLib;
import vazkii.botania.common.helper.ColorHelper;
import vazkii.botania.common.item.ColoredContentsPouchItem;

import java.util.List;

public class ColoredContentsPouchScreen extends AbstractContainerScreen<ColoredContentsPouchContainer> {

	private static final ResourceLocation texture = ResourceLocation.parse(ResourcesLib.GUI_FLOWER_BAG);

	public ColoredContentsPouchScreen(ColoredContentsPouchContainer container, Inventory playerInv, Component title) {
		super(container, playerInv, title);
		imageHeight += 36;

		// recompute, same as super
		inventoryLabelY = imageHeight - 94;
	}

	@Override
	public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(gui, mouseX, mouseY, partialTicks);
		super.render(gui, mouseX, mouseY, partialTicks);
		this.renderTooltip(gui, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics gui, float partialTicks, int mouseX, int mouseY) {
		PoseStack ms = gui.pose();
		Minecraft mc = Minecraft.getInstance();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		int k = (width - imageWidth) / 2;
		int l = (height - imageHeight) / 2;
		gui.blit(texture, k, l, 0, 0, imageWidth, imageHeight);

		List<TagKey<Item>> itemTypes = ColoredContentsPouchItem.getStoredItemTypes(menu.getPouch());
		List<DyeColor> colors = ColorHelper.supportedColors().toList();
		for (Slot slot : menu.slots) {
			if (slot.container == menu.flowerBagInv) {
				int x = this.leftPos + slot.x;
				int y = this.topPos + slot.y;
				if (!slot.hasItem()) {
					int typeIndex = slot.index / colors.size();
					int colorIndex = slot.index % colors.size();
					var holder = BuiltInRegistries.ITEM.getTag(itemTypes.get(typeIndex));
					holder.flatMap(holders -> holders.stream().map(Holder::value)
							.filter(item -> item instanceof Colored colored &&
									colored.getColor() == colors.get(colorIndex))
							.findFirst())
							.ifPresent(item -> {
								ItemStack missingFlower = new ItemStack(item);
								RenderHelper.renderGuiItemAlpha(missingFlower, x, y, 0x5F, mc.getItemRenderer());
							});
				} else if (slot.getItem().getCount() == 1) {
					// Always draw the count even at 1
					ms.pushPose();
					// Same as how much vanilla offsets when drawing items in guis
					ms.translate(0, 0, 200);
					gui.drawString(mc.font, "1", x + 11, y + 9, 0xFFFFFF);
					ms.popPose();
				}
			}
		}
	}

}
