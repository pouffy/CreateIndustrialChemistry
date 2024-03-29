package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.CompoundSubstanceTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.FluidTab;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes.AcidicFluidBlock;
import net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes.HotFluidBlock;
import net.forsteri.createindustrialchemistry.substances.compound.HydrochloricAcid;
import net.forsteri.createindustrialchemistry.substances.compound.MoltenSalt;
import net.forsteri.createindustrialchemistry.substances.compound.PureWater;
import net.forsteri.createindustrialchemistry.substances.equipment.MetalTank;
import net.forsteri.createindustrialchemistry.substances.abstracts.FluidBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

import static net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters.*;

@SuppressWarnings({"Convert2MethodRef", "FunctionalExpressionCanBeFolded"})
public class LiquidSubstances {
    public static final RegistryObject<FlowingFluid> PURE_WATER_SOURCE
            = FLUIDS.register("distilled_water", () -> new PureWater.Source(LiquidSubstances.PURE_WATER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> PURE_WATER_FLOWING
            = FLUIDS.register("distilled_water_flowing", () -> new PureWater.Flowing(LiquidSubstances.PURE_WATER_PROPERTIES));

    public static final ForgeFlowingFluid.Properties PURE_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> LiquidSubstances.PURE_WATER_SOURCE.get(), () -> LiquidSubstances.PURE_WATER_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFF43D5EE)
    )
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(() -> LiquidSubstances.PURE_WATER_BLOCK.get())
            .bucket(() -> Items.BUCKET);

    public static final RegistryObject<LiquidBlock> PURE_WATER_BLOCK = BLOCKS.register("distilled_water",
            () -> new FluidBlock(() -> LiquidSubstances.PURE_WATER_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noOcclusion()
                    .strength(100f)
                    .noDrops()));

    public static final RegistryObject<Item> PURE_WATER_TANK = ITEMS.register("distilled_water_tank",
            () -> new MetalTank(
                    LiquidSubstances.PURE_WATER_SOURCE,
                    new Item.Properties()
                            .stacksTo(1),
                    0xFF43D5EE, CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB, FluidTab.FLUID_TAB));

    public static final RegistryObject<FlowingFluid> MOLTEN_SALT_SOURCE
            = FLUIDS.register("molten_salt", () -> new MoltenSalt.Source(LiquidSubstances.MOLTEN_SALT_PROPERTIES));

    public static final RegistryObject<FlowingFluid> MOLTEN_SALT_FLOWING
            = FLUIDS.register("molten_salt_flowing", () -> new MoltenSalt.Flowing(LiquidSubstances.MOLTEN_SALT_PROPERTIES));

    public static final ForgeFlowingFluid.Properties MOLTEN_SALT_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> LiquidSubstances.MOLTEN_SALT_SOURCE.get(), () -> LiquidSubstances.MOLTEN_SALT_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFDD6612)
    )
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(() -> LiquidSubstances.MOLTEN_SALT_BLOCK.get())
            .bucket(() -> Items.BUCKET);

    public static final RegistryObject<LiquidBlock> MOLTEN_SALT_BLOCK = BLOCKS.register("molten_salt",
            () -> new HotFluidBlock(() -> LiquidSubstances.MOLTEN_SALT_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noOcclusion()
                    .strength(100f)
                    .noDrops()));

    public static final RegistryObject<Item> MOLTEN_SALT_TANK = ITEMS.register("molten_salt_tank",
            () -> new MetalTank(
                    LiquidSubstances.MOLTEN_SALT_SOURCE,
                    new Item.Properties()
                            .stacksTo(1)
                    ,0xFFDD6612, CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB, FluidTab.FLUID_TAB));



    public static void register(){}
}
