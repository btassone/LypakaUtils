package com.lypaka.lypakautils;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.Map;

public class ConfigGetters {

    public static boolean tickListenerEnabled;
    public static Map<String, Map<String, String>> permissionGroups;

    public static void load() throws ObjectMappingException {

        tickListenerEnabled = LypakaUtils.configManager.getConfigNode(0, "Enable-Tick-Listener").getBoolean();
        permissionGroups = LypakaUtils.configManager.getConfigNode(1, "Groups").getValue(new TypeToken<Map<String, Map<String, String>>>() {});

    }

}
