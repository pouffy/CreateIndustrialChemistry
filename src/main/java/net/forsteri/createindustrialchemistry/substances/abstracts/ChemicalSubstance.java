package net.forsteri.createindustrialchemistry.substances.abstracts;

import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.TooltipHelper;
import net.forsteri.createindustrialchemistry.utility.Lang;
import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

@ParametersAreNonnullByDefault
public class ChemicalSubstance extends Item {
    protected final Collection<CreativeModeTab> creativeModeTabs;

    public ChemicalSubstance(Properties pProperties, CreativeModeTab... creativeModeTabs) {
        super(pProperties);
        this.creativeModeTabs = new ArrayList<>(
                Arrays.asList(creativeModeTabs)
        );
    }

    @Override
    public Collection<CreativeModeTab> getCreativeTabs() {
        return this.creativeModeTabs;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Lang.translateDirectWithOutModId(
                    "iupac."+CreateIndustrialChemistry.MOD_ID+".format",
                    Lang.translateDirectWithOutModId("iupac." + CreateIndustrialChemistry.MOD_ID + "." + Objects.requireNonNull(this.getRegistryName()).getPath())
                            .withStyle(ChatFormatting.GRAY)
            )
                    .withStyle(ChatFormatting.DARK_GRAY));
        }else {
            pTooltipComponents.add(TooltipHelper.holdShift(ItemDescription.Palette.Blue, Screen.hasShiftDown()));
        }
    }
}
