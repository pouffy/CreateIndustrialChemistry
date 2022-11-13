package net.forsteri.createindustrialchemistry.substances.element;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.GasSubstances;
import net.forsteri.createindustrialchemistry.substances.abstracts.RisingGases;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class Chlorine extends RisingGases {
    protected Chlorine(Properties properties) {
        super(properties);
    }

    public Item getTank() {
        return GasSubstances.CHLORINE_TANK.get();
    }

    public static class Flowing extends Chlorine
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

    public static class Source extends Chlorine
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
}
