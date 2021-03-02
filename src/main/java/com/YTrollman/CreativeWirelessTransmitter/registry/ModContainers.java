package com.YTrollman.CreativeWirelessTransmitter.registry;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.container.CreativeWirelessTransmitterContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CreativeWirelessTransmitter.MOD_ID)
public class ModContainers {

    @ObjectHolder("creative_wireless_transmitter")
    public static final ContainerType<CreativeWirelessTransmitterContainer> CREATIVE_WIRELESS_TRANSMITTER = null;
}
