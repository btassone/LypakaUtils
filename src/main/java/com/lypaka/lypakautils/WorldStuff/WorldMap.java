package com.lypaka.lypakautils.WorldStuff;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.ServerWorldInfo;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {

    public static Map<String, ServerWorld> worldMap = new HashMap<>();

    public static void load() {

        for (ServerWorld world : ServerLifecycleHooks.getCurrentServer().getAllLevels()) {

            if (world == null || !(world.getLevelData() instanceof ServerWorldInfo)) continue;
            worldMap.put(((ServerWorldInfo) world.getLevelData()).getLevelName(), world);

        }

    }

}
