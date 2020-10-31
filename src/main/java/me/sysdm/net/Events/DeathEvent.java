package me.sysdm.net.Events;

import me.sysdm.net.Economy.Coin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener{

    final Coin coin = new Coin();

    @EventHandler
    void onPlayerDeath(PlayerDeathEvent e) {
        if(coin.hasIsland(e.getEntity().getUniqueId())) {
            if(coin.playerCoins.containsKey(coin.getIslandByPlayerUUID(e.getEntity().getUniqueId()).getIslandPlayer())) {
                coin.playerCoins.remove(coin.getIslandByPlayerUUID(e.getEntity().getUniqueId()).getIslandPlayer());
                e.getEntity().sendMessage(ChatColor.RED + "You lost " + coin.playerCoins.get(coin.getIslandByPlayerUUID(e.getEntity().getUniqueId()).getIslandPlayer()).length + ChatColor.RED + " coins!");
            }
        }
    }
}
