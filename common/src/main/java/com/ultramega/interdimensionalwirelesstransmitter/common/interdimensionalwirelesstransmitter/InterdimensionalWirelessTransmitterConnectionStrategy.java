package com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter;

import com.refinedmods.refinedstorage.common.api.support.network.ConnectionSink;
import com.refinedmods.refinedstorage.common.support.network.ColoredConnectionStrategy;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import static com.refinedmods.refinedstorage.common.support.AbstractDirectionalBlock.tryExtractDirection;

class InterdimensionalWirelessTransmitterConnectionStrategy extends ColoredConnectionStrategy {
    InterdimensionalWirelessTransmitterConnectionStrategy(final Supplier<BlockState> blockStateProvider, final BlockPos origin) {
        super(blockStateProvider, origin);
    }

    @Override
    public void addOutgoingConnections(final ConnectionSink sink) {
        final Direction myDirection = tryExtractDirection(this.blockStateProvider.get());
        if (myDirection == null) {
            return;
        }
        sink.tryConnectInSameDimension(this.origin.relative(myDirection), myDirection.getOpposite());
    }

    @Override
    public boolean canAcceptIncomingConnection(final Direction incomingDirection, final BlockState connectingState) {
        if (!this.colorsAllowConnecting(connectingState)) {
            return false;
        }
        final Direction myDirection = tryExtractDirection(this.blockStateProvider.get());
        return incomingDirection == myDirection;
    }
}
