package com.naturaliscraft.emojihead.gui;

import com.naturaliscraft.emojihead.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EvanMerz on 12/10/16.
 */
public class GUI {
    private ConfigManager cm;

    public GUI(ConfigManager cm){
        this.cm = cm;

    }

    public void openGUI(Player player){
        ItemStack[] heads = cm.getHeads();
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.YELLOW+"Emojis");
        inv.setContents(heads);
        player.openInventory(inv);
    }
}
