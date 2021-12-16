package com.YTrollman.CreativeWirelessTransmitter.blockentity;

import com.YTrollman.CreativeWirelessTransmitter.node.CreativeWirelessTransmitterNetworkNode;
import com.YTrollman.CreativeWirelessTransmitter.registry.ModBlockEntityTypes;
import com.refinedmods.refinedstorage.blockentity.NetworkNodeBlockEntity;
import com.refinedmods.refinedstorage.blockentity.data.BlockEntitySynchronizationParameter;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class CreativeWirelessTransmitterBlockEntity extends NetworkNodeBlockEntity<CreativeWirelessTransmitterNetworkNode> {
    public static final BlockEntitySynchronizationParameter<Integer, CreativeWirelessTransmitterBlockEntity> RANGE = new BlockEntitySynchronizationParameter<>(EntityDataSerializers.INT, 0, t -> t.getNode().getRange());

    public CreativeWirelessTransmitterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.CREATIVE_WIRELESS_TRANSMITTER, pos, state);

        dataManager.addWatchedParameter(RANGE);
    }

    @Override
    @Nonnull
    public CreativeWirelessTransmitterNetworkNode createNode(Level level, BlockPos pos) {
        return new CreativeWirelessTransmitterNetworkNode(level, pos);
    }
}
