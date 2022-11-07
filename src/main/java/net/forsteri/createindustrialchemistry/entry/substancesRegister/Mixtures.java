package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.families.mixtures.MixtureTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import static net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters.*;

public class Mixtures {
    public static void register(){}

    public static final RegistryObject<Block> ACIDIC_SAND = BLOCKS.register("acidic_sand",
            () -> new FallingBlock(BlockBehaviour.Properties.of(Material.SAND)));

    public static final RegistryObject<Item> ACIDIC_SAND_ITEM = ITEMS.register("acidic_sand",
            () -> new BlockItem(ACIDIC_SAND.get(), new Item.Properties().tab(MixtureTab.MIXTURE_TAB)));
}
