package net.forsteri.createindustrialchemistry.utility;

import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.TooltipHelper;
import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
public class ChemUtil {
    public static void addHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced, ResourceLocation registryName) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Lang.translateDirectWithOutModId(
                            "iupac."+ CreateIndustrialChemistry.MOD_ID+".format",
                            Lang.translateDirectWithOutModId("iupac." + CreateIndustrialChemistry.MOD_ID + "." + registryName.getPath())
                                    .withStyle(ChatFormatting.GRAY)
                    )
                    .withStyle(ChatFormatting.DARK_GRAY));

            pTooltipComponents.add(Lang.translateDirectWithOutModId(
                            "formula."+ CreateIndustrialChemistry.MOD_ID+".format",
                            Lang.translateDirectWithOutModId("formula." + CreateIndustrialChemistry.MOD_ID + "." + registryName.getPath())
                                    .withStyle(ChatFormatting.GRAY)
                    )
                    .withStyle(ChatFormatting.DARK_GRAY));
        }else {
            pTooltipComponents.add(TooltipHelper.holdShift(ItemDescription.Palette.Blue, Screen.hasShiftDown()));
        }
    }
}
