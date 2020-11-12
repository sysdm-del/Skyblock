package me.sysdm.net.Islands;

import me.sysdm.net.CustomCollections.SkyblockMap;
import me.sysdm.net.Islands.Island;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class IslandManager {

    private final SkyblockMap<Island, UUID> islandList = new SkyblockMap<>();

    private final SkyblockMap<UUID, Island> islandUUIDandObjList = new SkyblockMap<>();

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
    public boolean hasIsland(UUID uuid) {
        return islandList.containsValue(uuid);
    }

    public void addPlayerAndUUIDToIslandList(Island island, UUID uuid) {
        islandList.put(island, uuid);
    }

    public boolean containsValueIslandList(UUID uuid) {
        return  islandList.containsValue(uuid);
    }

    public boolean containsKeyIslandList(Island island) {
        return islandList.containsKey(island);
    }

    public void addToIslandUUIDAndObjList(UUID uuid, Island island) {
        islandUUIDandObjList.put(uuid, island);
    }


    public <Island, UUID> Island getKeyByValue(Map<Island, UUID> map, UUID value) {
        for (Map.Entry<Island, UUID> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
