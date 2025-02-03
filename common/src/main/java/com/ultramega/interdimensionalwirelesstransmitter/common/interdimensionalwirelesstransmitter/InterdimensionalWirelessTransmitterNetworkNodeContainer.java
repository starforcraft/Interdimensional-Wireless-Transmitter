package com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter;

import com.refinedmods.refinedstorage.api.network.impl.node.AbstractNetworkNode;
import com.refinedmods.refinedstorage.common.api.support.network.item.NetworkItemPlayerValidator;
import com.refinedmods.refinedstorage.common.support.network.InWorldNetworkNodeContainerImpl;

import net.minecraft.world.level.Level;

public class InterdimensionalWirelessTransmitterNetworkNodeContainer extends InWorldNetworkNodeContainerImpl implements NetworkItemPlayerValidator {
    private final InterdimensionalWirelessTransmitterBlockEntity blockEntity;
    private final AbstractNetworkNode node;

    InterdimensionalWirelessTransmitterNetworkNodeContainer(final InterdimensionalWirelessTransmitterBlockEntity blockEntity,
                                                            final AbstractNetworkNode node,
                                                            final String name,
                                                            final InterdimensionalWirelessTransmitterConnectionStrategy connectionStrategy) {
        super(blockEntity, node, name, 0, connectionStrategy, null);
        this.blockEntity = blockEntity;
        this.node = node;
    }

    @Override
    public boolean isValid(final PlayerCoordinates coordinates) {
        final Level level = blockEntity.getLevel();
        if (level == null) {
            return false;
        }
        return node.isActive();
    }
}
