package net.forsteri.createindustrialchemistry.datagen;

import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.Equipments;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class AutoWaterJsonProvider extends FluidTagsProvider {
    public AutoWaterJsonProvider(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, modId, existingFileHelper);
    }

    @Override
    public void addTags(){
        for(RegistryObject<Fluid> fluid : DeferredRegisters.FLUIDS.getEntries()){
            if(fluid.get() instanceof FlowingFluid) {
                this.tag(FluidTags.WATER).add(fluid.get());
            }
        }
    }
}
