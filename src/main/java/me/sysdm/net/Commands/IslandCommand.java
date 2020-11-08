package me.sysdm.net.Commands;

import me.sysdm.net.Islands.Island;
import me.sysdm.net.Islands.IslandCreator;
import me.sysdm.net.Islands.IslandManager;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;


public class IslandCommand implements CommandExecutor {

    final IslandManager im = new IslandManager();

    final IslandCreator ic = new IslandCreator();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                if (im.hasIsland(player.getUniqueId())) {
                    sender.sendMessage(ChatColor.GREEN + "Teleporting...");
                    player.teleport(im.getIslandByPlayerUUID(player.getUniqueId()).getPlayerSpawn());
                } else {
                    sender.sendMessage(ChatColor.GREEN + "Creating new island...");
                    Island island = ic.createIsland(player);
                    island.generateIsland();
                    sender.sendMessage(ChatColor.GREEN + "Teleporting...");
                    player.teleport(island.getPlayerSpawn());
                }
            }else if(args.length == 1) {
                if(args[0].equalsIgnoreCase("restart")) {
                    if(im.containsValueIslandList(player.getUniqueId())) {
                        im.getIslandByPlayerUUID(player.getUniqueId()).generateIsland();
                    }
                }
            }
        }
        return true;
    }
}
