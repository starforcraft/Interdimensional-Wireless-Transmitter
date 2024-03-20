package com.ultramega.creativewirelesstransmitter.registry;

import com.refinedmods.refinedstorage.blockentity.data.BlockEntitySynchronizationManager;
import com.refinedmods.refinedstorage.blockentity.data.BlockEntitySynchronizationSpec;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import com.ultramega.creativewirelesstransmitter.blockentity.CreativeWirelessTransmitterBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, CreativeWirelessTransmitter.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CreativeWirelessTransmitterBlockEntity>> CREATIVE_WIRELESS_TRANSMITTER =
            REGISTRY.register("creative_wireless_transmitter", () -> registerSynchronizationParameters(CreativeWirelessTransmitterBlockEntity.SPEC, BlockEntityType.Builder.of(CreativeWirelessTransmitterBlockEntity::new, ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.getBlocks()).build(null)));
    private static <T extends BlockEntity> BlockEntityType<T> registerSynchronizationParameters(BlockEntitySynchronizationSpec spec, BlockEntityType<T> t) {
        spec.getParameters().forEach(BlockEntitySynchronizationManager::registerParameter);
        return t;
    }
}
