package com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen.recipe;

import com.ultramega.interdimensionalwirelesstransmitter.common.Tags;
import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;

import com.refinedmods.refinedstorage.common.support.RecoloringRecipe;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.crafting.Recipe;

import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.createInterdimensionalIdentifier;

public class RecoloringRecipeProvider extends RecipeProvider {
    public RecoloringRecipeProvider(final HolderLookup.Provider registries, final RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().forEach((color, id, block) ->
            this.output.accept(this.recipeId(color, "interdimensional_wireless_transmitter"),
                RecoloringRecipe.create(Tags.INTERDIMENSIONAL_WIRELESS_TRANSMITTERS, color, block.get(), this.registries), null));
    }

    private ResourceKey<Recipe<?>> recipeId(final DyeColor color, final String suffix) {
        final Identifier recipeId = createInterdimensionalIdentifier("coloring/" + color.getName() + "_" + suffix);
        return ResourceKey.create(Registries.RECIPE, recipeId);
    }

    public static final class Runner extends RecipeProvider.Runner {
        public Runner(final PackOutput packOutput, final CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(final HolderLookup.Provider registries,
                                                      final RecipeOutput output) {
            return new RecoloringRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Interdimensional Wireless Transmitter recoloring recipes";
        }
    }
}
