package com.YTrollman.CreativeWirelessTransmitter.setup;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.container.CreativeWirelessTransmitterContainerMenu;
import com.YTrollman.CreativeWirelessTransmitter.node.CreativeWirelessTransmitterNetworkNode;
import com.YTrollman.CreativeWirelessTransmitter.registry.ModBlocks;
import com.YTrollman.CreativeWirelessTransmitter.blockentity.CreativeWirelessTransmitterBlockEntity;
import com.refinedmods.refinedstorage.api.network.node.INetworkNode;
import com.refinedmods.refinedstorage.apiimpl.API;
import com.refinedmods.refinedstorage.apiimpl.network.node.NetworkNode;
import com.refinedmods.refinedstorage.blockentity.BaseBlockEntity;
import com.refinedmods.refinedstorage.blockentity.data.BlockEntitySynchronizationManager;
import com.refinedmods.refinedstorage.container.factory.BlockEntityContainerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetup {
    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent e) { 
        API.instance().getNetworkNodeRegistry().add(CreativeWirelessTransmitterNetworkNode.ID, (tag, world, pos) -> readAndReturn(tag, new CreativeWirelessTransmitterNetworkNode(world, pos)));
    }

    private INetworkNode readAndReturn(CompoundTag tag, NetworkNode node) {
        node.read(tag);

        return node;
    }

    @SubscribeEvent
    public void onRegisterTiles(RegistryEvent.Register<BlockEntityType<?>> e) {
        e.getRegistry().register(registerSynchronizationParameters(BlockEntityType.Builder.of(CreativeWirelessTransmitterBlockEntity::new, ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.getBlocks()).build(null).setRegistryName(CreativeWirelessTransmitter.MOD_ID, "creative_wireless_transmitter")));
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerSynchronizationParameters(BlockEntityType<T> t) {
        BaseBlockEntity blockEntity = (BaseBlockEntity) t.create(BlockPos.ZERO, null);

        blockEntity.getDataManager().getParameters().forEach(BlockEntitySynchronizationManager::registerParameter);

        return t;
    }

    @SubscribeEvent
    public void onRegisterContainers(RegistryEvent.Register<MenuType<?>> e) {
        e.getRegistry().register(IForgeMenuType.create(new BlockEntityContainerFactory<CreativeWirelessTransmitterContainerMenu, CreativeWirelessTransmitterBlockEntity>((windowId, inv, tile) -> new CreativeWirelessTransmitterContainerMenu(tile, inv.player, windowId))).setRegistryName(CreativeWirelessTransmitter.MOD_ID, "creative_wireless_transmitter"));
    }
}
