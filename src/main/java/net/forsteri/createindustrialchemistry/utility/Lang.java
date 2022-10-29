package net.forsteri.createindustrialchemistry.utility;

import com.simibubi.create.foundation.utility.Components;
import com.simibubi.create.foundation.utility.LangBuilder;
import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.minecraft.network.chat.MutableComponent;

public class Lang {
    public static MutableComponent translateDirectWithModId(String key, Object... args) {
        return Components.translatable(CreateIndustrialChemistry.MOD_ID + "." + key, resolveBuilders(args));
    }

    public static MutableComponent translateDirectWithOutModId(String key, Object... args) {
        return Components.translatable(key, resolveBuilders(args));
    }

    public static Object[] resolveBuilders(Object[] args) {
        for (int i = 0; i < args.length; i++)
            if (args[i]instanceof LangBuilder cb)
                args[i] = cb.component();
        return args;
    }
}
