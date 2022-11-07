package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.CompoundSubstanceTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families.mixtures.MixtureTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families.mixtures.AqueousSolutionTab;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes.AcidicFluidBlock;
import net.forsteri.createindustrialchemistry.substances.compound.HydrochloricAcid;
import net.forsteri.createindustrialchemistry.substances.equipment.MetalTank;
import net.forsteri.createindustrialchemistry.substances.mixture.waterMixture.CalciumChlorideWaterMixture;
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
public class AqueousSolutions {
    public static void register(){}

    public static final RegistryObject<FlowingFluid> CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_SOURCE
            = FLUIDS.register("calcium_chloride_aqueous_solution", () -> new CalciumChlorideWaterMixture.Source(AqueousSolutions.CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_PROPERTIES) {
    });

    public static final RegistryObject<FlowingFluid> CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_FLOWING
            = FLUIDS.register("calcium_chloride_aqueous_solution_flowing", () -> new CalciumChlorideWaterMixture.Flowing(AqueousSolutions.CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_PROPERTIES));

    public static final ForgeFlowingFluid.Properties CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> AqueousSolutions.CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_SOURCE.get(), () -> AqueousSolutions.CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFFFFFFF)
    )
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(() -> AqueousSolutions.CALCIUM_CHLORIDE_AQUEOUS_SOLUTION.get())
            .bucket(() -> AqueousSolutions.CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_TANK.get());

    public static final RegistryObject<LiquidBlock> CALCIUM_CHLORIDE_AQUEOUS_SOLUTION = BLOCKS.register("calcium_chloride_aqueous_solution",
            () -> new AcidicFluidBlock(() -> AqueousSolutions.CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noOcclusion()
                    .strength(100f)
                    .noDrops(), 4.5f));

    public static final RegistryObject<Item> CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_TANK = ITEMS.register("calcium_chloride_aqueous_solution_tank",
            () -> new MetalTank(
                    AqueousSolutions.CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_SOURCE,
                    new Item.Properties()
                            .stacksTo(1), AqueousSolutionTab.WATER_MIXTURE_TAB, MixtureTab.MIXTURE_TAB
            ));

    public static final RegistryObject<FlowingFluid> HYDROCHLORIC_ACID_SOURCE
            = FLUIDS.register("hydrochloric_acid", () -> new HydrochloricAcid.Source(AqueousSolutions.HYDROCHLORIC_ACID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> HYDROCHLORIC_ACID_FLOWING
            = FLUIDS.register("hydrochloric_acid_flowing", () -> new HydrochloricAcid.Flowing(AqueousSolutions.HYDROCHLORIC_ACID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties HYDROCHLORIC_ACID_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> AqueousSolutions.HYDROCHLORIC_ACID_SOURCE.get(), () -> AqueousSolutions.HYDROCHLORIC_ACID_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFFDFD96)
    )
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(() -> AqueousSolutions.HYDROCHLORIC_ACID_BLOCK.get())
            .bucket(() -> AqueousSolutions.HYDROCHLORIC_ACID_TANK.get());

    public static final RegistryObject<LiquidBlock> HYDROCHLORIC_ACID_BLOCK = BLOCKS.register("hydrochloric_acid",
            () -> new AcidicFluidBlock(() -> AqueousSolutions.HYDROCHLORIC_ACID_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noOcclusion()
                    .strength(100f)
                    .noDrops(), -1.08f));

    public static final RegistryObject<Item> HYDROCHLORIC_ACID_TANK = ITEMS.register("hydrochloric_acid_tank",
            () -> new MetalTank(
                    AqueousSolutions.HYDROCHLORIC_ACID_SOURCE,
                    new Item.Properties()
                            .stacksTo(1)
                    , CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB));
}
