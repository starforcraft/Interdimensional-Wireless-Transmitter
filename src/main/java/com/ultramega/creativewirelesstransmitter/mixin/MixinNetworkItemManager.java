package com.ultramega.creativewirelesstransmitter.mixin;

import com.refinedmods.refinedstorage.api.network.INetwork;
import com.refinedmods.refinedstorage.api.network.INetworkNodeGraphEntry;
import com.refinedmods.refinedstorage.api.network.item.INetworkItem;
import com.refinedmods.refinedstorage.api.network.item.INetworkItemManager;
import com.refinedmods.refinedstorage.api.network.item.INetworkItemProvider;
import com.refinedmods.refinedstorage.api.network.node.INetworkNode;
import com.refinedmods.refinedstorage.apiimpl.network.item.NetworkItemManager;
import com.refinedmods.refinedstorage.inventory.player.PlayerSlot;
import com.ultramega.creativewirelesstransmitter.node.CreativeWirelessTransmitterNetworkNode;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(NetworkItemManager.class)
public abstract class MixinNetworkItemManager implements INetworkItemManager {
    @Shadow
    @Final
    private INetwork network;
    @Shadow
    @Final
    private Map<Player, INetworkItem> items = new ConcurrentHashMap<>();

    @Inject(at = @At("HEAD"), method = "open", cancellable = true)
    public void open(Player player, ItemStack stack, PlayerSlot slot, CallbackInfo ci) {
        boolean inRange = false;

        for (INetworkNodeGraphEntry entry : network.getNodeGraph().all()) {
            INetworkNode node = entry.getNode();

            if(node instanceof CreativeWirelessTransmitterNetworkNode &&
                    network.canRun() &&
                    node.isActive()) {
                inRange = true;

                break;
            }
        }

        if (!inRange) return;

        INetworkItem item = ((INetworkItemProvider) stack.getItem()).provide(this, player, stack, slot);

        if (item.onOpen(network)) {
            items.put(player, item);
            ci.cancel();
        }
    }
}
