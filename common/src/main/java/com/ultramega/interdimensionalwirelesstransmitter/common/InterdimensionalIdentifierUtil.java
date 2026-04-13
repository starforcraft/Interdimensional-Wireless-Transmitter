package com.ultramega.interdimensionalwirelesstransmitter.common;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;

public final class InterdimensionalIdentifierUtil {
    public static final String MOD_ID = "interdimensionalwirelesstransmitter";

    private InterdimensionalIdentifierUtil() {
    }

    public static Identifier createInterdimensionalIdentifier(final String value) {
        return Identifier.fromNamespaceAndPath(MOD_ID, value);
    }

    public static MutableComponent createInterdimensionalTranslation(final String category, final String value) {
        return Component.translatable(createInterdimensionalTranslationKey(category, value));
    }

    public static String createInterdimensionalTranslationKey(final String category, final String value) {
        return String.format("%s.%s.%s", category, MOD_ID, value);
    }
}
