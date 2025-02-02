package com.ultramega.creativewirelesstransmitter.common;

public interface Config {
    CreativeWirelessTransmitterEntry getCreativeWirelessTransmitter();

    interface CreativeWirelessTransmitterEntry {
        long getEnergyUsage();
    }
}
