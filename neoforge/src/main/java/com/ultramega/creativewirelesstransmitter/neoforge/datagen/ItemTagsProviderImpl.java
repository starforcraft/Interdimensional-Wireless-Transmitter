package com.ultramega.creativewirelesstransmitter.neoforge.datagen;

import com.ultramega.creativewirelesstransmitter.common.Tags;
import com.ultramega.creativewirelesstransmitter.common.registry.Blocks;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.ultramega.creativewirelesstransmitter.common.CreativeWirelessTransmitterIdentifierUtil.MOD_ID;

public class ItemTagsProviderImpl extends ItemTagsProvider {
    public ItemTagsProviderImpl(final PackOutput packOutput,
                                final CompletableFuture<HolderLookup.Provider> registries,
                                final TagsProvider<Block> blockTagsProvider,
                                final ExistingFileHelper existingFileHelper) {
        super(packOutput, registries, blockTagsProvider.contentsGetter(), MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(final HolderLookup.Provider provider) {
        addAllToTag(Tags.CREATIVE_WIRELESS_TRANSMITTERS,
            Blocks.INSTANCE.getCreativeWirelessTransmitter().values().stream()
                .map(block -> (Supplier<Item>) block::asItem)
                .toList());
    }

    private <T extends Item> void addAllToTag(final TagKey<Item> t, final Collection<Supplier<T>> items) {
        tag(t).add(items.stream().map(Supplier::get).toArray(Item[]::new)).replace(false);
    }
}
