package com.ultramega.creativewirelesstransmitter.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.ultramega.creativewirelesstransmitter.common.CreativeWirelessTransmitterIdentifierUtil.createCreativeWirelessTransmitterIdentifier;

public final class Tags {
    public static final TagKey<Item> CREATIVE_WIRELESS_TRANSMITTERS = createTag("creative_wireless_transmitters");

    private Tags() {
    }

    private static TagKey<Item> createTag(final String id) {
        return TagKey.create(Registries.ITEM, createCreativeWirelessTransmitterIdentifier(id));
    }
}
