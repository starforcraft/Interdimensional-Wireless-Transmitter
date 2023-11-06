package com.ultramega.creativewirelesstransmitter.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CreativeWirelessTransmitterConfig {
    public static ForgeConfigSpec.IntValue CREATIVE_WIRELESS_TRANSMITTER_ENERGY_CONSUME;

    public static void init(ForgeConfigSpec.Builder common) {
        common.push("Creative Wireless Transmitter Options");
            CREATIVE_WIRELESS_TRANSMITTER_ENERGY_CONSUME = common
                    .comment("\nThe energy used by the Creative Wireless Transmitter")
                    .defineInRange("creativeWirelessTransmitterEnergyUsage", 0, 0, Integer.MAX_VALUE);
        common.pop();

        common.build();
    }
}
