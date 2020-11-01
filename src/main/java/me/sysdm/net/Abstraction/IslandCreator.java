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

    public boolean hasIsland(UUID uuid) {
        return islandList.containsValue(uuid);
    }

    public boolean hasIsland(String name) {
        if(Bukkit.getPlayerExact(name) == null) {
            throw new NullPointerException();
        }
        return islandList.containsValue(Objects.requireNonNull(Bukkit.getPlayerExact(name)).getUniqueId());

    }

    public boolean hasIsland(IslandPlayer player) {
        return islandList.containsValue(player.getUUID());
    }

    public Island getIslandByIslandUUID(UUID uuid) {
        if(islandUUIDandObjList.containsKey(uuid)) {
            return islandUUIDandObjList.get(uuid);
        }else{
            throw new NullPointerException();
        }
    }
    public Island getIslandByPlayerUUID(UUID uuid) {
        if(islandList.containsValue(uuid)) {
            return getKeyByValue(islandList, uuid);
        }else{
            throw new NullPointerException();
        }
    }
    public static <Island, UUID> Island getKeyByValue(Map<Island, UUID> map, UUID value) {
        for (Map.Entry<Island, UUID> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }


}
