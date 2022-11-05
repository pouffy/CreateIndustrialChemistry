package net.forsteri.createindustrialchemistry.entry.handlers;

import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(CreateIndustrialChemistry.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    /*public static void register(){
        int disc = 0;
        INSTANCE.registerMessage(disc++,
                EntityCorrosionPacket.class,
                )
    }*/
}
