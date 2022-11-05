package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.ElementarySubstanceTab;
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
            = FLUIDS.register("hydrogen_source", () -> new Hydrogen.Source(GasSubstances.HYDROGEN_PROPERTIES));

    public static final RegistryObject<FlowingFluid> HYDROGEN_FLOWING
            = FLUIDS.register("hydrogen_flowing", () -> new Hydrogen.Flowing(GasSubstances.HYDROGEN_PROPERTIES));

    public static final ForgeFlowingFluid.Properties HYDROGEN_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> GasSubstances.HYDROGEN_SOURCE.get(), () -> GasSubstances.HYDROGEN_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(-10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xB3DFFF)
                    .gaseous())
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
                            .tab(ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB)
                            .stacksTo(1)
            ));

    public static void register(){}
}
