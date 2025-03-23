/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.common.block.flower.functional;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.block.state.BlockState;

import vazkii.botania.common.block.block_entity.BotaniaBlockEntities;

import java.util.function.Predicate;

public class DreadthornBlockEntity extends BellethornBlockEntity {
	public DreadthornBlockEntity(BlockPos pos, BlockState state) {
		super(BotaniaBlockEntities.DREADTHORN, pos, state);
	}

	@Override
	public int getColor() {
		return 0x260B45;
	}

	@Override
	public Predicate<Entity> getSelector() {
		return var1 -> var1 instanceof Animal animal && !animal.isBaby();
	}

	@Override
	public int getManaCost() {
		return 30;
	}

}
