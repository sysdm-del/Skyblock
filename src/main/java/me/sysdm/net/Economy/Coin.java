package me.sysdm.net.Economy;

import me.sysdm.net.Abstraction.IslandPlayer;

public class Coin extends Bank implements CoinInterface {

    int worth = 1;

    public void setWorth(int i) {
        this.worth = i;
    }

    public int getCoins(IslandPlayer islandPlayer) {
        if(!isInCoins(islandPlayer)) {
            if(!isInBank(islandPlayer)) {
                return 0;
            }else{
                return getBankCoinsInt(islandPlayer);
            }
        }else{
            return getBankCoinsInt(islandPlayer) + getCoins(islandPlayer);
        }
    }

    @Override
    public int coinWorth() {
        return worth;
    }
}
