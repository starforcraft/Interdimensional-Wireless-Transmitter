package com.ultramega.creativewirelesstransmitter.datageneration;

import com.refinedmods.refinedstorage.block.BlockDirection;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;

import java.util.function.Function;

public class BlockModels {
    private final BlockModelGenerator generator;

    public BlockModels(BlockModelGenerator blockModelGenerator) {
        this.generator = blockModelGenerator;
    }

    public void wirelessTransmitterBlock(Block block, Function<BlockState, ModelFile> modelFunc, int angleOffset) {
        generator.getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(BlockDirection.ANY.getProperty());

                    int xRotation;
                    if (dir.getAxis() == Direction.Axis.Y) {
                        xRotation = dir == Direction.UP ? 180 : 0;
                    } else {
                        xRotation = dir.getAxis().isHorizontal() ? 90 : 0;
                    }

                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(xRotation)
                            .rotationY(dir.getAxis().isVertical() ? 0 : (((int) dir.toYRot()) + angleOffset) % 360)
                            .build();
                });
    }

    public BlockModelBuilder createWirelessTransmitterModel(String name, ResourceLocation cutout) {
        return generator.models().withExistingParent(name, new ResourceLocation(CreativeWirelessTransmitter.MOD_ID, "creative_wireless_transmitter"))
                .texture("cutout", cutout);
    }

    public BlockModelBuilder createWirelessTransmitterNonEmissiveModel(String name, ResourceLocation cutout) {
        return generator.models().withExistingParent(name, new ResourceLocation(CreativeWirelessTransmitter.MOD_ID, "creative_wireless_transmitter_nonemissive"))
                .texture("cutout", cutout);
    }
}
