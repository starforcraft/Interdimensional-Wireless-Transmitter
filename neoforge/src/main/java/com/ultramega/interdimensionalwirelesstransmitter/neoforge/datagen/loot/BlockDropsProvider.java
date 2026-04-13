package com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen.loot;

import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;

import java.util.ArrayList;
import java.util.Set;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class BlockDropsProvider extends BlockLootSubProvider {
    public BlockDropsProvider(final HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().forEach((color, id, block) -> drop(block.get()));
    }

    private void drop(final Block block) {
        this.add(block, this.createSingleItemTable(block)
            .apply(copyName()));
    }

    private static CopyComponentsFunction.Builder copyName() {
        return CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
            .include(DataComponents.CUSTOM_NAME);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return new ArrayList<>(Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().values());
    }
}
