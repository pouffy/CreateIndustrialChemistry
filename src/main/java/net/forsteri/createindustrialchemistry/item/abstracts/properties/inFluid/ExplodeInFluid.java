package net.forsteri.createindustrialchemistry.item.abstracts.properties;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public interface ExplodeInFluid<T> {
    TagKey<Fluid> explodeInFluid();
}
