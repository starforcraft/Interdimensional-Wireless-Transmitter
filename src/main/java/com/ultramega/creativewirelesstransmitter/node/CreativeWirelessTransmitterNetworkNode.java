package com.ultramega.creativewirelesstransmitter.node;

import com.refinedmods.refinedstorage.api.network.IWirelessTransmitter;
import com.refinedmods.refinedstorage.apiimpl.network.node.NetworkNode;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;

public class CreativeWirelessTransmitterNetworkNode extends NetworkNode implements IWirelessTransmitter {
    public static final ResourceLocation ID = new ResourceLocation(CreativeWirelessTransmitter.MOD_ID, "creative_wireless_transmitter");

    public CreativeWirelessTransmitterNetworkNode(Level level, BlockPos pos) {
        super(level, pos);
    }

    @Override
    public int getEnergyUsage() {
        return CreativeWirelessTransmitter.SERVER_CONFIG.getCreativeWirelessTransmitterEnergyConsume();
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public int getRange() {
        return Integer.MAX_VALUE;
    }

    @Override
    public BlockPos getOrigin() {
        return pos;
    }

    @Override
    public ResourceKey<Level> getDimension() {
        return level.dimension();
    }

    @Override
    public boolean canConduct(Direction direction) {
        return getDirection() == direction;
    }

    @Override
    public void visit(Operator operator) {
        operator.apply(level, pos.relative(Direction.DOWN), Direction.UP);
    }
}
