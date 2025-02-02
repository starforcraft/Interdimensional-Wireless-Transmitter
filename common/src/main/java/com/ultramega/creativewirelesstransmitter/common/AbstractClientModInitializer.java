package com.ultramega.creativewirelesstransmitter.common;

import com.ultramega.creativewirelesstransmitter.common.creativewirelesstransmitter.CreativeWirelessTransmitterScreen;
import com.ultramega.creativewirelesstransmitter.common.registry.Menus;

public abstract class AbstractClientModInitializer {
    protected static void registerScreens(final com.refinedmods.refinedstorage.common.AbstractClientModInitializer.ScreenRegistration registration) {
        registration.register(Menus.INSTANCE.getCreativeWirelessTransmitter(), CreativeWirelessTransmitterScreen::new);
    }
}
