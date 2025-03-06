package com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen;

import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;

import com.refinedmods.refinedstorage.common.content.BlockColorMap;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.refinedmods.refinedstorage.neoforge.datagen.tag.BlockTagsProvider.MINEABLE;
import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.MOD_ID;

public class BlockTagsProvider extends TagsProvider<Block> {
    public BlockTagsProvider(final PackOutput packOutput,
                             final CompletableFuture<HolderLookup.Provider> providerCompletableFuture,
                             final @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.BLOCK, providerCompletableFuture, MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(final HolderLookup.Provider provider) {
        markAsMineable(Blocks.INSTANCE.getInterdimensionalWirelessTransmitter());
    }

    private void markAsMineable(final BlockColorMap<?, ?> map) {
        tag(MINEABLE).addAll(map.values().stream().map(b -> ResourceKey.create(
            Registries.BLOCK,
            BuiltInRegistries.BLOCK.getKey(b)
        )).toList());
    }
}
