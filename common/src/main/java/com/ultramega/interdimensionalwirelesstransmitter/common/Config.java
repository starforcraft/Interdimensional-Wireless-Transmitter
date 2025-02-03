package com.ultramega.interdimensionalwirelesstransmitter.common;

public interface Config {
    InterdimensionalWirelessTransmitterEntry getInterdimensionalWirelessTransmitter();

    interface InterdimensionalWirelessTransmitterEntry {
        long getEnergyUsage();
    }
}
