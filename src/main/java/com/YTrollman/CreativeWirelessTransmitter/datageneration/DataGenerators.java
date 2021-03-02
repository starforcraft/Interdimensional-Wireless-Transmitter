package com.YTrollman.CreativeWirelessTransmitter.datageneration;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class DataGenerators {
    @SubscribeEvent
    public void runDataGeneration(GatherDataEvent event) {
        if (event.includeClient()) {
            event.getGenerator().addProvider(new BlockModelGenerator(event.getGenerator(), CreativeWirelessTransmitter.MOD_ID, event.getExistingFileHelper()));
        }
    }
}
