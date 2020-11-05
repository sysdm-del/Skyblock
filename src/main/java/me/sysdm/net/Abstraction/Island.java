package me.sysdm.net.Abstraction;

import me.sysdm.net.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.TreeType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;


public class Island extends IslandCreator implements IslandInterface {

    private String islandName;

    private Block block;

    private World world;

    private UUID islandUUIDvar = null;

    private IslandPlayer islandPlayer = null;

    private Location spawnLocation;

    private final Player owner = getOwner();

    public void getRandomSpawnLocation() {
        Random random = new Random();
        block = this.world.getBlockAt(random.nextInt(1283), 200, random.nextInt(1283));
        spawnLocation = this.block.getLocation();
    }


    public void setIslandName(String name) {
        islandName = name;
    }

    public void generateIsland() {
        world = owner.getWorld();
        getRandomSpawnLocation();
        Bukkit.getLogger().info("Spawning SkyBlock island for " + owner.getName());
        owner.sendMessage(ChatColor.AQUA + "Please wait while your island is being generated...");
        Bukkit.broadcastMessage(owner.getName() + " joined the SkyBlock community!");
        Block spawner = block;
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
            Bukkit.getScheduler().runTaskLater(Skyblock.getInstance(), () -> {
                Chest chest = (Chest) chestBlock.getState();
                ItemStack seeds = new ItemStack(Material.WHEAT_SEEDS);
                seeds.setAmount(3);
                System.out.println(chest);
                chest.getInventory().addItem(
                        new ItemStack(Material.LAVA_BUCKET),
                        new ItemStack(Material.ICE),
                        new ItemStack(Material.SUGAR_CANE),
                        seeds
                );
            }, 1L);
            if (!this.world.generateTree(getPlayerSpawn().add(3, 0, 0), TreeType.TREE))
                owner.sendMessage(ChatColor.RED + "Error: Could not generate tree!");
            getPlayerSpawn().getBlock().getRelative(0, -1, 0).setType(Material.BEDROCK);
        }
    }



    @Override
    public UUID getIslandUUID() {
        if(islandUUIDvar == null) {
            islandUUIDvar = UUID.randomUUID();
            islandManager.addToIslandUUIDAndObjList(islandUUIDvar, this);
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
