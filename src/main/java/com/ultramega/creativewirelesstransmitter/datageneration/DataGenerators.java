package com.ultramega.creativewirelesstransmitter.datageneration;

import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGenerators {
    @SubscribeEvent
    public void runDataGeneration(GatherDataEvent event) {
        event.getGenerator().addProvider(event.includeClient(), new BlockModelGenerator(event.getGenerator().getPackOutput(), CreativeWirelessTransmitter.MOD_ID, event.getExistingFileHelper()));
    }
}
