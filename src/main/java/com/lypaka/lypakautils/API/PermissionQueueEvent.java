package com.lypaka.lypakautils.API;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class PermissionQueueEvent extends Event {

    private final ServerPlayerEntity player;
    private final String permission;
    private boolean bypass;

    public PermissionQueueEvent (ServerPlayerEntity player, String permission, boolean bypass) {

        this.player = player;
        this.permission = permission;
        this.bypass = bypass;

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public String getPermission() {

        return this.permission;

    }

    public boolean isBypassed() {

        return this.bypass;

    }

    public void setBypass (boolean bypass) {

        this.bypass = bypass;

    }

}
