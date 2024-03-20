package com.ultramega.creativewirelesstransmitter.registry;

import com.refinedmods.refinedstorage.util.BlockColorMap;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import com.ultramega.creativewirelesstransmitter.block.CreativeWirelessTransmitterBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, CreativeWirelessTransmitter.MOD_ID);

    public static final BlockColorMap<CreativeWirelessTransmitterBlock> CREATIVE_WIRELESS_TRANSMITTER = new BlockColorMap<>(BLOCKS);

    static {
        CREATIVE_WIRELESS_TRANSMITTER.registerBlocks("creative_wireless_transmitter", CreativeWirelessTransmitterBlock::new);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}