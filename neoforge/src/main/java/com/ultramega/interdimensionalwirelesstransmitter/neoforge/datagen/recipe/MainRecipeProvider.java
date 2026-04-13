package com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen.recipe;

import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;

import com.refinedmods.refinedstorage.common.content.Tags;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

public class MainRecipeProvider extends RecipeProvider {
    public MainRecipeProvider(final HolderLookup.Provider registries, final RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        this.interdimensionalWirelessTransmitter();
    }

    private void interdimensionalWirelessTransmitter() {
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().getDefault())
            .pattern("WNW")
            .pattern("NEN")
            .pattern("WNW")
            .define('W', com.refinedmods.refinedstorage.common.content.Blocks.INSTANCE.getWirelessTransmitter().getDefault())
            .define('N', net.minecraft.world.item.Items.NETHER_STAR)
            .define('E', net.minecraft.world.item.Items.ELYTRA)
            .unlockedBy("has_wireless_transmitter", this.has(Tags.WIRELESS_TRANSMITTERS))
            .save(this.output);
    }

    public static final class Runner extends RecipeProvider.Runner {
        public Runner(final PackOutput packOutput, final CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(final HolderLookup.Provider registries,
                                                      final RecipeOutput output) {
            return new MainRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Interdimensional Wireless Transmitter recipes";
        }
    }
}
