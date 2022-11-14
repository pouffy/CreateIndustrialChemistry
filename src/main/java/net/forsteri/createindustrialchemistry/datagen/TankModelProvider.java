package net.forsteri.createindustrialchemistry.datagen;

import net.forsteri.createindustrialchemistry.substances.equipment.MetalTank;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

import static net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters.ITEMS;

public class TankModelProvider extends ItemModelProvider {
    public TankModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> item : ITEMS.getEntries()) {
            if(item.get() instanceof MetalTank){
                if(((MetalTank) item.get()).genModel) {
                    getBuilder(Objects.requireNonNull(item.get().getRegistryName()).toString())
                            .parent(new ModelFile.UncheckedModelFile("item/generated"))
                            .texture("layer0", new ResourceLocation(Objects.requireNonNull(item.get().getRegistryName()).getNamespace(), "item/fluid_tanks/empty_metal_tank"))
                            .texture("layer1", new ResourceLocation(Objects.requireNonNull(item.get().getRegistryName()).getNamespace(), "item/fluid_tanks/tank_overlay"))
//                        .override()
                    ;
                }

            }
        }
    }
}
