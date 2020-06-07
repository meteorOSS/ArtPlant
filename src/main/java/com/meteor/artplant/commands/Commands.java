package com.meteor.artplant.commands;

import com.meteor.artplant.ArtPlant;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Commands implements CommandExecutor {
    ArtPlant plugin;
    public Commands(ArtPlant plugin){
        this.plugin = plugin;
    }
    private boolean isInt(String amount){
        try{
            int i = Integer.valueOf(amount);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
        if(!sender.isOp()){
            return true;
        }
        if(args.length==0||args[0].equalsIgnoreCase("help")&&sender.isOp()){
            plugin.getLang().getStringList("admin-help").forEach(a -> sender.sendMessage(a));
            return true;
        }
        if(args[0].equalsIgnoreCase("admin")&&args.length==5){
            if(!isInt(args[4])){return true;}
            if(Bukkit.getPlayerExact(args[2])==null){return true;}
            switch (args[1]){
                case "furist":
                    if(!plugin.getFuristHashMap().containsKey(args[3])){
                        sender.sendMessage(plugin.getLang().getString("noexit-fur"));
                        return true;
                    }
                    ItemStack itemStack = plugin.getTools().getFur(args[3]);
                    itemStack.setAmount(Integer.valueOf(args[4]));
                    Bukkit.getPlayer(args[2]).getInventory().addItem(itemStack);
                    sender.sendMessage(plugin.getLang().getString("use-done"));
                    return true;
                case "seed":
                    if(plugin.getTools().getSeedKey(args[3])==null){
                        sender.sendMessage(plugin.getLang().getString("noexit-seed"));
                        return true;
                    }
                    ItemStack seed = plugin.getTools().getSeed(args[3]);
                    seed.setAmount(Integer.valueOf(args[4]));
                    Bukkit.getPlayer(args[2]).getInventory().addItem(seed);
                    sender.sendMessage(plugin.getLang().getString("use-done"));
                    return true;
                default:
                    return true;
            }
        }
        return false;
    }
}
