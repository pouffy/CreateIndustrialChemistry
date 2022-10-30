package net.forsteri.createindustrialchemistry.substances.equipment;

import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.TooltipHelper;
import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.Equipments;
import net.forsteri.createindustrialchemistry.substances.utilities.fluids.TankPickup;
import net.forsteri.createindustrialchemistry.utility.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;


@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MetalTank extends BucketItem {

    protected final Fluid content;
    protected final java.util.function.Supplier<? extends Fluid> fluidSupplier;
    public MetalTank(java.util.function.Supplier<? extends Fluid> supplier, Item.Properties builder) {
        super(supplier, builder);
        this.content = null;
        this.fluidSupplier = supplier;
    }

    public MetalTank(Fluid pContent, Item.Properties pProperties) {
        super(pContent, pProperties);
        this.content = pContent;
        this.fluidSupplier = pContent.delegate;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Lang.translateDirectWithOutModId(
                            "iupac."+ CreateIndustrialChemistry.MOD_ID+".format",
                            Lang.translateDirectWithOutModId("iupac." + CreateIndustrialChemistry.MOD_ID + "." + Objects.requireNonNull(this.getRegistryName()).getPath())
                                    .withStyle(ChatFormatting.GRAY)
                    )
                    .withStyle(ChatFormatting.DARK_GRAY));
        }else {
            pTooltipComponents.add(TooltipHelper.holdShift(ItemDescription.Palette.Blue, Screen.hasShiftDown()));
        }
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(pLevel, pPlayer, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(pPlayer, pLevel, itemstack, blockhitresult);
        if (ret != null) return ret;
        if (blockhitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getDirection();
            BlockPos blockPos1 = blockpos.relative(direction);
            if (pLevel.mayInteract(pPlayer, blockpos) && pPlayer.mayUseItemAt(blockPos1, direction, itemstack)) {
                if (this.content == Fluids.EMPTY) {
                    BlockState blockState1 = pLevel.getBlockState(blockpos);
                    if (blockState1.getBlock() instanceof TankPickup tankPickup) {
                        ItemStack itemStack1 = tankPickup.tankPickupBlock(pLevel, blockpos, blockState1);
                        if (!itemStack1.isEmpty()) {
                            pPlayer.awardStat(Stats.ITEM_USED.get(this));
                            tankPickup.getPickupSound(blockState1).ifPresent((p_150709_) -> pPlayer.playSound(p_150709_, 1.0F, 1.0F));
                            pLevel.gameEvent(pPlayer, GameEvent.FLUID_PICKUP, blockpos);
                            ItemStack itemStack2 = ItemUtils.createFilledResult(itemstack, pPlayer, itemStack1);
                            if (!pLevel.isClientSide) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)pPlayer, itemStack1);
                            }

                            return InteractionResultHolder.sidedSuccess(itemStack2, pLevel.isClientSide());
                        }
                    }

                    return InteractionResultHolder.fail(itemstack);
                } else {
                    BlockState blockstate = pLevel.getBlockState(blockpos);
                    BlockPos blockpos2 = canBlockContainFluid(pLevel, blockpos, blockstate) ? blockpos : blockPos1;
                    if (this.emptyContents(pPlayer, pLevel, blockpos2, blockhitresult)) {
                        this.checkExtraContent(pPlayer, pLevel, itemstack, blockpos2);
                        if (pPlayer instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)pPlayer, blockpos2, itemstack);
                        }

                        pPlayer.awardStat(Stats.ITEM_USED.get(this));
                        return InteractionResultHolder.sidedSuccess(getEmptySuccessItem(itemstack, pPlayer), pLevel.isClientSide());
                    } else {
                        return InteractionResultHolder.fail(itemstack);
                    }
                }
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }

    protected boolean canBlockContainFluid(Level worldIn, BlockPos posIn, BlockState blockstate)
    {
        return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer)blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, this.content);
    }

    public static ItemStack getEmptySuccessItem(ItemStack pBucketStack, Player pPlayer) {
        return !pPlayer.getAbilities().instabuild ? new ItemStack(Equipments.EMPTY_TANK.get()) : pBucketStack;
    }
}
