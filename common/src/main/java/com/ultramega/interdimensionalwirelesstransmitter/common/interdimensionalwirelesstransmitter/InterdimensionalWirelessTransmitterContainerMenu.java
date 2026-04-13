package com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter;

import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Menus;

import com.refinedmods.refinedstorage.common.networking.WirelessTransmitterData;
import com.refinedmods.refinedstorage.common.support.AbstractBaseContainerMenu;
import com.refinedmods.refinedstorage.common.support.RedstoneMode;
import com.refinedmods.refinedstorage.common.support.containermenu.ClientProperty;
import com.refinedmods.refinedstorage.common.support.containermenu.PropertyTypes;
import com.refinedmods.refinedstorage.common.support.containermenu.ServerProperty;
import com.refinedmods.refinedstorage.common.support.packet.s2c.S2CPackets;

import com.google.common.util.concurrent.RateLimiter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;

public class InterdimensionalWirelessTransmitterContainerMenu extends AbstractBaseContainerMenu {
    private final RateLimiter rangeRateLimiter = RateLimiter.create(4);

    @Nullable
    private final InterdimensionalWirelessTransmitterBlockEntity interdimensionalWirelessTransmitter;
    private final Player player;

    private boolean active;

    public InterdimensionalWirelessTransmitterContainerMenu(final int syncId, final Inventory playerInventory, final WirelessTransmitterData data) {
        super(Menus.INSTANCE.getInterdimensionalWirelessTransmitter(), syncId);
        this.addPlayerInventory(playerInventory, 8, 55);
        this.registerProperty(new ClientProperty<>(PropertyTypes.REDSTONE_MODE, RedstoneMode.IGNORE));
        this.active = data.active();
        this.interdimensionalWirelessTransmitter = null;
        this.player = playerInventory.player;
    }

    InterdimensionalWirelessTransmitterContainerMenu(final int syncId,
                                                     final Inventory playerInventory,
                                                     final InterdimensionalWirelessTransmitterBlockEntity interdimensionalWirelessTransmitter) {
        super(Menus.INSTANCE.getInterdimensionalWirelessTransmitter(), syncId);
        this.addPlayerInventory(playerInventory, 8, 55);
        this.registerProperty(new ServerProperty<>(
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
        if (this.interdimensionalWirelessTransmitter == null) {
            return;
        }
        final boolean newActive = this.interdimensionalWirelessTransmitter.isActive();
        final boolean changed = this.active != newActive;
        if (changed && this.rangeRateLimiter.tryAcquire()) {
            this.active = newActive;
            S2CPackets.sendWirelessTransmitterData((ServerPlayer) this.player, Integer.MAX_VALUE, this.active);
        }
    }

    @Override
    public boolean stillValid(final Player p) {
        if (this.interdimensionalWirelessTransmitter == null) {
            return true;
        }
        return Container.stillValidBlockEntity(this.interdimensionalWirelessTransmitter, p);
    }

    boolean isActive() {
        return this.active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }
}
