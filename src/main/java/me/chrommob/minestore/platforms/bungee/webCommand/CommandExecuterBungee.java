package me.chrommob.minestore.platforms.bungee.webCommand;

import me.chrommob.minestore.platforms.bungee.MineStoreBungee;
import me.chrommob.minestore.common.interfaces.CommandExecuterCommon;

public class CommandExecuterBungee implements CommandExecuterCommon {
    private final MineStoreBungee mineStoreBungee;
    public CommandExecuterBungee(MineStoreBungee mineStoreBungee) {
        this.mineStoreBungee = mineStoreBungee;
    }


    @Override
    public void execute(String command) {
        mineStoreBungee.getProxy().getPluginManager().dispatchCommand(mineStoreBungee.getProxy().getConsole(), command);
    }

    @Override
    public boolean isOnline(String username) {
        return mineStoreBungee.getProxy().getPlayer(username) != null;
    }
}
