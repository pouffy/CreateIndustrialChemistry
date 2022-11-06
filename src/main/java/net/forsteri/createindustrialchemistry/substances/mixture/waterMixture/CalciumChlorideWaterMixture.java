package net.forsteri.createindustrialchemistry.substances.mixture.waterMixture;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.LiquidSubstances;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.WaterMixtures;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class CalciumChlorideWaterMixture extends FlowingFluid {

    protected CalciumChlorideWaterMixture(Properties properties) {
        super(properties);
    }

    @Override
    public Item getTank() {
        return WaterMixtures.CALCIUM_CHLORIDE_WATER_MIXTURE_TANK.get();
    }

    public static class Flowing extends CalciumChlorideWaterMixture
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

    public static class Source extends CalciumChlorideWaterMixture
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
