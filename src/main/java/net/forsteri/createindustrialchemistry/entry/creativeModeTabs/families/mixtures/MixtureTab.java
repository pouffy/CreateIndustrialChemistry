package net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families.mixtures;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.AqueousSolutions;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("NullableProblems")
public class MixtureTab {
    public static final CreativeModeTab MIXTURE_TAB = new CreativeModeTab("mixture_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AqueousSolutions.CALCIUM_CHLORIDE_AQUEOUS_SOLUTION_TANK.get());
        }
    };
}
