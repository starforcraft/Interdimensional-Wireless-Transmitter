package com.ultramega.creativewirelesstransmitter.common.creativewirelesstransmitter;

import com.ultramega.creativewirelesstransmitter.common.registry.Menus;

import com.refinedmods.refinedstorage.common.networking.WirelessTransmitterData;
import com.refinedmods.refinedstorage.common.support.AbstractBaseContainerMenu;
import com.refinedmods.refinedstorage.common.support.RedstoneMode;
import com.refinedmods.refinedstorage.common.support.containermenu.ClientProperty;
import com.refinedmods.refinedstorage.common.support.containermenu.PropertyTypes;
import com.refinedmods.refinedstorage.common.support.containermenu.ServerProperty;
import com.refinedmods.refinedstorage.common.support.packet.s2c.S2CPackets;

import javax.annotation.Nullable;

import com.google.common.util.concurrent.RateLimiter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class CreativeWirelessTransmitterContainerMenu extends AbstractBaseContainerMenu {
    private final RateLimiter rangeRateLimiter = RateLimiter.create(4);

    @Nullable
    private final CreativeWirelessTransmitterBlockEntity creativeWirelessTransmitter;
    private final Player player;

    private boolean active;

    public CreativeWirelessTransmitterContainerMenu(final int syncId, final Inventory playerInventory, final WirelessTransmitterData data) {
        super(Menus.INSTANCE.getCreativeWirelessTransmitter(), syncId);
        addPlayerInventory(playerInventory, 8, 55);
        registerProperty(new ClientProperty<>(PropertyTypes.REDSTONE_MODE, RedstoneMode.IGNORE));
        this.active = data.active();
        this.creativeWirelessTransmitter = null;
        this.player = playerInventory.player;
    }

    CreativeWirelessTransmitterContainerMenu(final int syncId,
                                             final Inventory playerInventory,
                                             final CreativeWirelessTransmitterBlockEntity creativeWirelessTransmitter) {
        super(Menus.INSTANCE.getCreativeWirelessTransmitter(), syncId);
        addPlayerInventory(playerInventory, 8, 55);
        registerProperty(new ServerProperty<>(
            PropertyTypes.REDSTONE_MODE,
            creativeWirelessTransmitter::getRedstoneMode,
            creativeWirelessTransmitter::setRedstoneMode
        ));
        this.active = creativeWirelessTransmitter.isActive();
        this.creativeWirelessTransmitter = creativeWirelessTransmitter;
        this.player = playerInventory.player;
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
        if (creativeWirelessTransmitter == null) {
            return;
        }
        final boolean newActive = creativeWirelessTransmitter.isActive();
        final boolean changed = active != newActive;
        if (changed && rangeRateLimiter.tryAcquire()) {
            this.active = newActive;
            S2CPackets.sendWirelessTransmitterData((ServerPlayer) player, Integer.MAX_VALUE, active);
        }
    }

    boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }
}
