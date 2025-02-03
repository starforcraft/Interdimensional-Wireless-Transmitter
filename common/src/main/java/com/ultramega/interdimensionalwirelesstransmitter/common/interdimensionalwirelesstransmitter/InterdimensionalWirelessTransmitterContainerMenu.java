package com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter;

import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Menus;

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

public class InterdimensionalWirelessTransmitterContainerMenu extends AbstractBaseContainerMenu {
    private final RateLimiter rangeRateLimiter = RateLimiter.create(4);

    @Nullable
    private final InterdimensionalWirelessTransmitterBlockEntity interdimensionalWirelessTransmitter;
    private final Player player;

    private boolean active;

    public InterdimensionalWirelessTransmitterContainerMenu(final int syncId, final Inventory playerInventory, final WirelessTransmitterData data) {
        super(Menus.INSTANCE.getInterdimensionalWirelessTransmitter(), syncId);
        addPlayerInventory(playerInventory, 8, 55);
        registerProperty(new ClientProperty<>(PropertyTypes.REDSTONE_MODE, RedstoneMode.IGNORE));
        this.active = data.active();
        this.interdimensionalWirelessTransmitter = null;
        this.player = playerInventory.player;
    }

    InterdimensionalWirelessTransmitterContainerMenu(final int syncId,
                                                     final Inventory playerInventory,
                                                     final InterdimensionalWirelessTransmitterBlockEntity interdimensionalWirelessTransmitter) {
        super(Menus.INSTANCE.getInterdimensionalWirelessTransmitter(), syncId);
        addPlayerInventory(playerInventory, 8, 55);
        registerProperty(new ServerProperty<>(
            PropertyTypes.REDSTONE_MODE,
            interdimensionalWirelessTransmitter::getRedstoneMode,
            interdimensionalWirelessTransmitter::setRedstoneMode
        ));
        this.active = interdimensionalWirelessTransmitter.isActive();
        this.interdimensionalWirelessTransmitter = interdimensionalWirelessTransmitter;
        this.player = playerInventory.player;
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
        if (interdimensionalWirelessTransmitter == null) {
            return;
        }
        final boolean newActive = interdimensionalWirelessTransmitter.isActive();
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
