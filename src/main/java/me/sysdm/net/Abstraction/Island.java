package me.sysdm.net.Abstraction;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.Random;
import java.util.UUID;

public class Island extends IslandCreator implements IslandInterface {

    int x = 0;

    int y = 0;

    int z = 0;

    UUID islandUUIDvar = null;

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


    @Override
    public UUID islandUUID() {
        if(islandUUIDvar == null) {
            UUID uuid = UUID.randomUUID();
            islandUUIDandObjList.put(uuid, this);
            islandUUIDvar = uuid;
            return uuid;
        }else{
            return islandUUIDvar;
        }
    }

    @Override
    public Location islandLocation() {
        return spawnLocation;
    }
}
