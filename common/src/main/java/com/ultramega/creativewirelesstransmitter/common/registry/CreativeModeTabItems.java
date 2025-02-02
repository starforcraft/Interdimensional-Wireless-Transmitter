package com.ultramega.creativewirelesstransmitter.common.registry;

import com.refinedmods.refinedstorage.common.content.BlockColorMap;

import java.util.function.Consumer;

import net.minecraft.world.item.ItemStack;

public final class CreativeModeTabItems {
    private CreativeModeTabItems() {
    }

    public static void appendBlocks(final Consumer<ItemStack> consumer) {
        appendDefaultBlockColor(consumer, Blocks.INSTANCE.getCreativeWirelessTransmitter());
    }

    private static void appendDefaultBlockColor(final Consumer<ItemStack> consumer, final BlockColorMap<?, ?> map) {
        consumer.accept(new ItemStack(map.getDefault()));
    }

    public static void appendColoredVariants(final Consumer<ItemStack> consumer) {
        appendColoredBlocks(consumer, Blocks.INSTANCE.getCreativeWirelessTransmitter());
    }

    private static void appendColoredBlocks(final Consumer<ItemStack> consumer, final BlockColorMap<?, ?> map) {
        map.forEach((color, id, block) -> {
            if (!map.isDefaultColor(color)) {
                consumer.accept(new ItemStack(block.get()));
            }
        });
    }
}
