package com.ultramega.creativewirelesstransmitter.common.creativewirelesstransmitter;

import com.ultramega.creativewirelesstransmitter.common.ContentNames;
import com.ultramega.creativewirelesstransmitter.common.Platform;
import com.ultramega.creativewirelesstransmitter.common.registry.BlockEntities;

import com.refinedmods.refinedstorage.api.network.impl.node.SimpleNetworkNode;
import com.refinedmods.refinedstorage.common.api.support.network.InWorldNetworkNodeContainer;
import com.refinedmods.refinedstorage.common.networking.WirelessTransmitterData;
import com.refinedmods.refinedstorage.common.support.AbstractDirectionalBlock;
import com.refinedmods.refinedstorage.common.support.containermenu.NetworkNodeExtendedMenuProvider;
import com.refinedmods.refinedstorage.common.support.network.AbstractBaseNetworkNodeContainerBlockEntity;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamEncoder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class CreativeWirelessTransmitterBlockEntity extends AbstractBaseNetworkNodeContainerBlockEntity<SimpleNetworkNode>
    implements NetworkNodeExtendedMenuProvider<WirelessTransmitterData> {

    public CreativeWirelessTransmitterBlockEntity(final BlockPos pos, final BlockState state) {
        super(BlockEntities.INSTANCE.getCreativeWirelessTransmitter(), pos, state, new SimpleNetworkNode(
            Platform.getConfig().getCreativeWirelessTransmitter().getEnergyUsage()
        ));
    }

    @Override
    protected InWorldNetworkNodeContainer createMainContainer(final SimpleNetworkNode networkNode) {
        return new CreativeWirelessTransmitterNetworkNodeContainer(
            this,
            networkNode,
            "main",
            new CreativeWirelessTransmitterConnectionStrategy(this::getBlockState, getBlockPos())
        );
    }

    @Override
    public Component getName() {
        return overrideName(ContentNames.CREATIVE_WIRELESS_TRANSMITTER);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(final int syncId, final Inventory inventory, final Player player) {
        return new CreativeWirelessTransmitterContainerMenu(syncId, inventory, this);
    }

    @Override
    public WirelessTransmitterData getMenuData() {
        return new WirelessTransmitterData(Integer.MAX_VALUE, isActive());
    }

    @Override
    public StreamEncoder<RegistryFriendlyByteBuf, WirelessTransmitterData> getMenuCodec() {
        return WirelessTransmitterData.STREAM_CODEC;
    }

    @Override
    protected boolean doesBlockStateChangeWarrantNetworkNodeUpdate(final BlockState oldBlockState,
                                                                   final BlockState newBlockState) {
        return AbstractDirectionalBlock.didDirectionChange(oldBlockState, newBlockState);
    }

    boolean isActive() {
        return mainNetworkNode.isActive();
    }
}
