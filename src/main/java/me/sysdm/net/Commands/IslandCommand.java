package me.sysdm.net.Commands;

import me.sysdm.net.Abstraction.Island;
import me.sysdm.net.Abstraction.IslandCreator;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;


public class IslandCommand implements CommandExecutor {

    final IslandCreator ic = new IslandCreator();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                if (ic.hasIsland(player.getUniqueId())) {
                    sender.sendMessage(ChatColor.GREEN + "Teleporting...");
                    player.teleport(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandLocation());
                } else {
                    sender.sendMessage(ChatColor.GREEN + "Creating new island...");
                    Island island = ic.createIsland(player.getUniqueId());
                    sender.sendMessage(ChatColor.GREEN + "Teleporting...");
                    player.teleport(island.getIslandLocation());
                }
            }else if(args.length == 1) {
                if(args[0].equalsIgnoreCase("remove")) {
                    if(ic.islandList.containsKey(player.getUniqueId())) {
                        ic.removeIsland(player.getUniqueId(), ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandUUID());
                        sender.sendMessage(ChatColor.RED + "Removed island");
                    }
                }
            }
        }
        return true;
    }
}