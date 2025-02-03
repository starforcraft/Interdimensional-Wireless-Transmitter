package com.ultramega.interdimensionalwirelesstransmitter.fabric;

import com.ultramega.interdimensionalwirelesstransmitter.common.AbstractClientModInitializer;
import com.ultramega.interdimensionalwirelesstransmitter.common.registry.Blocks;

import com.refinedmods.refinedstorage.common.content.BlockColorMap;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;

public class ClientModInitializerImpl extends AbstractClientModInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        setRenderLayers();
        registerScreens(new com.refinedmods.refinedstorage.common.AbstractClientModInitializer.ScreenRegistration() {
            @Override
            public <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void register(
                final MenuType<? extends M> type,
                final com.refinedmods.refinedstorage.common.AbstractClientModInitializer.ScreenConstructor<M, U> factory
            ) {
                MenuScreens.register(type, factory::create);
            }
        });
    }

    private void setRenderLayers() {
        setCutout(Blocks.INSTANCE.getInterdimensionalWirelessTransmitter());
    }

    private void setCutout(final BlockColorMap<?, ?> blockMap) {
        blockMap.values().forEach(this::setCutout);
    }

    private void setCutout(final Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
    }
}
