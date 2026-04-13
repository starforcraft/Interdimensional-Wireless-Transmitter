package com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen;

import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;

import com.refinedmods.refinedstorage.common.content.BlockColorMap;
import com.refinedmods.refinedstorage.common.support.BlockItemProvider;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

import static com.refinedmods.refinedstorage.neoforge.datagen.tag.BlockTagsProvider.MINEABLE;
import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.MOD_ID;

public class BlockTagsProvider extends IntrinsicHolderTagsProvider<Block> {
    @SuppressWarnings("deprecation")
    public BlockTagsProvider(final PackOutput packOutput, final CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, Registries.BLOCK, registries, block -> block.builtInRegistryHolder().key(), MOD_ID);
    }

    @Override
    protected void addTags(final HolderLookup.Provider provider) {
        this.markAsMineable(Blocks.INSTANCE.getInterdimensionalWirelessTransmitter());
    }

    private <T extends Block & BlockItemProvider<I>, I extends BlockItem> void markAsMineable(
        final BlockColorMap<T, I> map
    ) {
        this.tag(MINEABLE).addAll(map.values().stream().map(b -> (Block) b).toList());
    }
}
