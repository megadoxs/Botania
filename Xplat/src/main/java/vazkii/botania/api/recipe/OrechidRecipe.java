/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.api.recipe;

import net.minecraft.commands.CacheableFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import vazkii.botania.api.BotaniaAPI;
import static vazkii.botania.api.BotaniaAPI.botaniaRL;

import java.util.Optional;

public interface OrechidRecipe extends Recipe<RecipeInput> {
	ResourceLocation TYPE_ID = botaniaRL("orechid");
	ResourceLocation IGNEM_TYPE_ID = botaniaRL("orechid_ignem");
	ResourceLocation MARIMORPHOSIS_TYPE_ID = botaniaRL("marimorphosis");

	/** Valid inputs for the recipe */
	StateIngredient getInput();

	/** Output to display in recipes and to be used by default. */
	StateIngredient getOutput();

	@NotNull
	@Override
	RecipeType<? extends OrechidRecipe> getType();

	/** Location-sensitive output, called with the position of the block to convert. */
	default StateIngredient getOutput(@NotNull Level level, @NotNull BlockPos pos) {
		return getOutput();
	}

	/**
	 * Default weight, used if no special weight logic is provided, and to display
	 * in recipes (the JEI/REI displayed output per 64 input depends on the sum of default weights).
	 */
	int getWeight();

	/** Location-sensitive weight, called with the position of the block to convert. */
	default int getWeight(@NotNull Level level, @NotNull BlockPos pos) {
		return getWeight();
	}

	Optional<CacheableFunction> getSuccessFunction();

	/**
	 * @deprecated Not meant to be used for item crafting in a container.
	 */
	@Override
	@Deprecated
	default boolean matches(RecipeInput container, Level level) {
		return false;
	}

	/**
	 * @deprecated Not meant to be used for item crafting in a container.
	 */
	@Override
	@Deprecated
	default ItemStack assemble(RecipeInput container, @NotNull HolderLookup.Provider registryAccess) {
		return ItemStack.EMPTY;
	}

	/**
	 * @deprecated Not meant to be used for item crafting in a container.
	 */
	@Override
	@Deprecated
	default boolean canCraftInDimensions(int width, int height) {
		return false;
	}

	/**
	 * @deprecated Not meant to be used for item crafting in a container.
	 */
	@Override
	@Deprecated
	default ItemStack getResultItem(@NotNull HolderLookup.Provider registryAccess) {
		return ItemStack.EMPTY;
	}

	@Override
	default boolean isSpecial() {
		return true;
	}
}
