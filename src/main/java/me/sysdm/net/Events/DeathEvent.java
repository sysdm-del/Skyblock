package me.sysdm.net.Events;

import me.sysdm.net.Abstraction.IslandCreator;
import me.sysdm.net.Economy.Coin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    final Coin coin = new Coin();

    final IslandCreator ic = new IslandCreator();

    @EventHandler
    void onPlayerDeath(PlayerDeathEvent e) {
        if(ic.hasIsland(e.getEntity().getUniqueId())) {
            if(coin.playerCoins.containsKey(ic.getIslandByPlayerUUID(e.getEntity().getUniqueId()).getIslandPlayer())) {
                coin.playerCoins.remove(ic.getIslandByPlayerUUID(e.getEntity().getUniqueId()).getIslandPlayer());
                e.getEntity().sendMessage(ChatColor.RED + "You lost " + coin.playerCoins.get(ic.getIslandByPlayerUUID(e.getEntity().getUniqueId()).getIslandPlayer()).length + ChatColor.RED + " coins!");
            }
        }
    }
}
