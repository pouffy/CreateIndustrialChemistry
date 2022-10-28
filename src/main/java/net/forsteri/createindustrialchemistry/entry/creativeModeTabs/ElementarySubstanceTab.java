package net.forsteri.createindustrialchemistry.entry.creativeModeTabs;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.SolidSubstances;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ElementarySubstanceTab {
    public static final CreativeModeTab ELEMENTARY_SUBSTANCE_TAB = new CreativeModeTab("elementary_substance_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(SolidSubstances.SODIUM.get());
        }
    };
}
