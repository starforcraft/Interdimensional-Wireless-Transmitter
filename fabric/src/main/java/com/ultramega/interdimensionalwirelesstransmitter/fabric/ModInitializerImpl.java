package com.ultramega.interdimensionalwirelesstransmitter.fabric;

import com.ultramega.interdimensionalwirelesstransmitter.common.AbstractModInitializer;
import com.ultramega.interdimensionalwirelesstransmitter.common.Platform;
import com.ultramega.interdimensionalwirelesstransmitter.common.registry.BlockEntities;
import com.ultramega.interdimensionalwirelesstransmitter.common.registry.CreativeModeTabItems;

import com.refinedmods.refinedstorage.common.api.RefinedStorageApi;
import com.refinedmods.refinedstorage.common.content.BlockEntityProvider;
import com.refinedmods.refinedstorage.common.content.BlockEntityTypeFactory;
import com.refinedmods.refinedstorage.common.content.DirectRegistryCallback;
import com.refinedmods.refinedstorage.common.content.ExtendedMenuTypeFactory;
import com.refinedmods.refinedstorage.fabric.api.RefinedStorageFabricApi;
import com.refinedmods.refinedstorage.fabric.api.RefinedStoragePlugin;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModInitializerImpl extends AbstractModInitializer implements RefinedStoragePlugin, ModInitializer {
    @Override
    public void onApiAvailable(final RefinedStorageApi refinedStorageApi) {
        Platform.setConfigProvider(ConfigImpl::get);
        this.registerContent();
        this.registerCapabilities();
        this.registerCreativeModeTabListener(refinedStorageApi);
    }

    private void registerContent() {
        this.registerBlocks(new DirectRegistryCallback<>(BuiltInRegistries.BLOCK));
        this.registerItems(new DirectRegistryCallback<>(BuiltInRegistries.ITEM));
        this.registerBlockEntities(
            new DirectRegistryCallback<>(BuiltInRegistries.BLOCK_ENTITY_TYPE),
            new BlockEntityTypeFactory() {
                @Override
                public <T extends BlockEntity> BlockEntityType<T> create(final BlockEntityProvider<T> factory,
                                                                         final Block... allowedBlocks) {
                    return FabricBlockEntityTypeBuilder.create(factory::create, allowedBlocks).build();
                }
            }
        );
        this.registerMenus(new DirectRegistryCallback<>(BuiltInRegistries.MENU), new ExtendedMenuTypeFactory() {
            @Override
            public <T extends AbstractContainerMenu, D> MenuType<T> create(final MenuSupplier<T, D> supplier,
                                                                           final StreamCodec<RegistryFriendlyByteBuf, D> streamCodec) {
                return new ExtendedMenuType<>(supplier::create, streamCodec);
            }
        });
    }

    private void registerCapabilities() {
        this.registerEnergyBlockEntityProviders();
    }

    private void registerEnergyBlockEntityProviders() {
        RefinedStorageFabricApi.INSTANCE.getNetworkNodeContainerProviderLookup().registerForBlockEntity(
            (be, dir) -> be.getContainerProvider(),
            BlockEntities.INSTANCE.getInterdimensionalWirelessTransmitter()
        );
    }

    private void registerCreativeModeTabListener(final RefinedStorageApi refinedStorageApi) {
        final ResourceKey<CreativeModeTab> creativeModeTab = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            refinedStorageApi.getCreativeModeTabId()
        );
        CreativeModeTabEvents.modifyOutputEvent(creativeModeTab).register(
            entries -> CreativeModeTabItems.appendBlocks(entries::accept)
        );

        final ResourceKey<CreativeModeTab> coloredCreativeModeTab = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            refinedStorageApi.getColoredCreativeModeTabId()
        );
        CreativeModeTabEvents.modifyOutputEvent(coloredCreativeModeTab).register(
            entries -> CreativeModeTabItems.appendColoredVariants(entries::accept)
        );
    }

    @Override
    public void onInitialize() {
        AutoConfig.register(ConfigImpl.class, Toml4jConfigSerializer::new);
    }
}
