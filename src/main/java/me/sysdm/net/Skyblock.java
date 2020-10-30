package me.sysdm.net;

import org.bukkit.plugin.java.JavaPlugin;

public final class Skyblock extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("island").setExecutor(new Commands());
        getLogger().info("[Skyblock] Loaded!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[Skyblock] Disabled!");
    }
}
