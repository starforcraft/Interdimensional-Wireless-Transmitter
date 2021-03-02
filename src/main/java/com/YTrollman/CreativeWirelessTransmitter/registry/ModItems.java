package com.YTrollman.CreativeWirelessTransmitter.registry;

import java.util.ArrayList;
import java.util.List;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.refinedmods.refinedstorage.util.ColorMap;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CreativeWirelessTransmitter.MOD_ID);
    
    private static final List<Runnable> LATE_REGISTRATION = new ArrayList<>();
    
    public static final ColorMap<BlockItem> CREATIVE_WIRELESS_TRANSMITTER = new ColorMap<>(ITEMS, LATE_REGISTRATION);
    
    static {
        CREATIVE_WIRELESS_TRANSMITTER.registerItemsFromBlocks(ModBlocks.CREATIVE_WIRELESS_TRANSMITTER);
        
        LATE_REGISTRATION.forEach(Runnable::run);
    }
    
    private ModItems() {
    }

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}