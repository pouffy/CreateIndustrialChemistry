package net.forsteri.createindustrialchemistry.entry.creativeModeTabs;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.Equipments;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.GasSubstances;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

@MethodsReturnNonnullByDefault
public class FluidTab {
    public static final CreativeModeTab FLUID_TAB = new CreativeModeTab("fluid_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(GasSubstances.HYDROGEN_TANK.get());
        }
    };
}
