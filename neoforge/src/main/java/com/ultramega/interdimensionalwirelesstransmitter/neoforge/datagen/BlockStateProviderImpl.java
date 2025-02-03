package com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen;

import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;

import com.refinedmods.refinedstorage.common.support.AbstractActiveColoredDirectionalBlock;
import com.refinedmods.refinedstorage.common.support.direction.DefaultDirectionType;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.MOD_ID;
import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.createInterdimensionalIdentifier;

public class BlockStateProviderImpl extends BlockStateProvider {
    private final ExistingFileHelper existingFileHelper;

    public BlockStateProviderImpl(final PackOutput output, final ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
        this.existingFileHelper = existingFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        final ModelFile inactive = modelFile(createInterdimensionalIdentifier("block/interdimensional_wireless_transmitter/inactive"));
        Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().forEach((color, id, block) -> {
            final var builder = getVariantBuilder(block.get());
            builder.forAllStates(blockState -> {
                final ConfiguredModel.Builder<?> model = ConfiguredModel.builder();
                if (Boolean.TRUE.equals(blockState.getValue(AbstractActiveColoredDirectionalBlock.ACTIVE))) {
                    model.modelFile(modelFile(
                        createInterdimensionalIdentifier("block/interdimensional_wireless_transmitter/" + color.getName())));
                } else {
                    model.modelFile(inactive);
                }
                final Direction direction = blockState.getValue(DefaultDirectionType.FACE_CLICKED.getProperty());
                addRotation(model, direction);
                return model.build();
            });
        });
    }

    private void addRotation(final ConfiguredModel.Builder<?> model, final Direction direction) {
        final int rotationX;
        if (direction.getAxis() == Direction.Axis.Y) {
            rotationX = direction == Direction.UP ? 180 : 0;
        } else {
            rotationX = direction.getAxis().isHorizontal() ? 90 : 0;
        }
        final int rotationY = direction.getAxis().isVertical() ? 0 : ((int) direction.toYRot()) % 360;
        model.rotationX(rotationX);
        model.rotationY(rotationY);
    }

    private ModelFile modelFile(final ResourceLocation location) {
        return new ModelFile.ExistingModelFile(location, existingFileHelper);
    }
}
