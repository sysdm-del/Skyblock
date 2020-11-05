package me.sysdm.net.Abstraction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class IslandCreator {

    private Player owner;

    IslandManager islandManager = new IslandManager();

    public Island createIsland(Player player) {
        owner = player;
        Island island = new Island();
        islandManager.addPlayerAndUUID(island, player.getUniqueId());
        return island;
    }

    protected Player getOwner() {
        return this.owner;
    }



}
