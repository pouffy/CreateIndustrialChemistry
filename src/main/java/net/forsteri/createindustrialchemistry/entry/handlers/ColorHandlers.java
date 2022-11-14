package net.forsteri.createindustrialchemistry.entry.handlers;

import com.mojang.logging.LogUtils;
import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters;
import net.forsteri.createindustrialchemistry.substances.equipment.MetalTank;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = CreateIndustrialChemistry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ColorHandlers {
    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event)
    {
        LogUtils.getLogger().info("REGISTER ITEM COLORS");
        for (RegistryObject<Item> item: DeferredRegisters.ITEMS.getEntries()) {
            if(item.get() instanceof MetalTank) {
                event.getItemColors().register(
                        (pStack, pTintIndex) -> (pTintIndex == 1? ((MetalTank) item.get()).getColor(): 0xFFFFFF), item.get()
                );
            }
        }

    }
}
