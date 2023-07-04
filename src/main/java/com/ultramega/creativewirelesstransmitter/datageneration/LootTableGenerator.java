package com.ultramega.creativewirelesstransmitter.datageneration;

import com.refinedmods.refinedstorage.RSBlocks;
import com.ultramega.creativewirelesstransmitter.registry.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.stream.Collectors;

public class LootTableGenerator extends BlockLootSubProvider {
    public LootTableGenerator() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    protected void generate() {
        ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.values().forEach(block -> dropSelf(block.get()));
    }

    protected Iterable<Block> getKnownBlocks() {
        return RSBlocks.COLORED_BLOCKS.stream().map(RegistryObject::get).collect(Collectors.toList());
    }
}
