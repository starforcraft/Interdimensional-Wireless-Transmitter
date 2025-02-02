package com.ultramega.creativewirelesstransmitter.common;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

public final class CreativeWirelessTransmitterIdentifierUtil {
    public static final String MOD_ID = "creativewirelesstransmitter";

    private CreativeWirelessTransmitterIdentifierUtil() {
    }

    public static ResourceLocation createCreativeWirelessTransmitterIdentifier(final String value) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, value);
    }

    public static MutableComponent createCreativeWirelessTransmitterTranslation(final String category, final String value) {
        return Component.translatable(createCreativeWirelessTransmitterTranslationKey(category, value));
    }

    public static String createCreativeWirelessTransmitterTranslationKey(final String category, final String value) {
        return String.format("%s.%s.%s", category, MOD_ID, value);
    }
}
