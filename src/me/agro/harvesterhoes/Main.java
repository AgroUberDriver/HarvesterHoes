package me.agro.harvesterhoes;

import me.agro.harvesterhoes.commands.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private static Main instance;

    public CommandManager commandManager;
    public void onEnable() {
        setInstance(this);

        commandManager = new CommandManager();
        commandManager.setup();

        File configFile = new File(this.getDataFolder() + "/config.yml");
        if(!configFile.exists())
        {
            this.saveDefaultConfig();
        }

        System.out.println("[HarvesterHoes] Plugin Initialized.");

    }

    public void onDisable() {
        System.out.println("[HarvesterHoes] Plugin Disabled.");
    }

    public static Main getInstance() {
        return instance;
    }

    public static void setInstance(Main instance) {
        Main.instance = instance;
    }

}
