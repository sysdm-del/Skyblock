package me.sysdm.net.Abstraction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class IslandCreator {

    protected HashMap<UUID, UUID> islandList = new HashMap<>();

    protected HashMap<UUID, Island> islandUUIDandObjList = new HashMap<>();

    public Island createIsland(UUID uuid) {
        Island island = new Island();
        islandList.put(uuid, island.islandUUID());
        return island;
    }

    
    public void removeIsland(UUID puuid, UUID iuuid) {
        if(islandList.containsKey(puuid) && islandList.containsValue(iuuid)) {
            islandList.remove(puuid, iuuid);
        }else{
            throw new NullPointerException();
        }
    }

    public boolean hasIsland(UUID uuid) {
        return islandList.containsKey(uuid);
    }

    public boolean hasIsland(String name) {
        if(Bukkit.getPlayerExact(name) == null) {
            throw new NullPointerException();
        }
        return islandList.containsKey(Bukkit.getPlayerExact(name).getUniqueId());

    }

    public boolean hasIsland(Player player) {
        return islandList.containsKey(player.getUniqueId());
    }

    public Island getIslandByIslandUUID(UUID uuid) {
        if(islandUUIDandObjList.containsKey(uuid)) {
            return islandUUIDandObjList.get(uuid);
        }else{
            throw new NullPointerException();
        }
    }
    public Island getIslandByPlayerUUID(UUID uuid) {
        if(islandList.containsKey(uuid)) {
            UUID islandUUID = islandList.get(uuid);
            return getIslandByIslandUUID(islandUUID);
        }else{
            throw new NullPointerException();
        }
    }


}
