package com.YTrollman.CreativeWirelessTransmitter.mixin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.node.IPlaceHolder;
import com.refinedmods.refinedstorage.api.network.INetwork;
import com.refinedmods.refinedstorage.api.network.INetworkNodeGraphEntry;
import com.refinedmods.refinedstorage.api.network.IWirelessTransmitter;
import com.refinedmods.refinedstorage.api.network.item.INetworkItem;
import com.refinedmods.refinedstorage.api.network.item.INetworkItemManager;
import com.refinedmods.refinedstorage.api.network.item.INetworkItemProvider;
import com.refinedmods.refinedstorage.api.network.node.INetworkNode;
import com.refinedmods.refinedstorage.apiimpl.network.item.NetworkItemManager;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;

@Mixin(value = NetworkItemManager.class, remap = false)
public abstract class MixinNetworkItemManager implements INetworkItemManager {
	
    private final INetwork network;
    private final Map<PlayerEntity, INetworkItem> items = new ConcurrentHashMap<>();

    public MixinNetworkItemManager(INetwork network) {
        this.network = network;
    }
    
    @Overwrite
    public void open(PlayerEntity player, ItemStack stack, int slotId) {
        boolean inRange = false;

        for (INetworkNodeGraphEntry entry : network.getNodeGraph().all()) {
            INetworkNode node = entry.getNode();
            	if (node instanceof IWirelessTransmitter &&
                        network.canRun() &&
                        node.isActive() &&
                        ((IWirelessTransmitter) node).getDimension() == player.getEntityWorld().func_234923_W_()) {
            			IWirelessTransmitter transmitter = (IWirelessTransmitter) node;
                        
                        Vector3d pos = player.getPositionVec();

                        double distance = Math.sqrt(Math.pow(transmitter.getOrigin().getX() - pos.getX(), 2) + Math.pow(transmitter.getOrigin().getY() - pos.getY(), 2) + Math.pow(transmitter.getOrigin().getZ() - pos.getZ(), 2));
                        	
                        if (distance < transmitter.getRange()) {
                            inRange = true;

                            break;
                        }
                    }
            	else if (node instanceof IWirelessTransmitter &&
            		network.canRun() &&
                    node.isActive() &&
                    ((IWirelessTransmitter) node).getDimension() != player.getEntityWorld().func_234923_W_() 
                     && node instanceof IPlaceHolder) {
                		inRange = true;
                	
                		break;
            	}
            }

        if (!inRange) {
            player.sendMessage(new TranslationTextComponent("misc.refinedstorage.network_item.out_of_range"), player.getUniqueID());

            return;
        }

        INetworkItem item = ((INetworkItemProvider) stack.getItem()).provide(this, player, stack, slotId);

        if (item.onOpen(network)) {
            items.put(player, item);
        }
    }
}
