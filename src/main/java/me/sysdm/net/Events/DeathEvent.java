package me.sysdm.net.Events;

import me.sysdm.net.Islands.IslandManager;
import me.sysdm.net.Economy.Bank;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    final Bank bank = new Bank();

    final IslandManager im = new IslandManager();

    @EventHandler
    void onPlayerDeath(PlayerDeathEvent e) {
        if(im.hasIsland(e.getEntity().getUniqueId())) {
            if(bank.isInCoins(im.getIslandByPlayerUUID(e.getEntity().getUniqueId()).getIslandPlayer())) {
                bank.removeFromCoins(im.getIslandByPlayerUUID(e.getEntity().getUniqueId()).getIslandPlayer());
                e.getEntity().sendMessage(ChatColor.RED + "You lost " + bank.getValueFromCoins(im.getIslandByPlayerUUID(e.getEntity().getUniqueId()).getIslandPlayer()).length + ChatColor.RED + " coins!");
            }
        }
    }
}
