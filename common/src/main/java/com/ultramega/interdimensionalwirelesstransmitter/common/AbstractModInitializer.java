package com.ultramega.interdimensionalwirelesstransmitter.common;

import com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter.InterdimensionalWirelessTransmitterBlockEntity;
import com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter.InterdimensionalWirelessTransmitterContainerMenu;
import com.ultramega.interdimensionalwirelesstransmitter.common.registry.BlockEntities;
import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;
import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Items;
import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Menus;

import com.refinedmods.refinedstorage.common.content.BlockEntityTypeFactory;
import com.refinedmods.refinedstorage.common.content.ExtendedMenuTypeFactory;
import com.refinedmods.refinedstorage.common.content.RegistryCallback;
import com.refinedmods.refinedstorage.common.networking.WirelessTransmitterData;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class AbstractModInitializer {
    protected void registerBlocks(final RegistryCallback<Block> callback) {
        Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().registerBlocks(callback);
    }

    protected void registerItems(final RegistryCallback<Item> callback) {
        Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().registerItems(callback, Items.INSTANCE::addInterdimensionalWirelessTransmitter);
    }

    protected final void registerBlockEntities(final RegistryCallback<BlockEntityType<?>> callback,
                                               final BlockEntityTypeFactory typeFactory) {
        BlockEntities.INSTANCE.setInterdimensionalWirelessTransmitter(callback.register(
            ContentIds.INTERDIMENSIONAL_WIRELESS_TRANSMITTER,
            () -> typeFactory.create(
                InterdimensionalWirelessTransmitterBlockEntity::new,
                Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().toArray()
            )
        ));
    }

    protected final void registerMenus(final RegistryCallback<MenuType<?>> callback,
                                       final ExtendedMenuTypeFactory extendedMenuTypeFactory) {
        Menus.INSTANCE.setInterdimensionalWirelessTransmitter(callback.register(
            ContentIds.INTERDIMENSIONAL_WIRELESS_TRANSMITTER,
            () -> extendedMenuTypeFactory.create(InterdimensionalWirelessTransmitterContainerMenu::new,
                WirelessTransmitterData.STREAM_CODEC)
        ));
    }
}
