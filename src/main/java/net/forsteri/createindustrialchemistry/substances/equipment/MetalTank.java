package net.forsteri.createindustrialchemistry.substances.equipment;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.Equipments;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.forsteri.createindustrialchemistry.substances.utilities.fluids.TankPickup;
import net.forsteri.createindustrialchemistry.utility.ChemUtil;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.CriteriaTriggers;
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
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@SuppressWarnings("deprecation")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MetalTank extends BucketItem {

    protected final Fluid content;
    protected final Collection<CreativeModeTab> creativeModeTabs;
    protected final java.util.function.Supplier<? extends Fluid> fluidSupplier;
    public MetalTank(java.util.function.Supplier<? extends Fluid> supplier, Item.Properties builder, CreativeModeTab... creativeModeTabs) {
        super(supplier, builder);
        this.content = null;
        this.fluidSupplier = supplier;
        this.creativeModeTabs = new ArrayList<>(
                Arrays.asList(creativeModeTabs)
        );
        this.creativeModeTabs.add(CreativeModeTab.TAB_SEARCH);
    }

    public MetalTank(Fluid pContent, Item.Properties pProperties, CreativeModeTab... creativeModeTabs) {
        super(pContent, pProperties);
        this.content = pContent;
        this.fluidSupplier = pContent.delegate;
        this.creativeModeTabs = new ArrayList<>(
                Arrays.asList(creativeModeTabs)
        );
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        ChemUtil.addHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced, this.getRegistryName());
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
        return !pPlayer.getAbilities().instabuild ? (
                ((FlowingFluid) ((MetalTank) pBucketStack.getItem()).fluidSupplier.get()).damageTank() ?
                        new ItemStack(Equipments.DIRTY_TANK.get()) :
                        new ItemStack(Equipments.EMPTY_METAL_TANK.get())
        ) : pBucketStack;
    }

    @Override
    public Collection<CreativeModeTab> getCreativeTabs() {
        return this.creativeModeTabs;
    }
}
