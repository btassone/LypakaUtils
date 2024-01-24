package com.lypaka.lypakautils.MiscHandlers;

import net.minecraft.entity.player.ServerPlayerEntity;

public class WorldHelpers {

    public static boolean isPlayerInUltraSpace (ServerPlayerEntity player) {

        return player.world.getDimensionKey().getLocation().toString().equalsIgnoreCase("pixelmon:ultra_space");

    }

    public static boolean isPlayerInDrownedWorldForSomeUngodlyReason (ServerPlayerEntity player) {

        return player.world.getDimensionKey().getLocation().toString().equalsIgnoreCase("pixelmon:drowned_world");

    }

}
