package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.CompoundSubstanceTab;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.forsteri.createindustrialchemistry.substances.compound.HydrochloricAcid;
import net.forsteri.createindustrialchemistry.substances.equipment.FluidSubstanceBucketItem;
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
            = FLUIDS.register("hydrochloric_acid_source", () -> new HydrochloricAcid.Source(LiquidSubstances.HYDROCHLORIC_ACID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> HYDROCHLORIC_ACID_FLOWING
            = FLUIDS.register("hydrochloric_acid_flowing", () -> new HydrochloricAcid.Flowing(LiquidSubstances.HYDROCHLORIC_ACID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties HYDROCHLORIC_ACID_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> LiquidSubstances.HYDROCHLORIC_ACID_SOURCE.get(), () -> LiquidSubstances.HYDROCHLORIC_ACID_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(-10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFDFD96))
                    .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(() -> LiquidSubstances.HYDROCHLORIC_ACID_BLOCK.get())
            .bucket(() -> LiquidSubstances.HYDROCHLORIC_ACID_BUCKET.get());

    public static final RegistryObject<LiquidBlock> HYDROCHLORIC_ACID_BLOCK = BLOCKS.register("hydrochloric_acid",
            () -> new LiquidBlock(() -> LiquidSubstances.HYDROCHLORIC_ACID_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noOcclusion()
                    .strength(100f)
                    .noDrops()));

    public static final RegistryObject<Item> HYDROCHLORIC_ACID_BUCKET = ITEMS.register("hydrochloric_acid_bucket",
            () -> new FluidSubstanceBucketItem(
                    LiquidSubstances.HYDROCHLORIC_ACID_SOURCE,
                    new Item.Properties()
                            .tab(CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB)
                            .stacksTo(1)
            ));

    public static void register(){}
}
