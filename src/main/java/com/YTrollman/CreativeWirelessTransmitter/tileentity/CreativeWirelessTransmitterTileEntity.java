package com.YTrollman.CreativeWirelessTransmitter.tileentity;

import javax.annotation.Nonnull;

import com.YTrollman.CreativeWirelessTransmitter.node.CreativeWirelessTransmitterNetworkNode;
import com.YTrollman.CreativeWirelessTransmitter.registry.ModTileEntityTypes;
import com.refinedmods.refinedstorage.tile.NetworkNodeTile;
import com.refinedmods.refinedstorage.tile.data.TileDataParameter;

import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CreativeWirelessTransmitterTileEntity extends NetworkNodeTile<CreativeWirelessTransmitterNetworkNode> {
    public static final TileDataParameter<Integer, CreativeWirelessTransmitterTileEntity> RANGE = new TileDataParameter<>(DataSerializers.VARINT, 0, t -> t.getNode().getRange());

    public CreativeWirelessTransmitterTileEntity() {
        super(ModTileEntityTypes.CREATIVE_WIRELESS_TRANSMITTER);

        dataManager.addWatchedParameter(RANGE);
    }

    @Override
    @Nonnull
    public CreativeWirelessTransmitterNetworkNode createNode(World world, BlockPos pos) {
        return new CreativeWirelessTransmitterNetworkNode(world, pos);
    }    
}
