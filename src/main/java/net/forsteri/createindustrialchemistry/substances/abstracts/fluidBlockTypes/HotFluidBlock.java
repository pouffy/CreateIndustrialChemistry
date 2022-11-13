package net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes;

import net.forsteri.createindustrialchemistry.substances.abstracts.FluidBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class HotFluidBlock extends FluidBlock {

    public final int secondsOnFire;

    public HotFluidBlock(Supplier<? extends FlowingFluid> pFluid, Properties pProperties, int secondsOnFire) {
        super(pFluid, pProperties);
        this.secondsOnFire = secondsOnFire;
    }

    public HotFluidBlock(Supplier<? extends FlowingFluid> pFluid, Properties pProperties) {
        super(pFluid, pProperties);
        secondsOnFire = 2;
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if(pEntity instanceof LivingEntity entity){
            pEntity.setSecondsOnFire(secondsOnFire);
            entity.hurt(DamageSource.IN_FIRE, 2);
        }
        super.entityInside(pState, pLevel, pPos, pEntity);
    }
}
