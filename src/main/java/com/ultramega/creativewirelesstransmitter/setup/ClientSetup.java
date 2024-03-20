package com.ultramega.creativewirelesstransmitter.setup;

import com.ultramega.creativewirelesstransmitter.gui.CreativeWirelessTransmitterScreen;
import com.ultramega.creativewirelesstransmitter.registry.ModContainerMenus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public final class ClientSetup {
    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent e) {
        e.register(ModContainerMenus.CREATIVE_WIRELESS_TRANSMITTER.get(), CreativeWirelessTransmitterScreen::new);
    }
}
