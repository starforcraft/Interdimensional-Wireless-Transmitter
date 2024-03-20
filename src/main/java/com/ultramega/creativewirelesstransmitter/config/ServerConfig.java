package com.ultramega.creativewirelesstransmitter.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    private final ModConfigSpec spec;
    private final ModConfigSpec.IntValue creativeWirelessTransmitterEnergyConsume;

    public ServerConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("Creative Wireless Transmitter Options");

        creativeWirelessTransmitterEnergyConsume = builder.comment("\nThe energy used by the Creative Wireless Transmitter")
                .defineInRange("creativeWirelessTransmitterEnergyUsage", 0, 0, Integer.MAX_VALUE);

        builder.pop();

        spec = builder.build();
    }

    public ModConfigSpec getSpec() {
        return spec;
    }

    public int getCreativeWirelessTransmitterEnergyConsume() {
        return creativeWirelessTransmitterEnergyConsume.get();
    }
}
