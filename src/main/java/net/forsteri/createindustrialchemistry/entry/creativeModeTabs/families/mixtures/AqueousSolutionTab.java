package net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families.mixtures;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.AqueousSolutions;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AqueousSolutionTab {

    public static final CreativeModeTab WATER_MIXTURE_TAB = new CreativeModeTab("aqueous_solution_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(AqueousSolutions.HYDROCHLORIC_ACID_TANK.get());
        }
    };

}
