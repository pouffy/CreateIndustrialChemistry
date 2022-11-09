package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.CompoundSubstanceTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.ElementarySubstanceTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families.AlkaliSubstanceTab;
import net.forsteri.createindustrialchemistry.substances.abstracts.ChemicalSubstance;
import net.forsteri.createindustrialchemistry.substances.element.Potassium;
import net.forsteri.createindustrialchemistry.substances.element.Sodium;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import static net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters.ITEMS;

@SuppressWarnings("unused")
public class SolidSubstances {
    public static void register(){}

    public static final RegistryObject<Item> SODIUM = ITEMS.register("sodium",
            () -> new Sodium(new Item.Properties(), ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB, AlkaliSubstanceTab.ALKALI_SUBSTANCE_TAB));

    public static final RegistryObject<Item> POTASSIUM = ITEMS.register("potassium",
            () -> new Potassium(new Item.Properties(), ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB, AlkaliSubstanceTab.ALKALI_SUBSTANCE_TAB));

    public static final RegistryObject<Item> SODIUM_HYDROXIDE = ITEMS.register("sodium_hydroxide",
            () -> new ChemicalSubstance(new Item.Properties().fireResistant(), CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB, AlkaliSubstanceTab.ALKALI_SUBSTANCE_TAB));

    public static final RegistryObject<Item> POTASSIUM_HYDROXIDE = ITEMS.register("potassium_hydroxide",
            () -> new ChemicalSubstance(new Item.Properties().fireResistant(), CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB, AlkaliSubstanceTab.ALKALI_SUBSTANCE_TAB));

    public static final RegistryObject<Item> CALCIUM_CARBONATE = ITEMS.register("calcium_carbonate",
            () -> new ChemicalSubstance(new Item.Properties(), CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB, AlkaliSubstanceTab.ALKALI_SUBSTANCE_TAB));

    public static final RegistryObject<Item> CALCIUM_CHLORIDE = ITEMS.register("calcium_chloride",
            () -> new ChemicalSubstance(new Item.Properties(), CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB));

    public static final RegistryObject<Item> CRUDE_SILICON_DIOXIDE = ITEMS.register("crude_silicon_dioxide",
            () -> new ChemicalSubstance(new Item.Properties(), CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB));

    public static final RegistryObject<Item> CRUDE_SODIUM_METASILICATE = ITEMS.register("crude_sodium_metasilicate",
            () -> new ChemicalSubstance(new Item.Properties(), CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB));

    public static final RegistryObject<Item> SALT = ITEMS.register("salt",
            () -> new ChemicalSubstance(new Item.Properties(), CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB));

    public static final RegistryObject<Item> METASILICIC_ACID = ITEMS.register("metasilicic_acid",
            () -> new ChemicalSubstance(new Item.Properties(), CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB));

    public static final RegistryObject<Item> GRAPHITE = ITEMS.register("graphite",
            () -> new ChemicalSubstance(new Item.Properties(), ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB));

    public static final RegistryObject<Item> SILICON_DIOXIDE = ITEMS.register("silicon_dioxide",
            () -> new ChemicalSubstance(new Item.Properties(), CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB));

    public static final RegistryObject<Item> SILICON = ITEMS.register("silicon",
            () -> new ChemicalSubstance(new Item.Properties(), ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB));
}
