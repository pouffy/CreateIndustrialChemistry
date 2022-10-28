package net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.SolidSubstances;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AlkaliSubstanceTab {
    public static final CreativeModeTab ALKALI_SUBSTANCE_TAB = new CreativeModeTab("alkali_substance_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(SolidSubstances.SODIUM.get());
        }
    };
}
