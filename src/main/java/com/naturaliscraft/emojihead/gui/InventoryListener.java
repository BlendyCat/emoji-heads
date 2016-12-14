package com.naturaliscraft.emojihead.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * Created by EvanMerz on 12/11/16.
 */
public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(ChatColor.stripColor(e.getInventory().getName()).equals("Emojis")) {
            if (e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.AIR)) return;
            ItemStack sel = e.getCurrentItem();
            e.setCancelled(true);
            PlayerInventory pl = player.getInventory();
            if (pl.getHelmet() != null && pl.getHelmet().getType() != Material.AIR) {
                player.sendMessage(ChatColor.RED+"You must have an empty helmet slot to wear an emoji!");
                return;
            }
            pl.setHelmet(sel);
            player.closeInventory();
            player.sendMessage(ChatColor.GOLD+"Enjoy your new emoji head!");
        }else if(e.getSlotType() == InventoryType.SlotType.ARMOR){
            if (e.getCurrentItem() == null ||
                    e.getCurrentItem().getType() == null ||
                    e.getCurrentItem().getType().equals(Material.AIR))
                    return;
            if(!ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore()
                    .get(0)).equalsIgnoreCase("Emoji Head")) return;
            e.setCancelled(true);
            player.sendMessage(ChatColor.GOLD+"Emoji head removed!");
            player.closeInventory();
            player.getInventory().setHelmet(new ItemStack(Material.AIR));
        }
    }
}
