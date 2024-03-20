package com.ultramega.creativewirelesstransmitter;

import com.ultramega.creativewirelesstransmitter.config.ServerConfig;
import com.ultramega.creativewirelesstransmitter.datageneration.DataGenerators;
import com.ultramega.creativewirelesstransmitter.registry.ModBlockEntities;
import com.ultramega.creativewirelesstransmitter.registry.ModBlocks;
import com.ultramega.creativewirelesstransmitter.registry.ModContainerMenus;
import com.ultramega.creativewirelesstransmitter.registry.ModItems;
import com.ultramega.creativewirelesstransmitter.setup.ClientSetup;
import com.ultramega.creativewirelesstransmitter.setup.CommonSetup;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(CreativeWirelessTransmitter.MOD_ID)
public final class CreativeWirelessTransmitter {
    public static final String MOD_ID = "creativewirelesstransmitter";
    public static final ServerConfig SERVER_CONFIG = new ServerConfig();

    public CreativeWirelessTransmitter(IEventBus eventBus) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            eventBus.addListener(ClientSetup::registerMenuScreens);
        }

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG.getSpec());

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        eventBus.addListener(CommonSetup::onCommonSetup);
        eventBus.addListener(CommonSetup::onRegister);
        eventBus.register(new DataGenerators());

        ModContainerMenus.REGISTRY.register(eventBus);
        ModBlockEntities.REGISTRY.register(eventBus);
    }
}
