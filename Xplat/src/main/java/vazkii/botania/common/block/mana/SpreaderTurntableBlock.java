/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.common.block.mana;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.Nullable;

import vazkii.botania.common.block.BotaniaBlock;
import vazkii.botania.common.block.OpenCrateBlock;
import vazkii.botania.common.block.block_entity.BotaniaBlockEntities;
import vazkii.botania.common.block.block_entity.mana.SpreaderTurntableBlockEntity;

public class SpreaderTurntableBlock extends BotaniaBlock implements EntityBlock {

	public SpreaderTurntableBlock(Properties builder) {
		super(builder);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SpreaderTurntableBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, BotaniaBlockEntities.TURNTABLE, SpreaderTurntableBlockEntity::commonTick);
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {
		if (world.hasNeighborSignal(pos) && rand.nextDouble() < 0.2) {
			OpenCrateBlock.redstoneParticlesOnFullBlock(world, pos, rand);
		}
	}
}
