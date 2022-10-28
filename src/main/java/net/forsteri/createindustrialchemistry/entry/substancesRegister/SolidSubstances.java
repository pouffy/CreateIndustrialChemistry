package net.forsteri.createindustrialchemistry.entry.mattersRegister;

import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.CompoundSubstanceTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.ElementarySubstanceTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families.AlkaliSubstanceTab;
import net.forsteri.createindustrialchemistry.item.compound.SodiumHydroxide;
import net.forsteri.createindustrialchemistry.item.element.Sodium;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SolidSubstances {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CreateIndustrialChemistry.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> SODIUM = ITEMS.register("sodium",
            () -> new Sodium(new Item.Properties(), ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB, AlkaliSubstanceTab.ALKALI_SUBSTANCE_TAB));

    public static final RegistryObject<Item> SODIUM_HYDROXIDE = ITEMS.register("sodium_hydroxide",
            () -> new SodiumHydroxide(new Item.Properties().fireResistant(), CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB, AlkaliSubstanceTab.ALKALI_SUBSTANCE_TAB));
}
