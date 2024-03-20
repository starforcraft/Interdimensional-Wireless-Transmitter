package com.ultramega.creativewirelesstransmitter.registry;

import com.refinedmods.refinedstorage.util.ColorMap;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.RegisterEvent;

public final class ModCreativeTabs {
    public static void register(RegisterEvent.RegisterHelper<CreativeModeTab> helper) {
        helper.register(new ResourceLocation(CreativeWirelessTransmitter.MOD_ID, "general"), CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + CreativeWirelessTransmitter.MOD_ID))
                .icon(() -> new ItemStack(ModItems.CREATIVE_WIRELESS_TRANSMITTER.get(ColorMap.DEFAULT_COLOR).get()))
                .displayItems((params, output) -> ModCreativeTabs.append(output))
                .build());
    }


    public static void append(CreativeModeTab.Output output) {
        add(output, ModItems.CREATIVE_WIRELESS_TRANSMITTER);
        for (DyeColor color : DyeColor.values()) {
            output.accept(new ItemStack(ModItems.CREATIVE_WIRELESS_TRANSMITTER.get(color).get()));
        }
    }

    private static void add(CreativeModeTab.Output output, ColorMap<Item, ? extends Item> cm) {
        cm.values().forEach(c -> add(output, c));
    }

    private static void add(CreativeModeTab.Output output, DeferredHolder<Item, ? extends Item> ro) {
        output.accept(ro.get());
    }
}
