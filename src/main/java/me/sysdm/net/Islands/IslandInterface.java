package me.sysdm.net.Islands;

import org.bukkit.Location;

import java.util.UUID;

public interface IslandInterface {

    UUID getIslandUUID();

    Location getIslandLocation();

    IslandPlayer getIslandPlayer();

    Location getPlayerSpawn();

    String getIslandName();

}
