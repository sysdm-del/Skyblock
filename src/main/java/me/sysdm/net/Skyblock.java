package me.sysdm.net;

import me.sysdm.net.Commands.BankCommand;
import me.sysdm.net.Commands.IslandCommand;
import me.sysdm.net.Events.DeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skyblock extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("island").setExecutor(new IslandCommand());
        getCommand("bank").setExecutor(new BankCommand());
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getLogger().info("[Skyblock] Loaded!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[Skyblock] Disabled!");
    }
}
