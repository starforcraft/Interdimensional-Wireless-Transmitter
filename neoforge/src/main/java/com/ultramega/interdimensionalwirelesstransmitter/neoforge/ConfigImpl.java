package com.ultramega.interdimensionalwirelesstransmitter.neoforge;

import com.ultramega.interdimensionalwirelesstransmitter.common.Config;
import com.ultramega.interdimensionalwirelesstransmitter.common.DefaultEnergyUsage;

import net.neoforged.neoforge.common.ModConfigSpec;

import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.createInterdimensionalTranslationKey;

public class ConfigImpl implements Config {
    private final ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
    private final ModConfigSpec spec;
    private final InterdimensionalWirelessTransmitterEntry interdimensionalWirelessTransmitter;

    public ConfigImpl() {
        interdimensionalWirelessTransmitter = new InterdimensionalWirelessTransmitterEntryImpl();
        spec = builder.build();
    }

    public ModConfigSpec getSpec() {
        return spec;
    }

    @Override
    public InterdimensionalWirelessTransmitterEntry getInterdimensionalWirelessTransmitter() {
        return interdimensionalWirelessTransmitter;
    }

    private static String translationKey(final String value) {
        return createInterdimensionalTranslationKey("text.autoconfig", "option." + value);
    }

    private class InterdimensionalWirelessTransmitterEntryImpl implements InterdimensionalWirelessTransmitterEntry {
        private final ModConfigSpec.LongValue energyUsage;

        InterdimensionalWirelessTransmitterEntryImpl() {
            builder.translation(translationKey("interdimensionalWirelessTransmitter")).push("interdimensionalWirelessTransmitter");
            energyUsage = builder
                .translation(translationKey("interdimensionalWirelessTransmitter.energyUsage"))
                .defineInRange("energyUsage", DefaultEnergyUsage.INTERDIMENSIONAL_WIRELESS_TRANSMITTER_USAGE, 0, Long.MAX_VALUE);
            builder.pop();
        }

        @Override
        public long getEnergyUsage() {
            return energyUsage.get();
        }
    }
}
