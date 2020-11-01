package me.sysdm.net.Economy;

import me.sysdm.net.Abstraction.IslandPlayer;
import me.sysdm.net.Exceptions.InvalidLevelException;
import me.sysdm.net.Exceptions.NotEnoughBankSpaceException;
import me.sysdm.net.Exceptions.NotEnoughCoinsInAccountException;
import me.sysdm.net.Exceptions.NotEnoughCoinsInBankException;
import me.sysdm.net.Skyblock;

import java.util.*;
import java.util.stream.Stream;

public class Bank extends Coin {

    BankChecker bankChecker = Skyblock.getInstance().getBankChecker();

    public HashMap<IslandPlayer, Date> transactionTime = new HashMap<>();

    public HashMap<IslandPlayer, String> transaction = new HashMap<>();


    public void addCoin(IslandPlayer islandPlayer, int i) throws NotEnoughBankSpaceException {

        if(i > bankChecker.getBankSpace(islandPlayer)) {
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
            if(i > bankChecker.getBankSpace(islandPlayer)) {
                throw new NotEnoughBankSpaceException();
            }
            Coin[] coins = new Coin[i];
            Coin[] removed = new Coin[playerCoins.get(islandPlayer).length - i];
            bankChecker.bankSpace.put(islandPlayer, i);
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
            int current = bankChecker.getBankSpace(islandPlayer) - i;
            bankChecker.bankSpace.put(islandPlayer, current);
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
