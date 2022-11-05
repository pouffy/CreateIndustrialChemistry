package net.forsteri.createindustrialchemistry.substances.element;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.GasSubstances;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.SolidSubstances;
import net.forsteri.createindustrialchemistry.substances.abstracts.ChemicalSubstance;
import net.forsteri.createindustrialchemistry.substances.abstracts.properties.inFluid.ExplodeInFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class Sodium extends ChemicalSubstance implements ExplodeInFluid {
    public Sodium(Properties pProperties, CreativeModeTab... creativeModeTabs) {
        super(pProperties, creativeModeTabs);
    }

    @Override
    public Block[] fluidExplodesIn() {
        return new Block[]{Blocks.WATER};
    }

    @Override
    public ItemLike returnItem() {
        return SolidSubstances.SODIUM_HYDROXIDE.get();
    }

    @Override
    public void beforeExplode(ItemStack stack, ItemEntity entity) {
        entity.level.setBlock(new BlockPos(
                Math.floor(entity.getX()),
                Math.floor(entity.getY()),
                Math.floor(entity.getZ())), Blocks.AIR.defaultBlockState(),3);
    }

    @Override
    public void afterExplode(ItemStack stack, ItemEntity entity){
        entity.level.setBlock(new BlockPos(
                Math.floor(entity.getX()),
                Math.floor(entity.getY()),
                Math.floor(entity.getZ())), GasSubstances.HYDROGEN_BLOCK.get().defaultBlockState(),3);
    }
}
