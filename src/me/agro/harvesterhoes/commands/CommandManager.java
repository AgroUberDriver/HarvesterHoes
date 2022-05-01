package me.agro.harvesterhoes.commands;

import me.agro.harvesterhoes.Main;
import me.agro.harvesterhoes.util.SubCommands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommands> commands = new ArrayList<SubCommands>();

    private Main plugin = Main.getInstance();

    public String main = "hoe";
    public String help = "help";
    public String give = "give";
    public String info = "info";
    public String reload = "reload";


    public void setup() {
        plugin.getCommand(main).setExecutor(this);

        this.commands.add(new HelpCommand());
        this.commands.add(new GiveCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This is not a console command!");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase(main)) {
            if (args.length == 0) {
                return true;
            }

            SubCommands target = this.get(args[0]);

            if (target == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.INVALID-SUBCOMMAND")));
                return true;
            }

            ArrayList<String> arrayList = new ArrayList<String>();

            arrayList.addAll(Arrays.asList(args));
            arrayList.remove(0);

            try {
                target.onCommand(player, args);
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "An internal error has occured.");

                e.printStackTrace();
            }
        }

        return true;
    }

    private SubCommands get(String name) {
        Iterator<SubCommands> subcommands = this.commands.iterator();

        while (subcommands.hasNext()) {
            SubCommands sc = (SubCommands) subcommands.next();

            if (sc.name().equalsIgnoreCase(name)) {
                return sc;
            }

            String[] aliases;

            int length = (aliases = sc.aliases()).length;


            for (int var5 = 0; var5 < length; ++var5) {
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)) {
                    return sc;
                }
            }
        }
        return null;
    }

}
