package com.ultramega.creativewirelesstransmitter.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

@Mod.EventBusSubscriber
public class Config {
    private static final ForgeConfigSpec.Builder common_builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec common_config;

    static {
        CreativeWirelessTransmitterConfig.init(common_builder);
        common_config = common_builder.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CreativeWirelessTransmitter.LOGGER.info("Loading config: " + path);
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        CreativeWirelessTransmitter.LOGGER.info("Built config: " + path);
        file.load();
        CreativeWirelessTransmitter.LOGGER.info("Loaded config: " + path);
        config.setConfig(file);
    }
}