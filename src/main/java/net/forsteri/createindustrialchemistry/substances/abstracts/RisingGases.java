package net.forsteri.createindustrialchemistry.substances.abstracts;

import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.GasSubstances;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@ParametersAreNonnullByDefault
public abstract class RisingGases extends FlowingFluid{
    protected RisingGases(Properties properties) {
        super(properties);
    }

    protected static final ThreadLocal<Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey>> OCCLUSION_CACHE = ThreadLocal.withInitial(() -> {
        Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey> object2byteLinkedOpenHashMap = new Object2ByteLinkedOpenHashMap<>(200) {
            protected void rehash(int p_76102_) {
            }
        };
        object2byteLinkedOpenHashMap.defaultReturnValue((byte)127);
        return object2byteLinkedOpenHashMap;
    });

    protected int sourceNeighborCount(LevelReader pLevel, BlockPos pPos) {
        int i = 0;

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos blockpos = pPos.relative(direction);
            FluidState fluidstate = pLevel.getFluidState(blockpos);
            if (this.isSourceBlockOfThisType(fluidstate)) {
                ++i;
            }
        }

        return i;
    }

    protected boolean isSourceBlockOfThisType(FluidState pState) {
        return pState.getType().isSame(this) && pState.isSource();
    }

    protected void spreadToSides(LevelAccessor p_76015_, BlockPos p_76016_, FluidState p_76017_, BlockState p_76018_) {
        int i = p_76017_.getAmount() - this.getDropOff(p_76015_);
        if (p_76017_.getValue(FALLING)) {
            i = 7;
        }

        if (i > 0) {
            Map<Direction, FluidState> map = this.getSpread(p_76015_, p_76016_, p_76018_);

            for(Map.Entry<Direction, FluidState> entry : map.entrySet()) {
                Direction direction = entry.getKey();
                FluidState fluidstate = entry.getValue();
                BlockPos blockpos = p_76016_.relative(direction);
                BlockState blockstate = p_76015_.getBlockState(blockpos);
                if (this.canSpreadTo(p_76015_, p_76016_, p_76018_, direction, blockpos, blockstate, p_76015_.getFluidState(blockpos), fluidstate.getType())) {
                    this.spreadTo(p_76015_, blockpos, blockstate, direction, fluidstate);
                }
            }

        }
    }

    protected boolean isWaterHole(BlockGetter p_75957_, Fluid p_75958_, BlockPos p_75959_, BlockState p_75960_, BlockPos p_75961_, BlockState p_75962_) {
        if (!this.canPassThroughWall(Direction.DOWN, p_75957_, p_75959_, p_75960_, p_75961_, p_75962_)) {
            return false;
        } else {
            return p_75962_.getFluidState().getType().isSame(this) || this.canHoldFluid(p_75957_, p_75961_, p_75962_, p_75958_);
        }
    }

    protected boolean canPassThrough(BlockGetter p_75964_, Fluid p_75965_, BlockPos p_75966_, BlockState p_75967_, Direction p_75968_, BlockPos p_75969_, BlockState p_75970_, FluidState p_75971_) {
        return !this.isSourceBlockOfThisType(p_75971_) && this.canPassThroughWall(p_75968_, p_75964_, p_75966_, p_75967_, p_75969_, p_75970_) && this.canHoldFluid(p_75964_, p_75969_, p_75970_, p_75965_);
    }

    protected boolean canPassThroughWall(Direction p_76062_, BlockGetter p_76063_, BlockPos p_76064_, BlockState p_76065_, BlockPos p_76066_, BlockState p_76067_) {
        Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey> object2byteLinkedOpenHashMap;
        if (!p_76065_.getBlock().hasDynamicShape() && !p_76067_.getBlock().hasDynamicShape()) {
            object2byteLinkedOpenHashMap = OCCLUSION_CACHE.get();
        } else {
            object2byteLinkedOpenHashMap = null;
        }

        Block.BlockStatePairKey block$blockstatepairkey;
        if (object2byteLinkedOpenHashMap != null) {
            block$blockstatepairkey = new Block.BlockStatePairKey(p_76065_, p_76067_, p_76062_);
            byte b0 = object2byteLinkedOpenHashMap.getAndMoveToFirst(block$blockstatepairkey);
            if (b0 != 127) {
                return b0 != 0;
            }
        } else {
            block$blockstatepairkey = null;
        }

        VoxelShape voxelshape1 = p_76065_.getCollisionShape(p_76063_, p_76064_);
        VoxelShape voxelshape = p_76067_.getCollisionShape(p_76063_, p_76066_);
        boolean flag = !Shapes.mergedFaceOccludes(voxelshape1, voxelshape, p_76062_);
        if (object2byteLinkedOpenHashMap != null) {
            if (object2byteLinkedOpenHashMap.size() == 200) {
                object2byteLinkedOpenHashMap.removeLastByte();
            }

            object2byteLinkedOpenHashMap.putAndMoveToFirst(block$blockstatepairkey, (byte)(flag ? 1 : 0));
        }

        return flag;
    }

    protected boolean canHoldFluid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        Block block = pState.getBlock();
        if (block instanceof LiquidBlockContainer) {
            return ((LiquidBlockContainer)block).canPlaceLiquid(pLevel, pPos, pState, pFluid);
        } else if (!(block instanceof DoorBlock) && !pState.is(BlockTags.SIGNS) && !pState.is(Blocks.LADDER) && !pState.is(Blocks.SUGAR_CANE) && !pState.is(Blocks.BUBBLE_COLUMN)) {
            Material material = pState.getMaterial();
            if (material != Material.PORTAL && material != Material.STRUCTURAL_AIR && material != Material.WATER_PLANT && material != Material.REPLACEABLE_WATER_PLANT) {
                return !material.blocksMotion();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    protected void spreadTo(LevelAccessor pLevel, BlockPos pPos, BlockState pBlockState, Direction pDirection, FluidState pFluidState) {
        if (!pBlockState.isAir()) {
            this.beforeDestroyingBlock(pLevel, pPos, pBlockState);
        }
        pLevel.setBlock(pPos, pFluidState.createLegacyBlock(), 3);

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
                    pLevel.setBlock(pPos.above(), this.defaultFluidState().createLegacyBlock(), 3);
                    pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
                }else{
//                    this.spreadToSides(pLevel, pPos, pState, blockstate);
                    assert true;
                }
            }

        }
    }

    public abstract Item getTank();
}
