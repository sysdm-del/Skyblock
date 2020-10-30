package me.sysdm.net.Abstraction;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.UUID;

public class Island extends IslandCreator implements IslandInterface {

    String islandName = null;

    int x = 0;

    int y = 0;

    int z = 0;

    UUID islandUUIDvar = null;

    IslandPlayer islandPlayer = null;

    World world = null;

    final Random random = new Random();

    Location spawnLocation = new Location(world, x, y, z);

    public void setSpawnLocation(World world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setSpawnLocation(Location loc) {
        this.x = loc.getBlockX();
        this.y = loc.getBlockY();
        this.z = loc.getBlockZ();
    }

    public void setRandomSpawnLocation() {
        this.x = random.nextInt(200);
        this.y = 240;
        this.z = random.nextInt(200);
    }

    public void setIslandName(String name) {
        islandName = name;
    }


    @Override
    public UUID getIslandUUID() {
        if(islandUUIDvar == null) {
            islandUUIDvar = UUID.randomUUID();
            islandUUIDandObjList.put(islandUUIDvar, this);
        }
        return islandUUIDvar;
    }

    @Override
    public Location getIslandLocation() {
        return this.spawnLocation;
    }

    @Override
    public IslandPlayer getIslandPlayer() {
        if(islandPlayer == null) {
            islandPlayer = new IslandPlayer();
        }
        return islandPlayer;
    }
}
