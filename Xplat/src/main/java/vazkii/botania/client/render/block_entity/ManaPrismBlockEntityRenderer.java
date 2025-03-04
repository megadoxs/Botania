/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.client.render.block_entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import vazkii.botania.api.mana.BasicLensItem;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.block.block_entity.mana.ManaPrismBlockEntity;
import vazkii.botania.common.helper.VecHelper;

public class ManaPrismBlockEntityRenderer implements BlockEntityRenderer<ManaPrismBlockEntity> {

	public ManaPrismBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {}

	@Override
	public void render(ManaPrismBlockEntity prism, float partTicks, PoseStack ms, MultiBufferSource buffers, int light, int overlay) {
		float pos = (float) Math.sin((ClientTickHandler.ticksInGame + partTicks) * 0.05F) * 0.5F * (1F - 1F / 16F) * 0.997F - 0.5F;

		ItemStack stack = prism.getItemHandler().getItem(0);

		if (!stack.isEmpty()) {
			if (stack.getItem() instanceof BasicLensItem) {
				ms.pushPose();
				ms.mulPose(VecHelper.rotateX(90));
				ms.translate(0.5F, 0.5F, pos);
				ms.scale(1.003F, 1.003F, 1F);
				Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.NONE,
						light, overlay, ms, buffers, prism.getLevel(), 0);
				ms.popPose();
			}
		}
	}

}
