package com.ultramega.creativewirelesstransmitter.datageneration;

import com.refinedmods.refinedstorage.block.NetworkNodeBlock;
import com.refinedmods.refinedstorage.util.ColorMap;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import com.ultramega.creativewirelesstransmitter.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockModelGenerator extends BlockStateProvider {
    private final BlockModels models;

    public BlockModelGenerator(PackOutput output, String id, ExistingFileHelper existingFileHelper) {
        super(output, id, existingFileHelper);
        models = new BlockModels(this);
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.forEach((color, registryObject) -> {
            Block block = registryObject.get();
            String folderName = ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.get(ColorMap.DEFAULT_COLOR).getId().getPath();

            models.wirelessTransmitterBlock(block, state -> {
                if (Boolean.FALSE.equals(state.getValue(NetworkNodeBlock.CONNECTED))) {
                    return models.createWirelessTransmitterNonEmissiveModel(
                            "block/" + folderName + "/disconnected",
                            resourceLocation(folderName, "cutouts/disconnected")
                    );
                } else {
                    ModelFile model = models.createWirelessTransmitterModel(
                            "block/" + folderName + "/" + color,
                            resourceLocation(folderName, "cutouts/" + color)
                    );

                    simpleBlockItem(block, model);
                    return model;
                }
            }, 0);
        });
    }

    private ResourceLocation resourceLocation(String folderName, String name) {
        return new ResourceLocation(CreativeWirelessTransmitter.MOD_ID, "block/" + folderName + "/" + name);
    }
}
