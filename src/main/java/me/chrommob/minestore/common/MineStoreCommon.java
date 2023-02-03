package me.chrommob.minestore.common;

import co.aikar.commands.CommandManager;
import me.chrommob.minestore.common.command.ReloadCommandCommon;
import me.chrommob.minestore.common.commandGetters.WebListener;
import me.chrommob.minestore.common.commandHolder.CommandDumper;
import me.chrommob.minestore.common.commandHolder.CommandStorage;
import me.chrommob.minestore.common.templates.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MineStoreCommon {
    private static MineStoreCommon instance;

    public MineStoreCommon() {
        instance = this;
    }

    private ConfigReaderCommon configReader;
    public void registerConfigReader(ConfigReaderCommon configReader) {
        this.configReader = configReader;
    }

    private CommandExecuterCommon commandExecuterCommon;
    public void registerCommandExecuter(CommandExecuterCommon commandExecuter) {
        this.commandExecuterCommon = commandExecuter;
    }

    private LoggerCommon logger;
    public void registerLogger(LoggerCommon logger) {
        this.logger = logger;
    }

    private PlayerJoinListener playerJoinListener;
    public void registerPlayerJoinListener(PlayerJoinListener playerJoinListener) {
        this.playerJoinListener = playerJoinListener;
    }

    private CommandManager commandManager;
    public void registerCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    private CommandGetter commandGetter;
    private CommandStorage commandStorage;
    private CommandDumper commandDumper;
    public void init() {
        commandDumper = new CommandDumper();
        commandStorage = new CommandStorage();
        configReader.init();
        commandStorage.init();
        switch (configReader.commandMode()) {
            case WEBLISTENER:
                commandGetter = new WebListener(this);
                break;
            case WEBSOCKET:
        }
        if (!verify()) {
            log("Your plugin is not configured correctly. Please check your config.yml");
            return;
        }
        commandGetter.start();
        commandManager.registerCommand(new ReloadCommandCommon());
    }

    public void reload() {
        log("Reloading...");
        configReader.reload();
        if (commandGetter.load()) {
            log("Config reloaded.");
        }
        commandGetter.start();
    }

    private boolean verify() {
        if (commandManager == null) {
            log("CommandManager is not registered.");
            return false;
        }
        if (configReader == null) {
            log("ConfigReader is not registered.");
            return false;
        }
        if (commandExecuterCommon == null) {
            log("CommandExecuter is not registered.");
            return false;
        }
        if (logger == null) {
            log("Logger is not registered.");
            return false;
        }
        if (commandGetter == null) {
            log("CommandGetter is not registered.");
            return false;
        }
        if (!commandGetter.load()) {
            log("Url is not configured correctly.");
            return false;
        }
        return true;
    }

    public static MineStoreCommon getInstance() {
        return instance;
    }

    public ConfigReaderCommon configReader() {
        return configReader;
    }

    public CommandExecuterCommon commandExecuter() {
        return commandExecuterCommon;
    }

    public void log(String message) {
        logger.log(message);
    }

    public void debug(String message) {
        if (configReader.debug()) {
            try {
                logger.log("[DEBUG] " + message);
            } catch (Exception ignored) {
                System.out.println("[DEBUG] " + message);
            }
        }
    }

    public CommandStorage commandStorage() {
        return commandStorage;
    }

    public File dataFolder() {
        return configReader.dataFolder();
    }

    public CommandDumper commandDumper() {
        return commandDumper;
    }

    public void debug(Exception e) {
        if (e.getMessage() != null) {
            debug(e.getMessage());
        }
        if (e.getStackTrace() != null) {
            debug(Arrays.toString(e.getStackTrace()));
        }
        if (e.getCause() != null){
            debug(e.getCause().toString());
        }
    }
}
