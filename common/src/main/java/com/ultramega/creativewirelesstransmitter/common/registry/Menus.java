package com.ultramega.creativewirelesstransmitter.common.registry;

import com.ultramega.creativewirelesstransmitter.common.creativewirelesstransmitter.CreativeWirelessTransmitterContainerMenu;

import java.util.function.Supplier;
import javax.annotation.Nullable;

import net.minecraft.world.inventory.MenuType;

import static java.util.Objects.requireNonNull;

public final class Menus {
    public static final Menus INSTANCE = new Menus();

    @Nullable
    private Supplier<MenuType<CreativeWirelessTransmitterContainerMenu>> creativeWirelessTransmitter;

    public MenuType<CreativeWirelessTransmitterContainerMenu> getCreativeWirelessTransmitter() {
        return requireNonNull(creativeWirelessTransmitter).get();
    }

    public void setCreativeWirelessTransmitter(final Supplier<MenuType<CreativeWirelessTransmitterContainerMenu>> supplier) {
        this.creativeWirelessTransmitter = supplier;
    }
}
