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
        this.interdimensionalWirelessTransmitter = new InterdimensionalWirelessTransmitterEntryImpl();
        this.spec = this.builder.build();
    }

    public ModConfigSpec getSpec() {
        return this.spec;
    }

    @Override
    public InterdimensionalWirelessTransmitterEntry getInterdimensionalWirelessTransmitter() {
        return this.interdimensionalWirelessTransmitter;
    }

    private static String translationKey(final String value) {
        return createInterdimensionalTranslationKey("text.autoconfig", "option." + value);
    }

    private class InterdimensionalWirelessTransmitterEntryImpl implements InterdimensionalWirelessTransmitterEntry {
        private final ModConfigSpec.LongValue energyUsage;

        InterdimensionalWirelessTransmitterEntryImpl() {
            ConfigImpl.this.builder.translation(translationKey("interdimensionalWirelessTransmitter")).push("interdimensionalWirelessTransmitter");
            this.energyUsage = ConfigImpl.this.builder
                .translation(translationKey("interdimensionalWirelessTransmitter.energyUsage"))
                .defineInRange("energyUsage", DefaultEnergyUsage.INTERDIMENSIONAL_WIRELESS_TRANSMITTER_USAGE, 0, Long.MAX_VALUE);
            ConfigImpl.this.builder.pop();
        }

        @Override
        public long getEnergyUsage() {
            return this.energyUsage.get();
        }
    }
}
