package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.CompoundSubstanceTab;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes.AcidicFluidBlock;
import net.forsteri.createindustrialchemistry.substances.compound.HydrochloricAcid;
import net.forsteri.createindustrialchemistry.substances.compound.PureWater;
import net.forsteri.createindustrialchemistry.substances.equipment.MetalTank;
import net.forsteri.createindustrialchemistry.substances.abstracts.FluidBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

import static net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters.*;

@SuppressWarnings({"Convert2MethodRef", "FunctionalExpressionCanBeFolded"})
public class LiquidSubstances {

    public static final RegistryObject<FlowingFluid> HYDROCHLORIC_ACID_SOURCE
            = FLUIDS.register("hydrochloric_acid", () -> new HydrochloricAcid.Source(LiquidSubstances.HYDROCHLORIC_ACID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> HYDROCHLORIC_ACID_FLOWING
            = FLUIDS.register("hydrochloric_acid_flowing", () -> new HydrochloricAcid.Flowing(LiquidSubstances.HYDROCHLORIC_ACID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties HYDROCHLORIC_ACID_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> LiquidSubstances.HYDROCHLORIC_ACID_SOURCE.get(), () -> LiquidSubstances.HYDROCHLORIC_ACID_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFFDFD96)
    )
                    .slopeFindDistance(2)
                    .levelDecreasePerBlock(2)
                    .block(() -> LiquidSubstances.HYDROCHLORIC_ACID_BLOCK.get())
                    .bucket(() -> LiquidSubstances.HYDROCHLORIC_ACID_TANK.get());

    public static final RegistryObject<LiquidBlock> HYDROCHLORIC_ACID_BLOCK = BLOCKS.register("hydrochloric_acid",
            () -> new AcidicFluidBlock(() -> LiquidSubstances.HYDROCHLORIC_ACID_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noOcclusion()
                    .strength(100f)
                    .noDrops(), -1.08f));

    public static final RegistryObject<Item> HYDROCHLORIC_ACID_TANK = ITEMS.register("hydrochloric_acid_tank",
            () -> new MetalTank(
                    LiquidSubstances.HYDROCHLORIC_ACID_SOURCE,
                    new Item.Properties()
                            .stacksTo(1)
            , CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB));

    public static final RegistryObject<FlowingFluid> PURE_WATER_SOURCE
            = FLUIDS.register("pure_water", () -> new PureWater.Source(LiquidSubstances.PURE_WATER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> PURE_WATER_FLOWING
            = FLUIDS.register("pure_water_flowing", () -> new PureWater.Flowing(LiquidSubstances.PURE_WATER_PROPERTIES));

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
            .bucket(() -> LiquidSubstances.PURE_WATER_TANK.get());

    public static final RegistryObject<LiquidBlock> PURE_WATER_BLOCK = BLOCKS.register("pure_water",
            () -> new FluidBlock(() -> LiquidSubstances.PURE_WATER_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noOcclusion()
                    .strength(100f)
                    .noDrops()));

    public static final RegistryObject<Item> PURE_WATER_TANK = ITEMS.register("pure_water_tank",
            () -> new MetalTank(
                    LiquidSubstances.PURE_WATER_SOURCE,
                    new Item.Properties()
                            .stacksTo(1)
                    , CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB));



    public static void register(){}
}
