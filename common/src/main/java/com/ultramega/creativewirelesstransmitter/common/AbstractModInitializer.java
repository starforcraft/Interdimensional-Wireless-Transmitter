package com.ultramega.creativewirelesstransmitter.common;

import com.ultramega.creativewirelesstransmitter.common.creativewirelesstransmitter.CreativeWirelessTransmitterBlockEntity;
import com.ultramega.creativewirelesstransmitter.common.creativewirelesstransmitter.CreativeWirelessTransmitterContainerMenu;
import com.ultramega.creativewirelesstransmitter.common.registry.BlockEntities;
import com.ultramega.creativewirelesstransmitter.common.registry.Blocks;
import com.ultramega.creativewirelesstransmitter.common.registry.Items;
import com.ultramega.creativewirelesstransmitter.common.registry.Menus;

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
        Blocks.INSTANCE.getCreativeWirelessTransmitter().registerBlocks(callback);
    }

    protected void registerItems(final RegistryCallback<Item> callback) {
        Blocks.INSTANCE.getCreativeWirelessTransmitter().registerItems(callback, Items.INSTANCE::addCreativeWirelessTransmitter);
    }

    protected final void registerBlockEntities(final RegistryCallback<BlockEntityType<?>> callback,
                                               final BlockEntityTypeFactory typeFactory) {
        BlockEntities.INSTANCE.setCreativeWirelessTransmitter(callback.register(
            ContentIds.CREATIVE_WIRELESS_TRANSMITTER,
            () -> typeFactory.create(
                CreativeWirelessTransmitterBlockEntity::new,
                Blocks.INSTANCE.getCreativeWirelessTransmitter().toArray()
            )
        ));
    }

    protected final void registerMenus(final RegistryCallback<MenuType<?>> callback,
                                       final ExtendedMenuTypeFactory extendedMenuTypeFactory) {
        Menus.INSTANCE.setCreativeWirelessTransmitter(callback.register(
            ContentIds.CREATIVE_WIRELESS_TRANSMITTER,
            () -> extendedMenuTypeFactory.create(CreativeWirelessTransmitterContainerMenu::new,
                WirelessTransmitterData.STREAM_CODEC)
        ));
    }
}
