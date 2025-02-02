package com.ultramega.creativewirelesstransmitter.fabric;

import com.ultramega.creativewirelesstransmitter.common.Config;
import com.ultramega.creativewirelesstransmitter.common.CreativeWirelessTransmitterIdentifierUtil;
import com.ultramega.creativewirelesstransmitter.common.DefaultEnergyUsage;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@me.shedaniel.autoconfig.annotation.Config(name = CreativeWirelessTransmitterIdentifierUtil.MOD_ID)
@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal", "CanBeFinal"})
public class ConfigImpl implements ConfigData, Config {
    @ConfigEntry.Gui.CollapsibleObject
    private CreativeWirelessTransmitterEntryEntryImpl creativeWirelessTransmitter = new CreativeWirelessTransmitterEntryEntryImpl();

    public static ConfigImpl get() {
        return AutoConfig.getConfigHolder(ConfigImpl.class).getConfig();
    }

    @Override
    public CreativeWirelessTransmitterEntry getCreativeWirelessTransmitter() {
        return creativeWirelessTransmitter;
    }

    private static class CreativeWirelessTransmitterEntryEntryImpl implements CreativeWirelessTransmitterEntry {
        private long energyUsage = DefaultEnergyUsage.CREATIVE_WIRELESS_TRANSMITTER_USAGE;

        @Override
        public long getEnergyUsage() {
            return energyUsage;
        }
    }
}
