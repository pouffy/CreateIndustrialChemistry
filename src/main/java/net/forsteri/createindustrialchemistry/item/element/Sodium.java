package net.forsteri.createindustrialchemistry.item.element;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.SolidSubstances;
import net.forsteri.createindustrialchemistry.item.abstracts.ChemicalSubstance;
import net.forsteri.createindustrialchemistry.item.abstracts.properties.inFluid.ExplodeInFluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("rawtypes")
public class Sodium extends ChemicalSubstance implements ExplodeInFluid {
    public Sodium(Properties pProperties, CreativeModeTab... creativeModeTabs) {
        super(pProperties, creativeModeTabs);
    }

    @Override
    public TagKey[] fluidExplodesIn() {
        return new TagKey[]{FluidTags.WATER};
    }

    @Override
    public ItemLike returnItem() {
        return SolidSubstances.SODIUM_HYDROXIDE.get();
    }
}
