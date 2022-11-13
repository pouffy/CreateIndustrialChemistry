package net.forsteri.createindustrialchemistry.substances.abstracts;

import net.forsteri.createindustrialchemistry.utility.ChemUtil;
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
public class ChemicalSubstance extends Item{
    protected final Collection<CreativeModeTab> creativeModeTabs;

    public ChemicalSubstance(Properties pProperties, CreativeModeTab... creativeModeTabs) {
        super(pProperties);
        this.creativeModeTabs = new ArrayList<>(
                Arrays.asList(creativeModeTabs)
        );
        this.creativeModeTabs.add(CreativeModeTab.TAB_SEARCH);
    }

    @Override
    public Collection<CreativeModeTab> getCreativeTabs() {
        return this.creativeModeTabs;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        ChemUtil.addHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced, this.getRegistryName());
    }
}
