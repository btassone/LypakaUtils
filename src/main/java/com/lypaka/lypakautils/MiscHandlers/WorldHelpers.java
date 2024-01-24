package com.lypaka.lypakautils.MiscHandlers;

import com.lypaka.lypakautils.WorldStuff.WorldMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.DimensionType;

public class WorldHelpers {

    public static boolean isPlayerInUltraSpace (ServerPlayerEntity player) {

        return player.world.getDimensionKey().getLocation().toString().equalsIgnoreCase("pixelmon:ultra_space");

    }

    public static boolean isPlayerInDrownedWorldForSomeUngodlyReason (ServerPlayerEntity player) {

        return player.world.getDimensionKey().getLocation().toString().equalsIgnoreCase("pixelmon:drowned_world");

    }

    public static boolean isPlayerInOverworld (ServerPlayerEntity player) {

        return player.world.getDimensionKey().getLocation().toString().equalsIgnoreCase("minecraft:overworld");

    }

    public static boolean isPlayerInNether (ServerPlayerEntity player) {

        return player.world.getDimensionKey().getLocation().toString().equalsIgnoreCase("minecraft:nether");

    }

    public static boolean isPlayerInEnd (ServerPlayerEntity player) {

        return player.world.getDimensionKey().getLocation().toString().equalsIgnoreCase("minecraft:the_end") ||
                player.world.getDimensionKey().getLocation().toString().equalsIgnoreCase("minecraft:end"); // not sure what Minecraft calls that, lol

    }

    public static String getEntityDimensionID (Entity entity) {

        return entity.world.getDimensionKey().getLocation().toString();

    }

    public static void teleportPlayer (ServerPlayerEntity player, String world, int x, int y, int z, float yaw, float pitch) {

        player.teleport(WorldMap.worldMap.get(world), x, y, z, yaw, pitch);

    }

}
