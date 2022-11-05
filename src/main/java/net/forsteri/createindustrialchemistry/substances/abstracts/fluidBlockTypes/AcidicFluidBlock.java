package net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes;

import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.forsteri.createindustrialchemistry.substances.abstracts.FluidBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.eventbus.EventBus;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class AcidicFluidBlock extends FluidBlock {

    protected final float pH;
    public AcidicFluidBlock(Supplier<? extends FlowingFluid> pFluid, Properties pProperties, float pH) {
        super(pFluid, pProperties);
        this.pH = pH;
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if(pEntity instanceof LivingEntity entity){
            AttributeInstance attribute =  entity.getAttributes().getInstance(Attributes.MAX_HEALTH);
            if(entity.getHealth() < 1){
                pEntity.hurt(DamageSource.ON_FIRE, 1f);
            }
            pEntity.hurt(DamageSource.ON_FIRE, (7-this.pH)/10);
            assert attribute != null;
            attribute.addPermanentModifier(new AttributeModifier(/*MODIFIER_ID,*/ "acid", -(7+this.pH)/100, AttributeModifier.Operation.ADDITION));
        }
        super.entityInside(pState, pLevel, pPos, pEntity);
    }
}
