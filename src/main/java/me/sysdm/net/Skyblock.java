package me.sysdm.net;

import me.sysdm.net.Commands.BankCommand;
import me.sysdm.net.Commands.IslandCommand;
import me.sysdm.net.Events.DeathEvent;
import me.sysdm.net.Items.DragonSword.DragonSword;
import me.sysdm.net.Items.DragonSword.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skyblock extends JavaPlugin {

    private static Skyblock instance;

    private final ItemManager im = new ItemManager();

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setupManagers();
        getCommand("island").setExecutor(new IslandCommand());
        getCommand("bank").setExecutor(new BankCommand());
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new DragonSword(), this);
        getLogger().info("Loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabled!");
    }


    public static Skyblock getInstance() {
        return instance;
    }

    public void setupManagers() {
        im.loadItems();
    }

}
