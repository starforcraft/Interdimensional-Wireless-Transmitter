package com.ultramega.creativewirelesstransmitter.neoforge.datagen;

import com.ultramega.creativewirelesstransmitter.common.creativewirelesstransmitter.CreativeWirelessTransmitterBlock;
import com.ultramega.creativewirelesstransmitter.common.registry.Blocks;

import com.refinedmods.refinedstorage.common.content.ColorMap;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.ultramega.creativewirelesstransmitter.common.CreativeWirelessTransmitterIdentifierUtil.MOD_ID;
import static com.ultramega.creativewirelesstransmitter.common.CreativeWirelessTransmitterIdentifierUtil.createCreativeWirelessTransmitterIdentifier;

public class ItemModelProviderImpl extends ItemModelProvider {
    private static final String CUTOUT_TEXTURE_KEY = "cutout";

    public ItemModelProviderImpl(final PackOutput output, final ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        final ResourceLocation base = createCreativeWirelessTransmitterIdentifier("block/creative_wireless_transmitter/inactive");
        final ColorMap<CreativeWirelessTransmitterBlock> blocks = Blocks.INSTANCE.getCreativeWirelessTransmitter();
        blocks.forEach((color, id, block) -> singleTexture(
            id.getPath(),
            base,
            CUTOUT_TEXTURE_KEY,
            createCreativeWirelessTransmitterIdentifier("block/creative_wireless_transmitter/cutouts/" + color.getName())
        ));
    }
}
