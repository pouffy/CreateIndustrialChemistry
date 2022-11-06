package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families.mixtures.MixtureTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families.mixtures.WaterMixtureTab;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes.AcidicFluidBlock;
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
public class WaterMixtures {
    public static void register(){}

    public static final RegistryObject<FlowingFluid> CALCIUM_CHLORIDE_WATER_MIXTURE_SOURCE
            = FLUIDS.register("calcium_chloride_water_mixture", () -> new CalciumChlorideWaterMixture.Source(WaterMixtures.CALCIUM_CHLORIDE_WATER_MIXTURE_PROPERTIES) {
    });

    public static final RegistryObject<FlowingFluid> CALCIUM_CHLORIDE_WATER_MIXTURE_FLOWING
            = FLUIDS.register("calcium_chloride_water_mixture_flowing", () -> new CalciumChlorideWaterMixture.Flowing(WaterMixtures.CALCIUM_CHLORIDE_WATER_MIXTURE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties CALCIUM_CHLORIDE_WATER_MIXTURE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> WaterMixtures.CALCIUM_CHLORIDE_WATER_MIXTURE_SOURCE.get(), () -> WaterMixtures.CALCIUM_CHLORIDE_WATER_MIXTURE_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFFFFF))
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(() -> WaterMixtures.CALCIUM_CHLORIDE_WATER_MIXTURE_BLOCK.get())
            .bucket(() -> WaterMixtures.CALCIUM_CHLORIDE_WATER_MIXTURE_TANK.get());

    public static final RegistryObject<LiquidBlock> CALCIUM_CHLORIDE_WATER_MIXTURE_BLOCK = BLOCKS.register("calcium_chloride_water_mixture",
            () -> new AcidicFluidBlock(() -> WaterMixtures.CALCIUM_CHLORIDE_WATER_MIXTURE_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noOcclusion()
                    .strength(100f)
                    .noDrops(), 4.5f));

    public static final RegistryObject<Item> CALCIUM_CHLORIDE_WATER_MIXTURE_TANK = ITEMS.register("calcium_chloride_water_mixture_tank",
            () -> new MetalTank(
                    WaterMixtures.CALCIUM_CHLORIDE_WATER_MIXTURE_SOURCE,
                    new Item.Properties()
                            .stacksTo(1), WaterMixtureTab.WATER_MIXTURE_TAB, MixtureTab.MIXTURE_TAB
            ));
}
