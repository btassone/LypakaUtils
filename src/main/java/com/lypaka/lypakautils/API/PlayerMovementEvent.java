package com.lypaka.lypakautils.API;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class PlayerMovementEvent extends Event {

    private final ServerPlayerEntity player;
    private final int stepsTaken;

    public PlayerMovementEvent (ServerPlayerEntity player, int stepsTaken) {

        this.player = player;
        this.stepsTaken = stepsTaken;

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public int getStepsTaken() {

        return this.stepsTaken;

    }

}
