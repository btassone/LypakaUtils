package com.lypaka.lypakautils.WorldStuff;

import com.lypaka.lypakautils.LypakaUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DerivedWorldInfo;
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

    public static String getWorldName (ServerPlayerEntity player) {

        try {

            return ((ServerWorldInfo) player.getLevel().getLevelData()).getLevelName();

        } catch (ClassCastException er) {

            try {

                return ((DerivedWorldInfo) player.getLevel().getLevelData()).getLevelName();

            } catch (ClassCastException er2) {

                LypakaUtils.logger.error("Couldn't get world name for player: " + player.getName().getString());
                LypakaUtils.logger.info("Report this message to Lypaka along with all the following data:");
                LypakaUtils.logger.info("Player world data: " + player.getLevel().getLevelData());
                LypakaUtils.logger.info("Player dimension registry key: " + player.level.dimension().getRegistryName().toString());
                LypakaUtils.logger.info("Player level data storage: " + player.getLevel().getDataStorage().toString());

            }

        }

        return "";

    }

    public static String getWorldName (World world) {

        try {

            return ((ServerWorldInfo) world.getLevelData()).getLevelName();

        } catch (ClassCastException er) {

            return ((DerivedWorldInfo) world.getLevelData()).getLevelName();

        }

    }

}
