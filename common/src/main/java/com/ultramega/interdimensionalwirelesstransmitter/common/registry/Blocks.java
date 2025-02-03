package com.ultramega.interdimensionalwirelesstransmitter.common.registry;

import com.ultramega.interdimensionalwirelesstransmitter.common.ContentIds;
import com.ultramega.interdimensionalwirelesstransmitter.common.ContentNames;
import com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter.InterdimensionalWirelessTransmitterBlock;

import com.refinedmods.refinedstorage.common.content.BlockColorMap;
import com.refinedmods.refinedstorage.common.support.BaseBlockItem;

import static com.refinedmods.refinedstorage.common.content.Blocks.COLOR;

public final class Blocks {
    public static final Blocks INSTANCE = new Blocks();

    private final BlockColorMap<InterdimensionalWirelessTransmitterBlock, BaseBlockItem> interdimensionalWirelessTransmitter = new BlockColorMap<>(
        InterdimensionalWirelessTransmitterBlock::new,
        ContentIds.INTERDIMENSIONAL_WIRELESS_TRANSMITTER,
        ContentNames.INTERDIMENSIONAL_WIRELESS_TRANSMITTER,
        COLOR
    );

    private Blocks() {
    }

    public BlockColorMap<InterdimensionalWirelessTransmitterBlock, BaseBlockItem> getInterdimensionalWirelessTransmitter() {
        return interdimensionalWirelessTransmitter;
    }
}
