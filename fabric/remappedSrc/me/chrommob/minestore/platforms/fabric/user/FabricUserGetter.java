package me.chrommob.minestore.platforms.fabric.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import me.chrommob.minestore.common.interfaces.user.CommonUser;
import me.chrommob.minestore.common.interfaces.user.UserGetter;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

public class FabricUserGetter implements UserGetter {
    private final PlayerManager pManager;

    public FabricUserGetter(MinecraftServer server) {
        this.pManager = server.getPlayerManager();
    }

    @Override
    public CommonUser get(UUID uuid) {
        return new UserFabric(this.pManager.getPlayer(uuid));
    }

    @Override
    public CommonUser get(String username) {
        return new UserFabric(this.pManager.getPlayer(username));
    }

    @Override
    public Set<CommonUser> getAllPlayers() {
        Set<CommonUser> users = new HashSet<>();
        for (ServerPlayerEntity player : this.pManager.getPlayerList()) {
            users.add(new UserFabric(player));
        }
        return users;
    }

}
