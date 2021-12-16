package com.YTrollman.CreativeWirelessTransmitter.node;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.config.CreativeWirelessTransmitterConfig;
import com.refinedmods.refinedstorage.api.network.IWirelessTransmitter;
import com.refinedmods.refinedstorage.apiimpl.network.node.NetworkNode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;

public class CreativeWirelessTransmitterNetworkNode extends NetworkNode implements IWirelessTransmitter, IPlaceHolder  {
    public static final ResourceLocation ID = new ResourceLocation(CreativeWirelessTransmitter.MOD_ID, "creative_wireless_transmitter");
    
    public CreativeWirelessTransmitterNetworkNode(Level level, BlockPos pos) {
        super(level, pos);
    }

    @Override
    public int getEnergyUsage() {
        return CreativeWirelessTransmitterConfig.CREATIVE_WIRELESS_TRANSMITTER_ENERGY_CONSUME.get();
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public boolean isCreative()
    {
    	return true;
    }
    
    @Override
    public int getRange() {
        return 2147483647;
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
    public IItemHandler getDrops() {
        return null;
    }

    @Override
    public boolean canConduct(Direction direction) {
        return Direction.DOWN.equals(direction);
    }

    @Override
    public void visit(Operator operator) {
        operator.apply(level, pos.relative(Direction.DOWN), Direction.UP);
    }
}
