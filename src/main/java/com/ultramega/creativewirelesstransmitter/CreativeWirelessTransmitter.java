package com.ultramega.creativewirelesstransmitter;

import com.ultramega.creativewirelesstransmitter.config.Config;
import com.ultramega.creativewirelesstransmitter.datageneration.DataGenerators;
import com.ultramega.creativewirelesstransmitter.registry.ModBlockEntities;
import com.ultramega.creativewirelesstransmitter.registry.ModBlocks;
import com.ultramega.creativewirelesstransmitter.registry.ModContainerMenus;
import com.ultramega.creativewirelesstransmitter.registry.ModItems;
import com.ultramega.creativewirelesstransmitter.setup.ClientSetup;
import com.ultramega.creativewirelesstransmitter.setup.CommonSetup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("creativewirelesstransmitter")
public class CreativeWirelessTransmitter {
    public static final String MOD_ID = "creativewirelesstransmitter";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    
    public CreativeWirelessTransmitter() {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSetup::new);
        
        MinecraftForge.EVENT_BUS.register(this);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.common_config);
        
        CommonSetup commonSetup = new CommonSetup();
        ModBlocks.register();
        ModItems.register();
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(commonSetup::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().register(new DataGenerators());

        ModContainerMenus.REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlockEntities.REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
        
        Config.loadConfig(Config.common_config, FMLPaths.CONFIGDIR.get().resolve(CreativeWirelessTransmitter.MOD_ID + "-common.toml").toString());
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
    	
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    	
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    	
    }
}
