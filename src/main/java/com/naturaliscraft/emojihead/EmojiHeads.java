package com.naturaliscraft.emojihead;

import com.naturaliscraft.emojihead.gui.GUI;
import com.naturaliscraft.emojihead.gui.InventoryListener;
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
        if(command.getName().equals("emoji") && sender instanceof Player){
            new GUI(cm).openGUI((Player) sender);
            return true;
        }
        return false;
    }

}
