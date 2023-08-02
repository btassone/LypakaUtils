package com.lypaka.lypakautils.API;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public abstract class PlayerMovementEvent extends Event {

    private final ServerPlayerEntity player;
    private final int stepsTaken;

    public PlayerMovementEvent (ServerPlayerEntity player, int stepsTaken) {

        this.player = player;
        this.stepsTaken = stepsTaken;

    }

    public static class Land extends PlayerMovementEvent {

        private final String blockID;

        public Land (ServerPlayerEntity player, int stepsTaken, String blockID) {

            super(player, stepsTaken);
            this.blockID = blockID;

        }

        public String getBlockID() {

            return this.blockID;

        }

    }

    public static class Swim extends PlayerMovementEvent {

        private final String blockID;

        public Swim (ServerPlayerEntity player, int stepsTaken, String blockID) {

            super(player, stepsTaken);
            this.blockID = blockID;

        }

        public String getBlockID() {

            return this.blockID;

        }

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public int getStepsTaken() {

        return this.stepsTaken;

    }

}
