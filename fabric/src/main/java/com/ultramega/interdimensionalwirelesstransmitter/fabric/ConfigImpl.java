package com.ultramega.interdimensionalwirelesstransmitter.fabric;

import com.ultramega.interdimensionalwirelesstransmitter.common.Config;
import com.ultramega.interdimensionalwirelesstransmitter.common.DefaultEnergyUsage;
import com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@me.shedaniel.autoconfig.annotation.Config(name = InterdimensionalIdentifierUtil.MOD_ID)
@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal", "CanBeFinal"})
public class ConfigImpl implements ConfigData, Config {
    @ConfigEntry.Gui.CollapsibleObject
    private InterdimensionalWirelessTransmitterEntryEntryImpl
        creativeWirelessTransmitter = new InterdimensionalWirelessTransmitterEntryEntryImpl();

    public static ConfigImpl get() {
        return AutoConfig.getConfigHolder(ConfigImpl.class).getConfig();
    }

    @Override
    public InterdimensionalWirelessTransmitterEntry getInterdimensionalWirelessTransmitter() {
        return creativeWirelessTransmitter;
    }

    private static class InterdimensionalWirelessTransmitterEntryEntryImpl
        implements InterdimensionalWirelessTransmitterEntry {
        private long energyUsage = DefaultEnergyUsage.INTERDIMENSIONAL_WIRELESS_TRANSMITTER_USAGE;

        @Override
        public long getEnergyUsage() {
            return energyUsage;
        }
    }
}
