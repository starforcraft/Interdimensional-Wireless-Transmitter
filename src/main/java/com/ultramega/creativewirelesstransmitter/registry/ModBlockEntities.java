package com.ultramega.creativewirelesstransmitter.registry;

import com.refinedmods.refinedstorage.blockentity.data.BlockEntitySynchronizationManager;
import com.refinedmods.refinedstorage.blockentity.data.BlockEntitySynchronizationSpec;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import com.ultramega.creativewirelesstransmitter.blockentity.CreativeWirelessTransmitterBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CreativeWirelessTransmitter.MOD_ID);

    public static final RegistryObject<BlockEntityType<CreativeWirelessTransmitterBlockEntity>> CREATIVE_WIRELESS_TRANSMITTER =
            REGISTRY.register("creative_wireless_transmitter", () -> registerSynchronizationParameters(CreativeWirelessTransmitterBlockEntity.SPEC, BlockEntityType.Builder.of(CreativeWirelessTransmitterBlockEntity::new, ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.getBlocks()).build(null)));

    private static <T extends BlockEntity> BlockEntityType<T> registerSynchronizationParameters(BlockEntitySynchronizationSpec spec, BlockEntityType<T> t) {
        spec.getParameters().forEach(BlockEntitySynchronizationManager::registerParameter);
        return t;
    }
}
