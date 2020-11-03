package me.sysdm.net.Economy;

import me.sysdm.net.Abstraction.IslandCreator;
import me.sysdm.net.Abstraction.IslandPlayer;
import me.sysdm.net.Exceptions.InvalidLevelException;
import me.sysdm.net.Exceptions.NotEnoughBankSpaceException;
import me.sysdm.net.Exceptions.NotEnoughCoinsInAccountException;
import me.sysdm.net.Exceptions.NotEnoughCoinsInBankException;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Stream;

public class Bank {

    private final IslandCreator ic = new IslandCreator();

    private final HashMap<IslandPlayer, Coin[]> bank = new HashMap<>();

    private final HashMap<IslandPlayer, Integer> bankLevel = new HashMap<>();

    private final HashMap<IslandPlayer, Integer> bankSpace = new HashMap<>();
    private final HashMap<IslandPlayer, Date> transactionTime = new HashMap<>();
    private final HashMap<IslandPlayer, String> transaction = new HashMap<>();

    private final HashMap<IslandPlayer, Coin[]> playerCoins = new HashMap<>();

    private final Coin[] gold = new Coin[20000];
    private final Coin[] deluxe = new Coin[30000];
    private final Coin[] superdeluxe = new Coin[40000];
    private final Coin[] premier = new Coin[50000];
    private final Coin[] starterSpace = new Coin[10000];
    private final Coin[] goldSpace = new Coin[100000];
    private final Coin[] deluxeSpace = new Coin[500000];
    private final Coin[] superdeluxeSpace = new Coin[1000000];
    private final Coin[] premierSpace = new Coin[5000000];

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

    public boolean isInTransaction(IslandPlayer islandPlayer) {
        return transaction.containsKey(islandPlayer);
    }

    public String[] getTransactions(Player player) {
        List<String> list = new ArrayList<>();
        for(Map.Entry<IslandPlayer, Date> entry : transactionTime.entrySet()) {
            for(Map.Entry<IslandPlayer, String> otherentry : transaction.entrySet()) {
                if(entry.getKey().equals(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer()) && otherentry.getKey().equals(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer())) {
                    list.add(ChatColor.GREEN + entry.getValue().toString() + otherentry.getValue());
                }
            }
        }
        String[] array = new String[list.size()];
        return list.toArray(array);
    }

    public boolean isInBank(IslandPlayer islandPlayer) {
        return bank.containsKey(islandPlayer);
    }

    public boolean isInCoins(IslandPlayer islandPlayer) {
        return playerCoins.containsKey(islandPlayer);
    }

    public int getBankCoinsInt(IslandPlayer islandPlayer) {
        return bank.get(islandPlayer).length;
    }

}
