package com.YTrollman.CreativeWirelessTransmitter.datageneration;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DataGenerators {
    @SubscribeEvent
    public void runDataGeneration(GatherDataEvent event) {
        if (event.includeClient()) {
            event.getGenerator().addProvider(event.includeClient(), new BlockModelGenerator(event.getGenerator(), CreativeWirelessTransmitter.MOD_ID, event.getExistingFileHelper()));
        }
    }
}
