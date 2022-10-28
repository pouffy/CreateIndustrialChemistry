package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.CompoundSubstanceTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.ElementarySubstanceTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families.AlkaliSubstanceTab;
import net.forsteri.createindustrialchemistry.substances.compound.SodiumHydroxide;
import net.forsteri.createindustrialchemistry.substances.element.Sodium;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import static net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters.ITEMS;

public class SolidSubstances {
    public static void register(){}

    public static final RegistryObject<Item> SODIUM = ITEMS.register("sodium",
            () -> new Sodium(new Item.Properties(), ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB, AlkaliSubstanceTab.ALKALI_SUBSTANCE_TAB));

    public static final RegistryObject<Item> SODIUM_HYDROXIDE = ITEMS.register("sodium_hydroxide",
            () -> new SodiumHydroxide(new Item.Properties().fireResistant(), CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB, AlkaliSubstanceTab.ALKALI_SUBSTANCE_TAB));
}
