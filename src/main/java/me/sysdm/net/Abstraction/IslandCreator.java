package me.sysdm.net.Abstraction;

import org.bukkit.entity.Player;

public class IslandCreator {

    private Player owner;

    IslandManager islandManager = new IslandManager();

    public Island createIsland(Player player) {
        owner = player;
        Island island = new Island();
        islandManager.addPlayerAndUUIDToIslandList(island, player.getUniqueId());
        return island;
    }

    protected Player getOwner() {
        return this.owner;
    }



}
