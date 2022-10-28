package net.forsteri.createindustrialchemistry.substances.element;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.GasSubstances;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class Hydrogen extends FlowingFluid {
    protected Hydrogen(Properties properties) {
        super(properties);
    }

    @Override
    protected void spread(LevelAccessor pLevel, BlockPos pPos, FluidState pState) {
        if (!pState.isEmpty()) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            BlockPos blockpos = pPos.below();
            BlockState blockState1 = pLevel.getBlockState(blockpos);
            FluidState fluidstate = this.getNewLiquid(pLevel, blockpos, blockState1);
//            if (this.canSpreadTo(pLevel, pPos, blockstate, Direction.UP, blockpos, blockState1, pLevel.getFluidState(blockpos), fluidstate.getType())) {
//                this.spreadTo(pLevel, blockpos, blockState1, Direction.UP, fluidstate);
//                if (this.sourceNeighborCount(pLevel, pPos) >= 3) {
//                    this.spreadToSides(pLevel, pPos, pState, blockstate);
//                }
//            } else
            if (pState.isSource()) {
                if (pLevel.getBlockState(pPos.above()).getBlock().equals(Blocks.AIR) || pPos.getY() == (pLevel.getMaxBuildHeight() -1)) {
                    //        this.spreadTo(pLevel, pPos.above(), blockState1, Direction.UP, fluidstate);
                    pLevel.setBlock(pPos.above(), GasSubstances.HYDROGEN_BLOCK.get().defaultBlockState(), 3);
                    pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
                }else{
//                    this.spreadToSides(pLevel, pPos, pState, blockstate);
                    assert true;
                }
            }

        }
    }

    public static class Flowing extends Hydrogen
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

    public static class Source extends Hydrogen
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
