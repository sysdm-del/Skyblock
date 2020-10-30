package me.sysdm.net.Abstraction;

import org.bukkit.Location;

import java.util.UUID;

public interface IslandInterface {

    UUID getIslandUUID();

    Location getIslandLocation();

    IslandPlayer getIslandPlayer();

}
