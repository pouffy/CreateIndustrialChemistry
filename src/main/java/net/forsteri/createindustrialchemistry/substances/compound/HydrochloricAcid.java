package net.forsteri.createindustrialchemistry.substances.compound;

import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.AqueousSolutions;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.LiquidSubstances;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.forsteri.createindustrialchemistry.substances.element.Hydrogen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class HydrochloricAcid extends FlowingFluid {

    protected HydrochloricAcid(Properties properties) {
        super(properties);
    }

    @Override
    public Item getTank() {
        return AqueousSolutions.HYDROCHLORIC_ACID_TANK.get();
    }

    public static class Flowing extends HydrochloricAcid
    {
        public Flowing(Properties properties)
        {
            super(properties);
            registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
        }

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends HydrochloricAcid
    {
        public Source(Properties properties)
        {
            super(properties);
        }

        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }

    @Override
    public boolean damageTank() {
        return true;
    }
}
