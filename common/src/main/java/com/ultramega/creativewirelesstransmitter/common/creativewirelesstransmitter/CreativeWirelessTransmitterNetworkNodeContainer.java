package com.ultramega.creativewirelesstransmitter.common.creativewirelesstransmitter;

import com.refinedmods.refinedstorage.api.network.impl.node.AbstractNetworkNode;
import com.refinedmods.refinedstorage.common.api.support.network.item.NetworkItemPlayerValidator;
import com.refinedmods.refinedstorage.common.support.network.InWorldNetworkNodeContainerImpl;

import net.minecraft.world.level.Level;

public class CreativeWirelessTransmitterNetworkNodeContainer extends InWorldNetworkNodeContainerImpl implements NetworkItemPlayerValidator {
    private final CreativeWirelessTransmitterBlockEntity blockEntity;
    private final AbstractNetworkNode node;

    CreativeWirelessTransmitterNetworkNodeContainer(final CreativeWirelessTransmitterBlockEntity blockEntity,
                                                    final AbstractNetworkNode node,
                                                    final String name,
                                                    final CreativeWirelessTransmitterConnectionStrategy connectionStrategy) {
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
