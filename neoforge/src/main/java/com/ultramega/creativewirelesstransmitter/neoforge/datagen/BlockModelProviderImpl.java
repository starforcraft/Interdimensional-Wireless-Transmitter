package com.ultramega.creativewirelesstransmitter.neoforge.datagen;

import com.ultramega.creativewirelesstransmitter.common.registry.Blocks;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.ultramega.creativewirelesstransmitter.common.CreativeWirelessTransmitterIdentifierUtil.MOD_ID;
import static com.ultramega.creativewirelesstransmitter.common.CreativeWirelessTransmitterIdentifierUtil.createCreativeWirelessTransmitterIdentifier;

public class BlockModelProviderImpl extends BlockModelProvider {
    private static final String CUTOUT_TEXTURE = "cutout";

    public BlockModelProviderImpl(final PackOutput output, final ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        final ResourceLocation parent = createCreativeWirelessTransmitterIdentifier("block/creative_wireless_transmitter/active");
        Blocks.INSTANCE.getCreativeWirelessTransmitter().forEach((color, id, block) ->
            withExistingParent("block/creative_wireless_transmitter/" + color.getName(), parent)
                .texture(CUTOUT_TEXTURE, createCreativeWirelessTransmitterIdentifier("block/creative_wireless_transmitter/cutouts/" + color.getName())));
    }
}
