package com.ultramega.interdimensionalwirelesstransmitter.common;

import com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter.InterdimensionalWirelessTransmitterScreen;
import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Menus;

public abstract class AbstractClientModInitializer {
    protected static void registerScreens(final com.refinedmods.refinedstorage.common.AbstractClientModInitializer.ScreenRegistration registration) {
        registration.register(Menus.INSTANCE.getInterdimensionalWirelessTransmitter(), InterdimensionalWirelessTransmitterScreen::new);
    }
}
