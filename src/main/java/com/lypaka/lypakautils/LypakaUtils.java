package com.lypaka.lypakautils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;

@Mod(LypakaUtils.MOD_ID)
public class LypakaUtils {

    public static final String MOD_ID = "lypakautils";
    public static final String MOD_NAME = "LypakaUtils";
    public static final String VERSION = "1.16.5-0.0.9";
    public static final Logger logger = LogManager.getLogger("LypakaUtils");

    public LypakaUtils() throws MalformedURLException, ObjectMappingException {

        logger.info("Loading LypakaUtils version: " + VERSION);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(LypakaUtils::onServerStarted);

    }

    public static void onServerStarted (FMLServerStartingEvent event) {

        WorldMap.load();

    }

}
