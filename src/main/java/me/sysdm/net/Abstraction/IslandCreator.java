package me.sysdm.net.Abstraction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class IslandCreator {

    public static Player owner;

    public HashMap<Island, UUID> islandList = new HashMap<>();

    public HashMap<UUID, Island> islandUUIDandObjList = new HashMap<>();

    public Island createIsland(Player player) {
        owner = player;
        Island island = new Island();
        islandList.put(island, player.getUniqueId());
        return island;
    }

    public Island getIslandByIslandUUID(UUID uuid) {
        if(islandUUIDandObjList.containsKey(uuid)) {
            return islandUUIDandObjList.get(uuid);
        }
        return null;
    }
    public Island getIslandByPlayerUUID(UUID uuid)  {
        if(islandList.containsValue(uuid)) {
            return getKeyByValue(islandList, uuid);
        }
        return null;
    }
    public <Island, UUID> Island getKeyByValue(Map<Island, UUID> map, UUID value) {
        for (Map.Entry<Island, UUID> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean hasIsland(UUID uuid) {
        return islandList.containsValue(uuid);
    }


}
