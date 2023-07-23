package com.lypaka.lypakautils;

import com.lypaka.lypakautils.ConfigurationLoaders.BasicConfigManager;
import com.lypaka.lypakautils.ConfigurationLoaders.ConfigUtils;
import com.lypaka.lypakautils.WorldStuff.WorldMap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mod(LypakaUtils.MOD_ID)
public class LypakaUtils {

    public static final String MOD_ID = "lypakautils";
    public static final String MOD_NAME = "LypakaUtils";
    public static final String VERSION = "1.16.5-0.0.9";
    public static final Logger logger = LogManager.getLogger("LypakaUtils");
    public static BasicConfigManager configManager;

    public LypakaUtils() throws IOException, ObjectMappingException {

        logger.info("Loading LypakaUtils version: " + VERSION);
        MinecraftForge.EVENT_BUS.register(this);
        Path dir = ConfigUtils.checkDir(Paths.get("./config/lypakautils"));
        String[] files = new String[]{"lypakautils.conf"};
        configManager = new BasicConfigManager(files, dir, LypakaUtils.class, MOD_NAME, MOD_ID, logger);
        configManager.init();
        ConfigGetters.load();
        MinecraftForge.EVENT_BUS.addListener(LypakaUtils::onServerStarted);

    }

    public static void onServerStarted (FMLServerStartingEvent event) {

        WorldMap.load();

    }

}
