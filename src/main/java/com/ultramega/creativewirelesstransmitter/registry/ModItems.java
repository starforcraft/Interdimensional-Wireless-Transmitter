package com.ultramega.creativewirelesstransmitter.registry;

import com.refinedmods.refinedstorage.util.ItemColorMap;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, CreativeWirelessTransmitter.MOD_ID);

    private static final List<Runnable> LATE_REGISTRATION = new ArrayList<>();

    public static final ItemColorMap CREATIVE_WIRELESS_TRANSMITTER = new ItemColorMap(ITEMS, LATE_REGISTRATION);

    static {
        CREATIVE_WIRELESS_TRANSMITTER.registerItemsFromBlocks(ModBlocks.CREATIVE_WIRELESS_TRANSMITTER);

        LATE_REGISTRATION.forEach(Runnable::run);
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}