package me.sysdm.net.Economy;

import me.sysdm.net.Abstraction.IslandPlayer;
import me.sysdm.net.Exceptions.InvalidLevelException;
import me.sysdm.net.Exceptions.NotEnoughCoinsInAccountException;
import org.bukkit.ChatColor;

import javax.lang.model.type.PrimitiveType;
import java.util.HashMap;

public class BankChecker extends Bank {

    public HashMap<IslandPlayer, Integer> bankLevel = new HashMap<>();

    public HashMap<IslandPlayer, Integer> bankSpace = new HashMap<>();

    Coin[] gold = new Coin[20000];
    Coin[] deluxe = new Coin[30000];
    Coin[] superdeluxe = new Coin[40000];
    Coin[] premier = new Coin[50000];
    Coin[] starterSpace = new Coin[10000];
    Coin[] goldSpace = new Coin[100000];
    Coin[] deluxeSpace = new Coin[500000];
    Coin[] superdeluxeSpace = new Coin[1000000];
    Coin[] premierSpace = new Coin[5000000];

    public int getBankLevel(IslandPlayer islandPlayer) {
        return bankLevel.getOrDefault(islandPlayer, 1);
    }

    public Coin[] getDefaultBankSpace(IslandPlayer islandPlayer) {
        switch (getBankLevel(islandPlayer)) {
            case 1:
                return this.starterSpace;
            case 2:
                return this.goldSpace;
            case 3:
                return this.deluxeSpace;
            case 4:
                return this.superdeluxeSpace;
            case 5:
                return this.premierSpace;
        }
        return null;
    }
    public int getBankSpace(IslandPlayer islandPlayer) {
        return this.bankSpace.get(islandPlayer);
    }
    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public String getBankLevelString(IslandPlayer islandPlayer) {
        switch (getBankLevel(islandPlayer)) {
            case 1:
                return color("&7Starter (coin space: " + getDefaultBankSpace(islandPlayer).length + ")");
            case 2:
                return color("&6Gold (coin space: " + getDefaultBankSpace(islandPlayer).length + ")");
            case 3:
                return color("&cDeluxe (coin space: " + getDefaultBankSpace(islandPlayer).length + ")");
            case 4:
                return color("&dSuperDeluxe (coin space: " + getDefaultBankSpace(islandPlayer).length + ")");
            case 5:
                return color("&4 Premier (coin space: " + getDefaultBankSpace(islandPlayer).length + ")");
        }
        return null;
    }
    public void upgradeBank(IslandPlayer islandPlayer, int level) throws InvalidLevelException, NotEnoughCoinsInAccountException {
        switch (level) {
            case 2:
                if (this.gold.length <= playerCoins.get(islandPlayer).length) {
                    bankLevel.put(islandPlayer, level);
                } else {
                    throw new NotEnoughCoinsInAccountException();
                }
                break;
            case 3:
                if (this.deluxe.length <= playerCoins.get(islandPlayer).length) {
                    bankLevel.put(islandPlayer, level);
                } else {
                    throw new NotEnoughCoinsInAccountException();
                }
                break;
            case 4:
                if (this.superdeluxe.length <= playerCoins.get(islandPlayer).length) {
                    bankLevel.put(islandPlayer, level);
                } else {
                    throw new NotEnoughCoinsInAccountException();
                }
                break;
            case 5:
                if (this.premier.length <= playerCoins.get(islandPlayer).length) {
                    bankLevel.put(islandPlayer, level);
                } else {
                    throw new NotEnoughCoinsInAccountException();
                }
                break;
            default:
                throw new InvalidLevelException();
        }
    }
}
