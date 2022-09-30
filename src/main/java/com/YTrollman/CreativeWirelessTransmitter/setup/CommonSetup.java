package com.YTrollman.CreativeWirelessTransmitter.setup;

import com.YTrollman.CreativeWirelessTransmitter.node.CreativeWirelessTransmitterNetworkNode;
import com.refinedmods.refinedstorage.api.network.node.INetworkNode;
import com.refinedmods.refinedstorage.apiimpl.API;
import com.refinedmods.refinedstorage.apiimpl.network.node.NetworkNode;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetup {
    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent e) { 
        API.instance().getNetworkNodeRegistry().add(CreativeWirelessTransmitterNetworkNode.ID, (tag, world, pos) -> readAndReturn(tag, new CreativeWirelessTransmitterNetworkNode(world, pos)));
    }

    private INetworkNode readAndReturn(CompoundTag tag, NetworkNode node) {
        node.read(tag);

        return node;
    }
}
