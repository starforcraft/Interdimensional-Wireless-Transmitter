package com.ultramega.creativewirelesstransmitter.setup;

import com.refinedmods.refinedstorage.render.BakedModelOverrideRegistry;
import com.ultramega.creativewirelesstransmitter.gui.CreativeWirelessTransmitterScreen;
import com.ultramega.creativewirelesstransmitter.registry.ModContainerMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientSetup {
    private final BakedModelOverrideRegistry bakedModelOverrideRegistry = new BakedModelOverrideRegistry();

    public ClientSetup() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onModelBake);
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent e) {
        MenuScreens.register(ModContainerMenus.CREATIVE_WIRELESS_TRANSMITTER.get(), CreativeWirelessTransmitterScreen::new);
    }

    @SubscribeEvent
    public void onModelBake(ModelEvent.BakingCompleted e) {
        for (ResourceLocation id : e.getModels().keySet()) {
            BakedModelOverrideRegistry.BakedModelOverrideFactory factory = this.bakedModelOverrideRegistry.get(new ResourceLocation(id.getNamespace(), id.getPath()));

            if (factory != null) {
                e.getModels().put(id, factory.create(e.getModels().get(id), e.getModels()));
            }
        }
    }
}
