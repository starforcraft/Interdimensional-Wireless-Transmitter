package com.ultramega.creativewirelesstransmitter.common.registry;

import com.ultramega.creativewirelesstransmitter.common.ContentIds;
import com.ultramega.creativewirelesstransmitter.common.ContentNames;
import com.ultramega.creativewirelesstransmitter.common.creativewirelesstransmitter.CreativeWirelessTransmitterBlock;

import com.refinedmods.refinedstorage.common.content.BlockColorMap;
import com.refinedmods.refinedstorage.common.support.BaseBlockItem;

import static com.refinedmods.refinedstorage.common.content.Blocks.COLOR;

public final class Blocks {
    public static final Blocks INSTANCE = new Blocks();

    private final BlockColorMap<CreativeWirelessTransmitterBlock, BaseBlockItem> creativeWirelessTransmitter = new BlockColorMap<>(
        CreativeWirelessTransmitterBlock::new,
        ContentIds.CREATIVE_WIRELESS_TRANSMITTER,
        ContentNames.CREATIVE_WIRELESS_TRANSMITTER,
        COLOR
    );

    private Blocks() {
    }

    public BlockColorMap<CreativeWirelessTransmitterBlock, BaseBlockItem> getCreativeWirelessTransmitter() {
        return creativeWirelessTransmitter;
    }
}
