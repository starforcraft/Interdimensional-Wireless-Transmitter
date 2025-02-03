package com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen;

import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.MOD_ID;
import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.createInterdimensionalIdentifier;

public class BlockModelProviderImpl extends BlockModelProvider {
    private static final String CUTOUT_TEXTURE = "cutout";

    public BlockModelProviderImpl(final PackOutput output, final ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        final ResourceLocation parent = createInterdimensionalIdentifier("block/interdimensional_wireless_transmitter/active");
        Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().forEach((color, id, block) ->
            withExistingParent("block/interdimensional_wireless_transmitter/" + color.getName(), parent)
                .texture(CUTOUT_TEXTURE, createInterdimensionalIdentifier("block/interdimensional_wireless_transmitter/cutouts/" + color.getName())));
    }
}
