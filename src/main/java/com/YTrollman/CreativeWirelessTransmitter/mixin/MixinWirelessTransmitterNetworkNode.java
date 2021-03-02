package com.YTrollman.CreativeWirelessTransmitter.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.node.ICreativeThing;
import com.refinedmods.refinedstorage.apiimpl.network.node.WirelessTransmitterNetworkNode;

@Mixin(WirelessTransmitterNetworkNode.class)
public abstract class MixinWirelessTransmitterNetworkNode implements ICreativeThing {

	public boolean isCreative() {
		CreativeWirelessTransmitter.LOGGER.info("asd");
		return false;
	}
}
