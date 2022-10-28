package net.forsteri.createindustrialchemistry.item.abstracts.properties.inFluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.extensions.IForgeItem;

@SuppressWarnings("rawtypes")
public interface ExplodeInFluid extends IForgeItem {
    TagKey[] fluidExplodesIn();

    ItemLike returnItem();

    default boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        boolean bool = false;
        for(TagKey fluidTagKey: fluidExplodesIn()){
            if(entity.isEyeInFluid(fluidTagKey)) {
                bool = true;
                break;
            }
        }

        if(bool){
            entity.level.gameEvent(GameEvent.EXPLODE, new BlockPos(entity.getX(), entity.getY(), entity.getZ()));
            Explosion derp = new Explosion(entity.level, entity, entity.getX(), entity.getY()+2, entity.getZ(), 3, false, Explosion.BlockInteraction.BREAK);
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
            entity.level.removeBlock(new BlockPos(
                    Math.floor(entity.getX()),
                    Math.floor(entity.getY()),
                    Math.floor(entity.getZ())), false);
            entity.discard();
        }
        return false;
    }
}
