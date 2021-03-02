package com.YTrollman.CreativeWirelessTransmitter.registry;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.tileentity.CreativeWirelessTransmitterTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CreativeWirelessTransmitter.MOD_ID)
public class ModTileEntityTypes {
    @ObjectHolder("creative_wireless_transmitter")
    public static final TileEntityType<CreativeWirelessTransmitterTileEntity> CREATIVE_WIRELESS_TRANSMITTER = null;
    
    private ModTileEntityTypes() {
    }
}
