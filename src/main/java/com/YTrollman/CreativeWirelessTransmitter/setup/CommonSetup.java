package com.YTrollman.CreativeWirelessTransmitter.setup;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.container.CreativeWirelessTransmitterContainer;
import com.YTrollman.CreativeWirelessTransmitter.node.CreativeWirelessTransmitterNetworkNode;
import com.YTrollman.CreativeWirelessTransmitter.registry.ModBlocks;
import com.YTrollman.CreativeWirelessTransmitter.tileentity.CreativeWirelessTransmitterTileEntity;
import com.refinedmods.refinedstorage.api.network.node.INetworkNode;
import com.refinedmods.refinedstorage.apiimpl.API;
import com.refinedmods.refinedstorage.apiimpl.network.node.NetworkNode;
import com.refinedmods.refinedstorage.container.factory.PositionalTileContainerFactory;
import com.refinedmods.refinedstorage.tile.BaseTile;
import com.refinedmods.refinedstorage.tile.data.TileDataManager;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetup {
    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent e) { 
        API.instance().getNetworkNodeRegistry().add(CreativeWirelessTransmitterNetworkNode.ID, (tag, world, pos) -> readAndReturn(tag, new CreativeWirelessTransmitterNetworkNode(world, pos)));
    }

    private INetworkNode readAndReturn(CompoundNBT tag, NetworkNode node) {
        node.read(tag);

        return node;
    }

    @SubscribeEvent
    public void onRegisterTiles(RegistryEvent.Register<TileEntityType<?>> e) {
        e.getRegistry().register(registerTileDataParameters(TileEntityType.Builder.create(CreativeWirelessTransmitterTileEntity::new, ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.getBlocks()).build(null).setRegistryName(CreativeWirelessTransmitter.MOD_ID, "creative_wireless_transmitter")));
    }

    private <T extends TileEntity> TileEntityType<T> registerTileDataParameters(TileEntityType<T> t) {
        BaseTile tile = (BaseTile) t.create();

        tile.getDataManager().getParameters().forEach(TileDataManager::registerParameter);

        return t;
    }

    @SubscribeEvent
    public void onRegisterContainers(RegistryEvent.Register<ContainerType<?>> e) {
        e.getRegistry().register(IForgeContainerType.create(new PositionalTileContainerFactory<CreativeWirelessTransmitterContainer, CreativeWirelessTransmitterTileEntity>((windowId, inv, tile) -> new CreativeWirelessTransmitterContainer(tile, inv.player, windowId))).setRegistryName(CreativeWirelessTransmitter.MOD_ID, "creative_wireless_transmitter"));
    }
}
