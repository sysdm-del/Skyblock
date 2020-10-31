package me.sysdm.net.Abstraction;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static jdk.vm.ci.code.CodeUtil.K;

public class Island extends IslandCreator implements IslandInterface {

    String islandName = null;

    Player owner = Bukkit.getPlayer(getKey(islandList, this.getIslandUUID()));

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

    public void setRandomSpawnLocation(World world) {
        this.world = world;
        this.x = random.nextInt(200);
        this.y = 240;
        this.z = random.nextInt(200);
    }

    public void setIslandName(String name) {
        islandName = name;
    }

    public void generateIsland() {
        Bukkit.getLogger().info("Spawning SkyBlock island for " + this.owner.getName());
        this.owner.sendMessage(ChatColor.AQUA + "Please wait while your island is being generated...");
        Bukkit.broadcastMessage(this.owner.getName() + " joined the SkyBlock community!");
        Block spawner = this.spawnLocation.getBlock();
        for (int x = spawner.getX(); x <= spawner.getX() + 7; x++) {
            for (int z = spawner.getZ(); z <= spawner.getZ() + 4; z++) {
                for (int y = spawner.getY(); y <= spawner.getY() + 15; y++) {
                    int height = y - spawner.getY();
                    if (height >= 0 && height <= 1) {
                        int dice = (int) Math.round(Math.random() * 5);
                        switch (dice) {
                            case 0:
                            case 1:
                                this.world.getBlockAt(x, y, z).setType(Material.DIRT);
                                break;
                            case 2:
                            case 3:
                            case 4:
                                this.world.getBlockAt(x, y, z).setType(Material.STONE);
                                break;
                            case 5:
                                this.world.getBlockAt(x, y, z).setType(Material.IRON_ORE);
                                break;
                        }
                    } else if (height == 2) {
                        this.world.getBlockAt(x, y, z).setType(Material.SAND);
                    } else if (height == 3) {
                        this.world.getBlockAt(x, y, z).setType(Material.GRASS_BLOCK);
                    }else this.world.getBlockAt(x, y, z).setType(Material.AIR);
                }
            }

            Block chestBlock = getPlayerSpawn().getBlock().getRelative(4, 0, 2);
            chestBlock.setType(Material.CHEST);
            Chest chest = (Chest) chestBlock.getState();
            ItemStack seeds = new ItemStack(Material.WHEAT_SEEDS);
            seeds.setAmount(3);
            chest.getBlockInventory().addItem(
                    new ItemStack(Material.LAVA_BUCKET),
                    new ItemStack(Material.ICE),
                    new ItemStack(Material.SUGAR_CANE),
                    seeds
            );

            if (!this.world.generateTree(getPlayerSpawn().add(3, 0, 0), TreeType.TREE))
            this.owner.sendMessage(ChatColor.RED + "Error: Could not generate tree!");

            getPlayerSpawn().getBlock().getRelative(0, -1, 0).setType(Material.BEDROCK);
        }
    }

    public <UUID, uuid> UUID getKey(Map<UUID, UUID> map, UUID value) {
        for(Map.Entry<UUID, UUID> entry : map.entrySet()) {
            if(entry.getValue().equals(this.getIslandUUID())) {
                return entry.getKey();
            }
        }
        return null;
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

    @Override
    public Location getPlayerSpawn() {
        return this.spawnLocation.add(2, 4, 2);
    }
}
