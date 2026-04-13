package com.ultramega.interdimensionalwirelesstransmitter.neoforge.datagen.models;

import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;

import com.refinedmods.refinedstorage.common.support.AbstractActiveColoredDirectionalBlock;
import com.refinedmods.refinedstorage.common.support.direction.DefaultDirectionType;

import java.util.stream.Stream;

import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.MOD_ID;
import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.createInterdimensionalIdentifier;
import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

public class ModelProviders extends ModelProvider {
    private static final TextureSlot CUTOUT = TextureSlot.create("cutout");

    private static final ModelTemplate ACTIVE_INTERDIMENSIONAL_WIRELESS_TRANSMITTER_MODEL = ModelTemplates.create(
        MOD_ID + ":interdimensional_wireless_transmitter/active",
        CUTOUT
    );

    public ModelProviders(final PackOutput output) {
        super(output, MOD_ID);
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return Stream.of();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return Stream.of();
    }

    @Override
    protected void registerModels(final BlockModelGenerators blockModels, final ItemModelGenerators itemModels) {
        this.registerInterdimensionalWirelessTransmitters(blockModels, itemModels);
    }

    private void registerInterdimensionalWirelessTransmitters(final BlockModelGenerators blockModels,
                                                              final ItemModelGenerators itemModels) {
        final Identifier inactiveBlockModel = createInterdimensionalIdentifier("block/interdimensional_wireless_transmitter/inactive");

        Blocks.INSTANCE.getInterdimensionalWirelessTransmitter().forEach((color, id, transmitter) -> {
            final Identifier cutout = createInterdimensionalIdentifier("block/interdimensional_wireless_transmitter/cutouts/" + color.getName());

            final Identifier itemModel = ACTIVE_INTERDIMENSIONAL_WIRELESS_TRANSMITTER_MODEL.create(
                createInterdimensionalIdentifier("item/interdimensional_wireless_transmitter/" + color.getName()),
                new TextureMapping().put(CUTOUT, texture(cutout)),
                itemModels.modelOutput
            );
            itemModels.itemModelOutput.accept(transmitter.get().asItem(), ItemModelUtils.plainModel(itemModel));

            final Identifier activeBlockModel = ACTIVE_INTERDIMENSIONAL_WIRELESS_TRANSMITTER_MODEL.create(
                createInterdimensionalIdentifier("block/interdimensional_wireless_transmitter/" + color.getName()),
                new TextureMapping().put(CUTOUT, texture(cutout)),
                blockModels.modelOutput
            );

            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(transmitter.get())
                .with(PropertyDispatch.initial(AbstractActiveColoredDirectionalBlock.ACTIVE)
                    .select(false, plainVariant(inactiveBlockModel))
                    .select(true, plainVariant(activeBlockModel)))
                .with(PropertyDispatch.modify(DefaultDirectionType.FACE_CLICKED.getProperty())
                    .generate(direction -> variant -> variant
                        .withXRot(this.getXRot(direction))
                        .withYRot(this.getYRot(direction)))));
        });
    }

    private Quadrant getXRot(final Direction direction) {
        return switch (direction) {
            case DOWN -> Quadrant.R0;
            case UP -> Quadrant.R180;
            case NORTH, SOUTH, WEST, EAST -> Quadrant.R90;
        };
    }

    private Quadrant getYRot(final Direction direction) {
        return switch (direction) {
            case DOWN, UP, SOUTH -> Quadrant.R0;
            case NORTH -> Quadrant.R180;
            case EAST -> Quadrant.R270;
            case WEST -> Quadrant.R90;
        };
    }

    private static Material texture(final Identifier location) {
        return new Material(location);
    }
}
