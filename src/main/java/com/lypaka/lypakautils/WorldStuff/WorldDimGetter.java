package com.lypaka.lypakautils.WorldStuff;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.ServerWorldInfo;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class WorldDimGetter {

    public static int getDimID (World world) {

        ServerWorldInfo info = (ServerWorldInfo) world.getLevelData();
        String worldDimension = world.dimension().location().toString();
        if (worldDimension.equalsIgnoreCase("minecraft:nether") || worldDimension.equalsIgnoreCase("minecraft:the_nether")) {

            return -1;

        } else {

            return 0;

        }

    }

}
