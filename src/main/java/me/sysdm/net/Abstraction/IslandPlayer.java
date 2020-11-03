package me.sysdm.net.Abstraction;

import me.sysdm.net.Economy.Bank;
import me.sysdm.net.Economy.Coin;

import java.util.UUID;

public class IslandPlayer extends Island {

    final UUID uuid = UUID.randomUUID();

    final Bank bank = new Bank();

    final Coin coin = new Coin();

    int coins = 0;

    public void getIsland() {
        getIslandByPlayerUUID(uuid);
    }

    public int getCoins() {
        if(bank.isInCoins(this)) {
            this.coins = coin.getCoins(this);
        }
        return this.coins;
    }

    public UUID getUUID() {
        return uuid;
    }
}
