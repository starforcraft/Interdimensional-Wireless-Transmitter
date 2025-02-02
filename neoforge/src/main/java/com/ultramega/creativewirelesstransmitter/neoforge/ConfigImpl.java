package com.ultramega.creativewirelesstransmitter.neoforge;

import com.ultramega.creativewirelesstransmitter.common.Config;
import com.ultramega.creativewirelesstransmitter.common.DefaultEnergyUsage;

import net.neoforged.neoforge.common.ModConfigSpec;

import static com.ultramega.creativewirelesstransmitter.common.CreativeWirelessTransmitterIdentifierUtil.createCreativeWirelessTransmitterTranslationKey;

public class ConfigImpl implements Config {
    private final ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
    private final ModConfigSpec spec;
    private final CreativeWirelessTransmitterEntry creativeWirelessTransmitter;

    public ConfigImpl() {
        creativeWirelessTransmitter = new CreativeWirelessTransmitterEntryImpl();
        spec = builder.build();
    }

    public ModConfigSpec getSpec() {
        return spec;
    }

    @Override
    public CreativeWirelessTransmitterEntry getCreativeWirelessTransmitter() {
        return creativeWirelessTransmitter;
    }

    private static String translationKey(final String value) {
        return createCreativeWirelessTransmitterTranslationKey("text.autoconfig", "option." + value);
    }

    private class CreativeWirelessTransmitterEntryImpl implements CreativeWirelessTransmitterEntry {
        private final ModConfigSpec.LongValue energyUsage;

        CreativeWirelessTransmitterEntryImpl() {
            builder.translation(translationKey("creativeWirelessTransmitter")).push("creativeWirelessTransmitter");
            energyUsage = builder
                .translation(translationKey("creativeWirelessTransmitter.energyUsage"))
                .defineInRange("energyUsage", DefaultEnergyUsage.CREATIVE_WIRELESS_TRANSMITTER_USAGE, 0, Long.MAX_VALUE);
            builder.pop();
        }

        @Override
        public long getEnergyUsage() {
            return energyUsage.get();
        }
    }
}
