package com.ultramega.creativewirelesstransmitter.common;

import net.minecraft.network.chat.MutableComponent;

import static com.ultramega.creativewirelesstransmitter.common.CreativeWirelessTransmitterIdentifierUtil.createCreativeWirelessTransmitterTranslation;

public final class ContentNames {
    public static final MutableComponent CREATIVE_WIRELESS_TRANSMITTER = name("creative_wireless_transmitter");

    private ContentNames() {
    }

    private static MutableComponent name(final String name) {
        return createCreativeWirelessTransmitterTranslation("block", name);
    }
}
