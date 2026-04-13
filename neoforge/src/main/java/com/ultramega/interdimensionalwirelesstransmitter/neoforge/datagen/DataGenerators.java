package com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen;

import com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen.advancement.AdvancementProvider;
import com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen.loot.LootTableProviderImpl;
import com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen.models.ModelProviders;
import com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen.recipe.MainRecipeProvider;
import com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen.recipe.RecoloringRecipeProvider;

import java.util.List;

import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.MOD_ID;

@EventBusSubscriber(modid = MOD_ID)
public class DataGenerators {
    private DataGenerators() {
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent.Client e) {
        final DataGenerator generator = e.getGenerator();
        final DataGenerator.PackGenerator pack = generator.getVanillaPack(true);
        pack.addProvider(ModelProviders::new);
        pack.addProvider(output -> new LootTableProviderImpl(output, e.getLookupProvider()));
        pack.addProvider(output -> new RecoloringRecipeProvider.Runner(output, e.getLookupProvider()));
        pack.addProvider(output -> new MainRecipeProvider.Runner(output, e.getLookupProvider()));
        final BlockTagsProvider blockTagsProvider = pack.addProvider(output ->
            new BlockTagsProvider(output, e.getLookupProvider()));
        pack.addProvider(output ->
            new ItemTagsProvider(output, e.getLookupProvider(), blockTagsProvider.contentsGetter()));
        pack.addProvider(output -> new net.minecraft.data.advancements.AdvancementProvider(
            output,
            e.getLookupProvider(),
            List.of(new AdvancementProvider())
        ));
    }
}
