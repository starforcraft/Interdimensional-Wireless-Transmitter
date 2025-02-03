package com.ultramega.interdimensionalwirelesstransmitter.common.registry;

import com.refinedmods.refinedstorage.common.support.BaseBlockItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public final class Items {
    public static final Items INSTANCE = new Items();

    private final List<Supplier<BaseBlockItem>> allInterdimensionalWirelessTransmitters = new ArrayList<>();

    private Items() {
    }

    public void addInterdimensionalWirelessTransmitter(final Supplier<BaseBlockItem> supplier) {
        allInterdimensionalWirelessTransmitters.add(supplier);
    }

    public List<Supplier<BaseBlockItem>> getInterdimensionalWirelessTransmitters() {
        return Collections.unmodifiableList(allInterdimensionalWirelessTransmitters);
    }
}
