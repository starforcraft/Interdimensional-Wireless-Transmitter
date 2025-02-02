package com.ultramega.creativewirelesstransmitter.common.registry;

import com.refinedmods.refinedstorage.common.support.BaseBlockItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public final class Items {
    public static final Items INSTANCE = new Items();

    private final List<Supplier<BaseBlockItem>> allCreativeWirelessTransmitters = new ArrayList<>();

    private Items() {
    }

    public void addCreativeWirelessTransmitter(final Supplier<BaseBlockItem> supplier) {
        allCreativeWirelessTransmitters.add(supplier);
    }

    public List<Supplier<BaseBlockItem>> getCreativeWirelessTransmitters() {
        return Collections.unmodifiableList(allCreativeWirelessTransmitters);
    }
}
