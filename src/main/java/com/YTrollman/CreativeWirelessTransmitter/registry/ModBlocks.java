package com.YTrollman.CreativeWirelessTransmitter.registry;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.block.CreativeWirelessTransmitterBlock;
import com.refinedmods.refinedstorage.util.ColorMap;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CreativeWirelessTransmitter.MOD_ID);

    public static final ColorMap<CreativeWirelessTransmitterBlock> CREATIVE_WIRELESS_TRANSMITTER = new ColorMap<>(BLOCKS);

    public static final List<RegistryObject<? extends Block>> COLORED_BLOCKS = new ArrayList<>();
    
    static {
        CREATIVE_WIRELESS_TRANSMITTER.registerBlocks("creative_wireless_transmitter", CreativeWirelessTransmitterBlock::new);
    }
    
    private ModBlocks() {
    }
    
    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}