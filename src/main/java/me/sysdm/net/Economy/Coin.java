package me.sysdm.net.Economy;

import me.sysdm.net.Abstraction.Island;
import me.sysdm.net.Abstraction.IslandPlayer;

import java.util.HashMap;

public class Coin implements CoinInterface {

    public HashMap<IslandPlayer, Coin[]> playerCoins = new HashMap<>();

    public HashMap<IslandPlayer, Coin[]> bank = new HashMap<>();

    int worth = 1;

    public void setWorth(int i) {
        this.worth = i;
    }

    public int getCoins(IslandPlayer islandPlayer) {
        if(!playerCoins.containsKey(islandPlayer)) {
            if(!bank.containsKey(islandPlayer)) {
                return 0;
            }else{
                return bank.get(islandPlayer).length;
            }
        }else{
            return playerCoins.get(islandPlayer).length + bank.get(islandPlayer).length;
        }
    }

    @Override
    public int coinWorth() {
        return worth;
    }
}
