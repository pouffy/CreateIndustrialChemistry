package net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes;

import net.forsteri.createindustrialchemistry.substances.abstracts.FluidBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class PoisonousFluidBlock extends FluidBlock {

    protected final int level;
    protected final boolean killMob;
    public PoisonousFluidBlock(Supplier<? extends FlowingFluid> pFluid, Properties pProperties) {
        super(pFluid, pProperties);
        this.level = 1;
        this.killMob = true;
    }
    public PoisonousFluidBlock(Supplier<? extends FlowingFluid> pFluid, Properties pProperties, boolean killMob) {
        super(pFluid, pProperties);
        this.level = 1;
        this.killMob = killMob;
    }
    public PoisonousFluidBlock(Supplier<? extends FlowingFluid> pFluid, Properties pProperties, int level) {
        super(pFluid, pProperties);
        this.level = level;
        this.killMob = true;
    }
    public PoisonousFluidBlock(Supplier<? extends FlowingFluid> pFluid, Properties pProperties, int level, boolean killMob) {
        super(pFluid, pProperties);
        this.level = level;
        this.killMob = killMob;
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if(pEntity instanceof LivingEntity entity){
            if(!entity.hasEffect(MobEffects.POISON)) {
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, this.level));
            }
            if(!entity.hasEffect(MobEffects.CONFUSION)) {
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 1200, this.level));
            }
            if(killMob && entity.getHealth() <= 1){
                entity.hurt(DamageSource.MAGIC, 1);
            }
        }
        super.entityInside(pState, pLevel, pPos, pEntity);
    }
}
