package com.ultramega.creativewirelesstransmitter.block;

import com.refinedmods.refinedstorage.block.WirelessTransmitterBlock;
import com.refinedmods.refinedstorage.container.factory.BlockEntityMenuProvider;
import com.refinedmods.refinedstorage.util.NetworkUtils;
import com.ultramega.creativewirelesstransmitter.blockentity.CreativeWirelessTransmitterBlockEntity;
import com.ultramega.creativewirelesstransmitter.container.CreativeWirelessTransmitterContainerMenu;
import com.ultramega.creativewirelesstransmitter.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class CreativeWirelessTransmitterBlock extends WirelessTransmitterBlock {

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CreativeWirelessTransmitterBlockEntity(pos, state);
    }

    @NotNull
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        InteractionResult result = ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.changeBlockColor(state, player.getItemInHand(hand), level, pos, player);
        if (result != InteractionResult.PASS) {
            return result;
        }

        if (!level.isClientSide) {
            return NetworkUtils.attemptModify(level, pos, player, () -> NetworkHooks.openScreen(
                    (ServerPlayer) player,
                    new BlockEntityMenuProvider<CreativeWirelessTransmitterBlockEntity>(
                            Component.translatable(getDescriptionId()),
                            (blockEntity, windowId, inventory, p) -> new CreativeWirelessTransmitterContainerMenu(blockEntity, player, windowId),
                            pos
                    ),
                    pos
            ));
        }

        return InteractionResult.SUCCESS;
    }
}
