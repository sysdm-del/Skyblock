package me.sysdm.net.Abstraction;

import java.util.UUID;

public class IslandPlayer extends Island {

    final UUID uuid = UUID.randomUUID();


    public void getIsland() {
        getIslandByPlayerUUID(uuid);
    }

    public UUID getUUID() {
        return uuid;
    }
}
