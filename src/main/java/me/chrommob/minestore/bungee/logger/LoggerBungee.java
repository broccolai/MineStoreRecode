package me.chrommob.minestore.bungee.logger;

import me.chrommob.minestore.bungee.MineStoreBungee;
import me.chrommob.minestore.common.templates.LoggerCommon;

public class LoggerBungee implements LoggerCommon {
    private final MineStoreBungee mineStoreBungee;
    public LoggerBungee(MineStoreBungee mineStoreBungee) {
        this.mineStoreBungee = mineStoreBungee;
    }

    @Override
    public void log(String message) {
        mineStoreBungee.getLogger().info(message);
    }
}
