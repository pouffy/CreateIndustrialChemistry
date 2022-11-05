package net.forsteri.createindustrialchemistry.substances.abstracts.properties.inFluid;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.LiquidSubstances;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.extensions.IForgeItem;

public interface ExplodeInFluid extends IForgeItem {
    Block[] fluidExplodesIn();

    ItemLike returnItem();

    default boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        boolean bool = false;
        for(Block fluidTagKey: fluidExplodesIn()){
            if(entity.level.getBlockState(
                    new BlockPos(entity.getX(), entity.getY(), entity.getZ())
            ).is(fluidTagKey)) {
                bool = true;
                break;
            }
        }

        if(bool){
            beforeExplode(stack, entity);
            entity.level.gameEvent(GameEvent.EXPLODE, new BlockPos(entity.getX(), entity.getY(), entity.getZ()));
            Explosion derp = new Explosion(entity.level, entity, entity.getX(), entity.getY(), entity.getZ(), 3, false, Explosion.BlockInteraction.BREAK);
            if (!entity.level.isClientSide()) {
                derp.explode();
            }
            derp.finalizeExplosion(true);
            if (entity.level.isClientSide()){
                entity.level.addParticle(
                        ParticleTypes.EXPLOSION,
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        0,
                        0,
                        0
                );
            }
            ItemEntity returnItem = new ItemEntity(
                    entity.level,
                    entity.getX(),
                    entity.getY(),
                    entity.getZ(),
                    new ItemStack(
                            returnItem()
                    )
            );
            returnItem.setRemainingFireTicks(2147483647);
            entity.level.addFreshEntity(returnItem);
            afterExplode(stack, entity);
            entity.discard();
        }
        return false;
    }

    default void beforeExplode(ItemStack stack, ItemEntity entity) {}
    default void afterExplode(ItemStack stack, ItemEntity entity) {}
}
