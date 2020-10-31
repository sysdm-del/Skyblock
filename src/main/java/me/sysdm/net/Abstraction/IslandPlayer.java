package me.sysdm.net.Abstraction;

import me.sysdm.net.Economy.Coin;

import java.util.UUID;

public class IslandPlayer extends Island {

    final UUID uuid = UUID.randomUUID();

    final Coin coin = new Coin();

    int coins = 0;

    public void getIsland() {
        getIslandByPlayerUUID(uuid);
    }

    public int getCoins() {
        if(coin.playerCoins.containsKey(this)) {
            this.coins = coin.playerCoins.get(this).length;
        }
        return this.coins;
    }

    public UUID getUUID() {
        return uuid;
    }
}
