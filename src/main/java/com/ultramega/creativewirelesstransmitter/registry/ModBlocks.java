package com.ultramega.creativewirelesstransmitter.registry;

import com.refinedmods.refinedstorage.util.ColorMap;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import com.ultramega.creativewirelesstransmitter.block.CreativeWirelessTransmitterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CreativeWirelessTransmitter.MOD_ID);

    public static final ColorMap<CreativeWirelessTransmitterBlock> CREATIVE_WIRELESS_TRANSMITTER = new ColorMap<>(BLOCKS);

    static {
        CREATIVE_WIRELESS_TRANSMITTER.registerBlocks("creative_wireless_transmitter", CreativeWirelessTransmitterBlock::new);
    }

    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}