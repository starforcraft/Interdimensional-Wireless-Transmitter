package com.ultramega.interdimensionalwirelesstransmitter.common;

import net.minecraft.network.chat.MutableComponent;

import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.createInterdimensionalTranslation;

public final class ContentNames {
    public static final MutableComponent INTERDIMENSIONAL_WIRELESS_TRANSMITTER = name("interdimensional_wireless_transmitter");

    private ContentNames() {
    }

    private static MutableComponent name(final String name) {
        return createInterdimensionalTranslation("block", name);
    }
}
