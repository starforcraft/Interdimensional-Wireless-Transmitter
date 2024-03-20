package com.ultramega.creativewirelesstransmitter.registry;

import com.refinedmods.refinedstorage.container.factory.BlockEntityContainerFactory;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import com.ultramega.creativewirelesstransmitter.blockentity.CreativeWirelessTransmitterBlockEntity;
import com.ultramega.creativewirelesstransmitter.container.CreativeWirelessTransmitterContainerMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModContainerMenus {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.MENU, CreativeWirelessTransmitter.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<CreativeWirelessTransmitterContainerMenu>> CREATIVE_WIRELESS_TRANSMITTER = REGISTRY.register("creative_wireless_transmitter", () -> IMenuTypeExtension.create(new BlockEntityContainerFactory<CreativeWirelessTransmitterContainerMenu, CreativeWirelessTransmitterBlockEntity>((windowId, inv, blockEntity) -> new CreativeWirelessTransmitterContainerMenu(blockEntity, inv.player, windowId))));
}
