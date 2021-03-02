package com.YTrollman.CreativeWirelessTransmitter.container;

import com.YTrollman.CreativeWirelessTransmitter.registry.ModContainers;
import com.YTrollman.CreativeWirelessTransmitter.tileentity.CreativeWirelessTransmitterTileEntity;
import com.refinedmods.refinedstorage.container.BaseContainer;

import net.minecraft.entity.player.PlayerEntity;

public class CreativeWirelessTransmitterContainer extends BaseContainer {
    public CreativeWirelessTransmitterContainer(CreativeWirelessTransmitterTileEntity wirelessTransmitter, PlayerEntity player, int windowId) {
        super(ModContainers.CREATIVE_WIRELESS_TRANSMITTER, wirelessTransmitter, player, windowId);
    	
        addPlayerInventory(8, 55);

        //transferManager.addBiTransfer(player.inventory, wirelessTransmitter.getNode().getUpgrades());
    }
}
