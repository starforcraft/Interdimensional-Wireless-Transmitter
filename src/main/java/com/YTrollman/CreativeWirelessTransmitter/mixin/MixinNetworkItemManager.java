package com.YTrollman.CreativeWirelessTransmitter.mixin;

import com.YTrollman.CreativeWirelessTransmitter.node.IPlaceHolder;
import com.refinedmods.refinedstorage.api.network.INetwork;
import com.refinedmods.refinedstorage.api.network.INetworkNodeGraphEntry;
import com.refinedmods.refinedstorage.api.network.IWirelessTransmitter;
import com.refinedmods.refinedstorage.api.network.item.INetworkItem;
import com.refinedmods.refinedstorage.api.network.item.INetworkItemManager;
import com.refinedmods.refinedstorage.api.network.item.INetworkItemProvider;
import com.refinedmods.refinedstorage.api.network.node.INetworkNode;
import com.refinedmods.refinedstorage.apiimpl.network.item.NetworkItemManager;
import com.refinedmods.refinedstorage.inventory.player.PlayerSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(value = NetworkItemManager.class, remap = false)
public abstract class MixinNetworkItemManager implements INetworkItemManager {
	
    private final INetwork network;
    private final Map<PlayerEntity, INetworkItem> items = new ConcurrentHashMap<>();

    public MixinNetworkItemManager(INetwork network) {
        this.network = network;
    }
    
    /**
     * @author
     */
    @Overwrite
    public void open(PlayerEntity player, ItemStack stack, PlayerSlot slot) {
        boolean inRange = false;

        for (INetworkNodeGraphEntry entry : network.getNodeGraph().all()) {
            INetworkNode node = entry.getNode();
            	if (node instanceof IWirelessTransmitter &&
                        network.canRun() &&
                        node.isActive() &&
                        ((IWirelessTransmitter) node).getDimension() == player.getCommandSenderWorld().dimension()) {
            			IWirelessTransmitter transmitter = (IWirelessTransmitter) node;
                        
                        Vector3d pos = player.position();

                        double distance = Math.sqrt(Math.pow(transmitter.getOrigin().getX() - pos.x(), 2) + Math.pow(transmitter.getOrigin().getY() - pos.y(), 2) + Math.pow(transmitter.getOrigin().getZ() - pos.z(), 2));
                        	
                        if (distance < transmitter.getRange()) {
                            inRange = true;

                            break;
                        }
                    }
            	else if (node instanceof IWirelessTransmitter &&
            		network.canRun() &&
                    node.isActive() &&
                    ((IWirelessTransmitter) node).getDimension() != player.getCommandSenderWorld().dimension() 
                     && node instanceof IPlaceHolder) {
                		inRange = true;
                	
                		break;
            	}
            }

        if (!inRange) {
            player.sendMessage(new TranslationTextComponent("misc.refinedstorage.network_item.out_of_range"), player.getUUID());

            return;
        }

        INetworkItem item = ((INetworkItemProvider) stack.getItem()).provide(this, player, stack, slot);

        if (item.onOpen(network)) {
            items.put(player, item);
        }
    }
}
