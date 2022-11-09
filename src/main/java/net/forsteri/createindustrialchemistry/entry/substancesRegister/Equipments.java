package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.EquipmentTab;
import net.forsteri.createindustrialchemistry.substances.equipment.MetalTank;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.RegistryObject;
import static net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters.*;

public class Equipments {
    public static void register(){}

    public static final RegistryObject<Item> EMPTY_METAL_TANK = ITEMS.register("empty_metal_tank",
            () -> new MetalTank(
                    Fluids.EMPTY,
                    new Item.Properties()
                            .tab(EquipmentTab.EQUIPMENT_TAB)
                            .stacksTo(16)
            ));

    public static final RegistryObject<Item> DIRTY_TANK = ITEMS.register("dirty_metal_tank",
            () -> new Item(
                    new Item.Properties()
                            .tab(EquipmentTab.EQUIPMENT_TAB)
                            .stacksTo(1)
            ));
}
