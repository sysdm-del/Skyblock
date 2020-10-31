package me.sysdm.net.Economy;

import me.sysdm.net.Abstraction.IslandPlayer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class Bank extends Coin {

    public void addCoin(IslandPlayer islandPlayer, int i) {
        Coin[] coins = new Coin[i];
        if(playerCoins.containsKey(islandPlayer))  {
            Coin[] combi = Stream.concat( Arrays.stream( coins ), Arrays.stream( playerCoins.get(islandPlayer) ) ).toArray(Coin[]::new);
            playerCoins.put(islandPlayer, combi);
        }else{
            playerCoins.put(islandPlayer, coins);
        }
    }

}
