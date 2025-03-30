/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.common.block;

import net.minecraft.core.*;
import net.minecraft.core.dispenser.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import org.apache.commons.lang3.tuple.Triple;

import vazkii.botania.api.block.PetalApothecary;
import vazkii.botania.api.state.BotaniaStateProperties;
import vazkii.botania.api.state.enums.AlfheimPortalState;
import vazkii.botania.api.state.enums.LuminizerVariant;
import vazkii.botania.common.block.block_entity.BotaniaBlockEntities;
import vazkii.botania.common.block.corporea.*;
import vazkii.botania.common.block.decor.*;
import vazkii.botania.common.block.dispenser.*;
import vazkii.botania.common.block.mana.*;
import vazkii.botania.common.block.red_string.*;
import vazkii.botania.common.brew.BotaniaMobEffects;
import vazkii.botania.common.helper.ColorHelper;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.block.BlockItemWithSpecialRenderer;
import vazkii.botania.common.item.block.SpecialFlowerBlockItem;
import vazkii.botania.common.item.block.TinyPotatoBlockItem;
import vazkii.botania.common.lib.LibBlockNames;
import vazkii.botania.mixin.DispenserBlockAccessor;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static vazkii.botania.api.BotaniaAPI.botaniaRL;
import static vazkii.botania.common.lib.LibBlockNames.*;
import static vazkii.botania.common.lib.LibBlockNames.SLAB_SUFFIX;

public final class BotaniaBlocks {
	private static final BlockBehaviour.StateArgumentPredicate<EntityType<?>> NO_SPAWN = (state, world, pos, et) -> false;
	private static final BlockBehaviour.StatePredicate NO_SUFFOCATION = (state, world, pos) -> false;

	public static final Block whiteFlower = new BotaniaFlowerBlock(DyeColor.WHITE, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).strength(0).sound(SoundType.GRASS));
	public static final Block orangeFlower = new BotaniaFlowerBlock(DyeColor.ORANGE, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block magentaFlower = new BotaniaFlowerBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block lightBlueFlower = new BotaniaFlowerBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block yellowFlower = new BotaniaFlowerBlock(DyeColor.YELLOW, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block limeFlower = new BotaniaFlowerBlock(DyeColor.LIME, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block pinkFlower = new BotaniaFlowerBlock(DyeColor.PINK, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block grayFlower = new BotaniaFlowerBlock(DyeColor.GRAY, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block lightGrayFlower = new BotaniaFlowerBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block cyanFlower = new BotaniaFlowerBlock(DyeColor.CYAN, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block purpleFlower = new BotaniaFlowerBlock(DyeColor.PURPLE, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block blueFlower = new BotaniaFlowerBlock(DyeColor.BLUE, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block brownFlower = new BotaniaFlowerBlock(DyeColor.BROWN, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block greenFlower = new BotaniaFlowerBlock(DyeColor.GREEN, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block redFlower = new BotaniaFlowerBlock(DyeColor.RED, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block blackFlower = new BotaniaFlowerBlock(DyeColor.BLACK, BlockBehaviour.Properties.ofFullCopy(whiteFlower));

	public static final Block whiteShinyFlower = new GlimmeringFlowerBlock(DyeColor.WHITE, BlockBehaviour.Properties.ofFullCopy(whiteFlower).lightLevel(s -> 15));
	public static final Block orangeShinyFlower = new GlimmeringFlowerBlock(DyeColor.ORANGE, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block magentaShinyFlower = new GlimmeringFlowerBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block lightBlueShinyFlower = new GlimmeringFlowerBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block yellowShinyFlower = new GlimmeringFlowerBlock(DyeColor.YELLOW, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block limeShinyFlower = new GlimmeringFlowerBlock(DyeColor.LIME, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block pinkShinyFlower = new GlimmeringFlowerBlock(DyeColor.PINK, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block grayShinyFlower = new GlimmeringFlowerBlock(DyeColor.GRAY, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block lightGrayShinyFlower = new GlimmeringFlowerBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block cyanShinyFlower = new GlimmeringFlowerBlock(DyeColor.CYAN, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block purpleShinyFlower = new GlimmeringFlowerBlock(DyeColor.PURPLE, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block blueShinyFlower = new GlimmeringFlowerBlock(DyeColor.BLUE, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block brownShinyFlower = new GlimmeringFlowerBlock(DyeColor.BROWN, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block greenShinyFlower = new GlimmeringFlowerBlock(DyeColor.GREEN, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block redShinyFlower = new GlimmeringFlowerBlock(DyeColor.RED, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));
	public static final Block blackShinyFlower = new GlimmeringFlowerBlock(DyeColor.BLACK, BlockBehaviour.Properties.ofFullCopy(whiteShinyFlower));

	public static final Block whiteBuriedPetals = new BuriedPetalBlock(DyeColor.WHITE, BlockBehaviour.Properties.ofFullCopy(whiteFlower).sound(SoundType.MOSS).lightLevel(s -> 4));
	public static final Block orangeBuriedPetals = new BuriedPetalBlock(DyeColor.ORANGE, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block magentaBuriedPetals = new BuriedPetalBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block lightBlueBuriedPetals = new BuriedPetalBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block yellowBuriedPetals = new BuriedPetalBlock(DyeColor.YELLOW, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block limeBuriedPetals = new BuriedPetalBlock(DyeColor.LIME, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block pinkBuriedPetals = new BuriedPetalBlock(DyeColor.PINK, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block grayBuriedPetals = new BuriedPetalBlock(DyeColor.GRAY, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block lightGrayBuriedPetals = new BuriedPetalBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block cyanBuriedPetals = new BuriedPetalBlock(DyeColor.CYAN, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block purpleBuriedPetals = new BuriedPetalBlock(DyeColor.PURPLE, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block blueBuriedPetals = new BuriedPetalBlock(DyeColor.BLUE, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block brownBuriedPetals = new BuriedPetalBlock(DyeColor.BROWN, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block greenBuriedPetals = new BuriedPetalBlock(DyeColor.GREEN, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block redBuriedPetals = new BuriedPetalBlock(DyeColor.RED, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));
	public static final Block blackBuriedPetals = new BuriedPetalBlock(DyeColor.BLACK, BlockBehaviour.Properties.ofFullCopy(whiteBuriedPetals));

	public static final BlockBehaviour.Properties FLOATING_PROPS = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.5F).sound(SoundType.GRAVEL).lightLevel(s -> 15);
	public static final Block whiteFloatingFlower = new FloatingFlowerBlock(DyeColor.WHITE, FLOATING_PROPS);
	public static final Block orangeFloatingFlower = new FloatingFlowerBlock(DyeColor.ORANGE, FLOATING_PROPS);
	public static final Block magentaFloatingFlower = new FloatingFlowerBlock(DyeColor.MAGENTA, FLOATING_PROPS);
	public static final Block lightBlueFloatingFlower = new FloatingFlowerBlock(DyeColor.LIGHT_BLUE, FLOATING_PROPS);
	public static final Block yellowFloatingFlower = new FloatingFlowerBlock(DyeColor.YELLOW, FLOATING_PROPS);
	public static final Block limeFloatingFlower = new FloatingFlowerBlock(DyeColor.LIME, FLOATING_PROPS);
	public static final Block pinkFloatingFlower = new FloatingFlowerBlock(DyeColor.PINK, FLOATING_PROPS);
	public static final Block grayFloatingFlower = new FloatingFlowerBlock(DyeColor.GRAY, FLOATING_PROPS);
	public static final Block lightGrayFloatingFlower = new FloatingFlowerBlock(DyeColor.LIGHT_GRAY, FLOATING_PROPS);
	public static final Block cyanFloatingFlower = new FloatingFlowerBlock(DyeColor.CYAN, FLOATING_PROPS);
	public static final Block purpleFloatingFlower = new FloatingFlowerBlock(DyeColor.PURPLE, FLOATING_PROPS);
	public static final Block blueFloatingFlower = new FloatingFlowerBlock(DyeColor.BLUE, FLOATING_PROPS);
	public static final Block brownFloatingFlower = new FloatingFlowerBlock(DyeColor.BROWN, FLOATING_PROPS);
	public static final Block greenFloatingFlower = new FloatingFlowerBlock(DyeColor.GREEN, FLOATING_PROPS);
	public static final Block redFloatingFlower = new FloatingFlowerBlock(DyeColor.RED, FLOATING_PROPS);
	public static final Block blackFloatingFlower = new FloatingFlowerBlock(DyeColor.BLACK, FLOATING_PROPS);

	public static final Block petalBlockWhite = new PetalBlock(DyeColor.WHITE, BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).strength(0.4F).sound(SoundType.MOSS));
	public static final Block petalBlockOrange = new PetalBlock(DyeColor.ORANGE, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.ORANGE));
	public static final Block petalBlockMagenta = new PetalBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.MAGENTA));
	public static final Block petalBlockLightBlue = new PetalBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.LIGHT_BLUE));
	public static final Block petalBlockYellow = new PetalBlock(DyeColor.YELLOW, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.YELLOW));
	public static final Block petalBlockLime = new PetalBlock(DyeColor.LIME, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.LIME));
	public static final Block petalBlockPink = new PetalBlock(DyeColor.PINK, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.PINK));
	public static final Block petalBlockGray = new PetalBlock(DyeColor.GRAY, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.GRAY));
	public static final Block petalBlockSilver = new PetalBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.LIGHT_GRAY));
	public static final Block petalBlockCyan = new PetalBlock(DyeColor.CYAN, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.CYAN));
	public static final Block petalBlockPurple = new PetalBlock(DyeColor.PURPLE, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.PURPLE));
	public static final Block petalBlockBlue = new PetalBlock(DyeColor.BLUE, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.BLUE));
	public static final Block petalBlockBrown = new PetalBlock(DyeColor.BROWN, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.BROWN));
	public static final Block petalBlockGreen = new PetalBlock(DyeColor.GREEN, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.GREEN));
	public static final Block petalBlockRed = new PetalBlock(DyeColor.RED, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.RED));
	public static final Block petalBlockBlack = new PetalBlock(DyeColor.BLACK, BlockBehaviour.Properties.ofFullCopy(petalBlockWhite).mapColor(DyeColor.BLACK));

	public static final Block whiteMushroom = new BotaniaMushroomBlock(DyeColor.WHITE, BlockBehaviour.Properties.ofFullCopy(whiteFlower).lightLevel(s -> 3).offsetType(BlockBehaviour.OffsetType.NONE));
	public static final Block orangeMushroom = new BotaniaMushroomBlock(DyeColor.ORANGE, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block magentaMushroom = new BotaniaMushroomBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block lightBlueMushroom = new BotaniaMushroomBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block yellowMushroom = new BotaniaMushroomBlock(DyeColor.YELLOW, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block limeMushroom = new BotaniaMushroomBlock(DyeColor.LIME, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block pinkMushroom = new BotaniaMushroomBlock(DyeColor.PINK, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block grayMushroom = new BotaniaMushroomBlock(DyeColor.GRAY, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block lightGrayMushroom = new BotaniaMushroomBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block cyanMushroom = new BotaniaMushroomBlock(DyeColor.CYAN, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block purpleMushroom = new BotaniaMushroomBlock(DyeColor.PURPLE, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block blueMushroom = new BotaniaMushroomBlock(DyeColor.BLUE, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block brownMushroom = new BotaniaMushroomBlock(DyeColor.BROWN, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block greenMushroom = new BotaniaMushroomBlock(DyeColor.GREEN, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block redMushroom = new BotaniaMushroomBlock(DyeColor.RED, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));
	public static final Block blackMushroom = new BotaniaMushroomBlock(DyeColor.BLACK, BlockBehaviour.Properties.ofFullCopy(whiteMushroom));

	public static final Block doubleFlowerWhite = new BotaniaDoubleFlowerBlock(DyeColor.WHITE, BlockBehaviour.Properties.ofFullCopy(whiteFlower));
	public static final Block doubleFlowerOrange = new BotaniaDoubleFlowerBlock(DyeColor.ORANGE, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerMagenta = new BotaniaDoubleFlowerBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerLightBlue = new BotaniaDoubleFlowerBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerYellow = new BotaniaDoubleFlowerBlock(DyeColor.YELLOW, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerLime = new BotaniaDoubleFlowerBlock(DyeColor.LIME, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerPink = new BotaniaDoubleFlowerBlock(DyeColor.PINK, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerGray = new BotaniaDoubleFlowerBlock(DyeColor.GRAY, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerLightGray = new BotaniaDoubleFlowerBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerCyan = new BotaniaDoubleFlowerBlock(DyeColor.CYAN, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerPurple = new BotaniaDoubleFlowerBlock(DyeColor.PURPLE, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerBlue = new BotaniaDoubleFlowerBlock(DyeColor.BLUE, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerBrown = new BotaniaDoubleFlowerBlock(DyeColor.BROWN, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerGreen = new BotaniaDoubleFlowerBlock(DyeColor.GREEN, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerRed = new BotaniaDoubleFlowerBlock(DyeColor.RED, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));
	public static final Block doubleFlowerBlack = new BotaniaDoubleFlowerBlock(DyeColor.BLACK, BlockBehaviour.Properties.ofFullCopy(doubleFlowerWhite));

	public static final Block pottedWhiteFlower = flowerPot(whiteFlower, 0);
	public static final Block pottedOrangeFlower = flowerPot(orangeFlower, 0);
	public static final Block pottedMagentaFlower = flowerPot(magentaFlower, 0);
	public static final Block pottedLightBlueFlower = flowerPot(lightBlueFlower, 0);
	public static final Block pottedYellowFlower = flowerPot(yellowFlower, 0);
	public static final Block pottedLimeFlower = flowerPot(limeFlower, 0);
	public static final Block pottedPinkFlower = flowerPot(pinkFlower, 0);
	public static final Block pottedGrayFlower = flowerPot(grayFlower, 0);
	public static final Block pottedLightGrayFlower = flowerPot(lightGrayFlower, 0);
	public static final Block pottedCyanFlower = flowerPot(cyanFlower, 0);
	public static final Block pottedPurpleFlower = flowerPot(purpleFlower, 0);
	public static final Block pottedBlueFlower = flowerPot(blueFlower, 0);
	public static final Block pottedBrownFlower = flowerPot(brownFlower, 0);
	public static final Block pottedGreenFlower = flowerPot(greenFlower, 0);
	public static final Block pottedRedFlower = flowerPot(redFlower, 0);
	public static final Block pottedBlackFlower = flowerPot(blackFlower, 0);

	public static final Block pottedWhiteShinyFlower = flowerPot(whiteShinyFlower, 15);
	public static final Block pottedOrangeShinyFlower = flowerPot(orangeShinyFlower, 15);
	public static final Block pottedMagentaShinyFlower = flowerPot(magentaShinyFlower, 15);
	public static final Block pottedLightBlueShinyFlower = flowerPot(lightBlueShinyFlower, 15);
	public static final Block pottedYellowShinyFlower = flowerPot(yellowShinyFlower, 15);
	public static final Block pottedLimeShinyFlower = flowerPot(limeShinyFlower, 15);
	public static final Block pottedPinkShinyFlower = flowerPot(pinkShinyFlower, 15);
	public static final Block pottedGrayShinyFlower = flowerPot(grayShinyFlower, 15);
	public static final Block pottedLightGrayShinyFlower = flowerPot(lightGrayShinyFlower, 15);
	public static final Block pottedCyanShinyFlower = flowerPot(cyanShinyFlower, 15);
	public static final Block pottedPurpleShinyFlower = flowerPot(purpleShinyFlower, 15);
	public static final Block pottedBlueShinyFlower = flowerPot(blueShinyFlower, 15);
	public static final Block pottedBrownShinyFlower = flowerPot(brownShinyFlower, 15);
	public static final Block pottedGreenShinyFlower = flowerPot(greenShinyFlower, 15);
	public static final Block pottedRedShinyFlower = flowerPot(redShinyFlower, 15);
	public static final Block pottedBlackShinyFlower = flowerPot(blackShinyFlower, 15);

	public static final Block pottedWhiteMushroom = flowerPot(whiteMushroom, 3);
	public static final Block pottedOrangeMushroom = flowerPot(orangeMushroom, 3);
	public static final Block pottedMagentaMushroom = flowerPot(magentaMushroom, 3);
	public static final Block pottedLightBlueMushroom = flowerPot(lightBlueMushroom, 3);
	public static final Block pottedYellowMushroom = flowerPot(yellowMushroom, 3);
	public static final Block pottedLimeMushroom = flowerPot(limeMushroom, 3);
	public static final Block pottedPinkMushroom = flowerPot(pinkMushroom, 3);
	public static final Block pottedGrayMushroom = flowerPot(grayMushroom, 3);
	public static final Block pottedLightGrayMushroom = flowerPot(lightGrayMushroom, 3);
	public static final Block pottedCyanMushroom = flowerPot(cyanMushroom, 3);
	public static final Block pottedPurpleMushroom = flowerPot(purpleMushroom, 3);
	public static final Block pottedBlueMushroom = flowerPot(blueMushroom, 3);
	public static final Block pottedBrownMushroom = flowerPot(brownMushroom, 3);
	public static final Block pottedGreenMushroom = flowerPot(greenMushroom, 3);
	public static final Block pottedRedMushroom = flowerPot(redMushroom, 3);
	public static final Block pottedBlackMushroom = flowerPot(blackMushroom, 3);

	private static final BlockBehaviour.Properties FLOWER_PROPS = BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY);
	public static final Block pureDaisy = new SpecialFlowerBlock(BotaniaMobEffects.CLEAR, 1, FLOWER_PROPS, () -> BotaniaBlockEntities.PURE_DAISY);
	public static final Block pureDaisyFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.PURE_DAISY);
	public static final Block pureDaisyPotted = BotaniaBlocks.flowerPot(pureDaisy, 0);

	public static final Block manastar = new SpecialFlowerBlock(MobEffects.GLOWING, 10, FLOWER_PROPS, () -> BotaniaBlockEntities.MANASTAR);
	public static final Block manastarFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.MANASTAR);
	public static final Block manastarPotted = BotaniaBlocks.flowerPot(manastar, 0);

	public static final Block hydroangeas = new SpecialFlowerBlock(MobEffects.UNLUCK, 10, FLOWER_PROPS, () -> BotaniaBlockEntities.HYDROANGEAS);
	public static final Block hydroangeasFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.HYDROANGEAS);
	public static final Block hydroangeasPotted = BotaniaBlocks.flowerPot(hydroangeas, 0);

	public static final Block endoflame = new SpecialFlowerBlock(MobEffects.MOVEMENT_SLOWDOWN, 10, FLOWER_PROPS, () -> BotaniaBlockEntities.ENDOFLAME);
	public static final Block endoflameFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.ENDOFLAME);
	public static final Block endoflamePotted = BotaniaBlocks.flowerPot(endoflame, 0);

	public static final Block thermalily = new SpecialFlowerBlock(MobEffects.FIRE_RESISTANCE, 120, FLOWER_PROPS, () -> BotaniaBlockEntities.THERMALILY, true);
	public static final Block thermalilyFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.THERMALILY, true);
	public static final Block thermalilyPotted = BotaniaBlocks.flowerPot(thermalily, 0);

	public static final Block rosaArcana = new SpecialFlowerBlock(MobEffects.LUCK, 64, FLOWER_PROPS, () -> BotaniaBlockEntities.ROSA_ARCANA);
	public static final Block rosaArcanaFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.ROSA_ARCANA);
	public static final Block rosaArcanaPotted = BotaniaBlocks.flowerPot(rosaArcana, 0);

	public static final Block munchdew = new SpecialFlowerBlock(MobEffects.SLOW_FALLING, 300, FLOWER_PROPS, () -> BotaniaBlockEntities.MUNCHDEW);
	public static final Block munchdewFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.MUNCHDEW);
	public static final Block munchdewPotted = BotaniaBlocks.flowerPot(munchdew, 0);

	public static final Block entropinnyum = new SpecialFlowerBlock(MobEffects.DAMAGE_RESISTANCE, 72, FLOWER_PROPS, () -> BotaniaBlockEntities.ENTROPINNYUM);
	public static final Block entropinnyumFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.ENTROPINNYUM);
	public static final Block entropinnyumPotted = BotaniaBlocks.flowerPot(entropinnyum, 0);

	public static final Block kekimurus = new SpecialFlowerBlock(MobEffects.SATURATION, 15, FLOWER_PROPS, () -> BotaniaBlockEntities.KEKIMURUS);
	public static final Block kekimurusFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.KEKIMURUS);
	public static final Block kekimurusPotted = BotaniaBlocks.flowerPot(kekimurus, 0);

	public static final Block gourmaryllis = new SpecialFlowerBlock(MobEffects.HUNGER, 180, FLOWER_PROPS, () -> BotaniaBlockEntities.GOURMARYLLIS);
	public static final Block gourmaryllisFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.GOURMARYLLIS);
	public static final Block gourmaryllisPotted = BotaniaBlocks.flowerPot(gourmaryllis, 0);

	public static final Block narslimmus = new SpecialFlowerBlock(BotaniaMobEffects.FEATHER_FEET, 240, FLOWER_PROPS, () -> BotaniaBlockEntities.NARSLIMMUS);
	public static final Block narslimmusFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.NARSLIMMUS);
	public static final Block narslimmusPotted = BotaniaBlocks.flowerPot(narslimmus, 0);

	public static final Block spectrolus = new SpecialFlowerBlock(MobEffects.BLINDNESS, 240, FLOWER_PROPS, () -> BotaniaBlockEntities.SPECTROLUS);
	public static final Block spectrolusFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.SPECTROLUS);
	public static final Block spectrolusPotted = BotaniaBlocks.flowerPot(spectrolus, 0);

	public static final Block dandelifeon = new SpecialFlowerBlock(MobEffects.CONFUSION, 240, FLOWER_PROPS, () -> BotaniaBlockEntities.DANDELIFEON);
	public static final Block dandelifeonFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.DANDELIFEON);
	public static final Block dandelifeonPotted = BotaniaBlocks.flowerPot(dandelifeon, 0);

	public static final Block rafflowsia = new SpecialFlowerBlock(MobEffects.HEALTH_BOOST, 18, FLOWER_PROPS, () -> BotaniaBlockEntities.RAFFLOWSIA);
	public static final Block rafflowsiaFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.RAFFLOWSIA);
	public static final Block rafflowsiaPotted = BotaniaBlocks.flowerPot(rafflowsia, 0);

	public static final Block shulkMeNot = new SpecialFlowerBlock(MobEffects.LEVITATION, 72, FLOWER_PROPS, () -> BotaniaBlockEntities.SHULK_ME_NOT);
	public static final Block shulkMeNotFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.SHULK_ME_NOT);
	public static final Block shulkMeNotPotted = BotaniaBlocks.flowerPot(shulkMeNot, 0);

	public static final Block bellethorn = new SpecialFlowerBlock(MobEffects.WITHER, 10, FLOWER_PROPS, () -> BotaniaBlockEntities.BELLETHORNE);
	public static final Block bellethornChibi = new SpecialFlowerBlock(MobEffects.WITHER, 10, FLOWER_PROPS, () -> BotaniaBlockEntities.BELLETHORNE_CHIBI);
	public static final Block bellethornFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.BELLETHORNE);
	public static final Block bellethornChibiFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.BELLETHORNE_CHIBI);
	public static final Block bellethornPotted = BotaniaBlocks.flowerPot(bellethorn, 0);
	public static final Block bellethornChibiPotted = BotaniaBlocks.flowerPot(bellethornChibi, 0);

	public static final Block bergamute = new SpecialFlowerBlock(MobEffects.BLINDNESS, 10, FLOWER_PROPS, () -> BotaniaBlockEntities.BERGAMUTE);
	public static final Block bergamuteFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.BERGAMUTE);
	public static final Block bergamutePotted = BotaniaBlocks.flowerPot(bergamute, 0);

	public static final Block dreadthorn = new SpecialFlowerBlock(MobEffects.WITHER, 10, FLOWER_PROPS, () -> BotaniaBlockEntities.DREADTHORN);
	public static final Block dreadthornFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.DREADTHORN);
	public static final Block dreadthornPotted = BotaniaBlocks.flowerPot(dreadthorn, 0);

	public static final Block heiseiDream = new SpecialFlowerBlock(BotaniaMobEffects.SOUL_CROSS, 300, FLOWER_PROPS, () -> BotaniaBlockEntities.HEISEI_DREAM);
	public static final Block heiseiDreamFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.HEISEI_DREAM);
	public static final Block heiseiDreamPotted = BotaniaBlocks.flowerPot(heiseiDream, 0);

	public static final Block tigerseye = new SpecialFlowerBlock(MobEffects.DAMAGE_BOOST, 90, FLOWER_PROPS, () -> BotaniaBlockEntities.TIGERSEYE);
	public static final Block tigerseyeFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.TIGERSEYE);
	public static final Block tigerseyePotted = BotaniaBlocks.flowerPot(tigerseye, 0);

	public static final Block jadedAmaranthus = new SpecialFlowerBlock(MobEffects.HEAL, 1, FLOWER_PROPS, () -> BotaniaBlockEntities.JADED_AMARANTHUS);
	public static final Block jadedAmaranthusFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.JADED_AMARANTHUS);
	public static final Block jadedAmaranthusPotted = BotaniaBlocks.flowerPot(jadedAmaranthus, 0);

	public static final Block orechid = new SpecialFlowerBlock(MobEffects.DIG_SPEED, 10, FLOWER_PROPS, () -> BotaniaBlockEntities.ORECHID);
	public static final Block orechidFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.ORECHID);
	public static final Block orechidPotted = BotaniaBlocks.flowerPot(orechid, 0);

	public static final Block fallenKanade = new SpecialFlowerBlock(MobEffects.REGENERATION, 90, FLOWER_PROPS, () -> BotaniaBlockEntities.FALLEN_KANADE);
	public static final Block fallenKanadeFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.FALLEN_KANADE);
	public static final Block fallenKanadePotted = BotaniaBlocks.flowerPot(fallenKanade, 0);

	public static final Block exoflame = new SpecialFlowerBlock(MobEffects.MOVEMENT_SPEED, 240, FLOWER_PROPS, () -> BotaniaBlockEntities.EXOFLAME);
	public static final Block exoflameFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.EXOFLAME);
	public static final Block exoflamePotted = BotaniaBlocks.flowerPot(exoflame, 0);

	public static final Block agricarnation = new SpecialFlowerBlock(MobEffects.ABSORPTION, 48, FLOWER_PROPS, () -> BotaniaBlockEntities.AGRICARNATION);
	public static final Block agricarnationChibi = new SpecialFlowerBlock(MobEffects.ABSORPTION, 48, FLOWER_PROPS, () -> BotaniaBlockEntities.AGRICARNATION_CHIBI);
	public static final Block agricarnationFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.AGRICARNATION);
	public static final Block agricarnationChibiFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.AGRICARNATION_CHIBI);
	public static final Block agricarnationPotted = BotaniaBlocks.flowerPot(agricarnation, 0);
	public static final Block agricarnationChibiPotted = BotaniaBlocks.flowerPot(agricarnationChibi, 0);

	public static final Block hopperhock = new SpecialFlowerBlock(MobEffects.MOVEMENT_SPEED, 30, FLOWER_PROPS, () -> BotaniaBlockEntities.HOPPERHOCK);
	public static final Block hopperhockChibi = new SpecialFlowerBlock(MobEffects.MOVEMENT_SPEED, 30, FLOWER_PROPS, () -> BotaniaBlockEntities.HOPPERHOCK_CHIBI);
	public static final Block hopperhockFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.HOPPERHOCK);
	public static final Block hopperhockChibiFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.HOPPERHOCK_CHIBI);
	public static final Block hopperhockPotted = BotaniaBlocks.flowerPot(hopperhock, 0);
	public static final Block hopperhockChibiPotted = BotaniaBlocks.flowerPot(hopperhockChibi, 0);

	public static final Block tangleberrie = new SpecialFlowerBlock(BotaniaMobEffects.BLOODTHRST, 120, FLOWER_PROPS, () -> BotaniaBlockEntities.TANGLEBERRIE);
	public static final Block tangleberrieChibi = new SpecialFlowerBlock(BotaniaMobEffects.BLOODTHRST, 120, FLOWER_PROPS, () -> BotaniaBlockEntities.TANGLEBERRIE_CHIBI);
	public static final Block tangleberrieFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.TANGLEBERRIE);
	public static final Block tangleberrieChibiFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.TANGLEBERRIE_CHIBI);
	public static final Block tangleberriePotted = BotaniaBlocks.flowerPot(tangleberrie, 0);
	public static final Block tangleberrieChibiPotted = BotaniaBlocks.flowerPot(tangleberrieChibi, 0);

	public static final Block jiyuulia = new SpecialFlowerBlock(BotaniaMobEffects.EMPTINESS, 120, FLOWER_PROPS, () -> BotaniaBlockEntities.JIYUULIA);
	public static final Block jiyuuliaChibi = new SpecialFlowerBlock(BotaniaMobEffects.EMPTINESS, 120, FLOWER_PROPS, () -> BotaniaBlockEntities.JIYUULIA_CHIBI);
	public static final Block jiyuuliaFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.JIYUULIA);
	public static final Block jiyuuliaChibiFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.JIYUULIA_CHIBI);
	public static final Block jiyuuliaPotted = BotaniaBlocks.flowerPot(jiyuulia, 0);
	public static final Block jiyuuliaChibiPotted = BotaniaBlocks.flowerPot(jiyuuliaChibi, 0);

	public static final Block rannuncarpus = new SpecialFlowerBlock(MobEffects.JUMP, 30, FLOWER_PROPS, () -> BotaniaBlockEntities.RANNUNCARPUS);
	public static final Block rannuncarpusChibi = new SpecialFlowerBlock(MobEffects.JUMP, 30, FLOWER_PROPS, () -> BotaniaBlockEntities.RANNUNCARPUS_CHIBI);
	public static final Block rannuncarpusFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.RANNUNCARPUS);
	public static final Block rannuncarpusChibiFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.RANNUNCARPUS_CHIBI);
	public static final Block rannuncarpusPotted = BotaniaBlocks.flowerPot(rannuncarpus, 0);
	public static final Block rannuncarpusChibiPotted = BotaniaBlocks.flowerPot(rannuncarpusChibi, 0);

	public static final Block hyacidus = new SpecialFlowerBlock(MobEffects.POISON, 48, FLOWER_PROPS, () -> BotaniaBlockEntities.HYACIDUS);
	public static final Block hyacidusFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.HYACIDUS);
	public static final Block hyacidusPotted = BotaniaBlocks.flowerPot(hyacidus, 0);

	public static final Block pollidisiac = new SpecialFlowerBlock(MobEffects.DIG_SPEED, 369, FLOWER_PROPS, () -> BotaniaBlockEntities.POLLIDISIAC);
	public static final Block pollidisiacFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.POLLIDISIAC);
	public static final Block pollidisiacPotted = BotaniaBlocks.flowerPot(pollidisiac, 0);

	public static final Block clayconia = new SpecialFlowerBlock(MobEffects.WEAKNESS, 30, FLOWER_PROPS, () -> BotaniaBlockEntities.CLAYCONIA);
	public static final Block clayconiaChibi = new SpecialFlowerBlock(MobEffects.WEAKNESS, 30, FLOWER_PROPS, () -> BotaniaBlockEntities.CLAYCONIA_CHIBI);
	public static final Block clayconiaFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.CLAYCONIA);
	public static final Block clayconiaChibiFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.CLAYCONIA_CHIBI);
	public static final Block clayconiaPotted = BotaniaBlocks.flowerPot(clayconia, 0);
	public static final Block clayconiaChibiPotted = BotaniaBlocks.flowerPot(clayconiaChibi, 0);

	public static final Block loonium = new SpecialFlowerBlock(BotaniaMobEffects.ALLURE, 900, FLOWER_PROPS, () -> BotaniaBlockEntities.LOONIUM);
	public static final Block looniumFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.LOONIUM);
	public static final Block looniumPotted = BotaniaBlocks.flowerPot(loonium, 0);

	public static final Block daffomill = new SpecialFlowerBlock(MobEffects.LEVITATION, 6, FLOWER_PROPS, () -> BotaniaBlockEntities.DAFFOMILL);
	public static final Block daffomillFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.DAFFOMILL);
	public static final Block daffomillPotted = BotaniaBlocks.flowerPot(daffomill, 0);

	public static final Block vinculotus = new SpecialFlowerBlock(MobEffects.NIGHT_VISION, 900, FLOWER_PROPS, () -> BotaniaBlockEntities.VINCULOTUS);
	public static final Block vinculotusFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.VINCULOTUS);
	public static final Block vinculotusPotted = BotaniaBlocks.flowerPot(vinculotus, 0);

	public static final Block spectranthemum = new SpecialFlowerBlock(MobEffects.INVISIBILITY, 360, FLOWER_PROPS, () -> BotaniaBlockEntities.SPECTRANTHEMUM);
	public static final Block spectranthemumFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.SPECTRANTHEMUM);
	public static final Block spectranthemumPotted = BotaniaBlocks.flowerPot(spectranthemum, 0);

	public static final Block medumone = new SpecialFlowerBlock(MobEffects.MOVEMENT_SLOWDOWN, 3600, FLOWER_PROPS, () -> BotaniaBlockEntities.MEDUMONE);
	public static final Block medumoneFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.MEDUMONE);
	public static final Block medumonePotted = BotaniaBlocks.flowerPot(medumone, 0);

	public static final Block marimorphosis = new SpecialFlowerBlock(MobEffects.DIG_SLOWDOWN, 60, FLOWER_PROPS, () -> BotaniaBlockEntities.MARIMORPHOSIS);
	public static final Block marimorphosisChibi = new SpecialFlowerBlock(MobEffects.DIG_SLOWDOWN, 60, FLOWER_PROPS, () -> BotaniaBlockEntities.MARIMORPHOSIS_CHIBI);
	public static final Block marimorphosisFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.MARIMORPHOSIS);
	public static final Block marimorphosisChibiFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.MARIMORPHOSIS_CHIBI);
	public static final Block marimorphosisPotted = BotaniaBlocks.flowerPot(marimorphosis, 0);
	public static final Block marimorphosisChibiPotted = BotaniaBlocks.flowerPot(marimorphosisChibi, 0);

	public static final Block bubbell = new SpecialFlowerBlock(MobEffects.WATER_BREATHING, 240, FLOWER_PROPS, () -> BotaniaBlockEntities.BUBBELL);
	public static final Block bubbellChibi = new SpecialFlowerBlock(MobEffects.WATER_BREATHING, 240, FLOWER_PROPS, () -> BotaniaBlockEntities.BUBBELL_CHIBI);
	public static final Block bubbellFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.BUBBELL);
	public static final Block bubbellChibiFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.BUBBELL_CHIBI);
	public static final Block bubbellPotted = BotaniaBlocks.flowerPot(bubbell, 0);
	public static final Block bubbellChibiPotted = BotaniaBlocks.flowerPot(bubbellChibi, 0);

	public static final Block solegnolia = new SpecialFlowerBlock(MobEffects.HARM, 1, FLOWER_PROPS, () -> BotaniaBlockEntities.SOLEGNOLIA);
	public static final Block solegnoliaChibi = new SpecialFlowerBlock(MobEffects.HARM, 1, FLOWER_PROPS, () -> BotaniaBlockEntities.SOLEGNOLIA_CHIBI);
	public static final Block solegnoliaFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.SOLEGNOLIA);
	public static final Block solegnoliaChibiFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.SOLEGNOLIA_CHIBI);
	public static final Block solegnoliaPotted = BotaniaBlocks.flowerPot(solegnolia, 0);
	public static final Block solegnoliaChibiPotted = BotaniaBlocks.flowerPot(solegnoliaChibi, 0);

	public static final Block orechidIgnem = new SpecialFlowerBlock(MobEffects.FIRE_RESISTANCE, 600, FLOWER_PROPS, () -> BotaniaBlockEntities.ORECHID_IGNEM);
	public static final Block orechidIgnemFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.ORECHID_IGNEM);
	public static final Block orechidIgnemPotted = BotaniaBlocks.flowerPot(orechidIgnem, 0);

	public static final Block labellia = new SpecialFlowerBlock(MobEffects.FIRE_RESISTANCE, 600, FLOWER_PROPS, () -> BotaniaBlockEntities.LABELLIA);
	public static final Block labelliaFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> BotaniaBlockEntities.LABELLIA);
	public static final Block labelliaPotted = BotaniaBlocks.flowerPot(labellia, 0);

	public static final Block defaultAltar = new PetalApothecaryBlock(BlockBehaviour.Properties.of()
			.strength(3.5F).sound(SoundType.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().mapColor(MapColor.STONE)
			.lightLevel(s -> s.getValue(PetalApothecaryBlock.FLUID) == PetalApothecary.State.LAVA ? 15 : 0));
	public static final Block deepslateAltar = new PetalApothecaryBlock(
			BlockBehaviour.Properties.ofFullCopy(defaultAltar).sound(SoundType.DEEPSLATE).mapColor(MapColor.DEEPSLATE));
	public static final Block livingrockAltar = new PetalApothecaryBlock(
			BlockBehaviour.Properties.ofFullCopy(defaultAltar).sound(SoundType.STONE).mapColor(MapColor.TERRACOTTA_WHITE));
	public static final Block mossyAltar = new PetalApothecaryBlock(BlockBehaviour.Properties.ofFullCopy(defaultAltar));
	public static final Block forestAltar = new PetalApothecaryBlock(
			BlockBehaviour.Properties.ofFullCopy(defaultAltar).sound(SoundType.TUFF).mapColor(MapColor.PLANT));
	public static final Block plainsAltar = new PetalApothecaryBlock(
			BlockBehaviour.Properties.ofFullCopy(defaultAltar).sound(SoundType.CALCITE).mapColor(DyeColor.WHITE));
	public static final Block mountainAltar = new PetalApothecaryBlock(
			BlockBehaviour.Properties.ofFullCopy(defaultAltar).sound(SoundType.DEEPSLATE_TILES).mapColor(DyeColor.LIGHT_GRAY));
	public static final Block fungalAltar = new PetalApothecaryBlock(
			BlockBehaviour.Properties.ofFullCopy(defaultAltar).sound(SoundType.DEEPSLATE_BRICKS).mapColor(MapColor.CRIMSON_STEM));
	public static final Block swampAltar = new PetalApothecaryBlock(
			BlockBehaviour.Properties.ofFullCopy(defaultAltar).sound(SoundType.DEEPSLATE_TILES).mapColor(MapColor.TERRACOTTA_BROWN));
	public static final Block desertAltar = new PetalApothecaryBlock(
			BlockBehaviour.Properties.ofFullCopy(defaultAltar).sound(SoundType.DEEPSLATE).mapColor(MapColor.TERRACOTTA_ORANGE));
	public static final Block taigaAltar = new PetalApothecaryBlock(
			BlockBehaviour.Properties.ofFullCopy(defaultAltar).sound(SoundType.DEEPSLATE).mapColor(DyeColor.BLUE));
	public static final Block mesaAltar = new PetalApothecaryBlock(
			BlockBehaviour.Properties.ofFullCopy(defaultAltar).sound(SoundType.CALCITE).mapColor(MapColor.TERRACOTTA_WHITE));
	public static final Block[] ALL_APOTHECARIES = new Block[] { defaultAltar, deepslateAltar, livingrockAltar, mossyAltar, forestAltar, plainsAltar, mountainAltar, fungalAltar, swampAltar, desertAltar, taigaAltar, mesaAltar };

	public static final Block livingrock = new BotaniaBlock(BlockBehaviour.Properties.of().strength(2, 10).sound(SoundType.STONE)
			.instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops());
	public static final Block livingrockStairs = new StairBlock(livingrock.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block livingrockSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block livingrockWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block livingrockPolished = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block livingrockPolishedStairs = new StairBlock(livingrock.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(livingrockPolished));
	public static final Block livingrockPolishedSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(livingrockPolished));
	public static final Block livingrockPolishedWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(livingrockPolished));
	public static final Block livingrockSlate = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block livingrockBrick = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block livingrockBrickStairs = new StairBlock(livingrockBrick.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(livingrockBrick));
	public static final Block livingrockBrickSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(livingrockBrick));
	public static final Block livingrockBrickWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(livingrockBrick));
	public static final Block livingrockBrickMossy = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block livingrockBrickMossyStairs = new StairBlock(livingrockBrickMossy.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(livingrockBrickMossy));
	public static final Block livingrockBrickMossySlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(livingrockBrickMossy));
	public static final Block livingrockBrickMossyWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(livingrockBrickMossy));
	public static final Block livingrockBrickCracked = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block livingrockBrickChiseled = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));

	public static final Block livingwoodLog = new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2)
			.sound(SoundType.WOOD).instrument(NoteBlockInstrument.BASS).mapColor(state -> state.getValue(BlockStateProperties.AXIS) == Direction.Axis.Y ? MapColor.TERRACOTTA_RED : MapColor.TERRACOTTA_BROWN));
	public static final Block livingwood = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodLog).mapColor(MapColor.TERRACOTTA_BROWN));
	public static final Block livingwoodStairs = new StairBlock(livingwood.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block livingwoodSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block livingwoodWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block livingwoodLogStripped = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodLog).mapColor(MapColor.TERRACOTTA_RED));
	public static final Block livingwoodStripped = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodLogStripped));
	public static final Block livingwoodStrippedStairs = new StairBlock(livingwoodStripped.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block livingwoodStrippedSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block livingwoodStrippedWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block livingwoodLogGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodLog).lightLevel(b -> 12));
	public static final Block livingwoodGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodLogGlimmering).mapColor(MapColor.TERRACOTTA_BROWN));
	public static final Block livingwoodLogStrippedGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodLogStripped).lightLevel(b -> 8));
	public static final Block livingwoodStrippedGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodLogStrippedGlimmering).mapColor(MapColor.TERRACOTTA_BROWN));
	public static final Block livingwoodPlanks = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodLog).mapColor(MapColor.TERRACOTTA_RED));
	public static final Block livingwoodPlankStairs = new StairBlock(livingwoodPlanks.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(livingwoodPlanks));
	public static final Block livingwoodPlankSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodPlanks));
	public static final Block livingwoodFence = new FenceBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block livingwoodFenceGate = new FenceGateBlock(BotaniaBlockSetTypes.LIVINGWOOD, BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block livingwoodPlanksMossy = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodPlanks));
	public static final Block livingwoodFramed = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodPlanks));
	public static final Block livingwoodPatternFramed = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodPlanks));

	public static final Block dreamwoodLog = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodLog).mapColor(MapColor.QUARTZ));
	public static final Block dreamwood = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(dreamwoodLog));
	public static final Block dreamwoodStairs = new StairBlock(dreamwood.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(dreamwood));
	public static final Block dreamwoodSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(dreamwood));
	public static final Block dreamwoodWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(dreamwood));
	public static final Block dreamwoodLogStripped = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(dreamwoodLog));
	public static final Block dreamwoodStripped = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(dreamwoodLog));
	public static final Block dreamwoodStrippedStairs = new StairBlock(dreamwoodStripped.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(dreamwood));
	public static final Block dreamwoodStrippedSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(dreamwood));
	public static final Block dreamwoodStrippedWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(dreamwood));
	public static final Block dreamwoodLogGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodLogGlimmering).mapColor(MapColor.QUARTZ));
	public static final Block dreamwoodGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(dreamwoodLogGlimmering));
	public static final Block dreamwoodLogStrippedGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(livingwoodLogStrippedGlimmering).mapColor(MapColor.QUARTZ));
	public static final Block dreamwoodStrippedGlimmering = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(dreamwoodLogStrippedGlimmering));
	public static final Block dreamwoodPlanks = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(dreamwoodLog));
	public static final Block dreamwoodPlankStairs = new StairBlock(dreamwoodPlanks.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(dreamwoodPlanks));
	public static final Block dreamwoodPlankSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(dreamwoodPlanks));
	public static final Block dreamwoodFence = new FenceBlock(BlockBehaviour.Properties.ofFullCopy(dreamwood));
	public static final Block dreamwoodFenceGate = new FenceGateBlock(BotaniaBlockSetTypes.DREAMWOOD, BlockBehaviour.Properties.ofFullCopy(dreamwood));
	public static final Block dreamwoodPlanksMossy = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(dreamwoodPlanks));
	public static final Block dreamwoodFramed = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(dreamwoodPlanks));
	public static final Block dreamwoodPatternFramed = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(dreamwoodPlanks));

	public static final Block manaSpreader = new ManaSpreaderBlock(ManaSpreaderBlock.Variant.MANA, BlockBehaviour.Properties.ofFullCopy(livingwood).isValidSpawn(NO_SPAWN));
	public static final Block redstoneSpreader = new ManaSpreaderBlock(ManaSpreaderBlock.Variant.REDSTONE, BlockBehaviour.Properties.ofFullCopy(livingwood).isValidSpawn(NO_SPAWN));
	public static final Block elvenSpreader = new ManaSpreaderBlock(ManaSpreaderBlock.Variant.ELVEN, BlockBehaviour.Properties.ofFullCopy(dreamwood).isValidSpawn(NO_SPAWN));
	public static final Block gaiaSpreader = new ManaSpreaderBlock(ManaSpreaderBlock.Variant.GAIA, BlockBehaviour.Properties.ofFullCopy(dreamwood).isValidSpawn(NO_SPAWN));

	public static final Block manaPool = new ManaPoolBlock(ManaPoolBlock.Variant.DEFAULT, BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block creativePool = new ManaPoolBlock(ManaPoolBlock.Variant.CREATIVE, BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block dilutedPool = new ManaPoolBlock(ManaPoolBlock.Variant.DILUTED, BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block fabulousPool = new ManaPoolBlock(ManaPoolBlock.Variant.FABULOUS, BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block alchemyCatalyst = new AlchemyCatalystBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block conjurationCatalyst = new ConjurationCatalystBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));

	public static final Block manasteelBlock = new BotaniaBlock(BlockBehaviour.Properties.of().strength(3, 10).mapColor(MapColor.LAPIS)
			.sound(SoundType.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops());
	public static final Block terrasteelBlock = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(manasteelBlock).mapColor(MapColor.EMERALD));
	public static final Block elementiumBlock = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(manasteelBlock).mapColor(MapColor.COLOR_PINK));
	public static final Block manaDiamondBlock = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(manasteelBlock).mapColor(MapColor.DIAMOND));
	public static final Block dragonstoneBlock = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(manasteelBlock).instrument(NoteBlockInstrument.HARP).mapColor(MapColor.COLOR_PINK));

	public static final Block manaGlass = new HalfTransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).lightLevel(s -> 15).isViewBlocking(NO_SUFFOCATION).isSuffocating(NO_SUFFOCATION).isValidSpawn(NO_SPAWN));
	public static final Block elfGlass = new HalfTransparentBlock(BlockBehaviour.Properties.ofFullCopy(manaGlass).isViewBlocking(NO_SUFFOCATION).isSuffocating(NO_SUFFOCATION).isValidSpawn(NO_SPAWN));
	public static final Block bifrost = new BifrostBlock(BlockBehaviour.Properties.of().strength(-1, 0.3F)
			.lightLevel(s -> 15).sound(SoundType.GLASS).instrument(NoteBlockInstrument.HAT).noOcclusion()
			.isViewBlocking(NO_SUFFOCATION).isSuffocating(NO_SUFFOCATION).isValidSpawn(NO_SPAWN));
	public static final Block bifrostPerm = new PermanentBifrostBlock(BlockBehaviour.Properties.of().strength(0.3F)
			.lightLevel(s -> 15).sound(SoundType.GLASS).instrument(NoteBlockInstrument.HAT).noOcclusion()
			.isViewBlocking(NO_SUFFOCATION).isSuffocating(NO_SUFFOCATION).isValidSpawn(NO_SPAWN));

	public static final Block runeAltar = new RunicAltarBlock(BlockBehaviour.Properties.ofFullCopy(livingrock).requiresCorrectToolForDrops());
	public static final Block enchanter = new ManaEnchanterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.LAPIS).strength(3, 5).lightLevel(s -> 15).sound(SoundType.STONE));
	public static final Block brewery = new BotanicalBreweryBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block terraPlate = new TerrestrialAgglomerationPlateBlock(BlockBehaviour.Properties.of().mapColor(MapColor.LAPIS).strength(3, 10).sound(SoundType.METAL).requiresCorrectToolForDrops());
	public static final Block alfPortal = new AlfheimPortalBlock(BlockBehaviour.Properties.ofFullCopy(livingwood).strength(10).sound(SoundType.WOOD)
			.lightLevel(s -> s.getValue(BotaniaStateProperties.ALFPORTAL_STATE) != AlfheimPortalState.OFF ? 15 : 0));

	public static final Block manaPylon = new PylonBlock(PylonBlock.Variant.MANA, BlockBehaviour.Properties.of().mapColor(DyeColor.LIGHT_BLUE).strength(5.5F).sound(SoundType.METAL).lightLevel(s -> 7).requiresCorrectToolForDrops());
	public static final Block naturaPylon = new PylonBlock(PylonBlock.Variant.NATURA, BlockBehaviour.Properties.ofFullCopy(manaPylon).mapColor(MapColor.EMERALD));
	public static final Block gaiaPylon = new PylonBlock(PylonBlock.Variant.GAIA, BlockBehaviour.Properties.ofFullCopy(manaPylon).mapColor(DyeColor.PINK));

	public static final Block distributor = new ManaSplitterBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block manaVoid = new ManaVoidBlock(BlockBehaviour.Properties.ofFullCopy(livingrock).strength(2, 2000));
	public static final Block manaDetector = new ManaDetectorBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block pistonRelay = new ForceRelayBlock(BlockBehaviour.Properties.of().strength(2, 10).sound(SoundType.METAL).mapColor(MapColor.COLOR_PURPLE).isValidSpawn(NO_SPAWN));
	public static final Block turntable = new SpreaderTurntableBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block tinyPlanet = new TinyPlanetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(20, 100)
			.sound(SoundType.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops());
	public static final Block wildDrum = new DrumBlock(DrumBlock.Variant.WILD, BlockBehaviour.Properties.ofFullCopy(livingwood).instrument(NoteBlockInstrument.BASEDRUM));
	public static final Block gatheringDrum = new DrumBlock(DrumBlock.Variant.GATHERING, BlockBehaviour.Properties.ofFullCopy(wildDrum));
	public static final Block canopyDrum = new DrumBlock(DrumBlock.Variant.CANOPY, BlockBehaviour.Properties.ofFullCopy(wildDrum));
	public static final Block spawnerClaw = new LifeImbuerBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(3).requiresCorrectToolForDrops());
	public static final Block rfGenerator = new PowerGeneratorBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block prism = new ManaPrismBlock(BlockBehaviour.Properties.ofFullCopy(elfGlass).noCollission());
	public static final Block pump = new ManaPumpBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block sparkChanger = new SparkTinkererBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block manaBomb = new ManastormChargeBlock(BlockBehaviour.Properties.ofFullCopy(livingwood).strength(12));
	public static final Block bellows = new BellowsBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));

	public static final Block openCrate = new OpenCrateBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block craftCrate = new CraftyCrateBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block forestEye = new EyeOfTheAncientsBlock(BlockBehaviour.Properties.of().strength(5, 10).sound(SoundType.METAL).requiresCorrectToolForDrops());
	public static final Block solidVines = new SolidVineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE));
	public static final Block abstrusePlatform = new PlatformBlock(PlatformBlock.Variant.ABSTRUSE, BlockBehaviour.Properties.ofFullCopy(livingwood).strength(2, 5).isValidSpawn(NO_SPAWN).noOcclusion().isViewBlocking(NO_SUFFOCATION).isSuffocating(NO_SUFFOCATION));
	public static final Block spectralPlatform = new PlatformBlock(PlatformBlock.Variant.SPECTRAL, BlockBehaviour.Properties.ofFullCopy(abstrusePlatform));
	public static final Block infrangiblePlatform = new PlatformBlock(PlatformBlock.Variant.INFRANGIBLE, BlockBehaviour.Properties.ofFullCopy(abstrusePlatform).strength(-1, Float.MAX_VALUE).isValidSpawn(NO_SPAWN).noOcclusion());
	public static final Block tinyPotato = new TinyPotatoBlock(BlockBehaviour.Properties.of().strength(0.25F).mapColor(DyeColor.PINK));
	public static final Block enderEye = new EnderOverseerBlock(BlockBehaviour.Properties.ofFullCopy(manasteelBlock));
	public static final Block redStringContainer = new RedStringContainerBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block redStringDispenser = new RedStringDispenserBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block redStringFertilizer = new RedStringNutrifierBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block redStringComparator = new RedStringComparatorBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block redStringRelay = new RedStringSpooferBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block redStringInterceptor = new RedStringInterceptorBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));

	public static final Block corporeaFunnel = new CorporeaFunnelBlock(BlockBehaviour.Properties.of().strength(5.5F)
			.mapColor(DyeColor.PURPLE).sound(SoundType.METAL).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops());
	public static final Block corporeaInterceptor = new CorporeaInterceptorBlock(BlockBehaviour.Properties.ofFullCopy(corporeaFunnel));
	public static final Block corporeaIndex = new CorporeaIndexBlock(BlockBehaviour.Properties.ofFullCopy(corporeaFunnel).noOcclusion());
	public static final Block corporeaCrystalCube = new CorporeaCrystalCubeBlock(BlockBehaviour.Properties.ofFullCopy(corporeaFunnel));
	public static final Block corporeaRetainer = new CorporeaRetainerBlock(BlockBehaviour.Properties.ofFullCopy(corporeaFunnel));

	public static final Block corporeaBlock = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(corporeaFunnel));
	public static final StairBlock corporeaStairs = new StairBlock(corporeaBlock.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(corporeaBlock));
	public static final SlabBlock corporeaSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(corporeaBlock));
	public static final Block corporeaBrick = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(corporeaBlock));
	public static final StairBlock corporeaBrickStairs = new StairBlock(corporeaBrick.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(corporeaBrick));
	public static final SlabBlock corporeaBrickSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(corporeaBrick));
	public static final Block corporeaBrickWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(corporeaBrick));

	public static final Block incensePlate = new IncensePlateBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block hourglass = new HoveringHourglassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).strength(2).sound(SoundType.METAL));
	public static final Block ghostRail = new SpectralRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL));
	public static final Block lightRelayDefault = new LuminizerBlock(LuminizerVariant.DEFAULT, BlockBehaviour.Properties.of().noCollission());
	public static final Block lightRelayDetector = new LuminizerBlock(LuminizerVariant.DETECTOR, BlockBehaviour.Properties.ofFullCopy(lightRelayDefault));
	public static final Block lightRelayFork = new LuminizerBlock(LuminizerVariant.FORK, BlockBehaviour.Properties.ofFullCopy(lightRelayDefault));
	public static final Block lightRelayToggle = new LuminizerBlock(LuminizerVariant.TOGGLE, BlockBehaviour.Properties.ofFullCopy(lightRelayDefault));
	public static final Block lightLauncher = new LuminizerLauncherBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block cacophonium = new CacophoniumBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NOTE_BLOCK).strength(0.8F));
	public static final Block cellBlock = new CellularBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).sound(SoundType.WOOL));
	public static final Block teruTeruBozu = new TeruTeruBozuBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).instrument(NoteBlockInstrument.GUITAR).mapColor(DyeColor.WHITE));
	public static final Block avatar = new AvatarBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block fakeAir = new FakeAirBlock(BlockBehaviour.Properties.of().replaceable().noCollission().noLootTable().air().randomTicks());
	public static final Block root = new LivingRootBlock(BlockBehaviour.Properties.of().strength(1.2F).sound(SoundType.WOOD));
	public static final Block felPumpkin = new FelPumpkinBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARVED_PUMPKIN));
	public static final Block cocoon = new CocoonBlock(BlockBehaviour.Properties.of().strength(3, 60).sound(SoundType.WOOL));
	public static final Block enchantedSoil = new EnchantedSoilBlock(BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.GRASS).mapColor(MapColor.GRASS));
	public static final Block animatedTorch = new AnimatedTorchBlock(BlockBehaviour.Properties.of().lightLevel(s -> 7).noOcclusion());
	public static final Block starfield = new StarfieldCreatorBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.PINK).strength(5, 2000).sound(SoundType.METAL));

	public static final Block azulejo0 = new BotaniaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.LAPIS).strength(2, 5)
			.sound(SoundType.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops());
	public static final Block azulejo1 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo2 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo3 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo4 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo5 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo6 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo7 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo8 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo9 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo10 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo11 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo12 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo13 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo14 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block azulejo15 = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(azulejo0));
	public static final Block manaFlame = new ManaFlameBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).sound(SoundType.WOOL).lightLevel(s -> 15).noCollission());
	public static final Block blazeBlock = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(manasteelBlock).instrument(NoteBlockInstrument.PLING).lightLevel(s -> 15).mapColor(MapColor.GOLD));
	public static final Block gaiaHead = new GaiaHeadBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).strength(1));
	public static final Block gaiaHeadWall = new WallGaiaHeadBlock(BlockBehaviour.Properties.ofFullCopy(gaiaHead));

	public static final Block shimmerrock = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
	public static final Block shimmerrockStairs = new StairBlock(shimmerrock.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(shimmerrock));
	public static final Block shimmerrockSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(shimmerrock));
	public static final Block shimmerwoodPlanks = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingwood));
	public static final Block shimmerwoodPlankStairs = new StairBlock(shimmerwoodPlanks.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(shimmerwoodPlanks));
	public static final Block shimmerwoodPlankSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(shimmerwoodPlanks));

	public static final Block dryGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.DRY, BlockBehaviour.Properties.of().strength(0.6F).randomTicks().sound(SoundType.GRASS).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN));
	public static final Block goldenGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.GOLDEN, BlockBehaviour.Properties.ofFullCopy(dryGrass).mapColor(MapColor.GOLD));
	public static final Block vividGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.VIVID, BlockBehaviour.Properties.ofFullCopy(dryGrass).mapColor(MapColor.PLANT));
	public static final Block scorchedGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.SCORCHED, BlockBehaviour.Properties.ofFullCopy(dryGrass).mapColor(MapColor.NETHER));
	public static final Block infusedGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.INFUSED, BlockBehaviour.Properties.ofFullCopy(dryGrass).mapColor(MapColor.COLOR_CYAN));
	public static final Block mutatedGrass = new BotaniaGrassBlock(BotaniaGrassBlock.Variant.MUTATED, BlockBehaviour.Properties.ofFullCopy(dryGrass).mapColor(MapColor.WARPED_HYPHAE));

	public static final Block motifDaybloom = new FlowerMotifBlock(MobEffects.BLINDNESS, 15, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);
	public static final Block motifNightshade = new FlowerMotifBlock(MobEffects.POISON, 20, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);
	public static final Block motifHydroangeas = new FlowerMotifBlock(MobEffects.UNLUCK, 10, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), false);

	public static final Block pottedMotifDaybloom = flowerPot(motifDaybloom, 0);
	public static final Block pottedMotifNightshade = flowerPot(motifNightshade, 0);
	public static final Block pottedMotifHydroangeas = flowerPot(motifHydroangeas, 0);

	public static final Block darkQuartz = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.COLOR_BLACK));
	public static final Block darkQuartzStairs = new StairBlock(darkQuartz.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(darkQuartz));
	public static final Block darkQuartzSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(darkQuartz));
	public static final Block darkQuartzChiseled = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(darkQuartz));
	public static final Block darkQuartzPillar = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(darkQuartz));

	public static final Block manaQuartz = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(darkQuartz).mapColor(MapColor.DIAMOND));
	public static final Block manaQuartzStairs = new StairBlock(manaQuartz.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(darkQuartz));
	public static final Block manaQuartzSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(manaQuartz));
	public static final Block manaQuartzChiseled = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(manaQuartz));
	public static final Block manaQuartzPillar = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(manaQuartz));

	public static final Block blazeQuartz = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(darkQuartz).mapColor(MapColor.SAND));
	public static final Block blazeQuartzStairs = new StairBlock(blazeQuartz.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(blazeQuartz));
	public static final Block blazeQuartzSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(blazeQuartz));
	public static final Block blazeQuartzChiseled = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(blazeQuartz));
	public static final Block blazeQuartzPillar = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(blazeQuartz));

	public static final Block lavenderQuartz = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(darkQuartz).mapColor(MapColor.COLOR_PINK));
	public static final Block lavenderQuartzStairs = new StairBlock(lavenderQuartz.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(lavenderQuartz));
	public static final Block lavenderQuartzSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(lavenderQuartz));
	public static final Block lavenderQuartzChiseled = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(lavenderQuartz));
	public static final Block lavenderQuartzPillar = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(lavenderQuartz));

	public static final Block redQuartz = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(darkQuartz).mapColor(MapColor.TERRACOTTA_WHITE));
	public static final Block redQuartzStairs = new StairBlock(redQuartz.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(redQuartz));
	public static final Block redQuartzSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(redQuartz));
	public static final Block redQuartzChiseled = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(redQuartz));
	public static final Block redQuartzPillar = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(redQuartz));

	public static final Block elfQuartz = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(darkQuartz).mapColor(MapColor.COLOR_LIGHT_GREEN));
	public static final Block elfQuartzStairs = new StairBlock(elfQuartz.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(elfQuartz));
	public static final Block elfQuartzSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(elfQuartz));
	public static final Block elfQuartzChiseled = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(elfQuartz));
	public static final Block elfQuartzPillar = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(elfQuartz));

	public static final Block sunnyQuartz = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(darkQuartz).mapColor(MapColor.COLOR_YELLOW));
	public static final Block sunnyQuartzStairs = new StairBlock(sunnyQuartz.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(sunnyQuartz));
	public static final Block sunnyQuartzSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(sunnyQuartz));
	public static final Block sunnyQuartzChiseled = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(sunnyQuartz));
	public static final Block sunnyQuartzPillar = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(sunnyQuartz));

	public static final Block biomeStoneForest = new BotaniaBlock(BlockBehaviour.Properties.of().strength(1.5F, 10)
			.sound(SoundType.TUFF).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().mapColor(MapColor.WARPED_NYLIUM));
	public static final Block biomeStoneForestStairs = new StairBlock(biomeStoneForest.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));
	public static final Block biomeStoneForestSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));
	public static final Block biomeStoneForestWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));
	public static final Block biomeCobblestoneForest = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));
	public static final Block biomeCobblestoneForestStairs = new StairBlock(biomeStoneForest.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));
	public static final Block biomeCobblestoneForestSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));
	public static final Block biomeCobblestoneForestWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));
	public static final Block biomeBrickForest = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));
	public static final Block biomeBrickForestStairs = new StairBlock(biomeStoneForest.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));
	public static final Block biomeBrickForestSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));
	public static final Block biomeBrickForestWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));
	public static final Block biomeChiseledBrickForest = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest));

	public static final Block biomeStonePlains = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest)
			.sound(SoundType.CALCITE).mapColor(MapColor.QUARTZ));
	public static final Block biomeStonePlainsStairs = new StairBlock(biomeStonePlains.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));
	public static final Block biomeStonePlainsSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));
	public static final Block biomeStonePlainsWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));
	public static final Block biomeCobblestonePlains = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));
	public static final Block biomeCobblestonePlainsStairs = new StairBlock(biomeStonePlains.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));
	public static final Block biomeCobblestonePlainsSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));
	public static final Block biomeCobblestonePlainsWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));
	public static final Block biomeBrickPlains = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));
	public static final Block biomeBrickPlainsStairs = new StairBlock(biomeStonePlains.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));
	public static final Block biomeBrickPlainsSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));
	public static final Block biomeBrickPlainsWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));
	public static final Block biomeChiseledBrickPlains = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStonePlains));

	public static final Block biomeStoneMountain = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest)
			.sound(SoundType.DEEPSLATE_TILES).mapColor(MapColor.GLOW_LICHEN));
	public static final Block biomeStoneMountainStairs = new StairBlock(biomeStoneMountain.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));
	public static final Block biomeStoneMountainSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));
	public static final Block biomeStoneMountainWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));
	public static final Block biomeCobblestoneMountain = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));
	public static final Block biomeCobblestoneMountainStairs = new StairBlock(biomeStoneMountain.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));
	public static final Block biomeCobblestoneMountainSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));
	public static final Block biomeCobblestoneMountainWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));
	public static final Block biomeBrickMountain = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));
	public static final Block biomeBrickMountainStairs = new StairBlock(biomeStoneMountain.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));
	public static final Block biomeBrickMountainSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));
	public static final Block biomeBrickMountainWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));
	public static final Block biomeChiseledBrickMountain = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMountain));

	public static final Block biomeStoneFungal = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest)
			.sound(SoundType.DEEPSLATE_BRICKS).mapColor(MapColor.TERRACOTTA_PURPLE));
	public static final Block biomeStoneFungalStairs = new StairBlock(biomeStoneFungal.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));
	public static final Block biomeStoneFungalSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));
	public static final Block biomeStoneFungalWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));
	public static final Block biomeCobblestoneFungal = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));
	public static final Block biomeCobblestoneFungalStairs = new StairBlock(biomeStoneFungal.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));
	public static final Block biomeCobblestoneFungalSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));
	public static final Block biomeCobblestoneFungalWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));
	public static final Block biomeBrickFungal = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));
	public static final Block biomeBrickFungalStairs = new StairBlock(biomeStoneFungal.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));
	public static final Block biomeBrickFungalSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));
	public static final Block biomeBrickFungalWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));
	public static final Block biomeChiseledBrickFungal = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneFungal));

	public static final Block biomeStoneSwamp = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest)
			.sound(SoundType.DEEPSLATE_TILES).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
	public static final Block biomeStoneSwampStairs = new StairBlock(biomeStoneSwamp.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));
	public static final Block biomeStoneSwampSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));
	public static final Block biomeStoneSwampWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));
	public static final Block biomeCobblestoneSwamp = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));
	public static final Block biomeCobblestoneSwampStairs = new StairBlock(biomeStoneSwamp.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));
	public static final Block biomeCobblestoneSwampSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));
	public static final Block biomeCobblestoneSwampWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));
	public static final Block biomeBrickSwamp = new BotaniaDirectionalBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));
	public static final Block biomeBrickSwampStairs = new StairBlock(biomeStoneSwamp.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));
	public static final Block biomeBrickSwampSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));
	public static final Block biomeBrickSwampWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));
	public static final Block biomeChiseledBrickSwamp = new BotaniaDirectionalBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneSwamp));

	public static final Block biomeStoneDesert = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest)
			.sound(SoundType.DEEPSLATE).mapColor(MapColor.DIRT));
	public static final Block biomeStoneDesertStairs = new StairBlock(biomeStoneDesert.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));
	public static final Block biomeStoneDesertSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));
	public static final Block biomeStoneDesertWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));
	public static final Block biomeCobblestoneDesert = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));
	public static final Block biomeCobblestoneDesertStairs = new StairBlock(biomeStoneDesert.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));
	public static final Block biomeCobblestoneDesertSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));
	public static final Block biomeCobblestoneDesertWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));
	public static final Block biomeBrickDesert = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));
	public static final Block biomeBrickDesertStairs = new StairBlock(biomeStoneDesert.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));
	public static final Block biomeBrickDesertSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));
	public static final Block biomeBrickDesertWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));
	public static final Block biomeChiseledBrickDesert = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneDesert));

	public static final Block biomeStoneTaiga = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest)
			.sound(SoundType.DEEPSLATE).mapColor(MapColor.TERRACOTTA_BLUE));
	public static final Block biomeStoneTaigaStairs = new StairBlock(biomeStoneTaiga.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));
	public static final Block biomeStoneTaigaSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));
	public static final Block biomeStoneTaigaWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));
	public static final Block biomeCobblestoneTaiga = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));
	public static final Block biomeCobblestoneTaigaStairs = new StairBlock(biomeStoneTaiga.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));
	public static final Block biomeCobblestoneTaigaSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));
	public static final Block biomeCobblestoneTaigaWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));
	public static final Block biomeBrickTaiga = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));
	public static final Block biomeBrickTaigaStairs = new StairBlock(biomeStoneTaiga.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));
	public static final Block biomeBrickTaigaSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));
	public static final Block biomeBrickTaigaWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));
	public static final Block biomeChiseledBrickTaiga = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneTaiga));

	public static final Block biomeStoneMesa = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneForest)
			.sound(SoundType.CALCITE).mapColor(MapColor.TERRACOTTA_WHITE));
	public static final Block biomeStoneMesaStairs = new StairBlock(biomeStoneMesa.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));
	public static final Block biomeStoneMesaSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));
	public static final Block biomeStoneMesaWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));
	public static final Block biomeCobblestoneMesa = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));
	public static final Block biomeCobblestoneMesaStairs = new StairBlock(biomeStoneMesa.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));
	public static final Block biomeCobblestoneMesaSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));
	public static final Block biomeCobblestoneMesaWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));
	public static final Block biomeBrickMesa = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));
	public static final Block biomeBrickMesaStairs = new StairBlock(biomeStoneMesa.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));
	public static final Block biomeBrickMesaSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));
	public static final Block biomeBrickMesaWall = new WallBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));
	public static final Block biomeChiseledBrickMesa = new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(biomeStoneMesa));

	public static final Block whitePavement = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(livingrock).mapColor(MapColor.TERRACOTTA_WHITE));
	public static final Block whitePavementStair = new StairBlock(whitePavement.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(whitePavement));
	public static final Block whitePavementSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(whitePavement));

	public static final Block blackPavement = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(whitePavement).mapColor(MapColor.COLOR_GRAY));
	public static final Block blackPavementStair = new StairBlock(blackPavement.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(blackPavement));
	public static final Block blackPavementSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(blackPavement));

	public static final Block bluePavement = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(whitePavement).mapColor(MapColor.COLOR_BLUE));
	public static final Block bluePavementStair = new StairBlock(bluePavement.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(bluePavement));
	public static final Block bluePavementSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(bluePavement));

	public static final Block yellowPavement = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(whitePavement).mapColor(MapColor.TERRACOTTA_YELLOW));
	public static final Block yellowPavementStair = new StairBlock(yellowPavement.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(yellowPavement));
	public static final Block yellowPavementSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(yellowPavement));

	public static final Block redPavement = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(whitePavement).mapColor(MapColor.TERRACOTTA_RED));
	public static final Block redPavementStair = new StairBlock(redPavement.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(redPavement));
	public static final Block redPavementSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(redPavement));

	public static final Block greenPavement = new BotaniaBlock(BlockBehaviour.Properties.ofFullCopy(whitePavement).mapColor(MapColor.TERRACOTTA_GREEN));
	public static final Block greenPavementStair = new StairBlock(greenPavement.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(greenPavement));
	public static final Block greenPavementSlab = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(greenPavement));

	public static final Block managlassPane = new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(manaGlass));
	public static final Block alfglassPane = new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(elfGlass));
	public static final Block bifrostPane = new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(bifrostPerm));

	static FlowerPotBlock flowerPot(Block block, int lightLevel) {
		BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
		return new FlowerPotBlock(block, lightLevel > 0 ? properties.lightLevel(blockState -> lightLevel) : properties);
	}

	private static ResourceLocation floating(ResourceLocation orig) {
		return ResourceLocation.fromNamespaceAndPath(orig.getNamespace(), "floating_" + orig.getPath());
	}

	private static ResourceLocation potted(ResourceLocation orig) {
		return ResourceLocation.fromNamespaceAndPath(orig.getNamespace(), "potted_" + orig.getPath());
	}

	private static ResourceLocation chibi(ResourceLocation orig) {
		return ResourceLocation.fromNamespaceAndPath(orig.getNamespace(), orig.getPath() + "_chibi");
	}

	public static void registerBlocks(BiConsumer<Block, ResourceLocation> r) {
		// triples of: block getter from dye color, block ID prefix, block ID suffix
		Stream.<Triple<Function<DyeColor, Block>, String, String>>of(
				Triple.of(BotaniaBlocks::getFlower, "", LibBlockNames.MYSTICAL_FLOWER_SUFFIX),
				Triple.of(BotaniaBlocks::getShinyFlower, "", LibBlockNames.SHINY_FLOWER_SUFFIX),
				Triple.of(BotaniaBlocks::getBuriedPetal, "", LibBlockNames.BURIED_PETALS_SUFFIX),
				Triple.of(BotaniaBlocks::getFloatingFlower, "", LibBlockNames.FLOATING_FLOWER_SUFFIX),
				Triple.of(BotaniaBlocks::getPetalBlock, "", LibBlockNames.PETAL_BLOCK_SUFFIX),
				Triple.of(BotaniaBlocks::getMushroom, "", LibBlockNames.MUSHROOM_SUFFIX),
				Triple.of(BotaniaBlocks::getDoubleFlower, "", LibBlockNames.DOUBLE_FLOWER_SUFFIX),
				Triple.of(BotaniaBlocks::getPottedFlower, LibBlockNames.POTTED_PREFIX, LibBlockNames.MYSTICAL_FLOWER_SUFFIX),
				Triple.of(BotaniaBlocks::getPottedShinyFlower, LibBlockNames.POTTED_PREFIX, LibBlockNames.SHINY_FLOWER_SUFFIX),
				Triple.of(BotaniaBlocks::getPottedMushroom, LibBlockNames.POTTED_PREFIX, LibBlockNames.MUSHROOM_SUFFIX)
		).forEach(coloredBlockRegistration -> {
			ColorHelper.supportedColors().forEach(dyeColor -> {
				r.accept(coloredBlockRegistration.getLeft().apply(dyeColor),
						botaniaRL(coloredBlockRegistration.getMiddle() + dyeColor.getName() + coloredBlockRegistration.getRight()));
			});
		});

		r.accept(pureDaisy, LibBlockNames.SUBTILE_PUREDAISY);
		r.accept(pureDaisyFloating, floating(LibBlockNames.SUBTILE_PUREDAISY));
		r.accept(pureDaisyPotted, potted(LibBlockNames.SUBTILE_PUREDAISY));

		r.accept(manastar, LibBlockNames.SUBTILE_MANASTAR);
		r.accept(manastarFloating, floating(LibBlockNames.SUBTILE_MANASTAR));
		r.accept(manastarPotted, potted(LibBlockNames.SUBTILE_MANASTAR));

		r.accept(hydroangeas, LibBlockNames.SUBTILE_HYDROANGEAS);
		r.accept(hydroangeasFloating, floating(LibBlockNames.SUBTILE_HYDROANGEAS));
		r.accept(hydroangeasPotted, potted(LibBlockNames.SUBTILE_HYDROANGEAS));

		r.accept(endoflame, LibBlockNames.SUBTILE_ENDOFLAME);
		r.accept(endoflameFloating, floating(LibBlockNames.SUBTILE_ENDOFLAME));
		r.accept(endoflamePotted, potted(LibBlockNames.SUBTILE_ENDOFLAME));

		r.accept(thermalily, LibBlockNames.SUBTILE_THERMALILY);
		r.accept(thermalilyFloating, floating(LibBlockNames.SUBTILE_THERMALILY));
		r.accept(thermalilyPotted, potted(LibBlockNames.SUBTILE_THERMALILY));

		r.accept(rosaArcana, LibBlockNames.SUBTILE_ARCANE_ROSE);
		r.accept(rosaArcanaFloating, floating(LibBlockNames.SUBTILE_ARCANE_ROSE));
		r.accept(rosaArcanaPotted, potted(LibBlockNames.SUBTILE_ARCANE_ROSE));

		r.accept(munchdew, LibBlockNames.SUBTILE_MUNCHDEW);
		r.accept(munchdewFloating, floating(LibBlockNames.SUBTILE_MUNCHDEW));
		r.accept(munchdewPotted, potted(LibBlockNames.SUBTILE_MUNCHDEW));

		r.accept(entropinnyum, LibBlockNames.SUBTILE_ENTROPINNYUM);
		r.accept(entropinnyumFloating, floating(LibBlockNames.SUBTILE_ENTROPINNYUM));
		r.accept(entropinnyumPotted, potted(LibBlockNames.SUBTILE_ENTROPINNYUM));

		r.accept(kekimurus, LibBlockNames.SUBTILE_KEKIMURUS);
		r.accept(kekimurusFloating, floating(LibBlockNames.SUBTILE_KEKIMURUS));
		r.accept(kekimurusPotted, potted(LibBlockNames.SUBTILE_KEKIMURUS));

		r.accept(gourmaryllis, LibBlockNames.SUBTILE_GOURMARYLLIS);
		r.accept(gourmaryllisFloating, floating(LibBlockNames.SUBTILE_GOURMARYLLIS));
		r.accept(gourmaryllisPotted, potted(LibBlockNames.SUBTILE_GOURMARYLLIS));

		r.accept(narslimmus, LibBlockNames.SUBTILE_NARSLIMMUS);
		r.accept(narslimmusFloating, floating(LibBlockNames.SUBTILE_NARSLIMMUS));
		r.accept(narslimmusPotted, potted(LibBlockNames.SUBTILE_NARSLIMMUS));

		r.accept(spectrolus, LibBlockNames.SUBTILE_SPECTROLUS);
		r.accept(spectrolusFloating, floating(LibBlockNames.SUBTILE_SPECTROLUS));
		r.accept(spectrolusPotted, potted(LibBlockNames.SUBTILE_SPECTROLUS));

		r.accept(dandelifeon, LibBlockNames.SUBTILE_DANDELIFEON);
		r.accept(dandelifeonFloating, floating(LibBlockNames.SUBTILE_DANDELIFEON));
		r.accept(dandelifeonPotted, potted(LibBlockNames.SUBTILE_DANDELIFEON));

		r.accept(rafflowsia, LibBlockNames.SUBTILE_RAFFLOWSIA);
		r.accept(rafflowsiaFloating, floating(LibBlockNames.SUBTILE_RAFFLOWSIA));
		r.accept(rafflowsiaPotted, potted(LibBlockNames.SUBTILE_RAFFLOWSIA));

		r.accept(shulkMeNot, LibBlockNames.SUBTILE_SHULK_ME_NOT);
		r.accept(shulkMeNotFloating, floating(LibBlockNames.SUBTILE_SHULK_ME_NOT));
		r.accept(shulkMeNotPotted, potted(LibBlockNames.SUBTILE_SHULK_ME_NOT));

		r.accept(bellethorn, LibBlockNames.SUBTILE_BELLETHORN);
		r.accept(bellethornChibi, chibi(LibBlockNames.SUBTILE_BELLETHORN));
		r.accept(bellethornFloating, floating(LibBlockNames.SUBTILE_BELLETHORN));
		r.accept(bellethornChibiFloating, chibi(floating(LibBlockNames.SUBTILE_BELLETHORN)));
		r.accept(bellethornPotted, potted(LibBlockNames.SUBTILE_BELLETHORN));
		r.accept(bellethornChibiPotted, potted(chibi(LibBlockNames.SUBTILE_BELLETHORN)));

		r.accept(bergamute, LibBlockNames.SUBTILE_BERGAMUTE);
		r.accept(bergamuteFloating, floating(LibBlockNames.SUBTILE_BERGAMUTE));
		r.accept(bergamutePotted, potted(LibBlockNames.SUBTILE_BERGAMUTE));

		r.accept(dreadthorn, LibBlockNames.SUBTILE_DREADTHORN);
		r.accept(dreadthornFloating, floating(LibBlockNames.SUBTILE_DREADTHORN));
		r.accept(dreadthornPotted, potted(LibBlockNames.SUBTILE_DREADTHORN));

		r.accept(heiseiDream, LibBlockNames.SUBTILE_HEISEI_DREAM);
		r.accept(heiseiDreamFloating, floating(LibBlockNames.SUBTILE_HEISEI_DREAM));
		r.accept(heiseiDreamPotted, potted(LibBlockNames.SUBTILE_HEISEI_DREAM));

		r.accept(tigerseye, LibBlockNames.SUBTILE_TIGERSEYE);
		r.accept(tigerseyeFloating, floating(LibBlockNames.SUBTILE_TIGERSEYE));
		r.accept(tigerseyePotted, potted(LibBlockNames.SUBTILE_TIGERSEYE));

		r.accept(jadedAmaranthus, LibBlockNames.SUBTILE_JADED_AMARANTHUS);
		r.accept(jadedAmaranthusFloating, floating(LibBlockNames.SUBTILE_JADED_AMARANTHUS));
		r.accept(jadedAmaranthusPotted, potted(LibBlockNames.SUBTILE_JADED_AMARANTHUS));

		r.accept(orechid, LibBlockNames.SUBTILE_ORECHID);
		r.accept(orechidFloating, floating(LibBlockNames.SUBTILE_ORECHID));
		r.accept(orechidPotted, potted(LibBlockNames.SUBTILE_ORECHID));

		r.accept(fallenKanade, LibBlockNames.SUBTILE_FALLEN_KANADE);
		r.accept(fallenKanadeFloating, floating(LibBlockNames.SUBTILE_FALLEN_KANADE));
		r.accept(fallenKanadePotted, potted(LibBlockNames.SUBTILE_FALLEN_KANADE));

		r.accept(exoflame, LibBlockNames.SUBTILE_EXOFLAME);
		r.accept(exoflameFloating, floating(LibBlockNames.SUBTILE_EXOFLAME));
		r.accept(exoflamePotted, potted(LibBlockNames.SUBTILE_EXOFLAME));

		r.accept(agricarnation, LibBlockNames.SUBTILE_AGRICARNATION);
		r.accept(agricarnationChibi, chibi(LibBlockNames.SUBTILE_AGRICARNATION));
		r.accept(agricarnationFloating, floating(LibBlockNames.SUBTILE_AGRICARNATION));
		r.accept(agricarnationChibiFloating, chibi(floating(LibBlockNames.SUBTILE_AGRICARNATION)));
		r.accept(agricarnationPotted, potted(LibBlockNames.SUBTILE_AGRICARNATION));
		r.accept(agricarnationChibiPotted, potted(chibi(LibBlockNames.SUBTILE_AGRICARNATION)));

		r.accept(hopperhock, LibBlockNames.SUBTILE_HOPPERHOCK);
		r.accept(hopperhockChibi, chibi(LibBlockNames.SUBTILE_HOPPERHOCK));
		r.accept(hopperhockFloating, floating(LibBlockNames.SUBTILE_HOPPERHOCK));
		r.accept(hopperhockChibiFloating, chibi(floating(LibBlockNames.SUBTILE_HOPPERHOCK)));
		r.accept(hopperhockPotted, potted(LibBlockNames.SUBTILE_HOPPERHOCK));
		r.accept(hopperhockChibiPotted, potted(chibi(LibBlockNames.SUBTILE_HOPPERHOCK)));

		r.accept(tangleberrie, LibBlockNames.SUBTILE_TANGLEBERRIE);
		r.accept(tangleberrieChibi, chibi(LibBlockNames.SUBTILE_TANGLEBERRIE));
		r.accept(tangleberrieFloating, floating(LibBlockNames.SUBTILE_TANGLEBERRIE));
		r.accept(tangleberrieChibiFloating, chibi(floating(LibBlockNames.SUBTILE_TANGLEBERRIE)));
		r.accept(tangleberriePotted, potted(LibBlockNames.SUBTILE_TANGLEBERRIE));
		r.accept(tangleberrieChibiPotted, potted(chibi(LibBlockNames.SUBTILE_TANGLEBERRIE)));

		r.accept(jiyuulia, LibBlockNames.SUBTILE_JIYUULIA);
		r.accept(jiyuuliaChibi, chibi(LibBlockNames.SUBTILE_JIYUULIA));
		r.accept(jiyuuliaFloating, floating(LibBlockNames.SUBTILE_JIYUULIA));
		r.accept(jiyuuliaChibiFloating, chibi(floating(LibBlockNames.SUBTILE_JIYUULIA)));
		r.accept(jiyuuliaPotted, potted(LibBlockNames.SUBTILE_JIYUULIA));
		r.accept(jiyuuliaChibiPotted, potted(chibi(LibBlockNames.SUBTILE_JIYUULIA)));

		r.accept(rannuncarpus, LibBlockNames.SUBTILE_RANNUNCARPUS);
		r.accept(rannuncarpusChibi, chibi(LibBlockNames.SUBTILE_RANNUNCARPUS));
		r.accept(rannuncarpusFloating, floating(LibBlockNames.SUBTILE_RANNUNCARPUS));
		r.accept(rannuncarpusChibiFloating, chibi(floating(LibBlockNames.SUBTILE_RANNUNCARPUS)));
		r.accept(rannuncarpusPotted, potted(LibBlockNames.SUBTILE_RANNUNCARPUS));
		r.accept(rannuncarpusChibiPotted, potted(chibi(LibBlockNames.SUBTILE_RANNUNCARPUS)));

		r.accept(hyacidus, LibBlockNames.SUBTILE_HYACIDUS);
		r.accept(hyacidusFloating, floating(LibBlockNames.SUBTILE_HYACIDUS));
		r.accept(hyacidusPotted, potted(LibBlockNames.SUBTILE_HYACIDUS));

		r.accept(pollidisiac, LibBlockNames.SUBTILE_POLLIDISIAC);
		r.accept(pollidisiacFloating, floating(LibBlockNames.SUBTILE_POLLIDISIAC));
		r.accept(pollidisiacPotted, potted(LibBlockNames.SUBTILE_POLLIDISIAC));

		r.accept(clayconia, LibBlockNames.SUBTILE_CLAYCONIA);
		r.accept(clayconiaChibi, chibi(LibBlockNames.SUBTILE_CLAYCONIA));
		r.accept(clayconiaFloating, floating(LibBlockNames.SUBTILE_CLAYCONIA));
		r.accept(clayconiaChibiFloating, chibi(floating(LibBlockNames.SUBTILE_CLAYCONIA)));
		r.accept(clayconiaPotted, potted(LibBlockNames.SUBTILE_CLAYCONIA));
		r.accept(clayconiaChibiPotted, potted(chibi(LibBlockNames.SUBTILE_CLAYCONIA)));

		r.accept(loonium, LibBlockNames.SUBTILE_LOONIUM);
		r.accept(looniumFloating, floating(LibBlockNames.SUBTILE_LOONIUM));
		r.accept(looniumPotted, potted(LibBlockNames.SUBTILE_LOONIUM));

		r.accept(daffomill, LibBlockNames.SUBTILE_DAFFOMILL);
		r.accept(daffomillFloating, floating(LibBlockNames.SUBTILE_DAFFOMILL));
		r.accept(daffomillPotted, potted(LibBlockNames.SUBTILE_DAFFOMILL));

		r.accept(vinculotus, LibBlockNames.SUBTILE_VINCULOTUS);
		r.accept(vinculotusFloating, floating(LibBlockNames.SUBTILE_VINCULOTUS));
		r.accept(vinculotusPotted, potted(LibBlockNames.SUBTILE_VINCULOTUS));

		r.accept(spectranthemum, LibBlockNames.SUBTILE_SPECTRANTHEMUM);
		r.accept(spectranthemumFloating, floating(LibBlockNames.SUBTILE_SPECTRANTHEMUM));
		r.accept(spectranthemumPotted, potted(LibBlockNames.SUBTILE_SPECTRANTHEMUM));

		r.accept(medumone, LibBlockNames.SUBTILE_MEDUMONE);
		r.accept(medumoneFloating, floating(LibBlockNames.SUBTILE_MEDUMONE));
		r.accept(medumonePotted, potted(LibBlockNames.SUBTILE_MEDUMONE));

		r.accept(marimorphosis, LibBlockNames.SUBTILE_MARIMORPHOSIS);
		r.accept(marimorphosisChibi, chibi(LibBlockNames.SUBTILE_MARIMORPHOSIS));
		r.accept(marimorphosisFloating, floating(LibBlockNames.SUBTILE_MARIMORPHOSIS));
		r.accept(marimorphosisChibiFloating, chibi(floating(LibBlockNames.SUBTILE_MARIMORPHOSIS)));
		r.accept(marimorphosisPotted, potted(LibBlockNames.SUBTILE_MARIMORPHOSIS));
		r.accept(marimorphosisChibiPotted, potted(chibi(LibBlockNames.SUBTILE_MARIMORPHOSIS)));

		r.accept(bubbell, LibBlockNames.SUBTILE_BUBBELL);
		r.accept(bubbellChibi, chibi(LibBlockNames.SUBTILE_BUBBELL));
		r.accept(bubbellFloating, floating(LibBlockNames.SUBTILE_BUBBELL));
		r.accept(bubbellChibiFloating, chibi(floating(LibBlockNames.SUBTILE_BUBBELL)));
		r.accept(bubbellPotted, potted(LibBlockNames.SUBTILE_BUBBELL));
		r.accept(bubbellChibiPotted, potted(chibi(LibBlockNames.SUBTILE_BUBBELL)));

		r.accept(solegnolia, LibBlockNames.SUBTILE_SOLEGNOLIA);
		r.accept(solegnoliaChibi, chibi(LibBlockNames.SUBTILE_SOLEGNOLIA));
		r.accept(solegnoliaFloating, floating(LibBlockNames.SUBTILE_SOLEGNOLIA));
		r.accept(solegnoliaChibiFloating, chibi(floating(LibBlockNames.SUBTILE_SOLEGNOLIA)));
		r.accept(solegnoliaPotted, potted(LibBlockNames.SUBTILE_SOLEGNOLIA));
		r.accept(solegnoliaChibiPotted, potted(chibi(LibBlockNames.SUBTILE_SOLEGNOLIA)));

		r.accept(orechidIgnem, LibBlockNames.SUBTILE_ORECHID_IGNEM);
		r.accept(orechidIgnemFloating, floating(LibBlockNames.SUBTILE_ORECHID_IGNEM));
		r.accept(orechidIgnemPotted, potted(LibBlockNames.SUBTILE_ORECHID_IGNEM));

		r.accept(labellia, LibBlockNames.SUBTILE_LABELLIA);
		r.accept(labelliaFloating, floating(LibBlockNames.SUBTILE_LABELLIA));
		r.accept(labelliaPotted, potted(LibBlockNames.SUBTILE_LABELLIA));

		r.accept(defaultAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "default"));
		r.accept(deepslateAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "deepslate"));
		r.accept(livingrockAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "livingrock"));
		r.accept(mossyAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "mossy"));
		r.accept(forestAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "forest"));
		r.accept(plainsAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "plains"));
		r.accept(mountainAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "mountain"));
		r.accept(fungalAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "fungal"));
		r.accept(swampAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "swamp"));
		r.accept(desertAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "desert"));
		r.accept(taigaAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "taiga"));
		r.accept(mesaAltar, botaniaRL(LibBlockNames.APOTHECARY_PREFIX + "mesa"));

		r.accept(livingrock, botaniaRL(LibBlockNames.LIVING_ROCK));
		r.accept(livingrockStairs, botaniaRL(LibBlockNames.LIVING_ROCK + STAIR_SUFFIX));
		r.accept(livingrockSlab, botaniaRL(LibBlockNames.LIVING_ROCK + SLAB_SUFFIX));
		r.accept(livingrockWall, botaniaRL(LibBlockNames.LIVING_ROCK + WALL_SUFFIX));
		r.accept(livingrockPolished, botaniaRL(LibBlockNames.LIVING_ROCK_POLISHED));
		r.accept(livingrockPolishedStairs, botaniaRL(LibBlockNames.LIVING_ROCK_POLISHED + STAIR_SUFFIX));
		r.accept(livingrockPolishedSlab, botaniaRL(LibBlockNames.LIVING_ROCK_POLISHED + SLAB_SUFFIX));
		r.accept(livingrockPolishedWall, botaniaRL(LibBlockNames.LIVING_ROCK_POLISHED + WALL_SUFFIX));
		r.accept(livingrockSlate, botaniaRL(LibBlockNames.LIVING_ROCK_SLATE));
		r.accept(livingrockBrick, botaniaRL(LibBlockNames.LIVING_ROCK_BRICK));
		r.accept(livingrockBrickStairs, botaniaRL(LibBlockNames.LIVING_ROCK_BRICK + STAIR_SUFFIX));
		r.accept(livingrockBrickSlab, botaniaRL(LibBlockNames.LIVING_ROCK_BRICK + SLAB_SUFFIX));
		r.accept(livingrockBrickWall, botaniaRL(LibBlockNames.LIVING_ROCK_BRICK + WALL_SUFFIX));
		r.accept(livingrockBrickMossy, botaniaRL(LibBlockNames.LIVING_ROCK_BRICK_MOSSY));
		r.accept(livingrockBrickMossyStairs, botaniaRL(LibBlockNames.LIVING_ROCK_BRICK_MOSSY + STAIR_SUFFIX));
		r.accept(livingrockBrickMossySlab, botaniaRL(LibBlockNames.LIVING_ROCK_BRICK_MOSSY + SLAB_SUFFIX));
		r.accept(livingrockBrickMossyWall, botaniaRL(LibBlockNames.LIVING_ROCK_BRICK_MOSSY + WALL_SUFFIX));
		r.accept(livingrockBrickCracked, botaniaRL(LibBlockNames.LIVING_ROCK_BRICK_CRACKED));
		r.accept(livingrockBrickChiseled, botaniaRL(LibBlockNames.LIVING_ROCK_BRICK_CHISELED));

		r.accept(livingwoodLog, botaniaRL(LibBlockNames.LIVING_WOOD_LOG));
		r.accept(livingwood, botaniaRL(LibBlockNames.LIVING_WOOD));
		r.accept(livingwoodStairs, botaniaRL(LibBlockNames.LIVING_WOOD + STAIR_SUFFIX));
		r.accept(livingwoodSlab, botaniaRL(LibBlockNames.LIVING_WOOD + SLAB_SUFFIX));
		r.accept(livingwoodWall, botaniaRL(LibBlockNames.LIVING_WOOD + WALL_SUFFIX));
		r.accept(livingwoodLogStripped, botaniaRL(LibBlockNames.LIVING_WOOD_LOG_STRIPPED));
		r.accept(livingwoodStripped, botaniaRL(LibBlockNames.LIVING_WOOD_STRIPPED));
		r.accept(livingwoodStrippedStairs, botaniaRL(LibBlockNames.LIVING_WOOD_STRIPPED + STAIR_SUFFIX));
		r.accept(livingwoodStrippedSlab, botaniaRL(LibBlockNames.LIVING_WOOD_STRIPPED + SLAB_SUFFIX));
		r.accept(livingwoodStrippedWall, botaniaRL(LibBlockNames.LIVING_WOOD_STRIPPED + WALL_SUFFIX));
		r.accept(livingwoodLogGlimmering, botaniaRL(LibBlockNames.LIVING_WOOD_LOG_GLIMMERING));
		r.accept(livingwoodGlimmering, botaniaRL(LibBlockNames.LIVING_WOOD_GLIMMERING));
		r.accept(livingwoodLogStrippedGlimmering, botaniaRL(LibBlockNames.LIVING_WOOD_LOG_GLIMMERING_STRIPPED));
		r.accept(livingwoodStrippedGlimmering, botaniaRL(LibBlockNames.LIVING_WOOD_GLIMMERING_STRIPPED));
		r.accept(livingwoodPlanks, botaniaRL(LibBlockNames.LIVING_WOOD_PLANKS));
		r.accept(livingwoodPlankStairs, botaniaRL(LibBlockNames.LIVING_WOOD_PLANKS + STAIR_SUFFIX));
		r.accept(livingwoodPlankSlab, botaniaRL(LibBlockNames.LIVING_WOOD_PLANKS + SLAB_SUFFIX));
		r.accept(livingwoodFence, botaniaRL(LibBlockNames.LIVING_WOOD + FENCE_SUFFIX));
		r.accept(livingwoodFenceGate, botaniaRL(LibBlockNames.LIVING_WOOD + FENCE_GATE_SUFFIX));
		r.accept(livingwoodPlanksMossy, botaniaRL(LibBlockNames.LIVING_WOOD_PLANKS_MOSSY));
		r.accept(livingwoodFramed, botaniaRL(LibBlockNames.LIVING_WOOD_FRAMED));
		r.accept(livingwoodPatternFramed, botaniaRL(LibBlockNames.LIVING_WOOD_PATTERN_FRAMED));

		r.accept(dreamwoodLog, botaniaRL(LibBlockNames.DREAM_WOOD_LOG));
		r.accept(dreamwood, botaniaRL(LibBlockNames.DREAM_WOOD));
		r.accept(dreamwoodStairs, botaniaRL(LibBlockNames.DREAM_WOOD + STAIR_SUFFIX));
		r.accept(dreamwoodSlab, botaniaRL(LibBlockNames.DREAM_WOOD + SLAB_SUFFIX));
		r.accept(dreamwoodWall, botaniaRL(LibBlockNames.DREAM_WOOD + WALL_SUFFIX));
		r.accept(dreamwoodLogStripped, botaniaRL(LibBlockNames.DREAM_WOOD_LOG_STRIPPED));
		r.accept(dreamwoodStripped, botaniaRL(LibBlockNames.DREAM_WOOD_STRIPPED));
		r.accept(dreamwoodStrippedStairs, botaniaRL(LibBlockNames.DREAM_WOOD_STRIPPED + STAIR_SUFFIX));
		r.accept(dreamwoodStrippedSlab, botaniaRL(LibBlockNames.DREAM_WOOD_STRIPPED + SLAB_SUFFIX));
		r.accept(dreamwoodStrippedWall, botaniaRL(LibBlockNames.DREAM_WOOD_STRIPPED + WALL_SUFFIX));
		r.accept(dreamwoodLogGlimmering, botaniaRL(LibBlockNames.DREAM_WOOD_LOG_GLIMMERING));
		r.accept(dreamwoodGlimmering, botaniaRL(LibBlockNames.DREAM_WOOD_GLIMMERING));
		r.accept(dreamwoodLogStrippedGlimmering, botaniaRL(LibBlockNames.DREAM_WOOD_LOG_GLIMMERING_STRIPPED));
		r.accept(dreamwoodStrippedGlimmering, botaniaRL(LibBlockNames.DREAM_WOOD_GLIMMERING_STRIPPED));
		r.accept(dreamwoodPlanks, botaniaRL(LibBlockNames.DREAM_WOOD_PLANKS));
		r.accept(dreamwoodPlankStairs, botaniaRL(LibBlockNames.DREAM_WOOD_PLANKS + STAIR_SUFFIX));
		r.accept(dreamwoodPlankSlab, botaniaRL(LibBlockNames.DREAM_WOOD_PLANKS + SLAB_SUFFIX));
		r.accept(dreamwoodFence, botaniaRL(LibBlockNames.DREAM_WOOD + FENCE_SUFFIX));
		r.accept(dreamwoodFenceGate, botaniaRL(LibBlockNames.DREAM_WOOD + FENCE_GATE_SUFFIX));
		r.accept(dreamwoodPlanksMossy, botaniaRL(LibBlockNames.DREAM_WOOD_PLANKS_MOSSY));
		r.accept(dreamwoodFramed, botaniaRL(LibBlockNames.DREAM_WOOD_FRAMED));
		r.accept(dreamwoodPatternFramed, botaniaRL(LibBlockNames.DREAM_WOOD_PATTERN_FRAMED));

		r.accept(manaSpreader, botaniaRL(LibBlockNames.SPREADER));
		r.accept(redstoneSpreader, botaniaRL(LibBlockNames.SPREADER_REDSTONE));
		r.accept(elvenSpreader, botaniaRL(LibBlockNames.SPREADER_ELVEN));
		r.accept(gaiaSpreader, botaniaRL(LibBlockNames.SPREADER_GAIA));
		r.accept(manaPool, botaniaRL(LibBlockNames.POOL));
		r.accept(creativePool, botaniaRL(LibBlockNames.POOL_CREATIVE));
		r.accept(dilutedPool, botaniaRL(LibBlockNames.POOL_DILUTED));
		r.accept(fabulousPool, botaniaRL(LibBlockNames.POOL_FABULOUS));
		r.accept(alchemyCatalyst, botaniaRL(LibBlockNames.ALCHEMY_CATALYST));
		r.accept(conjurationCatalyst, botaniaRL(LibBlockNames.CONJURATION_CATALYST));
		r.accept(manasteelBlock, botaniaRL(LibBlockNames.MANASTEEL_BLOCK));
		r.accept(terrasteelBlock, botaniaRL(LibBlockNames.TERRASTEEL_BLOCK));
		r.accept(elementiumBlock, botaniaRL(LibBlockNames.ELEMENTIUM_BLOCK));
		r.accept(manaDiamondBlock, botaniaRL(LibBlockNames.MANA_DIAMOND_BLOCK));
		r.accept(dragonstoneBlock, botaniaRL(LibBlockNames.DRAGONSTONE_BLOCK));
		r.accept(manaGlass, botaniaRL(LibBlockNames.MANA_GLASS));
		r.accept(elfGlass, botaniaRL(LibBlockNames.ELF_GLASS));
		r.accept(bifrost, botaniaRL(LibBlockNames.BIFROST));
		r.accept(bifrostPerm, botaniaRL(LibBlockNames.BIFROST_PERM));
		r.accept(runeAltar, botaniaRL(LibBlockNames.RUNE_ALTAR));
		r.accept(enchanter, botaniaRL(LibBlockNames.ENCHANTER));
		r.accept(brewery, botaniaRL(LibBlockNames.BREWERY));
		r.accept(terraPlate, botaniaRL(LibBlockNames.TERRA_PLATE));
		r.accept(alfPortal, botaniaRL(LibBlockNames.ALF_PORTAL));
		r.accept(manaPylon, botaniaRL(LibBlockNames.PYLON));
		r.accept(naturaPylon, botaniaRL(LibBlockNames.PYLON_NATURA));
		r.accept(gaiaPylon, botaniaRL(LibBlockNames.PYLON_GAIA));
		r.accept(distributor, botaniaRL(LibBlockNames.DISTRIBUTOR));
		r.accept(manaVoid, botaniaRL(LibBlockNames.MANA_VOID));
		r.accept(manaDetector, botaniaRL(LibBlockNames.MANA_DETECTOR));
		r.accept(pistonRelay, botaniaRL(LibBlockNames.PISTON_RELAY));
		r.accept(turntable, botaniaRL(LibBlockNames.TURNTABLE));
		r.accept(tinyPlanet, botaniaRL(LibBlockNames.TINY_PLANET));
		r.accept(wildDrum, botaniaRL(LibBlockNames.DRUM_WILD));
		r.accept(gatheringDrum, botaniaRL(LibBlockNames.DRUM_GATHERING));
		r.accept(canopyDrum, botaniaRL(LibBlockNames.DRUM_CANOPY));
		r.accept(spawnerClaw, botaniaRL(LibBlockNames.SPAWNER_CLAW));
		r.accept(rfGenerator, botaniaRL(LibBlockNames.FLUXFIELD));
		r.accept(prism, botaniaRL(LibBlockNames.PRISM));
		r.accept(pump, botaniaRL(LibBlockNames.PUMP));
		r.accept(sparkChanger, botaniaRL(LibBlockNames.SPARK_CHANGER));
		r.accept(manaBomb, botaniaRL(LibBlockNames.MANA_BOMB));
		r.accept(bellows, botaniaRL(LibBlockNames.BELLOWS));
		r.accept(openCrate, botaniaRL(LibBlockNames.OPEN_CRATE));
		r.accept(craftCrate, botaniaRL(LibBlockNames.CRAFT_CRATE));
		r.accept(forestEye, botaniaRL(LibBlockNames.FOREST_EYE));
		r.accept(solidVines, botaniaRL(LibBlockNames.SOLID_VINE));
		r.accept(abstrusePlatform, botaniaRL(LibBlockNames.PLATFORM_ABSTRUSE));
		r.accept(spectralPlatform, botaniaRL(LibBlockNames.PLATFORM_SPECTRAL));
		r.accept(infrangiblePlatform, botaniaRL(LibBlockNames.PLATFORM_INFRANGIBLE));
		r.accept(tinyPotato, botaniaRL(LibBlockNames.TINY_POTATO));
		r.accept(enderEye, botaniaRL(LibBlockNames.ENDER_EYE_BLOCK));
		r.accept(redStringContainer, botaniaRL(LibBlockNames.RED_STRING_CONTAINER));
		r.accept(redStringDispenser, botaniaRL(LibBlockNames.RED_STRING_DISPENSER));
		r.accept(redStringFertilizer, botaniaRL(LibBlockNames.RED_STRING_FERTILIZER));
		r.accept(redStringComparator, botaniaRL(LibBlockNames.RED_STRING_COMPARATOR));
		r.accept(redStringRelay, botaniaRL(LibBlockNames.RED_STRING_RELAY));
		r.accept(redStringInterceptor, botaniaRL(LibBlockNames.RED_STRING_INTERCEPTOR));
		r.accept(corporeaFunnel, botaniaRL(LibBlockNames.CORPOREA_FUNNEL));
		r.accept(corporeaInterceptor, botaniaRL(LibBlockNames.CORPOREA_INTERCEPTOR));
		r.accept(corporeaIndex, botaniaRL(LibBlockNames.CORPOREA_INDEX));
		r.accept(corporeaCrystalCube, botaniaRL(LibBlockNames.CORPOREA_CRYSTAL_CUBE));
		r.accept(corporeaRetainer, botaniaRL(LibBlockNames.CORPOREA_RETAINER));
		r.accept(corporeaBlock, botaniaRL(LibBlockNames.CORPOREA_BLOCK));
		r.accept(corporeaStairs, botaniaRL(LibBlockNames.CORPOREA_STAIRS));
		r.accept(corporeaSlab, botaniaRL(LibBlockNames.CORPOREA_SLAB));
		r.accept(corporeaBrick, botaniaRL(LibBlockNames.CORPOREA_BRICK));
		r.accept(corporeaBrickStairs, botaniaRL(LibBlockNames.CORPOREA_BRICK + LibBlockNames.STAIR_SUFFIX));
		r.accept(corporeaBrickSlab, botaniaRL(LibBlockNames.CORPOREA_BRICK + LibBlockNames.SLAB_SUFFIX));
		r.accept(corporeaBrickWall, botaniaRL(LibBlockNames.CORPOREA_BRICK + LibBlockNames.WALL_SUFFIX));
		r.accept(incensePlate, botaniaRL(LibBlockNames.INCENSE_PLATE));
		r.accept(hourglass, botaniaRL(LibBlockNames.HOURGLASS));
		r.accept(ghostRail, botaniaRL(LibBlockNames.GHOST_RAIL));
		r.accept(lightRelayDefault, botaniaRL(LibBlockNames.LIGHT_RELAY));
		r.accept(lightRelayDetector, botaniaRL("detector" + LibBlockNames.LIGHT_RELAY_SUFFIX));
		r.accept(lightRelayFork, botaniaRL("fork" + LibBlockNames.LIGHT_RELAY_SUFFIX));
		r.accept(lightRelayToggle, botaniaRL("toggle" + LibBlockNames.LIGHT_RELAY_SUFFIX));
		r.accept(lightLauncher, botaniaRL(LibBlockNames.LIGHT_LAUNCHER));
		r.accept(cacophonium, botaniaRL(LibBlockNames.CACOPHONIUM));
		r.accept(cellBlock, botaniaRL(LibBlockNames.CELL_BLOCK));
		r.accept(teruTeruBozu, botaniaRL(LibBlockNames.TERU_TERU_BOZU));
		r.accept(avatar, botaniaRL(LibBlockNames.AVATAR));
		r.accept(fakeAir, botaniaRL(LibBlockNames.FAKE_AIR));
		r.accept(root, botaniaRL(LibBlockNames.ROOT));
		r.accept(felPumpkin, botaniaRL(LibBlockNames.FEL_PUMPKIN));
		r.accept(cocoon, botaniaRL(LibBlockNames.COCOON));
		r.accept(enchantedSoil, botaniaRL(LibBlockNames.ENCHANTED_SOIL));
		r.accept(animatedTorch, botaniaRL(LibBlockNames.ANIMATED_TORCH));
		r.accept(starfield, botaniaRL(LibBlockNames.STARFIELD));
		r.accept(azulejo0, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 0));
		r.accept(azulejo1, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 1));
		r.accept(azulejo2, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 2));
		r.accept(azulejo3, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 3));
		r.accept(azulejo4, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 4));
		r.accept(azulejo5, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 5));
		r.accept(azulejo6, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 6));
		r.accept(azulejo7, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 7));
		r.accept(azulejo8, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 8));
		r.accept(azulejo9, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 9));
		r.accept(azulejo10, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 10));
		r.accept(azulejo11, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 11));
		r.accept(azulejo12, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 12));
		r.accept(azulejo13, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 13));
		r.accept(azulejo14, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 14));
		r.accept(azulejo15, botaniaRL(LibBlockNames.AZULEJO_PREFIX + 15));
		r.accept(manaFlame, botaniaRL(LibBlockNames.MANA_FLAME));
		r.accept(blazeBlock, botaniaRL(LibBlockNames.BLAZE_BLOCK));
		r.accept(gaiaHeadWall, botaniaRL(LibBlockNames.GAIA_WALL_HEAD));
		r.accept(gaiaHead, botaniaRL(LibBlockNames.GAIA_HEAD));
		r.accept(shimmerrock, botaniaRL(LibBlockNames.SHIMMERROCK));
		r.accept(shimmerrockStairs, botaniaRL(LibBlockNames.SHIMMERROCK + STAIR_SUFFIX));
		r.accept(shimmerrockSlab, botaniaRL(LibBlockNames.SHIMMERROCK + SLAB_SUFFIX));
		r.accept(shimmerwoodPlanks, botaniaRL(LibBlockNames.SHIMMERWOOD_PLANKS));
		r.accept(shimmerwoodPlankStairs, botaniaRL(LibBlockNames.SHIMMERWOOD_PLANKS + STAIR_SUFFIX));
		r.accept(shimmerwoodPlankSlab, botaniaRL(LibBlockNames.SHIMMERWOOD_PLANKS + SLAB_SUFFIX));
		r.accept(dryGrass, botaniaRL(BotaniaGrassBlock.Variant.DRY.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(goldenGrass, botaniaRL(BotaniaGrassBlock.Variant.GOLDEN.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(vividGrass, botaniaRL(BotaniaGrassBlock.Variant.VIVID.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(scorchedGrass, botaniaRL(BotaniaGrassBlock.Variant.SCORCHED.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(infusedGrass, botaniaRL(BotaniaGrassBlock.Variant.INFUSED.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(mutatedGrass, botaniaRL(BotaniaGrassBlock.Variant.MUTATED.name().toLowerCase(Locale.ROOT) + LibBlockNames.ALT_GRASS_SUFFIX));
		r.accept(motifDaybloom, botaniaRL(LibBlockNames.MOTIF_DAYBLOOM));
		r.accept(motifNightshade, botaniaRL(LibBlockNames.MOTIF_NIGHTSHADE));
		r.accept(motifHydroangeas, botaniaRL(LibBlockNames.MOTIF_HYDROANGEAS));
		r.accept(pottedMotifDaybloom, botaniaRL(LibBlockNames.POTTED_PREFIX + LibBlockNames.MOTIF_DAYBLOOM));
		r.accept(pottedMotifNightshade, botaniaRL(LibBlockNames.POTTED_PREFIX + LibBlockNames.MOTIF_NIGHTSHADE));
		r.accept(pottedMotifHydroangeas, botaniaRL(LibBlockNames.POTTED_PREFIX + LibBlockNames.MOTIF_HYDROANGEAS));

		r.accept(darkQuartz, botaniaRL(QUARTZ_DARK));
		r.accept(darkQuartzStairs, botaniaRL(QUARTZ_DARK + STAIR_SUFFIX));
		r.accept(darkQuartzSlab, botaniaRL(QUARTZ_DARK + SLAB_SUFFIX));
		r.accept(darkQuartzChiseled, botaniaRL("chiseled_" + QUARTZ_DARK));
		r.accept(darkQuartzPillar, botaniaRL(QUARTZ_DARK + "_pillar"));

		r.accept(manaQuartz, botaniaRL(QUARTZ_MANA));
		r.accept(manaQuartzStairs, botaniaRL(QUARTZ_MANA + STAIR_SUFFIX));
		r.accept(manaQuartzSlab, botaniaRL(QUARTZ_MANA + SLAB_SUFFIX));
		r.accept(manaQuartzChiseled, botaniaRL("chiseled_" + QUARTZ_MANA));
		r.accept(manaQuartzPillar, botaniaRL(QUARTZ_MANA + "_pillar"));

		r.accept(blazeQuartz, botaniaRL(QUARTZ_BLAZE));
		r.accept(blazeQuartzStairs, botaniaRL(QUARTZ_BLAZE + STAIR_SUFFIX));
		r.accept(blazeQuartzSlab, botaniaRL(QUARTZ_BLAZE + SLAB_SUFFIX));
		r.accept(blazeQuartzChiseled, botaniaRL("chiseled_" + QUARTZ_BLAZE));
		r.accept(blazeQuartzPillar, botaniaRL(QUARTZ_BLAZE + "_pillar"));

		r.accept(lavenderQuartz, botaniaRL(QUARTZ_LAVENDER));
		r.accept(lavenderQuartzStairs, botaniaRL(QUARTZ_LAVENDER + STAIR_SUFFIX));
		r.accept(lavenderQuartzSlab, botaniaRL(QUARTZ_LAVENDER + SLAB_SUFFIX));
		r.accept(lavenderQuartzChiseled, botaniaRL("chiseled_" + QUARTZ_LAVENDER));
		r.accept(lavenderQuartzPillar, botaniaRL(QUARTZ_LAVENDER + "_pillar"));

		r.accept(redQuartz, botaniaRL(QUARTZ_RED));
		r.accept(redQuartzStairs, botaniaRL(QUARTZ_RED + STAIR_SUFFIX));
		r.accept(redQuartzSlab, botaniaRL(QUARTZ_RED + SLAB_SUFFIX));
		r.accept(redQuartzChiseled, botaniaRL("chiseled_" + QUARTZ_RED));
		r.accept(redQuartzPillar, botaniaRL(QUARTZ_RED + "_pillar"));

		r.accept(elfQuartz, botaniaRL(QUARTZ_ELF));
		r.accept(elfQuartzStairs, botaniaRL(QUARTZ_ELF + STAIR_SUFFIX));
		r.accept(elfQuartzSlab, botaniaRL(QUARTZ_ELF + SLAB_SUFFIX));
		r.accept(elfQuartzChiseled, botaniaRL("chiseled_" + QUARTZ_ELF));
		r.accept(elfQuartzPillar, botaniaRL(QUARTZ_ELF + "_pillar"));

		r.accept(sunnyQuartz, botaniaRL(QUARTZ_SUNNY));
		r.accept(sunnyQuartzStairs, botaniaRL(QUARTZ_SUNNY + STAIR_SUFFIX));
		r.accept(sunnyQuartzSlab, botaniaRL(QUARTZ_SUNNY + SLAB_SUFFIX));
		r.accept(sunnyQuartzChiseled, botaniaRL("chiseled_" + QUARTZ_SUNNY));
		r.accept(sunnyQuartzPillar, botaniaRL(QUARTZ_SUNNY + "_pillar"));

		r.accept(biomeStoneForest, botaniaRL(METAMORPHIC_PREFIX + "forest_stone"));
		r.accept(biomeStoneForestStairs, botaniaRL(METAMORPHIC_PREFIX + "forest_stone" + STAIR_SUFFIX));
		r.accept(biomeStoneForestSlab, botaniaRL(METAMORPHIC_PREFIX + "forest_stone" + SLAB_SUFFIX));
		r.accept(biomeStoneForestWall, botaniaRL(METAMORPHIC_PREFIX + "forest_stone" + WALL_SUFFIX));
		r.accept(biomeCobblestoneForest, botaniaRL(METAMORPHIC_PREFIX + "forest_cobblestone"));
		r.accept(biomeCobblestoneForestStairs, botaniaRL(METAMORPHIC_PREFIX + "forest_cobblestone" + STAIR_SUFFIX));
		r.accept(biomeCobblestoneForestSlab, botaniaRL(METAMORPHIC_PREFIX + "forest_cobblestone" + SLAB_SUFFIX));
		r.accept(biomeCobblestoneForestWall, botaniaRL(METAMORPHIC_PREFIX + "forest_cobblestone" + WALL_SUFFIX));
		r.accept(biomeBrickForest, botaniaRL(METAMORPHIC_PREFIX + "forest_bricks"));
		r.accept(biomeBrickForestStairs, botaniaRL(METAMORPHIC_PREFIX + "forest_bricks" + STAIR_SUFFIX));
		r.accept(biomeBrickForestSlab, botaniaRL(METAMORPHIC_PREFIX + "forest_bricks" + SLAB_SUFFIX));
		r.accept(biomeBrickForestWall, botaniaRL(METAMORPHIC_PREFIX + "forest_bricks" + WALL_SUFFIX));
		r.accept(biomeChiseledBrickForest, botaniaRL("chiseled_" + METAMORPHIC_PREFIX + "forest_bricks"));

		r.accept(biomeStonePlains, botaniaRL(METAMORPHIC_PREFIX + "plains_stone"));
		r.accept(biomeStonePlainsStairs, botaniaRL(METAMORPHIC_PREFIX + "plains_stone" + STAIR_SUFFIX));
		r.accept(biomeStonePlainsSlab, botaniaRL(METAMORPHIC_PREFIX + "plains_stone" + SLAB_SUFFIX));
		r.accept(biomeStonePlainsWall, botaniaRL(METAMORPHIC_PREFIX + "plains_stone" + WALL_SUFFIX));
		r.accept(biomeCobblestonePlains, botaniaRL(METAMORPHIC_PREFIX + "plains_cobblestone"));
		r.accept(biomeCobblestonePlainsStairs, botaniaRL(METAMORPHIC_PREFIX + "plains_cobblestone" + STAIR_SUFFIX));
		r.accept(biomeCobblestonePlainsSlab, botaniaRL(METAMORPHIC_PREFIX + "plains_cobblestone" + SLAB_SUFFIX));
		r.accept(biomeCobblestonePlainsWall, botaniaRL(METAMORPHIC_PREFIX + "plains_cobblestone" + WALL_SUFFIX));
		r.accept(biomeBrickPlains, botaniaRL(METAMORPHIC_PREFIX + "plains_bricks"));
		r.accept(biomeBrickPlainsStairs, botaniaRL(METAMORPHIC_PREFIX + "plains_bricks" + STAIR_SUFFIX));
		r.accept(biomeBrickPlainsSlab, botaniaRL(METAMORPHIC_PREFIX + "plains_bricks" + SLAB_SUFFIX));
		r.accept(biomeBrickPlainsWall, botaniaRL(METAMORPHIC_PREFIX + "plains_bricks" + WALL_SUFFIX));
		r.accept(biomeChiseledBrickPlains, botaniaRL("chiseled_" + METAMORPHIC_PREFIX + "plains_bricks"));

		r.accept(biomeStoneMountain, botaniaRL(METAMORPHIC_PREFIX + "mountain_stone"));
		r.accept(biomeStoneMountainStairs, botaniaRL(METAMORPHIC_PREFIX + "mountain_stone" + STAIR_SUFFIX));
		r.accept(biomeStoneMountainSlab, botaniaRL(METAMORPHIC_PREFIX + "mountain_stone" + SLAB_SUFFIX));
		r.accept(biomeStoneMountainWall, botaniaRL(METAMORPHIC_PREFIX + "mountain_stone" + WALL_SUFFIX));
		r.accept(biomeCobblestoneMountain, botaniaRL(METAMORPHIC_PREFIX + "mountain_cobblestone"));
		r.accept(biomeCobblestoneMountainStairs, botaniaRL(METAMORPHIC_PREFIX + "mountain_cobblestone" + STAIR_SUFFIX));
		r.accept(biomeCobblestoneMountainSlab, botaniaRL(METAMORPHIC_PREFIX + "mountain_cobblestone" + SLAB_SUFFIX));
		r.accept(biomeCobblestoneMountainWall, botaniaRL(METAMORPHIC_PREFIX + "mountain_cobblestone" + WALL_SUFFIX));
		r.accept(biomeBrickMountain, botaniaRL(METAMORPHIC_PREFIX + "mountain_bricks"));
		r.accept(biomeBrickMountainStairs, botaniaRL(METAMORPHIC_PREFIX + "mountain_bricks" + STAIR_SUFFIX));
		r.accept(biomeBrickMountainSlab, botaniaRL(METAMORPHIC_PREFIX + "mountain_bricks" + SLAB_SUFFIX));
		r.accept(biomeBrickMountainWall, botaniaRL(METAMORPHIC_PREFIX + "mountain_bricks" + WALL_SUFFIX));
		r.accept(biomeChiseledBrickMountain, botaniaRL("chiseled_" + METAMORPHIC_PREFIX + "mountain_bricks"));

		r.accept(biomeStoneFungal, botaniaRL(METAMORPHIC_PREFIX + "fungal_stone"));
		r.accept(biomeStoneFungalStairs, botaniaRL(METAMORPHIC_PREFIX + "fungal_stone" + STAIR_SUFFIX));
		r.accept(biomeStoneFungalSlab, botaniaRL(METAMORPHIC_PREFIX + "fungal_stone" + SLAB_SUFFIX));
		r.accept(biomeStoneFungalWall, botaniaRL(METAMORPHIC_PREFIX + "fungal_stone" + WALL_SUFFIX));
		r.accept(biomeCobblestoneFungal, botaniaRL(METAMORPHIC_PREFIX + "fungal_cobblestone"));
		r.accept(biomeCobblestoneFungalStairs, botaniaRL(METAMORPHIC_PREFIX + "fungal_cobblestone" + STAIR_SUFFIX));
		r.accept(biomeCobblestoneFungalSlab, botaniaRL(METAMORPHIC_PREFIX + "fungal_cobblestone" + SLAB_SUFFIX));
		r.accept(biomeCobblestoneFungalWall, botaniaRL(METAMORPHIC_PREFIX + "fungal_cobblestone" + WALL_SUFFIX));
		r.accept(biomeBrickFungal, botaniaRL(METAMORPHIC_PREFIX + "fungal_bricks"));
		r.accept(biomeBrickFungalStairs, botaniaRL(METAMORPHIC_PREFIX + "fungal_bricks" + STAIR_SUFFIX));
		r.accept(biomeBrickFungalSlab, botaniaRL(METAMORPHIC_PREFIX + "fungal_bricks" + SLAB_SUFFIX));
		r.accept(biomeBrickFungalWall, botaniaRL(METAMORPHIC_PREFIX + "fungal_bricks" + WALL_SUFFIX));
		r.accept(biomeChiseledBrickFungal, botaniaRL("chiseled_" + METAMORPHIC_PREFIX + "fungal_bricks"));

		r.accept(biomeStoneSwamp, botaniaRL(METAMORPHIC_PREFIX + "swamp_stone"));
		r.accept(biomeStoneSwampStairs, botaniaRL(METAMORPHIC_PREFIX + "swamp_stone" + STAIR_SUFFIX));
		r.accept(biomeStoneSwampSlab, botaniaRL(METAMORPHIC_PREFIX + "swamp_stone" + SLAB_SUFFIX));
		r.accept(biomeStoneSwampWall, botaniaRL(METAMORPHIC_PREFIX + "swamp_stone" + WALL_SUFFIX));
		r.accept(biomeCobblestoneSwamp, botaniaRL(METAMORPHIC_PREFIX + "swamp_cobblestone"));
		r.accept(biomeCobblestoneSwampStairs, botaniaRL(METAMORPHIC_PREFIX + "swamp_cobblestone" + STAIR_SUFFIX));
		r.accept(biomeCobblestoneSwampSlab, botaniaRL(METAMORPHIC_PREFIX + "swamp_cobblestone" + SLAB_SUFFIX));
		r.accept(biomeCobblestoneSwampWall, botaniaRL(METAMORPHIC_PREFIX + "swamp_cobblestone" + WALL_SUFFIX));
		r.accept(biomeBrickSwamp, botaniaRL(METAMORPHIC_PREFIX + "swamp_bricks"));
		r.accept(biomeBrickSwampStairs, botaniaRL(METAMORPHIC_PREFIX + "swamp_bricks" + STAIR_SUFFIX));
		r.accept(biomeBrickSwampSlab, botaniaRL(METAMORPHIC_PREFIX + "swamp_bricks" + SLAB_SUFFIX));
		r.accept(biomeBrickSwampWall, botaniaRL(METAMORPHIC_PREFIX + "swamp_bricks" + WALL_SUFFIX));
		r.accept(biomeChiseledBrickSwamp, botaniaRL("chiseled_" + METAMORPHIC_PREFIX + "swamp_bricks"));

		r.accept(biomeStoneDesert, botaniaRL(METAMORPHIC_PREFIX + "desert_stone"));
		r.accept(biomeStoneDesertStairs, botaniaRL(METAMORPHIC_PREFIX + "desert_stone" + STAIR_SUFFIX));
		r.accept(biomeStoneDesertSlab, botaniaRL(METAMORPHIC_PREFIX + "desert_stone" + SLAB_SUFFIX));
		r.accept(biomeStoneDesertWall, botaniaRL(METAMORPHIC_PREFIX + "desert_stone" + WALL_SUFFIX));
		r.accept(biomeCobblestoneDesert, botaniaRL(METAMORPHIC_PREFIX + "desert_cobblestone"));
		r.accept(biomeCobblestoneDesertStairs, botaniaRL(METAMORPHIC_PREFIX + "desert_cobblestone" + STAIR_SUFFIX));
		r.accept(biomeCobblestoneDesertSlab, botaniaRL(METAMORPHIC_PREFIX + "desert_cobblestone" + SLAB_SUFFIX));
		r.accept(biomeCobblestoneDesertWall, botaniaRL(METAMORPHIC_PREFIX + "desert_cobblestone" + WALL_SUFFIX));
		r.accept(biomeBrickDesert, botaniaRL(METAMORPHIC_PREFIX + "desert_bricks"));
		r.accept(biomeBrickDesertStairs, botaniaRL(METAMORPHIC_PREFIX + "desert_bricks" + STAIR_SUFFIX));
		r.accept(biomeBrickDesertSlab, botaniaRL(METAMORPHIC_PREFIX + "desert_bricks" + SLAB_SUFFIX));
		r.accept(biomeBrickDesertWall, botaniaRL(METAMORPHIC_PREFIX + "desert_bricks" + WALL_SUFFIX));
		r.accept(biomeChiseledBrickDesert, botaniaRL("chiseled_" + METAMORPHIC_PREFIX + "desert_bricks"));

		r.accept(biomeStoneTaiga, botaniaRL(METAMORPHIC_PREFIX + "taiga_stone"));
		r.accept(biomeStoneTaigaStairs, botaniaRL(METAMORPHIC_PREFIX + "taiga_stone" + STAIR_SUFFIX));
		r.accept(biomeStoneTaigaSlab, botaniaRL(METAMORPHIC_PREFIX + "taiga_stone" + SLAB_SUFFIX));
		r.accept(biomeStoneTaigaWall, botaniaRL(METAMORPHIC_PREFIX + "taiga_stone" + WALL_SUFFIX));
		r.accept(biomeCobblestoneTaiga, botaniaRL(METAMORPHIC_PREFIX + "taiga_cobblestone"));
		r.accept(biomeCobblestoneTaigaStairs, botaniaRL(METAMORPHIC_PREFIX + "taiga_cobblestone" + STAIR_SUFFIX));
		r.accept(biomeCobblestoneTaigaSlab, botaniaRL(METAMORPHIC_PREFIX + "taiga_cobblestone" + SLAB_SUFFIX));
		r.accept(biomeCobblestoneTaigaWall, botaniaRL(METAMORPHIC_PREFIX + "taiga_cobblestone" + WALL_SUFFIX));
		r.accept(biomeBrickTaiga, botaniaRL(METAMORPHIC_PREFIX + "taiga_bricks"));
		r.accept(biomeBrickTaigaStairs, botaniaRL(METAMORPHIC_PREFIX + "taiga_bricks" + STAIR_SUFFIX));
		r.accept(biomeBrickTaigaSlab, botaniaRL(METAMORPHIC_PREFIX + "taiga_bricks" + SLAB_SUFFIX));
		r.accept(biomeBrickTaigaWall, botaniaRL(METAMORPHIC_PREFIX + "taiga_bricks" + WALL_SUFFIX));
		r.accept(biomeChiseledBrickTaiga, botaniaRL("chiseled_" + METAMORPHIC_PREFIX + "taiga_bricks"));

		r.accept(biomeStoneMesa, botaniaRL(METAMORPHIC_PREFIX + "mesa_stone"));
		r.accept(biomeStoneMesaStairs, botaniaRL(METAMORPHIC_PREFIX + "mesa_stone" + STAIR_SUFFIX));
		r.accept(biomeStoneMesaSlab, botaniaRL(METAMORPHIC_PREFIX + "mesa_stone" + SLAB_SUFFIX));
		r.accept(biomeStoneMesaWall, botaniaRL(METAMORPHIC_PREFIX + "mesa_stone" + WALL_SUFFIX));
		r.accept(biomeCobblestoneMesa, botaniaRL(METAMORPHIC_PREFIX + "mesa_cobblestone"));
		r.accept(biomeCobblestoneMesaStairs, botaniaRL(METAMORPHIC_PREFIX + "mesa_cobblestone" + STAIR_SUFFIX));
		r.accept(biomeCobblestoneMesaSlab, botaniaRL(METAMORPHIC_PREFIX + "mesa_cobblestone" + SLAB_SUFFIX));
		r.accept(biomeCobblestoneMesaWall, botaniaRL(METAMORPHIC_PREFIX + "mesa_cobblestone" + WALL_SUFFIX));
		r.accept(biomeBrickMesa, botaniaRL(METAMORPHIC_PREFIX + "mesa_bricks"));
		r.accept(biomeBrickMesaStairs, botaniaRL(METAMORPHIC_PREFIX + "mesa_bricks" + STAIR_SUFFIX));
		r.accept(biomeBrickMesaSlab, botaniaRL(METAMORPHIC_PREFIX + "mesa_bricks" + SLAB_SUFFIX));
		r.accept(biomeBrickMesaWall, botaniaRL(METAMORPHIC_PREFIX + "mesa_bricks" + WALL_SUFFIX));
		r.accept(biomeChiseledBrickMesa, botaniaRL("chiseled_" + METAMORPHIC_PREFIX + "mesa_bricks"));

		r.accept(whitePavement, botaniaRL("white" + PAVEMENT_SUFFIX));
		r.accept(whitePavementStair, botaniaRL("white" + PAVEMENT_SUFFIX + STAIR_SUFFIX));
		r.accept(whitePavementSlab, botaniaRL("white" + PAVEMENT_SUFFIX + SLAB_SUFFIX));

		r.accept(blackPavement, botaniaRL("black" + PAVEMENT_SUFFIX));
		r.accept(blackPavementStair, botaniaRL("black" + PAVEMENT_SUFFIX + STAIR_SUFFIX));
		r.accept(blackPavementSlab, botaniaRL("black" + PAVEMENT_SUFFIX + SLAB_SUFFIX));

		r.accept(bluePavement, botaniaRL("blue" + PAVEMENT_SUFFIX));
		r.accept(bluePavementStair, botaniaRL("blue" + PAVEMENT_SUFFIX + STAIR_SUFFIX));
		r.accept(bluePavementSlab, botaniaRL("blue" + PAVEMENT_SUFFIX + SLAB_SUFFIX));

		r.accept(yellowPavement, botaniaRL("yellow" + PAVEMENT_SUFFIX));
		r.accept(yellowPavementStair, botaniaRL("yellow" + PAVEMENT_SUFFIX + STAIR_SUFFIX));
		r.accept(yellowPavementSlab, botaniaRL("yellow" + PAVEMENT_SUFFIX + SLAB_SUFFIX));

		r.accept(redPavement, botaniaRL("red" + PAVEMENT_SUFFIX));
		r.accept(redPavementStair, botaniaRL("red" + PAVEMENT_SUFFIX + STAIR_SUFFIX));
		r.accept(redPavementSlab, botaniaRL("red" + PAVEMENT_SUFFIX + SLAB_SUFFIX));

		r.accept(greenPavement, botaniaRL("green" + PAVEMENT_SUFFIX));
		r.accept(greenPavementStair, botaniaRL("green" + PAVEMENT_SUFFIX + STAIR_SUFFIX));
		r.accept(greenPavementSlab, botaniaRL("green" + PAVEMENT_SUFFIX + SLAB_SUFFIX));

		r.accept(managlassPane, botaniaRL(LibBlockNames.MANA_GLASS + "_pane"));
		r.accept(alfglassPane, botaniaRL(LibBlockNames.ELF_GLASS + "_pane"));
		r.accept(bifrostPane, botaniaRL(LibBlockNames.BIFROST + "_pane"));
	}

	public static void registerItemBlocks(BiConsumer<Item, ResourceLocation> r) {
		Item.Properties props = BotaniaItems.defaultBuilder();
		Stream.<Function<DyeColor, Block>>of(
				BotaniaBlocks::getFlower,
				BotaniaBlocks::getShinyFlower,
				BotaniaBlocks::getFloatingFlower,
				BotaniaBlocks::getPetalBlock,
				BotaniaBlocks::getMushroom,
				BotaniaBlocks::getDoubleFlower
		).forEach(
				blockGetter -> ColorHelper.supportedColors().map(blockGetter).forEach(
						block -> r.accept(new BlockItem(block, props), BuiltInRegistries.BLOCK.getKey(block))
				)
		);

		r.accept(new SpecialFlowerBlockItem(pureDaisy, props), BuiltInRegistries.BLOCK.getKey(pureDaisy));
		r.accept(new SpecialFlowerBlockItem(pureDaisyFloating, props), BuiltInRegistries.BLOCK.getKey(pureDaisyFloating));

		r.accept(new SpecialFlowerBlockItem(manastar, props), BuiltInRegistries.BLOCK.getKey(manastar));
		r.accept(new SpecialFlowerBlockItem(manastarFloating, props), BuiltInRegistries.BLOCK.getKey(manastarFloating));

		r.accept(new SpecialFlowerBlockItem(hydroangeas, props), BuiltInRegistries.BLOCK.getKey(hydroangeas));
		r.accept(new SpecialFlowerBlockItem(hydroangeasFloating, props), BuiltInRegistries.BLOCK.getKey(hydroangeasFloating));

		r.accept(new SpecialFlowerBlockItem(endoflame, props), BuiltInRegistries.BLOCK.getKey(endoflame));
		r.accept(new SpecialFlowerBlockItem(endoflameFloating, props), BuiltInRegistries.BLOCK.getKey(endoflameFloating));

		r.accept(new SpecialFlowerBlockItem(thermalily, props), BuiltInRegistries.BLOCK.getKey(thermalily));
		r.accept(new SpecialFlowerBlockItem(thermalilyFloating, props), BuiltInRegistries.BLOCK.getKey(thermalilyFloating));

		r.accept(new SpecialFlowerBlockItem(rosaArcana, props), BuiltInRegistries.BLOCK.getKey(rosaArcana));
		r.accept(new SpecialFlowerBlockItem(rosaArcanaFloating, props), BuiltInRegistries.BLOCK.getKey(rosaArcanaFloating));

		r.accept(new SpecialFlowerBlockItem(munchdew, props), BuiltInRegistries.BLOCK.getKey(munchdew));
		r.accept(new SpecialFlowerBlockItem(munchdewFloating, props), BuiltInRegistries.BLOCK.getKey(munchdewFloating));

		r.accept(new SpecialFlowerBlockItem(entropinnyum, props), BuiltInRegistries.BLOCK.getKey(entropinnyum));
		r.accept(new SpecialFlowerBlockItem(entropinnyumFloating, props), BuiltInRegistries.BLOCK.getKey(entropinnyumFloating));

		r.accept(new SpecialFlowerBlockItem(kekimurus, props), BuiltInRegistries.BLOCK.getKey(kekimurus));
		r.accept(new SpecialFlowerBlockItem(kekimurusFloating, props), BuiltInRegistries.BLOCK.getKey(kekimurusFloating));

		r.accept(new SpecialFlowerBlockItem(gourmaryllis, props), BuiltInRegistries.BLOCK.getKey(gourmaryllis));
		r.accept(new SpecialFlowerBlockItem(gourmaryllisFloating, props), BuiltInRegistries.BLOCK.getKey(gourmaryllisFloating));

		r.accept(new SpecialFlowerBlockItem(narslimmus, props), BuiltInRegistries.BLOCK.getKey(narslimmus));
		r.accept(new SpecialFlowerBlockItem(narslimmusFloating, props), BuiltInRegistries.BLOCK.getKey(narslimmusFloating));

		r.accept(new SpecialFlowerBlockItem(spectrolus, props), BuiltInRegistries.BLOCK.getKey(spectrolus));
		r.accept(new SpecialFlowerBlockItem(spectrolusFloating, props), BuiltInRegistries.BLOCK.getKey(spectrolusFloating));

		r.accept(new SpecialFlowerBlockItem(dandelifeon, props), BuiltInRegistries.BLOCK.getKey(dandelifeon));
		r.accept(new SpecialFlowerBlockItem(dandelifeonFloating, props), BuiltInRegistries.BLOCK.getKey(dandelifeonFloating));

		r.accept(new SpecialFlowerBlockItem(rafflowsia, props), BuiltInRegistries.BLOCK.getKey(rafflowsia));
		r.accept(new SpecialFlowerBlockItem(rafflowsiaFloating, props), BuiltInRegistries.BLOCK.getKey(rafflowsiaFloating));

		r.accept(new SpecialFlowerBlockItem(shulkMeNot, props), BuiltInRegistries.BLOCK.getKey(shulkMeNot));
		r.accept(new SpecialFlowerBlockItem(shulkMeNotFloating, props), BuiltInRegistries.BLOCK.getKey(shulkMeNotFloating));

		r.accept(new SpecialFlowerBlockItem(bellethorn, props), BuiltInRegistries.BLOCK.getKey(bellethorn));
		r.accept(new SpecialFlowerBlockItem(bellethornChibi, props), BuiltInRegistries.BLOCK.getKey(bellethornChibi));
		r.accept(new SpecialFlowerBlockItem(bellethornFloating, props), BuiltInRegistries.BLOCK.getKey(bellethornFloating));
		r.accept(new SpecialFlowerBlockItem(bellethornChibiFloating, props), BuiltInRegistries.BLOCK.getKey(bellethornChibiFloating));

		r.accept(new SpecialFlowerBlockItem(bergamute, props), BuiltInRegistries.BLOCK.getKey(bergamute));
		r.accept(new SpecialFlowerBlockItem(bergamuteFloating, props), BuiltInRegistries.BLOCK.getKey(bergamuteFloating));

		r.accept(new SpecialFlowerBlockItem(dreadthorn, props), BuiltInRegistries.BLOCK.getKey(dreadthorn));
		r.accept(new SpecialFlowerBlockItem(dreadthornFloating, props), BuiltInRegistries.BLOCK.getKey(dreadthornFloating));

		r.accept(new SpecialFlowerBlockItem(heiseiDream, props), BuiltInRegistries.BLOCK.getKey(heiseiDream));
		r.accept(new SpecialFlowerBlockItem(heiseiDreamFloating, props), BuiltInRegistries.BLOCK.getKey(heiseiDreamFloating));

		r.accept(new SpecialFlowerBlockItem(tigerseye, props), BuiltInRegistries.BLOCK.getKey(tigerseye));
		r.accept(new SpecialFlowerBlockItem(tigerseyeFloating, props), BuiltInRegistries.BLOCK.getKey(tigerseyeFloating));

		r.accept(new SpecialFlowerBlockItem(jadedAmaranthus, props), BuiltInRegistries.BLOCK.getKey(jadedAmaranthus));
		r.accept(new SpecialFlowerBlockItem(jadedAmaranthusFloating, props), BuiltInRegistries.BLOCK.getKey(jadedAmaranthusFloating));

		r.accept(new SpecialFlowerBlockItem(orechid, props), BuiltInRegistries.BLOCK.getKey(orechid));
		r.accept(new SpecialFlowerBlockItem(orechidFloating, props), BuiltInRegistries.BLOCK.getKey(orechidFloating));

		r.accept(new SpecialFlowerBlockItem(fallenKanade, props), BuiltInRegistries.BLOCK.getKey(fallenKanade));
		r.accept(new SpecialFlowerBlockItem(fallenKanadeFloating, props), BuiltInRegistries.BLOCK.getKey(fallenKanadeFloating));

		r.accept(new SpecialFlowerBlockItem(exoflame, props), BuiltInRegistries.BLOCK.getKey(exoflame));
		r.accept(new SpecialFlowerBlockItem(exoflameFloating, props), BuiltInRegistries.BLOCK.getKey(exoflameFloating));

		r.accept(new SpecialFlowerBlockItem(agricarnation, props), BuiltInRegistries.BLOCK.getKey(agricarnation));
		r.accept(new SpecialFlowerBlockItem(agricarnationChibi, props), BuiltInRegistries.BLOCK.getKey(agricarnationChibi));
		r.accept(new SpecialFlowerBlockItem(agricarnationFloating, props), BuiltInRegistries.BLOCK.getKey(agricarnationFloating));
		r.accept(new SpecialFlowerBlockItem(agricarnationChibiFloating, props), BuiltInRegistries.BLOCK.getKey(agricarnationChibiFloating));

		r.accept(new SpecialFlowerBlockItem(hopperhock, props), BuiltInRegistries.BLOCK.getKey(hopperhock));
		r.accept(new SpecialFlowerBlockItem(hopperhockChibi, props), BuiltInRegistries.BLOCK.getKey(hopperhockChibi));
		r.accept(new SpecialFlowerBlockItem(hopperhockFloating, props), BuiltInRegistries.BLOCK.getKey(hopperhockFloating));
		r.accept(new SpecialFlowerBlockItem(hopperhockChibiFloating, props), BuiltInRegistries.BLOCK.getKey(hopperhockChibiFloating));

		r.accept(new SpecialFlowerBlockItem(tangleberrie, props), BuiltInRegistries.BLOCK.getKey(tangleberrie));
		r.accept(new SpecialFlowerBlockItem(tangleberrieChibi, props), BuiltInRegistries.BLOCK.getKey(tangleberrieChibi));
		r.accept(new SpecialFlowerBlockItem(tangleberrieFloating, props), BuiltInRegistries.BLOCK.getKey(tangleberrieFloating));
		r.accept(new SpecialFlowerBlockItem(tangleberrieChibiFloating, props), BuiltInRegistries.BLOCK.getKey(tangleberrieChibiFloating));

		r.accept(new SpecialFlowerBlockItem(jiyuulia, props), BuiltInRegistries.BLOCK.getKey(jiyuulia));
		r.accept(new SpecialFlowerBlockItem(jiyuuliaChibi, props), BuiltInRegistries.BLOCK.getKey(jiyuuliaChibi));
		r.accept(new SpecialFlowerBlockItem(jiyuuliaFloating, props), BuiltInRegistries.BLOCK.getKey(jiyuuliaFloating));
		r.accept(new SpecialFlowerBlockItem(jiyuuliaChibiFloating, props), BuiltInRegistries.BLOCK.getKey(jiyuuliaChibiFloating));

		r.accept(new SpecialFlowerBlockItem(rannuncarpus, props), BuiltInRegistries.BLOCK.getKey(rannuncarpus));
		r.accept(new SpecialFlowerBlockItem(rannuncarpusChibi, props), BuiltInRegistries.BLOCK.getKey(rannuncarpusChibi));
		r.accept(new SpecialFlowerBlockItem(rannuncarpusFloating, props), BuiltInRegistries.BLOCK.getKey(rannuncarpusFloating));
		r.accept(new SpecialFlowerBlockItem(rannuncarpusChibiFloating, props), BuiltInRegistries.BLOCK.getKey(rannuncarpusChibiFloating));

		r.accept(new SpecialFlowerBlockItem(hyacidus, props), BuiltInRegistries.BLOCK.getKey(hyacidus));
		r.accept(new SpecialFlowerBlockItem(hyacidusFloating, props), BuiltInRegistries.BLOCK.getKey(hyacidusFloating));

		r.accept(new SpecialFlowerBlockItem(pollidisiac, props), BuiltInRegistries.BLOCK.getKey(pollidisiac));
		r.accept(new SpecialFlowerBlockItem(pollidisiacFloating, props), BuiltInRegistries.BLOCK.getKey(pollidisiacFloating));

		r.accept(new SpecialFlowerBlockItem(clayconia, props), BuiltInRegistries.BLOCK.getKey(clayconia));
		r.accept(new SpecialFlowerBlockItem(clayconiaChibi, props), BuiltInRegistries.BLOCK.getKey(clayconiaChibi));
		r.accept(new SpecialFlowerBlockItem(clayconiaFloating, props), BuiltInRegistries.BLOCK.getKey(clayconiaFloating));
		r.accept(new SpecialFlowerBlockItem(clayconiaChibiFloating, props), BuiltInRegistries.BLOCK.getKey(clayconiaChibiFloating));

		r.accept(new SpecialFlowerBlockItem(loonium, props), BuiltInRegistries.BLOCK.getKey(loonium));
		r.accept(new SpecialFlowerBlockItem(looniumFloating, props), BuiltInRegistries.BLOCK.getKey(looniumFloating));

		r.accept(new SpecialFlowerBlockItem(daffomill, props), BuiltInRegistries.BLOCK.getKey(daffomill));
		r.accept(new SpecialFlowerBlockItem(daffomillFloating, props), BuiltInRegistries.BLOCK.getKey(daffomillFloating));

		r.accept(new SpecialFlowerBlockItem(vinculotus, props), BuiltInRegistries.BLOCK.getKey(vinculotus));
		r.accept(new SpecialFlowerBlockItem(vinculotusFloating, props), BuiltInRegistries.BLOCK.getKey(vinculotusFloating));

		r.accept(new SpecialFlowerBlockItem(spectranthemum, props), BuiltInRegistries.BLOCK.getKey(spectranthemum));
		r.accept(new SpecialFlowerBlockItem(spectranthemumFloating, props), BuiltInRegistries.BLOCK.getKey(spectranthemumFloating));

		r.accept(new SpecialFlowerBlockItem(medumone, props), BuiltInRegistries.BLOCK.getKey(medumone));
		r.accept(new SpecialFlowerBlockItem(medumoneFloating, props), BuiltInRegistries.BLOCK.getKey(medumoneFloating));

		r.accept(new SpecialFlowerBlockItem(marimorphosis, props), BuiltInRegistries.BLOCK.getKey(marimorphosis));
		r.accept(new SpecialFlowerBlockItem(marimorphosisChibi, props), BuiltInRegistries.BLOCK.getKey(marimorphosisChibi));
		r.accept(new SpecialFlowerBlockItem(marimorphosisFloating, props), BuiltInRegistries.BLOCK.getKey(marimorphosisFloating));
		r.accept(new SpecialFlowerBlockItem(marimorphosisChibiFloating, props), BuiltInRegistries.BLOCK.getKey(marimorphosisChibiFloating));

		r.accept(new SpecialFlowerBlockItem(bubbell, props), BuiltInRegistries.BLOCK.getKey(bubbell));
		r.accept(new SpecialFlowerBlockItem(bubbellChibi, props), BuiltInRegistries.BLOCK.getKey(bubbellChibi));
		r.accept(new SpecialFlowerBlockItem(bubbellFloating, props), BuiltInRegistries.BLOCK.getKey(bubbellFloating));
		r.accept(new SpecialFlowerBlockItem(bubbellChibiFloating, props), BuiltInRegistries.BLOCK.getKey(bubbellChibiFloating));

		r.accept(new SpecialFlowerBlockItem(solegnolia, props), BuiltInRegistries.BLOCK.getKey(solegnolia));
		r.accept(new SpecialFlowerBlockItem(solegnoliaChibi, props), BuiltInRegistries.BLOCK.getKey(solegnoliaChibi));
		r.accept(new SpecialFlowerBlockItem(solegnoliaFloating, props), BuiltInRegistries.BLOCK.getKey(solegnoliaFloating));
		r.accept(new SpecialFlowerBlockItem(solegnoliaChibiFloating, props), BuiltInRegistries.BLOCK.getKey(solegnoliaChibiFloating));

		r.accept(new SpecialFlowerBlockItem(orechidIgnem, props), BuiltInRegistries.BLOCK.getKey(orechidIgnem));
		r.accept(new SpecialFlowerBlockItem(orechidIgnemFloating, props), BuiltInRegistries.BLOCK.getKey(orechidIgnemFloating));

		r.accept(new SpecialFlowerBlockItem(labellia, props), BuiltInRegistries.BLOCK.getKey(labellia));
		r.accept(new SpecialFlowerBlockItem(labelliaFloating, props), BuiltInRegistries.BLOCK.getKey(labelliaFloating));

		r.accept(new BlockItem(defaultAltar, props), BuiltInRegistries.BLOCK.getKey(defaultAltar));
		r.accept(new BlockItem(deepslateAltar, props), BuiltInRegistries.BLOCK.getKey(deepslateAltar));
		r.accept(new BlockItem(livingrockAltar, props), BuiltInRegistries.BLOCK.getKey(livingrockAltar));
		r.accept(new BlockItem(mossyAltar, props), BuiltInRegistries.BLOCK.getKey(mossyAltar));
		r.accept(new BlockItem(forestAltar, props), BuiltInRegistries.BLOCK.getKey(forestAltar));
		r.accept(new BlockItem(plainsAltar, props), BuiltInRegistries.BLOCK.getKey(plainsAltar));
		r.accept(new BlockItem(mountainAltar, props), BuiltInRegistries.BLOCK.getKey(mountainAltar));
		r.accept(new BlockItem(fungalAltar, props), BuiltInRegistries.BLOCK.getKey(fungalAltar));
		r.accept(new BlockItem(swampAltar, props), BuiltInRegistries.BLOCK.getKey(swampAltar));
		r.accept(new BlockItem(desertAltar, props), BuiltInRegistries.BLOCK.getKey(desertAltar));
		r.accept(new BlockItem(taigaAltar, props), BuiltInRegistries.BLOCK.getKey(taigaAltar));
		r.accept(new BlockItem(mesaAltar, props), BuiltInRegistries.BLOCK.getKey(mesaAltar));

		r.accept(new BlockItem(livingrock, props), BuiltInRegistries.BLOCK.getKey(livingrock));
		r.accept(new BlockItem(livingrockStairs, props), BuiltInRegistries.BLOCK.getKey(livingrockStairs));
		r.accept(new BlockItem(livingrockSlab, props), BuiltInRegistries.BLOCK.getKey(livingrockSlab));
		r.accept(new BlockItem(livingrockWall, props), BuiltInRegistries.BLOCK.getKey(livingrockWall));
		r.accept(new BlockItem(livingrockPolished, props), BuiltInRegistries.BLOCK.getKey(livingrockPolished));
		r.accept(new BlockItem(livingrockPolishedStairs, props), BuiltInRegistries.BLOCK.getKey(livingrockPolishedStairs));
		r.accept(new BlockItem(livingrockPolishedSlab, props), BuiltInRegistries.BLOCK.getKey(livingrockPolishedSlab));
		r.accept(new BlockItem(livingrockPolishedWall, props), BuiltInRegistries.BLOCK.getKey(livingrockPolishedWall));
		r.accept(new BlockItem(livingrockSlate, props), BuiltInRegistries.BLOCK.getKey(livingrockSlate));
		r.accept(new BlockItem(livingrockBrick, props), BuiltInRegistries.BLOCK.getKey(livingrockBrick));
		r.accept(new BlockItem(livingrockBrickStairs, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickStairs));
		r.accept(new BlockItem(livingrockBrickSlab, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickSlab));
		r.accept(new BlockItem(livingrockBrickWall, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickWall));
		r.accept(new BlockItem(livingrockBrickMossy, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickMossy));
		r.accept(new BlockItem(livingrockBrickMossyStairs, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickMossyStairs));
		r.accept(new BlockItem(livingrockBrickMossySlab, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickMossySlab));
		r.accept(new BlockItem(livingrockBrickMossyWall, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickMossyWall));
		r.accept(new BlockItem(livingrockBrickCracked, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickCracked));
		r.accept(new BlockItem(livingrockBrickChiseled, props), BuiltInRegistries.BLOCK.getKey(livingrockBrickChiseled));

		r.accept(new BlockItem(livingwoodLog, props), BuiltInRegistries.BLOCK.getKey(livingwoodLog));
		r.accept(new BlockItem(livingwood, props), BuiltInRegistries.BLOCK.getKey(livingwood));
		r.accept(new BlockItem(livingwoodStairs, props), BuiltInRegistries.BLOCK.getKey(livingwoodStairs));
		r.accept(new BlockItem(livingwoodSlab, props), BuiltInRegistries.BLOCK.getKey(livingwoodSlab));
		r.accept(new BlockItem(livingwoodWall, props), BuiltInRegistries.BLOCK.getKey(livingwoodWall));
		r.accept(new BlockItem(livingwoodLogStripped, props), BuiltInRegistries.BLOCK.getKey(livingwoodLogStripped));
		r.accept(new BlockItem(livingwoodStripped, props), BuiltInRegistries.BLOCK.getKey(livingwoodStripped));
		r.accept(new BlockItem(livingwoodStrippedStairs, props), BuiltInRegistries.BLOCK.getKey(livingwoodStrippedStairs));
		r.accept(new BlockItem(livingwoodStrippedSlab, props), BuiltInRegistries.BLOCK.getKey(livingwoodStrippedSlab));
		r.accept(new BlockItem(livingwoodStrippedWall, props), BuiltInRegistries.BLOCK.getKey(livingwoodStrippedWall));
		r.accept(new BlockItem(livingwoodLogGlimmering, props), BuiltInRegistries.BLOCK.getKey(livingwoodLogGlimmering));
		r.accept(new BlockItem(livingwoodGlimmering, props), BuiltInRegistries.BLOCK.getKey(livingwoodGlimmering));
		r.accept(new BlockItem(livingwoodLogStrippedGlimmering, props), BuiltInRegistries.BLOCK.getKey(livingwoodLogStrippedGlimmering));
		r.accept(new BlockItem(livingwoodStrippedGlimmering, props), BuiltInRegistries.BLOCK.getKey(livingwoodStrippedGlimmering));
		r.accept(new BlockItem(livingwoodPlanks, props), BuiltInRegistries.BLOCK.getKey(livingwoodPlanks));
		r.accept(new BlockItem(livingwoodPlankStairs, props), BuiltInRegistries.BLOCK.getKey(livingwoodPlankStairs));
		r.accept(new BlockItem(livingwoodPlankSlab, props), BuiltInRegistries.BLOCK.getKey(livingwoodPlankSlab));
		r.accept(new BlockItem(livingwoodFence, props), BuiltInRegistries.BLOCK.getKey(livingwoodFence));
		r.accept(new BlockItem(livingwoodFenceGate, props), BuiltInRegistries.BLOCK.getKey(livingwoodFenceGate));
		r.accept(new BlockItem(livingwoodPlanksMossy, props), BuiltInRegistries.BLOCK.getKey(livingwoodPlanksMossy));
		r.accept(new BlockItem(livingwoodFramed, props), BuiltInRegistries.BLOCK.getKey(livingwoodFramed));
		r.accept(new BlockItem(livingwoodPatternFramed, props), BuiltInRegistries.BLOCK.getKey(livingwoodPatternFramed));

		r.accept(new BlockItem(dreamwoodLog, props), BuiltInRegistries.BLOCK.getKey(dreamwoodLog));
		r.accept(new BlockItem(dreamwood, props), BuiltInRegistries.BLOCK.getKey(dreamwood));
		r.accept(new BlockItem(dreamwoodStairs, props), BuiltInRegistries.BLOCK.getKey(dreamwoodStairs));
		r.accept(new BlockItem(dreamwoodSlab, props), BuiltInRegistries.BLOCK.getKey(dreamwoodSlab));
		r.accept(new BlockItem(dreamwoodWall, props), BuiltInRegistries.BLOCK.getKey(dreamwoodWall));
		r.accept(new BlockItem(dreamwoodLogStripped, props), BuiltInRegistries.BLOCK.getKey(dreamwoodLogStripped));
		r.accept(new BlockItem(dreamwoodStripped, props), BuiltInRegistries.BLOCK.getKey(dreamwoodStripped));
		r.accept(new BlockItem(dreamwoodStrippedStairs, props), BuiltInRegistries.BLOCK.getKey(dreamwoodStrippedStairs));
		r.accept(new BlockItem(dreamwoodStrippedSlab, props), BuiltInRegistries.BLOCK.getKey(dreamwoodStrippedSlab));
		r.accept(new BlockItem(dreamwoodStrippedWall, props), BuiltInRegistries.BLOCK.getKey(dreamwoodStrippedWall));
		r.accept(new BlockItem(dreamwoodLogGlimmering, props), BuiltInRegistries.BLOCK.getKey(dreamwoodLogGlimmering));
		r.accept(new BlockItem(dreamwoodGlimmering, props), BuiltInRegistries.BLOCK.getKey(dreamwoodGlimmering));
		r.accept(new BlockItem(dreamwoodLogStrippedGlimmering, props), BuiltInRegistries.BLOCK.getKey(dreamwoodLogStrippedGlimmering));
		r.accept(new BlockItem(dreamwoodStrippedGlimmering, props), BuiltInRegistries.BLOCK.getKey(dreamwoodStrippedGlimmering));
		r.accept(new BlockItem(dreamwoodPlanks, props), BuiltInRegistries.BLOCK.getKey(dreamwoodPlanks));
		r.accept(new BlockItem(dreamwoodPlankStairs, props), BuiltInRegistries.BLOCK.getKey(dreamwoodPlankStairs));
		r.accept(new BlockItem(dreamwoodPlankSlab, props), BuiltInRegistries.BLOCK.getKey(dreamwoodPlankSlab));
		r.accept(new BlockItem(dreamwoodFence, props), BuiltInRegistries.BLOCK.getKey(dreamwoodFence));
		r.accept(new BlockItem(dreamwoodFenceGate, props), BuiltInRegistries.BLOCK.getKey(dreamwoodFenceGate));
		r.accept(new BlockItem(dreamwoodPlanksMossy, props), BuiltInRegistries.BLOCK.getKey(dreamwoodPlanksMossy));
		r.accept(new BlockItem(dreamwoodFramed, props), BuiltInRegistries.BLOCK.getKey(dreamwoodFramed));
		r.accept(new BlockItem(dreamwoodPatternFramed, props), BuiltInRegistries.BLOCK.getKey(dreamwoodPatternFramed));

		r.accept(new BlockItem(manaSpreader, props), BuiltInRegistries.BLOCK.getKey(manaSpreader));
		r.accept(new BlockItem(redstoneSpreader, props), BuiltInRegistries.BLOCK.getKey(redstoneSpreader));
		r.accept(new BlockItem(elvenSpreader, props), BuiltInRegistries.BLOCK.getKey(elvenSpreader));
		r.accept(new BlockItem(gaiaSpreader, props), BuiltInRegistries.BLOCK.getKey(gaiaSpreader));
		r.accept(new BlockItem(manaPool, props), BuiltInRegistries.BLOCK.getKey(manaPool));
		r.accept(new BlockItem(creativePool, BotaniaItems.defaultBuilder().rarity(Rarity.EPIC)), BuiltInRegistries.BLOCK.getKey(creativePool));
		r.accept(new BlockItem(dilutedPool, props), BuiltInRegistries.BLOCK.getKey(dilutedPool));
		r.accept(new BlockItem(fabulousPool, props), BuiltInRegistries.BLOCK.getKey(fabulousPool));
		r.accept(new BlockItem(alchemyCatalyst, props), BuiltInRegistries.BLOCK.getKey(alchemyCatalyst));
		r.accept(new BlockItem(conjurationCatalyst, props), BuiltInRegistries.BLOCK.getKey(conjurationCatalyst));
		r.accept(new BlockItem(manasteelBlock, props), BuiltInRegistries.BLOCK.getKey(manasteelBlock));
		r.accept(new BlockItem(terrasteelBlock, BotaniaItems.defaultBuilder().rarity(Rarity.UNCOMMON)), BuiltInRegistries.BLOCK.getKey(terrasteelBlock));
		r.accept(new BlockItem(elementiumBlock, props), BuiltInRegistries.BLOCK.getKey(elementiumBlock));
		r.accept(new BlockItem(manaDiamondBlock, props), BuiltInRegistries.BLOCK.getKey(manaDiamondBlock));
		r.accept(new BlockItem(dragonstoneBlock, props), BuiltInRegistries.BLOCK.getKey(dragonstoneBlock));
		r.accept(new BlockItem(manaGlass, props), BuiltInRegistries.BLOCK.getKey(manaGlass));
		r.accept(new BlockItem(elfGlass, props), BuiltInRegistries.BLOCK.getKey(elfGlass));
		r.accept(new BlockItem(bifrostPerm, props), BuiltInRegistries.BLOCK.getKey(bifrostPerm));
		r.accept(new BlockItem(runeAltar, props), BuiltInRegistries.BLOCK.getKey(runeAltar));
		r.accept(new BlockItem(enchanter, props), BuiltInRegistries.BLOCK.getKey(enchanter));
		r.accept(new BlockItemWithSpecialRenderer(brewery, props), BuiltInRegistries.BLOCK.getKey(brewery));
		r.accept(new BlockItem(terraPlate, props), BuiltInRegistries.BLOCK.getKey(terraPlate));
		r.accept(new BlockItem(alfPortal, BotaniaItems.defaultBuilder().rarity(Rarity.UNCOMMON)), BuiltInRegistries.BLOCK.getKey(alfPortal));

		r.accept(new BlockItemWithSpecialRenderer(manaPylon, props), BuiltInRegistries.BLOCK.getKey(manaPylon));
		r.accept(new BlockItemWithSpecialRenderer(naturaPylon, props), BuiltInRegistries.BLOCK.getKey(naturaPylon));
		r.accept(new BlockItemWithSpecialRenderer(gaiaPylon, props), BuiltInRegistries.BLOCK.getKey(gaiaPylon));
		r.accept(new BlockItem(distributor, props), BuiltInRegistries.BLOCK.getKey(distributor));
		r.accept(new BlockItem(manaVoid, props), BuiltInRegistries.BLOCK.getKey(manaVoid));
		r.accept(new BlockItem(manaDetector, props), BuiltInRegistries.BLOCK.getKey(manaDetector));
		r.accept(new BlockItem(pistonRelay, props), BuiltInRegistries.BLOCK.getKey(pistonRelay));
		r.accept(new BlockItem(turntable, props), BuiltInRegistries.BLOCK.getKey(turntable));
		r.accept(new BlockItem(tinyPlanet, props), BuiltInRegistries.BLOCK.getKey(tinyPlanet));
		r.accept(new BlockItem(wildDrum, props), BuiltInRegistries.BLOCK.getKey(wildDrum));
		r.accept(new BlockItem(gatheringDrum, props), BuiltInRegistries.BLOCK.getKey(gatheringDrum));
		r.accept(new BlockItem(canopyDrum, props), BuiltInRegistries.BLOCK.getKey(canopyDrum));
		r.accept(new BlockItem(spawnerClaw, props), BuiltInRegistries.BLOCK.getKey(spawnerClaw));
		r.accept(new BlockItem(rfGenerator, props), BuiltInRegistries.BLOCK.getKey(rfGenerator));
		r.accept(new BlockItem(prism, props), BuiltInRegistries.BLOCK.getKey(prism));
		r.accept(new BlockItem(pump, props), BuiltInRegistries.BLOCK.getKey(pump));
		r.accept(new BlockItem(sparkChanger, props), BuiltInRegistries.BLOCK.getKey(sparkChanger));
		r.accept(new BlockItem(manaBomb, props), BuiltInRegistries.BLOCK.getKey(manaBomb));
		r.accept(new BlockItemWithSpecialRenderer(bellows, props), BuiltInRegistries.BLOCK.getKey(bellows));
		r.accept(new BlockItem(openCrate, props), BuiltInRegistries.BLOCK.getKey(openCrate));
		r.accept(new BlockItem(craftCrate, props), BuiltInRegistries.BLOCK.getKey(craftCrate));
		r.accept(new BlockItem(forestEye, props), BuiltInRegistries.BLOCK.getKey(forestEye));
		r.accept(new BlockItem(abstrusePlatform, props), BuiltInRegistries.BLOCK.getKey(abstrusePlatform));
		r.accept(new BlockItem(spectralPlatform, props), BuiltInRegistries.BLOCK.getKey(spectralPlatform));
		r.accept(new BlockItem(infrangiblePlatform, BotaniaItems.defaultBuilder().rarity(Rarity.EPIC)), BuiltInRegistries.BLOCK.getKey(infrangiblePlatform));
		r.accept(new TinyPotatoBlockItem(tinyPotato, BotaniaItems.defaultBuilder().rarity(Rarity.UNCOMMON)), BuiltInRegistries.BLOCK.getKey(tinyPotato));
		r.accept(new BlockItem(enderEye, props), BuiltInRegistries.BLOCK.getKey(enderEye));
		r.accept(new BlockItem(redStringContainer, props), BuiltInRegistries.BLOCK.getKey(redStringContainer));
		r.accept(new BlockItem(redStringDispenser, props), BuiltInRegistries.BLOCK.getKey(redStringDispenser));
		r.accept(new BlockItem(redStringFertilizer, props), BuiltInRegistries.BLOCK.getKey(redStringFertilizer));
		r.accept(new BlockItem(redStringComparator, props), BuiltInRegistries.BLOCK.getKey(redStringComparator));
		r.accept(new BlockItem(redStringRelay, props), BuiltInRegistries.BLOCK.getKey(redStringRelay));
		r.accept(new BlockItem(redStringInterceptor, props), BuiltInRegistries.BLOCK.getKey(redStringInterceptor));
		r.accept(new BlockItem(corporeaFunnel, props), BuiltInRegistries.BLOCK.getKey(corporeaFunnel));
		r.accept(new BlockItem(corporeaInterceptor, props), BuiltInRegistries.BLOCK.getKey(corporeaInterceptor));
		r.accept(new BlockItemWithSpecialRenderer(corporeaIndex, props), BuiltInRegistries.BLOCK.getKey(corporeaIndex));
		r.accept(new BlockItem(corporeaCrystalCube, props), BuiltInRegistries.BLOCK.getKey(corporeaCrystalCube));
		r.accept(new BlockItem(corporeaRetainer, props), BuiltInRegistries.BLOCK.getKey(corporeaRetainer));
		r.accept(new BlockItem(corporeaBlock, props), BuiltInRegistries.BLOCK.getKey(corporeaBlock));
		r.accept(new BlockItem(corporeaStairs, props), BuiltInRegistries.BLOCK.getKey(corporeaStairs));
		r.accept(new BlockItem(corporeaSlab, props), BuiltInRegistries.BLOCK.getKey(corporeaSlab));
		r.accept(new BlockItem(corporeaBrick, props), BuiltInRegistries.BLOCK.getKey(corporeaBrick));
		r.accept(new BlockItem(corporeaBrickStairs, props), BuiltInRegistries.BLOCK.getKey(corporeaBrickStairs));
		r.accept(new BlockItem(corporeaBrickSlab, props), BuiltInRegistries.BLOCK.getKey(corporeaBrickSlab));
		r.accept(new BlockItem(corporeaBrickWall, props), BuiltInRegistries.BLOCK.getKey(corporeaBrickWall));
		r.accept(new BlockItem(incensePlate, props), BuiltInRegistries.BLOCK.getKey(incensePlate));
		r.accept(new BlockItemWithSpecialRenderer(hourglass, props), BuiltInRegistries.BLOCK.getKey(hourglass));
		r.accept(new BlockItem(ghostRail, props), BuiltInRegistries.BLOCK.getKey(ghostRail));
		r.accept(new BlockItem(lightRelayDefault, props), BuiltInRegistries.BLOCK.getKey(lightRelayDefault));
		r.accept(new BlockItem(lightRelayDetector, props), BuiltInRegistries.BLOCK.getKey(lightRelayDetector));
		r.accept(new BlockItem(lightRelayFork, props), BuiltInRegistries.BLOCK.getKey(lightRelayFork));
		r.accept(new BlockItem(lightRelayToggle, props), BuiltInRegistries.BLOCK.getKey(lightRelayToggle));
		r.accept(new BlockItem(lightLauncher, props), BuiltInRegistries.BLOCK.getKey(lightLauncher));
		r.accept(new BlockItem(cacophonium, props), BuiltInRegistries.BLOCK.getKey(cacophonium));
		r.accept(new BlockItem(cellBlock, props), BuiltInRegistries.BLOCK.getKey(cellBlock));
		r.accept(new BlockItemWithSpecialRenderer(teruTeruBozu, props), BuiltInRegistries.BLOCK.getKey(teruTeruBozu));
		r.accept(new BlockItemWithSpecialRenderer(avatar, props), BuiltInRegistries.BLOCK.getKey(avatar));
		r.accept(new BlockItem(root, props), BuiltInRegistries.BLOCK.getKey(root));
		r.accept(new BlockItem(felPumpkin, props), BuiltInRegistries.BLOCK.getKey(felPumpkin));
		r.accept(new BlockItem(cocoon, props), BuiltInRegistries.BLOCK.getKey(cocoon));
		r.accept(new BlockItem(enchantedSoil, BotaniaItems.defaultBuilder().rarity(Rarity.RARE)), BuiltInRegistries.BLOCK.getKey(enchantedSoil));
		r.accept(new BlockItem(animatedTorch, props), BuiltInRegistries.BLOCK.getKey(animatedTorch));
		r.accept(new BlockItem(starfield, props), BuiltInRegistries.BLOCK.getKey(starfield));
		r.accept(new BlockItem(azulejo0, props), BuiltInRegistries.BLOCK.getKey(azulejo0));
		r.accept(new BlockItem(azulejo1, props), BuiltInRegistries.BLOCK.getKey(azulejo1));
		r.accept(new BlockItem(azulejo2, props), BuiltInRegistries.BLOCK.getKey(azulejo2));
		r.accept(new BlockItem(azulejo3, props), BuiltInRegistries.BLOCK.getKey(azulejo3));
		r.accept(new BlockItem(azulejo4, props), BuiltInRegistries.BLOCK.getKey(azulejo4));
		r.accept(new BlockItem(azulejo5, props), BuiltInRegistries.BLOCK.getKey(azulejo5));
		r.accept(new BlockItem(azulejo6, props), BuiltInRegistries.BLOCK.getKey(azulejo6));
		r.accept(new BlockItem(azulejo7, props), BuiltInRegistries.BLOCK.getKey(azulejo7));
		r.accept(new BlockItem(azulejo8, props), BuiltInRegistries.BLOCK.getKey(azulejo8));
		r.accept(new BlockItem(azulejo9, props), BuiltInRegistries.BLOCK.getKey(azulejo9));
		r.accept(new BlockItem(azulejo10, props), BuiltInRegistries.BLOCK.getKey(azulejo10));
		r.accept(new BlockItem(azulejo11, props), BuiltInRegistries.BLOCK.getKey(azulejo11));
		r.accept(new BlockItem(azulejo12, props), BuiltInRegistries.BLOCK.getKey(azulejo12));
		r.accept(new BlockItem(azulejo13, props), BuiltInRegistries.BLOCK.getKey(azulejo13));
		r.accept(new BlockItem(azulejo14, props), BuiltInRegistries.BLOCK.getKey(azulejo14));
		r.accept(new BlockItem(azulejo15, props), BuiltInRegistries.BLOCK.getKey(azulejo15));
		r.accept(new BlockItem(blazeBlock, props), BuiltInRegistries.BLOCK.getKey(blazeBlock));
		r.accept(new StandingAndWallBlockItem(gaiaHead, gaiaHeadWall, BotaniaItems.defaultBuilder().rarity(Rarity.UNCOMMON), Direction.DOWN), BuiltInRegistries.BLOCK.getKey(gaiaHead));
		r.accept(new BlockItem(shimmerrock, props), BuiltInRegistries.BLOCK.getKey(shimmerrock));
		r.accept(new BlockItem(shimmerrockStairs, props), BuiltInRegistries.BLOCK.getKey(shimmerrockStairs));
		r.accept(new BlockItem(shimmerrockSlab, props), BuiltInRegistries.BLOCK.getKey(shimmerrockSlab));
		r.accept(new BlockItem(shimmerwoodPlanks, props), BuiltInRegistries.BLOCK.getKey(shimmerwoodPlanks));
		r.accept(new BlockItem(shimmerwoodPlankStairs, props), BuiltInRegistries.BLOCK.getKey(shimmerwoodPlankStairs));
		r.accept(new BlockItem(shimmerwoodPlankSlab, props), BuiltInRegistries.BLOCK.getKey(shimmerwoodPlankSlab));
		r.accept(new BlockItem(dryGrass, props), BuiltInRegistries.BLOCK.getKey(dryGrass));
		r.accept(new BlockItem(goldenGrass, props), BuiltInRegistries.BLOCK.getKey(goldenGrass));
		r.accept(new BlockItem(vividGrass, props), BuiltInRegistries.BLOCK.getKey(vividGrass));
		r.accept(new BlockItem(scorchedGrass, props), BuiltInRegistries.BLOCK.getKey(scorchedGrass));
		r.accept(new BlockItem(infusedGrass, props), BuiltInRegistries.BLOCK.getKey(infusedGrass));
		r.accept(new BlockItem(mutatedGrass, props), BuiltInRegistries.BLOCK.getKey(mutatedGrass));
		r.accept(new BlockItem(motifDaybloom, props), BuiltInRegistries.BLOCK.getKey(motifDaybloom));
		r.accept(new BlockItem(motifNightshade, props), BuiltInRegistries.BLOCK.getKey(motifNightshade));
		r.accept(new BlockItem(motifHydroangeas, props), BuiltInRegistries.BLOCK.getKey(motifHydroangeas));

		r.accept(new BlockItem(darkQuartz, props), BuiltInRegistries.BLOCK.getKey(darkQuartz));
		r.accept(new BlockItem(darkQuartzStairs, props), BuiltInRegistries.BLOCK.getKey(darkQuartzStairs));
		r.accept(new BlockItem(darkQuartzSlab, props), BuiltInRegistries.BLOCK.getKey(darkQuartzSlab));
		r.accept(new BlockItem(darkQuartzChiseled, props), BuiltInRegistries.BLOCK.getKey(darkQuartzChiseled));
		r.accept(new BlockItem(darkQuartzPillar, props), BuiltInRegistries.BLOCK.getKey(darkQuartzPillar));

		r.accept(new BlockItem(manaQuartz, props), BuiltInRegistries.BLOCK.getKey(manaQuartz));
		r.accept(new BlockItem(manaQuartzStairs, props), BuiltInRegistries.BLOCK.getKey(manaQuartzStairs));
		r.accept(new BlockItem(manaQuartzSlab, props), BuiltInRegistries.BLOCK.getKey(manaQuartzSlab));
		r.accept(new BlockItem(manaQuartzChiseled, props), BuiltInRegistries.BLOCK.getKey(manaQuartzChiseled));
		r.accept(new BlockItem(manaQuartzPillar, props), BuiltInRegistries.BLOCK.getKey(manaQuartzPillar));

		r.accept(new BlockItem(blazeQuartz, props), BuiltInRegistries.BLOCK.getKey(blazeQuartz));
		r.accept(new BlockItem(blazeQuartzStairs, props), BuiltInRegistries.BLOCK.getKey(blazeQuartzStairs));
		r.accept(new BlockItem(blazeQuartzSlab, props), BuiltInRegistries.BLOCK.getKey(blazeQuartzSlab));
		r.accept(new BlockItem(blazeQuartzChiseled, props), BuiltInRegistries.BLOCK.getKey(blazeQuartzChiseled));
		r.accept(new BlockItem(blazeQuartzPillar, props), BuiltInRegistries.BLOCK.getKey(blazeQuartzPillar));

		r.accept(new BlockItem(lavenderQuartz, props), BuiltInRegistries.BLOCK.getKey(lavenderQuartz));
		r.accept(new BlockItem(lavenderQuartzStairs, props), BuiltInRegistries.BLOCK.getKey(lavenderQuartzStairs));
		r.accept(new BlockItem(lavenderQuartzSlab, props), BuiltInRegistries.BLOCK.getKey(lavenderQuartzSlab));
		r.accept(new BlockItem(lavenderQuartzChiseled, props), BuiltInRegistries.BLOCK.getKey(lavenderQuartzChiseled));
		r.accept(new BlockItem(lavenderQuartzPillar, props), BuiltInRegistries.BLOCK.getKey(lavenderQuartzPillar));

		r.accept(new BlockItem(redQuartz, props), BuiltInRegistries.BLOCK.getKey(redQuartz));
		r.accept(new BlockItem(redQuartzStairs, props), BuiltInRegistries.BLOCK.getKey(redQuartzStairs));
		r.accept(new BlockItem(redQuartzSlab, props), BuiltInRegistries.BLOCK.getKey(redQuartzSlab));
		r.accept(new BlockItem(redQuartzChiseled, props), BuiltInRegistries.BLOCK.getKey(redQuartzChiseled));
		r.accept(new BlockItem(redQuartzPillar, props), BuiltInRegistries.BLOCK.getKey(redQuartzPillar));

		r.accept(new BlockItem(elfQuartz, props), BuiltInRegistries.BLOCK.getKey(elfQuartz));
		r.accept(new BlockItem(elfQuartzStairs, props), BuiltInRegistries.BLOCK.getKey(elfQuartzStairs));
		r.accept(new BlockItem(elfQuartzSlab, props), BuiltInRegistries.BLOCK.getKey(elfQuartzSlab));
		r.accept(new BlockItem(elfQuartzChiseled, props), BuiltInRegistries.BLOCK.getKey(elfQuartzChiseled));
		r.accept(new BlockItem(elfQuartzPillar, props), BuiltInRegistries.BLOCK.getKey(elfQuartzPillar));

		r.accept(new BlockItem(sunnyQuartz, props), BuiltInRegistries.BLOCK.getKey(sunnyQuartz));
		r.accept(new BlockItem(sunnyQuartzStairs, props), BuiltInRegistries.BLOCK.getKey(sunnyQuartzStairs));
		r.accept(new BlockItem(sunnyQuartzSlab, props), BuiltInRegistries.BLOCK.getKey(sunnyQuartzSlab));
		r.accept(new BlockItem(sunnyQuartzChiseled, props), BuiltInRegistries.BLOCK.getKey(sunnyQuartzChiseled));
		r.accept(new BlockItem(sunnyQuartzPillar, props), BuiltInRegistries.BLOCK.getKey(sunnyQuartzPillar));

		r.accept(new BlockItem(biomeStoneForest, props), BuiltInRegistries.BLOCK.getKey(biomeStoneForest));
		r.accept(new BlockItem(biomeStoneForestStairs, props), BuiltInRegistries.BLOCK.getKey(biomeStoneForestStairs));
		r.accept(new BlockItem(biomeStoneForestSlab, props), BuiltInRegistries.BLOCK.getKey(biomeStoneForestSlab));
		r.accept(new BlockItem(biomeStoneForestWall, props), BuiltInRegistries.BLOCK.getKey(biomeStoneForestWall));
		r.accept(new BlockItem(biomeBrickForest, props), BuiltInRegistries.BLOCK.getKey(biomeBrickForest));
		r.accept(new BlockItem(biomeBrickForestStairs, props), BuiltInRegistries.BLOCK.getKey(biomeBrickForestStairs));
		r.accept(new BlockItem(biomeBrickForestSlab, props), BuiltInRegistries.BLOCK.getKey(biomeBrickForestSlab));
		r.accept(new BlockItem(biomeBrickForestWall, props), BuiltInRegistries.BLOCK.getKey(biomeBrickForestWall));
		r.accept(new BlockItem(biomeCobblestoneForest, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneForest));
		r.accept(new BlockItem(biomeCobblestoneForestStairs, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneForestStairs));
		r.accept(new BlockItem(biomeCobblestoneForestSlab, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneForestSlab));
		r.accept(new BlockItem(biomeCobblestoneForestWall, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneForestWall));
		r.accept(new BlockItem(biomeChiseledBrickForest, props), BuiltInRegistries.BLOCK.getKey(biomeChiseledBrickForest));

		r.accept(new BlockItem(biomeStonePlains, props), BuiltInRegistries.BLOCK.getKey(biomeStonePlains));
		r.accept(new BlockItem(biomeStonePlainsStairs, props), BuiltInRegistries.BLOCK.getKey(biomeStonePlainsStairs));
		r.accept(new BlockItem(biomeStonePlainsSlab, props), BuiltInRegistries.BLOCK.getKey(biomeStonePlainsSlab));
		r.accept(new BlockItem(biomeStonePlainsWall, props), BuiltInRegistries.BLOCK.getKey(biomeStonePlainsWall));
		r.accept(new BlockItem(biomeBrickPlains, props), BuiltInRegistries.BLOCK.getKey(biomeBrickPlains));
		r.accept(new BlockItem(biomeBrickPlainsStairs, props), BuiltInRegistries.BLOCK.getKey(biomeBrickPlainsStairs));
		r.accept(new BlockItem(biomeBrickPlainsSlab, props), BuiltInRegistries.BLOCK.getKey(biomeBrickPlainsSlab));
		r.accept(new BlockItem(biomeBrickPlainsWall, props), BuiltInRegistries.BLOCK.getKey(biomeBrickPlainsWall));
		r.accept(new BlockItem(biomeCobblestonePlains, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestonePlains));
		r.accept(new BlockItem(biomeCobblestonePlainsStairs, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestonePlainsStairs));
		r.accept(new BlockItem(biomeCobblestonePlainsSlab, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestonePlainsSlab));
		r.accept(new BlockItem(biomeCobblestonePlainsWall, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestonePlainsWall));
		r.accept(new BlockItem(biomeChiseledBrickPlains, props), BuiltInRegistries.BLOCK.getKey(biomeChiseledBrickPlains));

		r.accept(new BlockItem(biomeStoneMountain, props), BuiltInRegistries.BLOCK.getKey(biomeStoneMountain));
		r.accept(new BlockItem(biomeStoneMountainStairs, props), BuiltInRegistries.BLOCK.getKey(biomeStoneMountainStairs));
		r.accept(new BlockItem(biomeStoneMountainSlab, props), BuiltInRegistries.BLOCK.getKey(biomeStoneMountainSlab));
		r.accept(new BlockItem(biomeStoneMountainWall, props), BuiltInRegistries.BLOCK.getKey(biomeStoneMountainWall));
		r.accept(new BlockItem(biomeBrickMountain, props), BuiltInRegistries.BLOCK.getKey(biomeBrickMountain));
		r.accept(new BlockItem(biomeBrickMountainStairs, props), BuiltInRegistries.BLOCK.getKey(biomeBrickMountainStairs));
		r.accept(new BlockItem(biomeBrickMountainSlab, props), BuiltInRegistries.BLOCK.getKey(biomeBrickMountainSlab));
		r.accept(new BlockItem(biomeBrickMountainWall, props), BuiltInRegistries.BLOCK.getKey(biomeBrickMountainWall));
		r.accept(new BlockItem(biomeCobblestoneMountain, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneMountain));
		r.accept(new BlockItem(biomeCobblestoneMountainStairs, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneMountainStairs));
		r.accept(new BlockItem(biomeCobblestoneMountainSlab, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneMountainSlab));
		r.accept(new BlockItem(biomeCobblestoneMountainWall, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneMountainWall));
		r.accept(new BlockItem(biomeChiseledBrickMountain, props), BuiltInRegistries.BLOCK.getKey(biomeChiseledBrickMountain));

		r.accept(new BlockItem(biomeStoneFungal, props), BuiltInRegistries.BLOCK.getKey(biomeStoneFungal));
		r.accept(new BlockItem(biomeStoneFungalStairs, props), BuiltInRegistries.BLOCK.getKey(biomeStoneFungalStairs));
		r.accept(new BlockItem(biomeStoneFungalSlab, props), BuiltInRegistries.BLOCK.getKey(biomeStoneFungalSlab));
		r.accept(new BlockItem(biomeStoneFungalWall, props), BuiltInRegistries.BLOCK.getKey(biomeStoneFungalWall));
		r.accept(new BlockItem(biomeBrickFungal, props), BuiltInRegistries.BLOCK.getKey(biomeBrickFungal));
		r.accept(new BlockItem(biomeBrickFungalStairs, props), BuiltInRegistries.BLOCK.getKey(biomeBrickFungalStairs));
		r.accept(new BlockItem(biomeBrickFungalSlab, props), BuiltInRegistries.BLOCK.getKey(biomeBrickFungalSlab));
		r.accept(new BlockItem(biomeBrickFungalWall, props), BuiltInRegistries.BLOCK.getKey(biomeBrickFungalWall));
		r.accept(new BlockItem(biomeCobblestoneFungal, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneFungal));
		r.accept(new BlockItem(biomeCobblestoneFungalStairs, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneFungalStairs));
		r.accept(new BlockItem(biomeCobblestoneFungalSlab, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneFungalSlab));
		r.accept(new BlockItem(biomeCobblestoneFungalWall, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneFungalWall));
		r.accept(new BlockItem(biomeChiseledBrickFungal, props), BuiltInRegistries.BLOCK.getKey(biomeChiseledBrickFungal));

		r.accept(new BlockItem(biomeStoneSwamp, props), BuiltInRegistries.BLOCK.getKey(biomeStoneSwamp));
		r.accept(new BlockItem(biomeStoneSwampStairs, props), BuiltInRegistries.BLOCK.getKey(biomeStoneSwampStairs));
		r.accept(new BlockItem(biomeStoneSwampSlab, props), BuiltInRegistries.BLOCK.getKey(biomeStoneSwampSlab));
		r.accept(new BlockItem(biomeStoneSwampWall, props), BuiltInRegistries.BLOCK.getKey(biomeStoneSwampWall));
		r.accept(new BlockItem(biomeBrickSwamp, props), BuiltInRegistries.BLOCK.getKey(biomeBrickSwamp));
		r.accept(new BlockItem(biomeBrickSwampStairs, props), BuiltInRegistries.BLOCK.getKey(biomeBrickSwampStairs));
		r.accept(new BlockItem(biomeBrickSwampSlab, props), BuiltInRegistries.BLOCK.getKey(biomeBrickSwampSlab));
		r.accept(new BlockItem(biomeBrickSwampWall, props), BuiltInRegistries.BLOCK.getKey(biomeBrickSwampWall));
		r.accept(new BlockItem(biomeCobblestoneSwamp, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneSwamp));
		r.accept(new BlockItem(biomeCobblestoneSwampStairs, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneSwampStairs));
		r.accept(new BlockItem(biomeCobblestoneSwampSlab, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneSwampSlab));
		r.accept(new BlockItem(biomeCobblestoneSwampWall, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneSwampWall));
		r.accept(new BlockItem(biomeChiseledBrickSwamp, props), BuiltInRegistries.BLOCK.getKey(biomeChiseledBrickSwamp));

		r.accept(new BlockItem(biomeStoneDesert, props), BuiltInRegistries.BLOCK.getKey(biomeStoneDesert));
		r.accept(new BlockItem(biomeStoneDesertStairs, props), BuiltInRegistries.BLOCK.getKey(biomeStoneDesertStairs));
		r.accept(new BlockItem(biomeStoneDesertSlab, props), BuiltInRegistries.BLOCK.getKey(biomeStoneDesertSlab));
		r.accept(new BlockItem(biomeStoneDesertWall, props), BuiltInRegistries.BLOCK.getKey(biomeStoneDesertWall));
		r.accept(new BlockItem(biomeBrickDesert, props), BuiltInRegistries.BLOCK.getKey(biomeBrickDesert));
		r.accept(new BlockItem(biomeBrickDesertStairs, props), BuiltInRegistries.BLOCK.getKey(biomeBrickDesertStairs));
		r.accept(new BlockItem(biomeBrickDesertSlab, props), BuiltInRegistries.BLOCK.getKey(biomeBrickDesertSlab));
		r.accept(new BlockItem(biomeBrickDesertWall, props), BuiltInRegistries.BLOCK.getKey(biomeBrickDesertWall));
		r.accept(new BlockItem(biomeCobblestoneDesert, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneDesert));
		r.accept(new BlockItem(biomeCobblestoneDesertStairs, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneDesertStairs));
		r.accept(new BlockItem(biomeCobblestoneDesertSlab, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneDesertSlab));
		r.accept(new BlockItem(biomeCobblestoneDesertWall, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneDesertWall));
		r.accept(new BlockItem(biomeChiseledBrickDesert, props), BuiltInRegistries.BLOCK.getKey(biomeChiseledBrickDesert));

		r.accept(new BlockItem(biomeStoneTaiga, props), BuiltInRegistries.BLOCK.getKey(biomeStoneTaiga));
		r.accept(new BlockItem(biomeStoneTaigaStairs, props), BuiltInRegistries.BLOCK.getKey(biomeStoneTaigaStairs));
		r.accept(new BlockItem(biomeStoneTaigaSlab, props), BuiltInRegistries.BLOCK.getKey(biomeStoneTaigaSlab));
		r.accept(new BlockItem(biomeStoneTaigaWall, props), BuiltInRegistries.BLOCK.getKey(biomeStoneTaigaWall));
		r.accept(new BlockItem(biomeBrickTaiga, props), BuiltInRegistries.BLOCK.getKey(biomeBrickTaiga));
		r.accept(new BlockItem(biomeBrickTaigaStairs, props), BuiltInRegistries.BLOCK.getKey(biomeBrickTaigaStairs));
		r.accept(new BlockItem(biomeBrickTaigaSlab, props), BuiltInRegistries.BLOCK.getKey(biomeBrickTaigaSlab));
		r.accept(new BlockItem(biomeBrickTaigaWall, props), BuiltInRegistries.BLOCK.getKey(biomeBrickTaigaWall));
		r.accept(new BlockItem(biomeCobblestoneTaiga, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneTaiga));
		r.accept(new BlockItem(biomeCobblestoneTaigaStairs, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneTaigaStairs));
		r.accept(new BlockItem(biomeCobblestoneTaigaSlab, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneTaigaSlab));
		r.accept(new BlockItem(biomeCobblestoneTaigaWall, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneTaigaWall));
		r.accept(new BlockItem(biomeChiseledBrickTaiga, props), BuiltInRegistries.BLOCK.getKey(biomeChiseledBrickTaiga));

		r.accept(new BlockItem(biomeStoneMesa, props), BuiltInRegistries.BLOCK.getKey(biomeStoneMesa));
		r.accept(new BlockItem(biomeStoneMesaStairs, props), BuiltInRegistries.BLOCK.getKey(biomeStoneMesaStairs));
		r.accept(new BlockItem(biomeStoneMesaSlab, props), BuiltInRegistries.BLOCK.getKey(biomeStoneMesaSlab));
		r.accept(new BlockItem(biomeStoneMesaWall, props), BuiltInRegistries.BLOCK.getKey(biomeStoneMesaWall));
		r.accept(new BlockItem(biomeBrickMesa, props), BuiltInRegistries.BLOCK.getKey(biomeBrickMesa));
		r.accept(new BlockItem(biomeBrickMesaStairs, props), BuiltInRegistries.BLOCK.getKey(biomeBrickMesaStairs));
		r.accept(new BlockItem(biomeBrickMesaSlab, props), BuiltInRegistries.BLOCK.getKey(biomeBrickMesaSlab));
		r.accept(new BlockItem(biomeBrickMesaWall, props), BuiltInRegistries.BLOCK.getKey(biomeBrickMesaWall));
		r.accept(new BlockItem(biomeCobblestoneMesa, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneMesa));
		r.accept(new BlockItem(biomeCobblestoneMesaStairs, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneMesaStairs));
		r.accept(new BlockItem(biomeCobblestoneMesaSlab, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneMesaSlab));
		r.accept(new BlockItem(biomeCobblestoneMesaWall, props), BuiltInRegistries.BLOCK.getKey(biomeCobblestoneMesaWall));
		r.accept(new BlockItem(biomeChiseledBrickMesa, props), BuiltInRegistries.BLOCK.getKey(biomeChiseledBrickMesa));

		r.accept(new BlockItem(whitePavement, props), BuiltInRegistries.BLOCK.getKey(whitePavement));
		r.accept(new BlockItem(whitePavementStair, props), BuiltInRegistries.BLOCK.getKey(whitePavementStair));
		r.accept(new BlockItem(whitePavementSlab, props), BuiltInRegistries.BLOCK.getKey(whitePavementSlab));

		r.accept(new BlockItem(blackPavement, props), BuiltInRegistries.BLOCK.getKey(blackPavement));
		r.accept(new BlockItem(blackPavementSlab, props), BuiltInRegistries.BLOCK.getKey(blackPavementSlab));
		r.accept(new BlockItem(blackPavementStair, props), BuiltInRegistries.BLOCK.getKey(blackPavementStair));

		r.accept(new BlockItem(bluePavement, props), BuiltInRegistries.BLOCK.getKey(bluePavement));
		r.accept(new BlockItem(bluePavementStair, props), BuiltInRegistries.BLOCK.getKey(bluePavementStair));
		r.accept(new BlockItem(bluePavementSlab, props), BuiltInRegistries.BLOCK.getKey(bluePavementSlab));

		r.accept(new BlockItem(yellowPavement, props), BuiltInRegistries.BLOCK.getKey(yellowPavement));
		r.accept(new BlockItem(yellowPavementStair, props), BuiltInRegistries.BLOCK.getKey(yellowPavementStair));
		r.accept(new BlockItem(yellowPavementSlab, props), BuiltInRegistries.BLOCK.getKey(yellowPavementSlab));

		r.accept(new BlockItem(redPavement, props), BuiltInRegistries.BLOCK.getKey(redPavement));
		r.accept(new BlockItem(redPavementStair, props), BuiltInRegistries.BLOCK.getKey(redPavementStair));
		r.accept(new BlockItem(redPavementSlab, props), BuiltInRegistries.BLOCK.getKey(redPavementSlab));

		r.accept(new BlockItem(greenPavement, props), BuiltInRegistries.BLOCK.getKey(greenPavement));
		r.accept(new BlockItem(greenPavementStair, props), BuiltInRegistries.BLOCK.getKey(greenPavementStair));
		r.accept(new BlockItem(greenPavementSlab, props), BuiltInRegistries.BLOCK.getKey(greenPavementSlab));

		r.accept(new BlockItem(managlassPane, props), BuiltInRegistries.BLOCK.getKey(managlassPane));
		r.accept(new BlockItem(alfglassPane, props), BuiltInRegistries.BLOCK.getKey(alfglassPane));
		r.accept(new BlockItem(bifrostPane, props), BuiltInRegistries.BLOCK.getKey(bifrostPane));
	}

	public static void addDispenserBehaviours() {
		DispenserBlock.registerBehavior(BotaniaItems.twigWand, new WandBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.dreamwoodWand, new WandBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.obedienceStick, new StickBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.poolMinecart, new ManaPoolMinecartBehavior());
		DispenserBlock.registerBehavior(BotaniaBlocks.felPumpkin, new FelPumpkinBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.spark, new ManaSparkBehavior());
		DispenserBlock.registerBehavior(BotaniaBlocks.gaiaHead, new OptionalDispenseItemBehavior() {
			@Override
			protected ItemStack execute(BlockSource source, ItemStack stack) {
				setSuccess(ArmorItem.dispenseArmor(source, stack));
				return stack;
			}
		});

		DispenseItemBehavior behavior = new CorporeaSparkBehavior();
		DispenserBlock.registerBehavior(BotaniaItems.corporeaSpark, behavior);
		DispenserBlock.registerBehavior(BotaniaItems.corporeaSparkMaster, behavior);
		DispenserBlock.registerBehavior(BotaniaItems.corporeaSparkCreative, behavior);
		DispenserBlock.registerBehavior(BotaniaItems.enderAirBottle, new ProjectileDispenseBehavior(BotaniaItems.enderAirBottle));
		behavior = DispenserBlockAccessor.getDispenserRegistry().get(Items.GLASS_BOTTLE);
		DispenserBlock.registerBehavior(Items.GLASS_BOTTLE, new EnderAirBottlingBehavior(behavior));

		behavior = new GrassSeedsBehavior();
		Item[] seedItems = {
				BotaniaItems.grassSeeds,
				BotaniaItems.podzolSeeds,
				BotaniaItems.mycelSeeds,
				BotaniaItems.drySeeds,
				BotaniaItems.goldenSeeds,
				BotaniaItems.vividSeeds,
				BotaniaItems.scorchedSeeds,
				BotaniaItems.infusedSeeds,
				BotaniaItems.mutatedSeeds,
		};
		for (Item seed : seedItems) {
			DispenserBlock.registerBehavior(seed, behavior);
		}

		DispenserBlock.registerBehavior(BotaniaItems.manasteelShears, new ShearsDispenseItemBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.elementiumShears, new ShearsDispenseItemBehavior());
		DispenserBlock.registerBehavior(BotaniaItems.vineBall, new ProjectileDispenseBehavior(BotaniaItems.vineBall));

		SeedBehaviors.init();
	}

	public static void addAxeStripping() {
		XplatAbstractions xplat = XplatAbstractions.INSTANCE;
		xplat.addAxeStripping(livingwoodLog, livingwoodLogStripped);
		xplat.addAxeStripping(livingwoodLogGlimmering, livingwoodLogStrippedGlimmering);
		xplat.addAxeStripping(livingwood, livingwoodStripped);
		xplat.addAxeStripping(livingwoodGlimmering, livingwoodStrippedGlimmering);
		xplat.addAxeStripping(dreamwoodLog, dreamwoodLogStripped);
		xplat.addAxeStripping(dreamwoodLogGlimmering, dreamwoodLogStrippedGlimmering);
		xplat.addAxeStripping(dreamwood, dreamwoodStripped);
		xplat.addAxeStripping(dreamwoodGlimmering, dreamwoodStrippedGlimmering);

		xplat.addAxeStripping(BotaniaBlocks.livingwoodStairs, BotaniaBlocks.livingwoodStrippedStairs);
		xplat.addAxeStripping(BotaniaBlocks.livingwoodSlab, BotaniaBlocks.livingwoodStrippedSlab);
		xplat.addAxeStripping(BotaniaBlocks.livingwoodWall, BotaniaBlocks.livingwoodStrippedWall);
		xplat.addAxeStripping(BotaniaBlocks.dreamwoodStairs, BotaniaBlocks.dreamwoodStrippedStairs);
		xplat.addAxeStripping(BotaniaBlocks.dreamwoodSlab, BotaniaBlocks.dreamwoodStrippedSlab);
		xplat.addAxeStripping(BotaniaBlocks.dreamwoodWall, BotaniaBlocks.dreamwoodStrippedWall);
	}

	public static Block getFlower(DyeColor color) {
		return switch (color) {
			case WHITE -> whiteFlower;
			case ORANGE -> orangeFlower;
			case MAGENTA -> magentaFlower;
			case LIGHT_BLUE -> lightBlueFlower;
			case YELLOW -> yellowFlower;
			case LIME -> limeFlower;
			case PINK -> pinkFlower;
			case GRAY -> grayFlower;
			case LIGHT_GRAY -> lightGrayFlower;
			case CYAN -> cyanFlower;
			case PURPLE -> purpleFlower;
			case BLUE -> blueFlower;
			case BROWN -> brownFlower;
			case GREEN -> greenFlower;
			case RED -> redFlower;
			case BLACK -> blackFlower;
		};
	}

	public static Block getMushroom(DyeColor color) {
		return switch (color) {
			case WHITE -> whiteMushroom;
			case ORANGE -> orangeMushroom;
			case MAGENTA -> magentaMushroom;
			case LIGHT_BLUE -> lightBlueMushroom;
			case YELLOW -> yellowMushroom;
			case LIME -> limeMushroom;
			case PINK -> pinkMushroom;
			case GRAY -> grayMushroom;
			case LIGHT_GRAY -> lightGrayMushroom;
			case CYAN -> cyanMushroom;
			case PURPLE -> purpleMushroom;
			case BLUE -> blueMushroom;
			case BROWN -> brownMushroom;
			case GREEN -> greenMushroom;
			case RED -> redMushroom;
			case BLACK -> blackMushroom;
		};
	}

	public static Block getBuriedPetal(DyeColor color) {
		return switch (color) {
			case WHITE -> whiteBuriedPetals;
			case ORANGE -> orangeBuriedPetals;
			case MAGENTA -> magentaBuriedPetals;
			case LIGHT_BLUE -> lightBlueBuriedPetals;
			case YELLOW -> yellowBuriedPetals;
			case LIME -> limeBuriedPetals;
			case PINK -> pinkBuriedPetals;
			case GRAY -> grayBuriedPetals;
			case LIGHT_GRAY -> lightGrayBuriedPetals;
			case CYAN -> cyanBuriedPetals;
			case PURPLE -> purpleBuriedPetals;
			case BLUE -> blueBuriedPetals;
			case BROWN -> brownBuriedPetals;
			case GREEN -> greenBuriedPetals;
			case RED -> redBuriedPetals;
			case BLACK -> blackBuriedPetals;
		};
	}

	public static Block getShinyFlower(DyeColor color) {
		return switch (color) {
			case WHITE -> whiteShinyFlower;
			case ORANGE -> orangeShinyFlower;
			case MAGENTA -> magentaShinyFlower;
			case LIGHT_BLUE -> lightBlueShinyFlower;
			case YELLOW -> yellowShinyFlower;
			case LIME -> limeShinyFlower;
			case PINK -> pinkShinyFlower;
			case GRAY -> grayShinyFlower;
			case LIGHT_GRAY -> lightGrayShinyFlower;
			case CYAN -> cyanShinyFlower;
			case PURPLE -> purpleShinyFlower;
			case BLUE -> blueShinyFlower;
			case BROWN -> brownShinyFlower;
			case GREEN -> greenShinyFlower;
			case RED -> redShinyFlower;
			case BLACK -> blackShinyFlower;
		};
	}

	public static Block getFloatingFlower(DyeColor color) {
		return switch (color) {
			case WHITE -> whiteFloatingFlower;
			case ORANGE -> orangeFloatingFlower;
			case MAGENTA -> magentaFloatingFlower;
			case LIGHT_BLUE -> lightBlueFloatingFlower;
			case YELLOW -> yellowFloatingFlower;
			case LIME -> limeFloatingFlower;
			case PINK -> pinkFloatingFlower;
			case GRAY -> grayFloatingFlower;
			case LIGHT_GRAY -> lightGrayFloatingFlower;
			case CYAN -> cyanFloatingFlower;
			case PURPLE -> purpleFloatingFlower;
			case BLUE -> blueFloatingFlower;
			case BROWN -> brownFloatingFlower;
			case GREEN -> greenFloatingFlower;
			case RED -> redFloatingFlower;
			case BLACK -> blackFloatingFlower;
		};
	}

	public static Block getDoubleFlower(DyeColor color) {
		return switch (color) {
			case WHITE -> doubleFlowerWhite;
			case ORANGE -> doubleFlowerOrange;
			case MAGENTA -> doubleFlowerMagenta;
			case LIGHT_BLUE -> doubleFlowerLightBlue;
			case YELLOW -> doubleFlowerYellow;
			case LIME -> doubleFlowerLime;
			case PINK -> doubleFlowerPink;
			case GRAY -> doubleFlowerGray;
			case LIGHT_GRAY -> doubleFlowerLightGray;
			case CYAN -> doubleFlowerCyan;
			case PURPLE -> doubleFlowerPurple;
			case BLUE -> doubleFlowerBlue;
			case BROWN -> doubleFlowerBrown;
			case GREEN -> doubleFlowerGreen;
			case RED -> doubleFlowerRed;
			case BLACK -> doubleFlowerBlack;
		};
	}

	public static Block getPetalBlock(DyeColor color) {
		return switch (color) {
			case WHITE -> petalBlockWhite;
			case ORANGE -> petalBlockOrange;
			case MAGENTA -> petalBlockMagenta;
			case LIGHT_BLUE -> petalBlockLightBlue;
			case YELLOW -> petalBlockYellow;
			case LIME -> petalBlockLime;
			case PINK -> petalBlockPink;
			case GRAY -> petalBlockGray;
			case LIGHT_GRAY -> petalBlockSilver;
			case CYAN -> petalBlockCyan;
			case PURPLE -> petalBlockPurple;
			case BLUE -> petalBlockBlue;
			case BROWN -> petalBlockBrown;
			case GREEN -> petalBlockGreen;
			case RED -> petalBlockRed;
			case BLACK -> petalBlockBlack;
		};
	}

	public static Block getPottedFlower(DyeColor color) {
		return switch (color) {
			case WHITE -> pottedWhiteFlower;
			case ORANGE -> pottedOrangeFlower;
			case MAGENTA -> pottedMagentaFlower;
			case LIGHT_BLUE -> pottedLightBlueFlower;
			case YELLOW -> pottedYellowFlower;
			case LIME -> pottedLimeFlower;
			case PINK -> pottedPinkFlower;
			case GRAY -> pottedGrayFlower;
			case LIGHT_GRAY -> pottedLightGrayFlower;
			case CYAN -> pottedCyanFlower;
			case PURPLE -> pottedPurpleFlower;
			case BLUE -> pottedBlueFlower;
			case BROWN -> pottedBrownFlower;
			case GREEN -> pottedGreenFlower;
			case RED -> pottedRedFlower;
			case BLACK -> pottedBlackFlower;
		};
	}

	public static Block getPottedShinyFlower(DyeColor color) {
		return switch (color) {
			case WHITE -> pottedWhiteShinyFlower;
			case ORANGE -> pottedOrangeShinyFlower;
			case MAGENTA -> pottedMagentaShinyFlower;
			case LIGHT_BLUE -> pottedLightBlueShinyFlower;
			case YELLOW -> pottedYellowShinyFlower;
			case LIME -> pottedLimeShinyFlower;
			case PINK -> pottedPinkShinyFlower;
			case GRAY -> pottedGrayShinyFlower;
			case LIGHT_GRAY -> pottedLightGrayShinyFlower;
			case CYAN -> pottedCyanShinyFlower;
			case PURPLE -> pottedPurpleShinyFlower;
			case BLUE -> pottedBlueShinyFlower;
			case BROWN -> pottedBrownShinyFlower;
			case GREEN -> pottedGreenShinyFlower;
			case RED -> pottedRedShinyFlower;
			case BLACK -> pottedBlackShinyFlower;
		};
	}

	public static Block getPottedMushroom(DyeColor color) {
		return switch (color) {
			case WHITE -> pottedWhiteMushroom;
			case ORANGE -> pottedOrangeMushroom;
			case MAGENTA -> pottedMagentaMushroom;
			case LIGHT_BLUE -> pottedLightBlueMushroom;
			case YELLOW -> pottedYellowMushroom;
			case LIME -> pottedLimeMushroom;
			case PINK -> pottedPinkMushroom;
			case GRAY -> pottedGrayMushroom;
			case LIGHT_GRAY -> pottedLightGrayMushroom;
			case CYAN -> pottedCyanMushroom;
			case PURPLE -> pottedPurpleMushroom;
			case BLUE -> pottedBlueMushroom;
			case BROWN -> pottedBrownMushroom;
			case GREEN -> pottedGreenMushroom;
			case RED -> pottedRedMushroom;
			case BLACK -> pottedBlackMushroom;
		};
	}

	// public static void registerFlowerPotPlants(BiConsumer<ResourceLocation, Supplier<? extends Block>> consumer) {
	// 	ColorHelper.supportedColors().forEach(dyeColor -> {
	// 		consumer.accept(botaniaRL(dyeColor.getName() + MYSTICAL_FLOWER_SUFFIX), () -> getPottedFlower(dyeColor));
	// 		consumer.accept(botaniaRL(dyeColor.getName() + SHINY_FLOWER_SUFFIX), () -> getPottedShinyFlower(dyeColor));
	// 		consumer.accept(botaniaRL(dyeColor.getName() + MUSHROOM_SUFFIX), () -> getPottedMushroom(dyeColor));
	// 	});
	// 	consumer.accept(botaniaRL(LibBlockNames.MOTIF_DAYBLOOM), () -> pottedMotifDaybloom);
	// 	consumer.accept(botaniaRL(LibBlockNames.MOTIF_NIGHTSHADE), () -> pottedMotifNightshade);
	// 	consumer.accept(botaniaRL(LibBlockNames.MOTIF_HYDROANGEAS), () -> pottedMotifHydroangeas);
	// }

	public static void registerFlowerPotPlants(BiConsumer<ResourceLocation, Supplier<? extends Block>> consumer) {
		registerBlocks((block, resourceLocation) -> {
			if (block instanceof FlowerPotBlock) {
				var id = BuiltInRegistries.BLOCK.getKey(block);
				consumer.accept(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath().substring(LibBlockNames.POTTED_PREFIX.length())), () -> block);
			}
		});
	}
}
