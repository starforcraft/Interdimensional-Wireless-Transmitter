package com.ultramega.interdimensionalwirelesstransmitter.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.createInterdimensionalIdentifier;

public final class Tags {
    public static final TagKey<Item> INTERDIMENSIONAL_WIRELESS_TRANSMITTERS = createTag("interdimensional_wireless_transmitters");

    private Tags() {
    }

    private static TagKey<Item> createTag(final String id) {
        return TagKey.create(Registries.ITEM, createInterdimensionalIdentifier(id));
    }
}
