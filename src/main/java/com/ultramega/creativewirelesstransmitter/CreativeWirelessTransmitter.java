package com.ultramega.creativewirelesstransmitter;

import com.ultramega.creativewirelesstransmitter.config.Config;
import com.ultramega.creativewirelesstransmitter.datageneration.DataGenerators;
import com.ultramega.creativewirelesstransmitter.registry.*;
import com.ultramega.creativewirelesstransmitter.setup.ClientSetup;
import com.ultramega.creativewirelesstransmitter.setup.CommonSetup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CreativeWirelessTransmitter.MOD_ID)
public class CreativeWirelessTransmitter {
    public static final String MOD_ID = "creativewirelesstransmitter";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public CreativeWirelessTransmitter() {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSetup::new);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.common_config);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        CommonSetup commonSetup = new CommonSetup();
        ModCreativeTabs.TABS.register(bus);
        ModItems.register();
        ModBlocks.register();
        ModContainerMenus.REGISTRY.register(bus);
        ModBlockEntities.REGISTRY.register(bus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(commonSetup::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().register(new DataGenerators());

        Config.loadConfig(Config.common_config, FMLPaths.CONFIGDIR.get().resolve(CreativeWirelessTransmitter.MOD_ID + "-common.toml").toString());
    }
}
