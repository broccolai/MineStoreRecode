package me.chrommob.minestore.bungee.events;

import me.chrommob.minestore.bungee.MineStoreBungee;
import me.chrommob.minestore.common.MineStoreCommon;
import me.chrommob.minestore.common.templates.PlayerJoinListener;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerJoinListenerBungee implements Listener, PlayerJoinListener {
    public PlayerJoinListenerBungee(MineStoreBungee mineStoreBungee) {
        mineStoreBungee.getProxy().getPluginManager().registerListener(mineStoreBungee, this);
    }

    @EventHandler
    public void onPlayerJoin(PostLoginEvent event) {
        MineStoreCommon.getInstance().commandStorage().onPlayerJoin(event.getPlayer().getName());
    }
}
