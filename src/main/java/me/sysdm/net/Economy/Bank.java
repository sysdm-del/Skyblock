package me.sysdm.net.Economy;

import me.sysdm.net.Abstraction.IslandPlayer;
import me.sysdm.net.Exceptions.InvalidLevelException;
import me.sysdm.net.Exceptions.NotEnoughBankSpaceException;
import me.sysdm.net.Exceptions.NotEnoughCoinsInAccountException;
import me.sysdm.net.Exceptions.NotEnoughCoinsInBankException;
import me.sysdm.net.Skyblock;
import org.bukkit.ChatColor;

import java.util.*;
import java.util.stream.Stream;

public class Bank extends Coin {

    public HashMap<IslandPlayer, Integer> bankLevel = new HashMap<>();

    public HashMap<IslandPlayer, Integer> bankSpace = new HashMap<>();

    public HashMap<IslandPlayer, Date> transactionTime = new HashMap<>();

    public HashMap<IslandPlayer, String> transaction = new HashMap<>();

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
    public void addCoin(IslandPlayer islandPlayer, int i) throws NotEnoughBankSpaceException {

        if(i > getBankSpace(islandPlayer)) {
            throw new NotEnoughBankSpaceException();
        }
        Coin[] coins = new Coin[i];
        if(playerCoins.containsKey(islandPlayer))  {
            Coin[] combi = combinateCoinArrays(coins, playerCoins.get(islandPlayer));
            playerCoins.put(islandPlayer, combi);
        }else{
            playerCoins.put(islandPlayer, coins);
        }
    }
    public void deposit(IslandPlayer islandPlayer, int i) throws NotEnoughCoinsInAccountException, NotEnoughBankSpaceException {
        if(!(i <= playerCoins.get(islandPlayer).length)) {
            if(i > getBankSpace(islandPlayer)) {
                throw new NotEnoughBankSpaceException();
            }
            Coin[] coins = new Coin[i];
            Coin[] removed = new Coin[playerCoins.get(islandPlayer).length - i];
           bankSpace.put(islandPlayer, i);
            if(bank.containsKey(islandPlayer)) {
                Coin[] combi = combinateCoinArrays(coins, bank.get(islandPlayer));
                playerCoins.put(islandPlayer, removed);
                bank.put(islandPlayer, combi);
                transactionTime.put(islandPlayer, getTime());
                transaction.put(islandPlayer, "Deposit " + i + " coins");
            }else{
                bank.put(islandPlayer, coins);
                playerCoins.put(islandPlayer, removed);
            }
        }else{
            throw new NotEnoughCoinsInAccountException();
        }
    }
    public void withdraw(IslandPlayer islandPlayer, int i) throws NotEnoughCoinsInBankException {
        if(!(i <= bank.get(islandPlayer).length)) {
            int current = getBankSpace(islandPlayer) - i;
            bankSpace.put(islandPlayer, current);
            Coin[] coins = new Coin[i];
            Coin[] removed = new Coin[bank.get(islandPlayer).length - i];
            Coin[] combi = combinateCoinArrays(coins, playerCoins.get(islandPlayer));
            playerCoins.put(islandPlayer, combi);
            bank.put(islandPlayer, removed);
            transactionTime.put(islandPlayer, getTime());
            transaction.put(islandPlayer, "Withdraw " + i + " coins");
        }else{
            throw new NotEnoughCoinsInBankException();
        }
    }

    public Coin[] combinateCoinArrays(Coin[] a, Coin[] b) {
        return Stream.concat( Arrays.stream( a ), Arrays.stream( b ) ).toArray(Coin[]::new);
    }

    public Date getTime() {
        long mills = System.currentTimeMillis();
        return new Date(mills);
    }

}
