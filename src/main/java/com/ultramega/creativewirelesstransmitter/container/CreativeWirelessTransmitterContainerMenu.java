package com.ultramega.creativewirelesstransmitter.container;

import com.ultramega.creativewirelesstransmitter.registry.ModContainerMenus;
import com.ultramega.creativewirelesstransmitter.blockentity.CreativeWirelessTransmitterBlockEntity;
import com.refinedmods.refinedstorage.container.BaseContainerMenu;
import net.minecraft.world.entity.player.Player;

public class CreativeWirelessTransmitterContainerMenu extends BaseContainerMenu {
    public CreativeWirelessTransmitterContainerMenu(CreativeWirelessTransmitterBlockEntity wirelessTransmitter, Player player, int windowId) {
        super(ModContainerMenus.CREATIVE_WIRELESS_TRANSMITTER.get(), wirelessTransmitter, player, windowId);
    	
        addPlayerInventory(8, 55);
    }
}
