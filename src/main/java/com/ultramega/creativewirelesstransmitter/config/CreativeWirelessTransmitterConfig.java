package com.ultramega.creativewirelesstransmitter.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CreativeWirelessTransmitterConfig {

    public static ForgeConfigSpec.IntValue CREATIVE_WIRELESS_TRANSMITTER_ENERGY_CONSUME;

    public static void init(ForgeConfigSpec.Builder client) {

            client.comment("Creative Wireless Transmitter Options");
            
            CREATIVE_WIRELESS_TRANSMITTER_ENERGY_CONSUME = client
            		.comment("\nHow much should the Creative Wireless Transmitter Consume?")
                    .defineInRange("creativeWirelessTransmitterEnergyConsume", 0, 0, 1000000000);
    }
}
