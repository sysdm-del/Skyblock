package me.sysdm.net.Commands;

import me.sysdm.net.Abstraction.IslandCreator;
import me.sysdm.net.Abstraction.IslandPlayer;
import me.sysdm.net.Economy.Bank;
import me.sysdm.net.Exceptions.NotEnoughCoinsInAccountException;
import me.sysdm.net.Exceptions.NotEnoughCoinsInBankException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.Map;

public class BankCommand implements CommandExecutor {

    final IslandCreator ic = new IslandCreator();

    final Bank bank = new Bank();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                if (ic.hasIsland(player.getUniqueId())) {
                    player.sendMessage(ChatColor.GREEN + "Your balance: $" + ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer().getCoins());
                } else {
                    player.sendMessage("You don't have a island. Create one by doing \"/island\".");
                }
            }else if(args.length == 1) {
                if (args[0].equalsIgnoreCase("balance")) {
                    if (ic.hasIsland(player.getUniqueId())) {
                        player.sendMessage(ChatColor.GREEN + "Your balance: $" + ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer().getCoins());
                    } else {
                        player.sendMessage("You don't have a island. Create one by doing \"/island\".");
                    }
                }else if(args[0].equalsIgnoreCase("coinworth")) {
                    player.sendMessage(ChatColor.GREEN + "Current coin worth: $" + bank.coinWorth());
                }else if(args[0].equalsIgnoreCase("transactions")) {
                    player.sendMessage(ChatColor.GREEN + "---Transactions---");
                    if(bank.transaction.containsKey(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer())) {
                        for(Map.Entry<IslandPlayer, Date> entry : bank.transactionTime.entrySet()) {
                            for(Map.Entry<IslandPlayer, String> otherentry : bank.transaction.entrySet()) {
                                if(entry.getKey().equals(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer()) && otherentry.getKey().equals(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer())) {
                                    player.sendMessage(ChatColor.GREEN + entry.getValue().toString() + otherentry.getValue());
                                }
                            }
                        }
                    }
                }
            }else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("setcoinworth")) {
                    bank.setWorth(Integer.parseInt(args[1]));
                }else if(args[0].equalsIgnoreCase("deposit")) {
                    try {
                        bank.deposit(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer(), Integer.parseInt(args[1]));
                        player.sendMessage(ChatColor.GREEN + "Deposited " + args[1] + " coins.");
                    } catch (NotEnoughCoinsInAccountException e) {
                        player.sendMessage(ChatColor.RED + "You don't have enough coins in your account to make that deposit!");
                    }
                }else if(args[0].equalsIgnoreCase("withdraw")) {
                    try {
                        bank.withdraw(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer(), Integer.parseInt(args[1]));
                        player.sendMessage(ChatColor.GREEN + "Withdrew " + args[1] + " coins.");
                    } catch (NotEnoughCoinsInBankException e) {
                        player.sendMessage(ChatColor.RED + "You don't have enough coins in your bank to make that withdraw!");
                    }
                }
            }else if(args.length == 3) {
                if(args[0].equalsIgnoreCase("addcoins")){
                    if(Bukkit.getPlayerExact(args[1]) == null) {
                        sender.sendMessage(ChatColor.RED + "Invalid player.");
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(ic.hasIsland(target.getUniqueId())) {
                            bank.addCoin(ic.getIslandByPlayerUUID(target.getUniqueId()).getIslandPlayer(), Integer.parseInt(args[2]));
                        }else{
                            sender.sendMessage(ChatColor.RED + target.getName() + ChatColor.RED + " Doesn't have an island!");
                        }
                    }
                }
            }
        }
        return true;
    }
}
