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
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import org.jetbrains.annotations.Nullable;

import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.block.block_entity.corporea.CorporeaCrystalCubeBlockEntity;
import vazkii.botania.common.helper.VecHelper;
import vazkii.botania.mixin.ItemEntityAccessor;

public class CorporeaCrystalCubeBlockEntityRenderer implements BlockEntityRenderer<CorporeaCrystalCubeBlockEntity> {
	// Ugly but there's no other way to get the model besides grabbing it from the event
	public static BakedModel cubeModel = null;
	private ItemEntity entity = null;
	private final BlockRenderDispatcher blockRenderDispatcher;

	public CorporeaCrystalCubeBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
		this.blockRenderDispatcher = ctx.getBlockRenderDispatcher();
	}

	@Override
	public void render(@Nullable CorporeaCrystalCubeBlockEntity cube, float f, PoseStack ms, MultiBufferSource buffers, int light, int overlay) {
		ItemStack stack = ItemStack.EMPTY;
		if (cube != null) {
			if (entity == null) {
				entity = new ItemEntity(cube.getLevel(), cube.getBlockPos().getX(), cube.getBlockPos().getY(), cube.getBlockPos().getZ(), new ItemStack(Blocks.STONE));
			}

			((ItemEntityAccessor) entity).setAge(ClientTickHandler.ticksInGame);
			stack = cube.getRequestTarget();
			entity.setItem(stack);
		}

		Minecraft mc = Minecraft.getInstance();
		ms.pushPose();
		ms.translate(0.5F, 1.5F, 0.5F);
		ms.scale(1F, -1F, -1F);
		/*
			Using Mth.sin(((float)entity.getAge() + f) / 10.0F + entity.bobOffs from ItemEntityRender#render to sync the bobbing.
			Divided by a negative number to make the item inside not be static (-1 almost make the cube and item the same speed).
			Making the divider smaller, slows down the cube bobbing (you can multiply instead to make it faster).
			This still keeps the item and the cube in sync, the cube just doesn't move as much as the item,
			but the item never goes outside the cube (based on the tests I made; some edge cases could exist).
		*/
		ms.translate(0F, (Mth.sin(((float) entity.getAge() + f) / 10.0F + entity.bobOffs) * 0.1F + 0.1F) / -7F, 0F);

		if (!stack.isEmpty()) {
			ms.pushPose();
			ms.translate(0F, 0.96F, 0F);
			ms.scale(0.64F, 0.64F, 0.64F);
			ms.mulPose(VecHelper.rotateZ(180F));
			Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity).render(entity, 0, f, ms, buffers, light);
			ms.popPose();
		}

		if (cubeModel != null) {
			ms.pushPose();
			ms.translate(-0.5F, 0.25F, -0.5F);
			VertexConsumer buffer = buffers.getBuffer(Sheets.translucentCullBlockSheet());
			blockRenderDispatcher.getModelRenderer().renderModel(ms.last(), buffer, null, cubeModel, 1, 1, 1, light, overlay);
			ms.popPose();
		}

		if (!stack.isEmpty() && cube != null && !cube.hideCount) {
			int count = cube.getItemCount();
			String countStr = String.valueOf(count);
			int color = 0xFFFFFF;
			if (count > 9_999) {
				countStr = count / 1_000 + "K";
				color = 0xFFFF00;
				if (count > 9_999_999) {
					countStr = count / 1_000_000 + "M";
					color = 0x00FF00;
				}
			}
			color |= 0xA0 << 24;
			int colorShade = (color & 16579836) >> 2 | color & -16777216;

			float s = 1F / 64F;
			ms.scale(s, s, s);
			int l = mc.font.width(countStr);

			ms.translate(0F, 55F, 0F);
			float tr = -16.5F;
			for (int i = 0; i < 4; i++) {
				ms.mulPose(VecHelper.rotateY(90F));
				ms.translate(0F, 0F, tr);
				mc.font.drawInBatch(countStr, -l / 2, 0, color, false, ms.last().pose(), buffers, Font.DisplayMode.NORMAL, 0, light);
				ms.translate(0F, 0F, 0.1F);
				mc.font.drawInBatch(countStr, -l / 2 + 1, 1, colorShade, false, ms.last().pose(), buffers, Font.DisplayMode.NORMAL, 0, light);
				ms.translate(0F, 0F, -tr - 0.1F);
			}
		}

		ms.popPose();
	}
}
