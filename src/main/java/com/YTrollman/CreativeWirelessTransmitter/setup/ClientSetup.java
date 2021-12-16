package com.YTrollman.CreativeWirelessTransmitter.setup;

import com.YTrollman.CreativeWirelessTransmitter.CreativeWirelessTransmitter;
import com.YTrollman.CreativeWirelessTransmitter.gui.screen.CreativeWirelessTransmitterScreen;
import com.YTrollman.CreativeWirelessTransmitter.registry.ModBlocks;
import com.YTrollman.CreativeWirelessTransmitter.registry.ModContainers;
import com.refinedmods.refinedstorage.render.BakedModelOverrideRegistry;
import com.refinedmods.refinedstorage.render.model.FullbrightBakedModel;
import com.refinedmods.refinedstorage.util.ColorMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.BiConsumer;

public class ClientSetup {

    private final BakedModelOverrideRegistry bakedModelOverrideRegistry = new BakedModelOverrideRegistry();

    public ClientSetup() {
        forEachColorApply("creative_wireless_transmitter", (name, color) -> bakedModelOverrideRegistry.add(name, (base, registry) -> new FullbrightBakedModel(base, true, getColoredModel(color, "block/creative_wireless_transmitter/cutouts/"))));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onModelBake);
    }

    private ResourceLocation getColoredModel(DyeColor color, String path) {
        return new ResourceLocation(CreativeWirelessTransmitter.MOD_ID, path + color);
    }

    private void forEachColorApply(String name, BiConsumer<ResourceLocation, DyeColor> consumer) {
        for (DyeColor color : DyeColor.values()) {
            String prefix = color == ColorMap.DEFAULT_COLOR ? "" : color + "_";
            consumer.accept(new ResourceLocation(CreativeWirelessTransmitter.MOD_ID, prefix + name), color);
        }
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent e) {
        MenuScreens.register(ModContainers.CREATIVE_WIRELESS_TRANSMITTER, CreativeWirelessTransmitterScreen::new);

        ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.values().forEach(block -> ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout()));
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent e) {
        FullbrightBakedModel.invalidateCache();

        for (ResourceLocation id : e.getModelRegistry().keySet()) {
            BakedModelOverrideRegistry.BakedModelOverrideFactory factory = this.bakedModelOverrideRegistry.get(new ResourceLocation(id.getNamespace(), id.getPath()));

            if (factory != null) {
                e.getModelRegistry().put(id, factory.create(e.getModelRegistry().get(id), e.getModelRegistry()));
            }
        }
    }
}
