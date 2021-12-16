package com.YTrollman.CreativeWirelessTransmitter.registry;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.blockentity.CreativeWirelessTransmitterBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CreativeWirelessTransmitter.MOD_ID)
public class ModBlockEntityTypes {
    @ObjectHolder("creative_wireless_transmitter")
    public static final BlockEntityType<CreativeWirelessTransmitterBlockEntity> CREATIVE_WIRELESS_TRANSMITTER = null;
    
    private ModBlockEntityTypes() {
    }
}
