package com.YTrollman.CreativeWirelessTransmitter.datageneration;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.registry.ModBlocks;
import com.refinedmods.refinedstorage.block.NetworkNodeBlock;
import com.refinedmods.refinedstorage.util.ColorMap;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockModelGenerator extends BlockStateProvider {

    private final BlockModels models;

    public BlockModelGenerator(DataGenerator generator, String id, ExistingFileHelper existingFileHelper) {
        super(generator, id, existingFileHelper);
        models = new BlockModels(this);
    }

    @Override
    protected void registerStatesAndModels() {
    	genCreativeWirelessTransmitterModels();
    }

    private void genCreativeWirelessTransmitterModels() {
        ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.forEach((color, registryObject) -> {
            Block block = registryObject.get();
            String folderName = ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.get(ColorMap.DEFAULT_COLOR).getId().getPath();

            models.CreativeWirelessTransmitterBlock(block, state -> {
                if (Boolean.FALSE.equals(state.get(NetworkNodeBlock.CONNECTED))) {
                    return models.createCreativeWirelessTransmitterModel(
                        "block/" + folderName + "/disconnected",
                        resourceLocation(folderName, "disconnected")
                    );
                } else {
                    ModelFile model = models.createCreativeWirelessTransmitterModel(
                        "block/" + folderName + "/" + color,
                        resourceLocation(folderName, "" + color)
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
