package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.ElementarySubstanceTab;
import net.forsteri.createindustrialchemistry.substances.equipment.MetalTank;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.RegistryObject;
import static net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters.*;

public class Equipments {
    public static void register(){}

    public static final RegistryObject<Item> EMPTY_TANK = ITEMS.register("empty_tank",
            () -> new MetalTank(
                    Fluids.EMPTY,
                    new Item.Properties()
                            .tab(ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB)
                            .stacksTo(16)
            ));

}
