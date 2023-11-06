package com.ultramega.creativewirelesstransmitter.blockentity;

import com.refinedmods.refinedstorage.blockentity.NetworkNodeBlockEntity;
import com.refinedmods.refinedstorage.blockentity.data.BlockEntitySynchronizationSpec;
import com.ultramega.creativewirelesstransmitter.node.CreativeWirelessTransmitterNetworkNode;
import com.ultramega.creativewirelesstransmitter.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class CreativeWirelessTransmitterBlockEntity extends NetworkNodeBlockEntity<CreativeWirelessTransmitterNetworkNode> {
    public static BlockEntitySynchronizationSpec SPEC = BlockEntitySynchronizationSpec.builder()
            .addWatchedParameter(REDSTONE_MODE)
            .build();


    public CreativeWirelessTransmitterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CREATIVE_WIRELESS_TRANSMITTER.get(), pos, state, SPEC, CreativeWirelessTransmitterNetworkNode.class);
    }

    @Override
    @Nonnull
    public CreativeWirelessTransmitterNetworkNode createNode(Level level, BlockPos pos) {
        return new CreativeWirelessTransmitterNetworkNode(level, pos);
    }
}
