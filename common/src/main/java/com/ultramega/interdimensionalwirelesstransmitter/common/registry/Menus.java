package com.ultramega.interdimensionalwirelesstransmitter.common.registry;

import com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter.InterdimensionalWirelessTransmitterContainerMenu;

import java.util.function.Supplier;

import net.minecraft.world.inventory.MenuType;
import org.jspecify.annotations.Nullable;

import static java.util.Objects.requireNonNull;

public final class Menus {
    public static final Menus INSTANCE = new Menus();

    @Nullable
    private Supplier<MenuType<InterdimensionalWirelessTransmitterContainerMenu>> interdimensionalWirelessTransmitter;

    public MenuType<InterdimensionalWirelessTransmitterContainerMenu> getInterdimensionalWirelessTransmitter() {
        return requireNonNull(this.interdimensionalWirelessTransmitter).get();
    }

    public void setInterdimensionalWirelessTransmitter(final Supplier<MenuType<InterdimensionalWirelessTransmitterContainerMenu>> supplier) {
        this.interdimensionalWirelessTransmitter = supplier;
    }
}
