package net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families.mixtures;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.LiquidSubstances;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes.AcidicFluidBlock;
import net.forsteri.createindustrialchemistry.substances.compound.HydrochloricAcid;
import net.forsteri.createindustrialchemistry.substances.equipment.MetalTank;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import static net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters.*;

public class WaterMixtureTab {

    public static final CreativeModeTab WATER_MIXTURE_TAB = new CreativeModeTab("water_mixture_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(LiquidSubstances.HYDROCHLORIC_ACID_TANK.get());
        }
    };

}
