package com.ultramega.creativewirelesstransmitter.mixin;

import com.ultramega.creativewirelesstransmitter.node.IPlaceHolder;
import com.refinedmods.refinedstorage.api.network.INetwork;
import com.refinedmods.refinedstorage.api.network.INetworkNodeGraphEntry;
import com.refinedmods.refinedstorage.api.network.IWirelessTransmitter;
import com.refinedmods.refinedstorage.api.network.item.INetworkItem;
import com.refinedmods.refinedstorage.api.network.item.INetworkItemManager;
import com.refinedmods.refinedstorage.api.network.item.INetworkItemProvider;
import com.refinedmods.refinedstorage.api.network.node.INetworkNode;
import com.refinedmods.refinedstorage.apiimpl.network.item.NetworkItemManager;
import com.refinedmods.refinedstorage.inventory.player.PlayerSlot;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(value = NetworkItemManager.class, remap = false)
public abstract class MixinNetworkItemManager implements INetworkItemManager {
	
    private final INetwork network;
    private final Map<Player, INetworkItem> items = new ConcurrentHashMap<>();

    public MixinNetworkItemManager(INetwork network) {
        this.network = network;
    }

    /**
     * @author
     */
    @Overwrite
    public void open(Player player, ItemStack stack, PlayerSlot slot) {
        boolean inRange = false;

        for (INetworkNodeGraphEntry entry : network.getNodeGraph().all()) {
            INetworkNode node = entry.getNode();

            if (node instanceof IWirelessTransmitter &&
                    network.canRun() &&
                    node.isActive() &&
                    ((IWirelessTransmitter) node).getDimension() == player.getCommandSenderWorld().dimension()) {
                IWirelessTransmitter transmitter = (IWirelessTransmitter) node;

                Vec3 pos = player.position();

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
            player.sendSystemMessage(Component.translatable("misc.refinedstorage.network_item.out_of_range"));

            return;
        }

        INetworkItem item = ((INetworkItemProvider) stack.getItem()).provide(this, player, stack, slot);

        if (item.onOpen(network)) {
            items.put(player, item);
        }
    }
}
