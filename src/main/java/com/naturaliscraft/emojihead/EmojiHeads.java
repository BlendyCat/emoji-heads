package com.naturaliscraft.emojihead;

import com.naturaliscraft.emojihead.gui.GUI;
import com.naturaliscraft.emojihead.gui.InventoryListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by EvanMerz on 12/10/16.
 */

public class EmojiHeads extends JavaPlugin{
    private ConfigManager cm;
    @Override
    public void onEnable(){
        cm = new ConfigManager(getConfig());
        cm.setupConfig();
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(command.getName().equalsIgnoreCase("emoji")){
            if(sender instanceof Player && args.length == 0){
                if(sender.hasPermission("emojiheads.emoji")) {
                    new GUI(cm).openGUI((Player) sender);
                }else sender.sendMessage(ChatColor.RED+"No Permission!");
            }else if(sender.hasPermission("emojiheads.reload") &&
                    args[0].equalsIgnoreCase("reload")){
                reloadConfig();
                cm = new ConfigManager(getConfig());
            }else sender.sendMessage(ChatColor.RED+"No Permission!");
            return true;
        }
        return false;
    }

}
