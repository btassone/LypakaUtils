package com.lypaka.lypakautils.Listeners;

import com.google.common.reflect.TypeToken;
import com.lypaka.lypakautils.LPPlayer;
import com.lypaka.lypakautils.LypakaUtils;
import com.lypaka.lypakautils.PlayerLocationData.PlayerDataHandler;
import com.lypaka.lypakautils.PlayerLocationData.PlayerLocation;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = LypakaUtils.MOD_ID)
public class JoinListener {

    /**
     * Used to store all actively online players
     * I know Forge has a way to do this, but I'm not particularly fond of how it works
     */
    public static Map<UUID, ServerPlayerEntity> playerMap = new HashMap<>();

    @SubscribeEvent
    public static void onJoin (PlayerEvent.PlayerLoggedInEvent event) throws ObjectMappingException {

        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        playerMap.put(player.getUUID(), player);
        if (!PlayerDataHandler.playerLocationMap.containsKey(player.getUUID())) {

            int x = (int) player.position().x;
            int z = (int) player.position().z;
            PlayerLocation location = new PlayerLocation(x, z, x, z);
            PlayerDataHandler.playerLocationMap.put(player.getUUID(), location);

        }
        LypakaUtils.playerConfigManager.loadPlayer(player.getUUID());
        List<String> groups = LypakaUtils.playerConfigManager.getPlayerConfigNode(player.getUUID(), "Groups").getList(TypeToken.of(String.class));
        List<String> permissions = LypakaUtils.playerConfigManager.getPlayerConfigNode(player.getUUID(), "Permissions").getList(TypeToken.of(String.class));

        LPPlayer lpPlayer = new LPPlayer(player.getUUID(), groups, permissions);
        LypakaUtils.playerMap.put(player.getUUID(), lpPlayer);

    }

    @SubscribeEvent
    public static void onLeave (PlayerEvent.PlayerLoggedOutEvent event) {

        playerMap.entrySet().removeIf(entry -> entry.getKey().toString().equalsIgnoreCase(event.getPlayer().getUUID().toString()));
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        LPPlayer lpPlayer = LypakaUtils.playerMap.get(player.getUUID());
        lpPlayer.save(true);

    }

}
