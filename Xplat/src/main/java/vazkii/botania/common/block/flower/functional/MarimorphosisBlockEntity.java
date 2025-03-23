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
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import vazkii.botania.api.recipe.OrechidRecipe;
import vazkii.botania.common.block.block_entity.BotaniaBlockEntities;
import vazkii.botania.common.crafting.BotaniaRecipeTypes;

public class MarimorphosisBlockEntity extends OrechidBlockEntity {
	private static final int COST = 12;
	private static final int RANGE = 8;
	private static final int RANGE_Y = 5;

	private static final int RANGE_MINI = 2;
	private static final int RANGE_Y_MINI = 1;

	protected MarimorphosisBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	public MarimorphosisBlockEntity(BlockPos pos, BlockState state) {
		this(BotaniaBlockEntities.MARIMORPHOSIS, pos, state);
	}

	@Override
	protected void playSound(BlockPos coords) {
		// no loud sound for this one, sorry
	}

	@Override
	public RecipeType<? extends OrechidRecipe> getRecipeType() {
		return BotaniaRecipeTypes.MARIMORPHOSIS_TYPE;
	}

	@Override
	public int getDelay() {
		return 2;
	}

	@Override
	public int getCost() {
		return COST;
	}

	@Override
	public int getRange() {
		return RANGE;
	}

	@Override
	public int getRangeY() {
		return RANGE_Y;
	}

	@Override
	public int getColor() {
		return 0x769897;
	}

	@Override
	public int getMaxMana() {
		return 1000;
	}

	public static class Mini extends MarimorphosisBlockEntity {
		public Mini(BlockPos pos, BlockState state) {
			super(BotaniaBlockEntities.MARIMORPHOSIS_CHIBI, pos, state);
		}

		@Override
		public int getRange() {
			return RANGE_MINI;
		}

		@Override
		public int getRangeY() {
			return RANGE_Y_MINI;
		}
	}

}
