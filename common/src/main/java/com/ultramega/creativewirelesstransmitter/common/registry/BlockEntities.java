package com.ultramega.creativewirelesstransmitter.common.registry;

import com.ultramega.creativewirelesstransmitter.common.creativewirelesstransmitter.CreativeWirelessTransmitterBlockEntity;

import java.util.function.Supplier;
import javax.annotation.Nullable;

import net.minecraft.world.level.block.entity.BlockEntityType;

import static java.util.Objects.requireNonNull;

public final class BlockEntities {
    public static final BlockEntities INSTANCE = new BlockEntities();

    @Nullable
    private Supplier<BlockEntityType<CreativeWirelessTransmitterBlockEntity>> creativeWirelessTransmitter;

    public BlockEntityType<CreativeWirelessTransmitterBlockEntity> getCreativeWirelessTransmitter() {
        return requireNonNull(creativeWirelessTransmitter).get();
    }

    public void setCreativeWirelessTransmitter(final Supplier<BlockEntityType<CreativeWirelessTransmitterBlockEntity>> supplier) {
        this.creativeWirelessTransmitter = supplier;
    }
}
