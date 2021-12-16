package com.YTrollman.CreativeWirelessTransmitter.registry;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.container.CreativeWirelessTransmitterContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CreativeWirelessTransmitter.MOD_ID)
public class ModContainers {

    @ObjectHolder("creative_wireless_transmitter")
    public static final MenuType<CreativeWirelessTransmitterContainerMenu> CREATIVE_WIRELESS_TRANSMITTER = null;
}
