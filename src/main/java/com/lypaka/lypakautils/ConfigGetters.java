package com.lypaka.lypakautils;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.List;
import java.util.Map;

public class ConfigGetters {

    public static boolean tickListenerEnabled;
    public static boolean loadPokemonTypeMap;
    public static Map<String, Map<String, String>> permissionGroups;
    public static List<String> spigotCommands;

    public static void load() throws ObjectMappingException {

        tickListenerEnabled = LypakaUtils.configManager.getConfigNode(0, "Enable-Tick-Listener").getBoolean();
        loadPokemonTypeMap = false;
        if (LypakaUtils.configManager.getConfigNode(0, "Load-Pokemon-Type-Map").isVirtual()) {

            LypakaUtils.configManager.getConfigNode(0, "Load-Pokemon-Type-Map").setValue(false);
            LypakaUtils.configManager.save();

        } else {

            loadPokemonTypeMap = LypakaUtils.configManager.getConfigNode(0, "Load-Pokemon-Type-Map").getBoolean();

        }
        permissionGroups = LypakaUtils.configManager.getConfigNode(1, "Groups").getValue(new TypeToken<Map<String, Map<String, String>>>() {});
        spigotCommands = LypakaUtils.configManager.getConfigNode(2, "Spigot-Commands").getList(TypeToken.of(String.class));

    }

}
