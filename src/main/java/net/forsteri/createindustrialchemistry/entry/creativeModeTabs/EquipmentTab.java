package net.forsteri.createindustrialchemistry.entry.creativeModeTabs;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.Equipments;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

@MethodsReturnNonnullByDefault
public class EquipmentTab {
    public static final CreativeModeTab EQUIPMENT_TAB = new CreativeModeTab("equipment_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Equipments.EMPTY_METAL_TANK.get());
        }
    };
}
