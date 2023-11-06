package com.ultramega.creativewirelesstransmitter.datageneration;

import com.refinedmods.refinedstorage.block.NetworkNodeBlock;
import com.refinedmods.refinedstorage.util.ColorMap;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import com.ultramega.creativewirelesstransmitter.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockModelGenerator extends BlockStateProvider {
    private final BlockModels models;

    public BlockModelGenerator(PackOutput output, String id, ExistingFileHelper existingFileHelper) {
        super(output, id, existingFileHelper);
        models = new BlockModels(this);
    }

    @Override
    protected void registerStatesAndModels() {
        genCubeAllCutoutModels(ModBlocks.CREATIVE_WIRELESS_TRANSMITTER);
    }

    private <T extends Block> void genCubeAllCutoutModels(ColorMap<T> blockMap) {
        blockMap.forEach((color, registryObject) -> {
            Block block = registryObject.get();
            String folderName = blockMap.get(ColorMap.DEFAULT_COLOR).getId().getPath();

            models.simpleBlockStateModel(block, state -> {
                if (Boolean.FALSE.equals(state.getValue(NetworkNodeBlock.CONNECTED))) {
                    return models.createCubeAllCutoutModel(
                            "block/" + folderName + "/disconnected",
                            resourceLocation(folderName, folderName),
                            resourceLocation(folderName, folderName),
                            resourceLocation(folderName, "cutouts/disconnected")
                    );
                } else {
                    ModelFile model = models.createCubeAllCutoutModel(
                            "block/" + folderName + "/" + color,
                            resourceLocation(folderName, folderName),
                            resourceLocation(folderName, folderName),
                            resourceLocation(folderName, "cutouts/" + color)
                    );

                    simpleBlockItem(block, model);
                    return model;
                }
            });
        });
    }

    private ResourceLocation resourceLocation(String folderName, String name) {
        return new ResourceLocation(CreativeWirelessTransmitter.MOD_ID, "block/" + folderName + "/" + name);
    }
}
