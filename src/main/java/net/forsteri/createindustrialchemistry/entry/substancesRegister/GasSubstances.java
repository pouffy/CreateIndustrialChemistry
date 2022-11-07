package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.CompoundSubstanceTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.ElementarySubstanceTab;
import net.forsteri.createindustrialchemistry.substances.compound.CarbonDioxide;
import net.forsteri.createindustrialchemistry.substances.equipment.MetalTank;
import net.forsteri.createindustrialchemistry.substances.element.Hydrogen;
import net.forsteri.createindustrialchemistry.substances.abstracts.FluidBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

import static net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters.*;

@SuppressWarnings({"Convert2MethodRef", "FunctionalExpressionCanBeFolded"})
public class GasSubstances {
    public static final RegistryObject<FlowingFluid> HYDROGEN_SOURCE
            = FLUIDS.register("hydrogen", () -> new Hydrogen.Source(GasSubstances.HYDROGEN_PROPERTIES));

    public static final RegistryObject<FlowingFluid> HYDROGEN_FLOWING
            = FLUIDS.register("hydrogen_flowing", () -> new Hydrogen.Flowing(GasSubstances.HYDROGEN_PROPERTIES));

    public static final ForgeFlowingFluid.Properties HYDROGEN_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> GasSubstances.HYDROGEN_SOURCE.get(), () -> GasSubstances.HYDROGEN_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFB3DFFF)
                    .gaseous()
    )
                    .slopeFindDistance(5)
                    .levelDecreasePerBlock(2)
                    .block(() -> GasSubstances.HYDROGEN_BLOCK.get())
                    .bucket(() -> Items.BUCKET)
                    ;

    public static final RegistryObject<FluidBlock> HYDROGEN_BLOCK = BLOCKS.register("hydrogen",
            () -> new FluidBlock(() -> GasSubstances.HYDROGEN_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<Item> HYDROGEN_TANK = ITEMS.register("hydrogen_tank",
            () -> new MetalTank(
                    GasSubstances.HYDROGEN_SOURCE,
                    new Item.Properties()
                            .stacksTo(1),
                    ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB
            ));

    public static final RegistryObject<FlowingFluid> CARBON_DIOXIDE_SOURCE
            = FLUIDS.register("carbon_dioxide", () -> new CarbonDioxide.Source(GasSubstances.CARBON_DIOXIDE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> CARBON_DIOXIDE_FLOWING
            = FLUIDS.register("carbon_dioxide_flowing", () -> new CarbonDioxide.Flowing(GasSubstances.CARBON_DIOXIDE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties CARBON_DIOXIDE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> GasSubstances.CARBON_DIOXIDE_SOURCE.get(), () -> GasSubstances.CARBON_DIOXIDE_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFFFFFFF)
                    .gaseous()
    )
            .slopeFindDistance(5)
            .levelDecreasePerBlock(2)
            .block(() -> GasSubstances.CARBON_DIOXIDE_BLOCK.get())
            .bucket(() -> Items.BUCKET)
            ;

    public static final RegistryObject<FluidBlock> CARBON_DIOXIDE_BLOCK = BLOCKS.register("carbon_dioxide",
            () -> new FluidBlock(() -> GasSubstances.CARBON_DIOXIDE_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<Item> CARBON_DIOXIDE_TANK = ITEMS.register("carbon_dioxide_tank",
            () -> new MetalTank(
                    GasSubstances.CARBON_DIOXIDE_SOURCE,
                    new Item.Properties()
                            .stacksTo(1),
                    CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB
            ));

    public static void register(){}
}
