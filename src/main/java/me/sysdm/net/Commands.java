package me.sysdm.net;

import me.sysdm.net.Abstraction.Island;
import me.sysdm.net.Abstraction.IslandCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Commands implements CommandExecutor {

    final IslandCreator ic = new IslandCreator();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(ic.hasIsland(player.getUniqueId())) {
                player.teleport(ic.getIslandByPlayerUUID(player.getUniqueId()).islandLocation());
            }else{
                Island island = ic.createIsland(player.getUniqueId());
                player.teleport(island.islandLocation());
            }
        }
        return true;
    }
}
