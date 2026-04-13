package com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen.advancement;

import com.ultramega.interdimensionalwirelesstransmitter.common.Tags;
import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;

import java.util.function.Consumer;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;

import static com.refinedmods.refinedstorage.common.util.IdentifierUtil.createIdentifier;
import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.MOD_ID;
import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.createInterdimensionalTranslation;

public class AdvancementProvider implements AdvancementSubProvider {
    @Override
    public void generate(final HolderLookup.Provider registries, final Consumer<AdvancementHolder> consumer) {
        final var items = registries.lookupOrThrow(Registries.ITEM);

        Advancement.Builder.advancement()
            .display(
                Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().getDefault(),
                createInterdimensionalTranslation("advancements", "interdimensional_wireless_transmitter"),
                createInterdimensionalTranslation("advancements", "interdimensional_wireless_transmitter.description"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                false
            )
            .parent(new AdvancementHolder(createIdentifier("wireless"), null))
            .addCriterion("interdimensional_wireless_transmitter_in_inventory",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(items, Tags.INTERDIMENSIONAL_WIRELESS_TRANSMITTERS).build()
                ))
            .save(consumer, MOD_ID + ":interdimensional_wireless_transmitter");
    }
}
