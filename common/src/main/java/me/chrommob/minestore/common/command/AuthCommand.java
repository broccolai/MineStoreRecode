package me.chrommob.minestore.common.command;

import me.chrommob.minestore.common.MineStoreCommon;
import me.chrommob.minestore.common.authHolder.AuthUser;
import me.chrommob.minestore.common.command.types.CommonConsoleUser;
import me.chrommob.minestore.common.config.ConfigKey;
import me.chrommob.minestore.common.interfaces.user.AbstractUser;
import me.chrommob.minestore.common.interfaces.user.CommonUser;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.Permission;

@SuppressWarnings("unused")
public class AuthCommand {
    private final MineStoreCommon plugin;
    public AuthCommand(MineStoreCommon plugin) {
        this.plugin = plugin;
    }
    @Permission("minestore.auth")
    @Command("minestore|ms auth")
    public void onAuth(AbstractUser abstractUser) {
        CommonUser user = abstractUser.user();
        if (user instanceof CommonConsoleUser) {
            user.sendMessage("[MineStore] You can't use this command from console!");
            return;
        }
        AuthUser authUser = plugin.authHolder().getAuthUser(user.getName());
        if (authUser == null) {
            user.sendMessage((plugin.miniMessage()).deserialize((String)plugin.configReader().get(ConfigKey.AUTH_FAILURE_MESSAGE)));
            return;
        }
        authUser.confirmAuth();
        user.sendMessage((plugin.miniMessage()).deserialize((String)plugin.configReader().get(ConfigKey.AUTH_SUCCESS_MESSAGE)));
    }
}
