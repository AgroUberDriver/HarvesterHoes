package me.agro.harvesterhoes.util;

import org.bukkit.entity.Player;

import java.io.IOException;

public abstract class SubCommands {
    public SubCommands() {

    }

    public abstract void onCommand(Player player, String[] args) throws IOException;

    public abstract String name();

    public abstract String help();

    public abstract String[] aliases();
}
