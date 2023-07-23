package com.lypaka.lypakautils.Listeners;

import com.lypaka.lypakautils.API.PlayerMovementEvent;
import com.lypaka.lypakautils.ConfigGetters;
import com.lypaka.lypakautils.PlayerLocationData.PlayerDataHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.UUID;

public class TickListener {

    private static int count = -1;

    @SubscribeEvent
    public void onTick (TickEvent.ServerTickEvent event) {

        if (!ConfigGetters.tickListenerEnabled) return;

        count++;
        if (count >= 20) {

            count = -1;
            for (Map.Entry<UUID, ServerPlayerEntity> entry : JoinListener.playerMap.entrySet()) {

                ServerPlayerEntity player = entry.getValue();
                int currentX = (int) player.position().x;
                int currentZ = (int) player.position().z;
                int steps = PlayerDataHandler.calculateStepsTaken(player.getUUID(), currentX, currentZ);
                if (steps > 0) {

                    PlayerMovementEvent playerMovementEvent = new PlayerMovementEvent(player, steps);
                    MinecraftForge.EVENT_BUS.post(playerMovementEvent);

                }

            }

        }

    }

}
