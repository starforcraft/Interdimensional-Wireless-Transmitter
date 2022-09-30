package com.YTrollman.CreativeWirelessTransmitter.registry;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.blockentity.CreativeWirelessTransmitterBlockEntity;
import com.YTrollman.CreativeWirelessTransmitter.container.CreativeWirelessTransmitterContainerMenu;
import com.refinedmods.refinedstorage.container.factory.BlockEntityContainerFactory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainerMenus {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CreativeWirelessTransmitter.MOD_ID);

    public static final RegistryObject<MenuType<CreativeWirelessTransmitterContainerMenu>> CREATIVE_WIRELESS_TRANSMITTER = REGISTRY.register("creative_wireless_transmitter", () -> IForgeMenuType.create(new BlockEntityContainerFactory<CreativeWirelessTransmitterContainerMenu, CreativeWirelessTransmitterBlockEntity>((windowId, inv, blockEntity) -> new CreativeWirelessTransmitterContainerMenu(blockEntity, inv.player, windowId))));
}
