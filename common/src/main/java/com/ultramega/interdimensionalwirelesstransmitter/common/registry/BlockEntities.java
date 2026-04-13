package com.ultramega.interdimensionalwirelesstransmitter.common.registry;

import com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter.InterdimensionalWirelessTransmitterBlockEntity;

import java.util.function.Supplier;

import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jspecify.annotations.Nullable;

import static java.util.Objects.requireNonNull;

public final class BlockEntities {
    public static final BlockEntities INSTANCE = new BlockEntities();

    @Nullable
    private Supplier<BlockEntityType<InterdimensionalWirelessTransmitterBlockEntity>> interdimensionalWirelessTransmitter;

    public BlockEntityType<InterdimensionalWirelessTransmitterBlockEntity> getInterdimensionalWirelessTransmitter() {
        return requireNonNull(this.interdimensionalWirelessTransmitter).get();
    }

    public void setInterdimensionalWirelessTransmitter(final Supplier<BlockEntityType<InterdimensionalWirelessTransmitterBlockEntity>> supplier) {
        this.interdimensionalWirelessTransmitter = supplier;
    }
}
