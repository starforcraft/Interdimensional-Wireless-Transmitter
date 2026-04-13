package com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen;

import com.ultramega.interdimensionalwirelesstransmitter.common.Tags;
import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagCopyingItemTagProvider;

import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.MOD_ID;

public class ItemTagsProvider extends BlockTagCopyingItemTagProvider {
    public ItemTagsProvider(final PackOutput packOutput,
                            final CompletableFuture<HolderLookup.Provider> registries,
                            final CompletableFuture<TagLookup<Block>> blockTagsProvider) {
        super(packOutput, registries, blockTagsProvider, MOD_ID);
    }

    @Override
    protected void addTags(final HolderLookup.Provider provider) {
        this.addAllToTag(Tags.INTERDIMENSIONAL_WIRELESS_TRANSMITTERS,
            Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().values().stream()
                .map(block -> (Supplier<Item>) block::asItem)
                .toList());
    }

    private <T extends Item> void addAllToTag(final TagKey<Item> t, final Collection<Supplier<T>> items) {
        this.tag(t).add(items.stream().map(Supplier::get).toArray(Item[]::new)).replace(false);
    }
}
