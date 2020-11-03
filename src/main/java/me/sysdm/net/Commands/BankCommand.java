package me.sysdm.net.Commands;

import me.sysdm.net.Abstraction.IslandCreator;
import me.sysdm.net.Abstraction.IslandPlayer;
import me.sysdm.net.Economy.Bank;
import me.sysdm.net.Economy.Coin;
import me.sysdm.net.Exceptions.InvalidLevelException;
import me.sysdm.net.Exceptions.NotEnoughBankSpaceException;
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

    final Coin coin = new Coin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                System.out.println(ic.islandList);
                if(!ic.hasIsland(player.getUniqueId())) {
                    player.sendMessage("You don't have a island! Create one by doing \"/island\".");
                    return true;
                }
                player.sendMessage(ChatColor.GREEN + "Your balance: $" + ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer().getCoins());
            }else if(args.length == 1) {
                if (args[0].equalsIgnoreCase("balance")) {
                    if(!ic.hasIsland(player.getUniqueId())) {
                        player.sendMessage("You don't have a island! Create one by doing \"/island\".");
                        return true;
                    }
                    player.sendMessage(ChatColor.GREEN + "Your balance: $" + ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer().getCoins());
                }else if(args[0].equalsIgnoreCase("coinworth")) {
                    if(!ic.hasIsland(player.getUniqueId())) {
                        player.sendMessage("You don't have a island! Create one by doing \"/island\".");
                        return true;
                    }
                    player.sendMessage(ChatColor.GREEN + "Current coin worth: $" + coin.coinWorth());
                }else if(args[0].equalsIgnoreCase("transactions")) {
                    if(!ic.hasIsland(player.getUniqueId())) {
                        player.sendMessage("You don't have a island! Create one by doing \"/island\".");
                        return true;
                    }
                    player.sendMessage(ChatColor.GREEN + "---Transactions---");
                    if(bank.isInTransaction(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer())) {
                        player.sendMessage(bank.getTransactions(player));
                    }else{
                        player.sendMessage(ChatColor.RED + "No transactions found");
                    }
                }else if(args[0].equalsIgnoreCase("banklevel"))  {
                    if(!ic.hasIsland(player.getUniqueId())) {
                        player.sendMessage("You don't have a island! Create one by doing \"/island\".");
                        return true;
                    }
                    player.sendMessage(bank.getBankLevelString(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer()));
                }
            }else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("setcoinworth")) {
                    if(!ic.hasIsland(player.getUniqueId())) {
                        player.sendMessage("You don't have a island! Create one by doing \"/island\".");
                        return true;
                    }
                    coin.setWorth(Integer.parseInt(args[1]));
                }else if(args[0].equalsIgnoreCase("deposit")) {
                    if(!ic.hasIsland(player.getUniqueId())) {
                        player.sendMessage("You don't have a island! Create one by doing \"/island\".");
                        return true;
                    }
                    try {
                        bank.deposit(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer(), Integer.parseInt(args[1]));
                        player.sendMessage(ChatColor.GREEN + "Deposited " + args[1] + " coins.");
                    } catch (NotEnoughCoinsInAccountException e) {
                        player.sendMessage(ChatColor.RED + "You don't have enough coins in your account to make that deposit!");
                    }catch (NotEnoughBankSpaceException e) {
                        player.sendMessage(ChatColor.RED + "You don't have enough bank space to make that deposit!");
                    }
                }else if(args[0].equalsIgnoreCase("withdraw")) {
                    if(!ic.hasIsland(player.getUniqueId())) {
                        player.sendMessage("You don't have a island! Create one by doing \"/island\".");
                        return true;
                    }
                    try {
                        bank.withdraw(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer(), Integer.parseInt(args[1]));
                        player.sendMessage(ChatColor.GREEN + "Withdrew " + args[1] + " coins.");
                    } catch (NotEnoughCoinsInBankException e) {
                        player.sendMessage(ChatColor.RED + "You don't have enough coins in your bank to make that withdraw!");
                    }
                }else if(args[0].equalsIgnoreCase("upgrade")) {
                    if(!ic.hasIsland(player.getUniqueId())) {
                        player.sendMessage("You don't have a island! Create one by doing \"/island\".");
                        return true;
                    }
                    try{
                        bank.upgradeBank(ic.getIslandByPlayerUUID(player.getUniqueId()).getIslandPlayer(), Integer.parseInt(args[1]));
                    } catch (InvalidLevelException e) {
                        player.sendMessage(ChatColor.RED + "Please enter a level between 2 - 5.");
                    } catch (NotEnoughCoinsInAccountException e) {
                        player.sendMessage(ChatColor.RED + "You don't have enough coins in your bank to make that upgrade!");
                    }
                }
            }else if(args.length == 3) {
                if(args[0].equalsIgnoreCase("addcoins")){
                    if(!ic.hasIsland(player.getUniqueId())) {
                        player.sendMessage("You don't have a island! Create one by doing \"/island\".");
                        return true;
                    }
                    if(Bukkit.getPlayerExact(args[1]) == null) {
                        sender.sendMessage(ChatColor.RED + "Invalid player.");
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(ic.hasIsland(target.getUniqueId())) {
                            try {
                                bank.addCoin(ic.getIslandByPlayerUUID(target.getUniqueId()).getIslandPlayer(), Integer.parseInt(args[2]));
                            } catch (NotEnoughBankSpaceException e) {
                                sender.sendMessage(ChatColor.RED + target.getName() + ChatColor.RED + " Doesn't have enough bank space!");
                            }
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
