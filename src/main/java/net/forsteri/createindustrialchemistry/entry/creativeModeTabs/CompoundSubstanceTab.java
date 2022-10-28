package net.forsteri.createindustrialchemistry.entry.creativeModeTabs;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.SolidSubstances;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CompoundSubstanceTab {
    public static final CreativeModeTab COMPOUND_SUBSTANCE_TAB = new CreativeModeTab("compound_substance_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(SolidSubstances.SODIUM_HYDROXIDE.get());
        }
    };
}
