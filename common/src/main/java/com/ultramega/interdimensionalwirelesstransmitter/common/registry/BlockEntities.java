package com.ultramega.interdimensionalwirelesstransmitter.common.registry;

import com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter.InterdimensionalWirelessTransmitterBlockEntity;

import java.util.function.Supplier;
import javax.annotation.Nullable;

import net.minecraft.world.level.block.entity.BlockEntityType;

import static java.util.Objects.requireNonNull;

public final class BlockEntities {
    public static final BlockEntities INSTANCE = new BlockEntities();

    @Nullable
    private Supplier<BlockEntityType<InterdimensionalWirelessTransmitterBlockEntity>> interdimensionalWirelessTransmitter;

    public BlockEntityType<InterdimensionalWirelessTransmitterBlockEntity> getInterdimensionalWirelessTransmitter() {
        return requireNonNull(interdimensionalWirelessTransmitter).get();
    }

    public void setInterdimensionalWirelessTransmitter(final Supplier<BlockEntityType<InterdimensionalWirelessTransmitterBlockEntity>> supplier) {
        this.interdimensionalWirelessTransmitter = supplier;
    }
}
