package com.YTrollman.CreativeWirelessTransmitter;

import com.YTrollman.CreativeWirelessTransmitter.config.Config;
import com.YTrollman.CreativeWirelessTransmitter.datageneration.DataGenerators;
import com.YTrollman.CreativeWirelessTransmitter.registry.ModBlocks;
import com.YTrollman.CreativeWirelessTransmitter.registry.ModItems;
import com.YTrollman.CreativeWirelessTransmitter.setup.ClientSetup;
import com.YTrollman.CreativeWirelessTransmitter.setup.CommonSetup;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
public class CreativeWirelessTransmitter
{
    public static final String MOD_ID = "creativewirelesstransmitter";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    
    public CreativeWirelessTransmitter() {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSetup::new);
        
        MinecraftForge.EVENT_BUS.register(this);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.client_config);
        
        CommonSetup commonSetup = new CommonSetup();
        ModBlocks.register();
        ModItems.register();
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(commonSetup::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(BlockEntityType.class, commonSetup::onRegisterTiles);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(MenuType.class, commonSetup::onRegisterContainers);
        FMLJavaModLoadingContext.get().getModEventBus().register(new DataGenerators());
        
        Config.loadConfig(Config.client_config, FMLPaths.CONFIGDIR.get().resolve("creative_wireless_transmitter-client.toml").toString());
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }

    private void doClientStuff(final FMLClientSetupEvent event) 
    {
    	
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    	
    }
}
