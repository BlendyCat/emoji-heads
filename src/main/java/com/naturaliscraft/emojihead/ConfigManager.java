package com.naturaliscraft.emojihead;

import com.naturaliscraft.emojihead.utils.HeadCreator;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EvanMerz on 12/10/16.
 */
public class ConfigManager {
    private FileConfiguration config;

    ConfigManager(FileConfiguration config){
        this.config = config;
    }

    void setupConfig(){
        config.options()
                .header("Reference Example Head for making new heads. 54 max");
        config.addDefault("heads.tongue-stuck-out.title", "&6Stuck-out-tongue");
        config.addDefault("heads.tongue-stuck-out.texture",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzViN2YwYTE5OGJhYzc2N2RkNGYzMjUwMWJkMjllN2IyZmEzZTY1OWJmNGY2NjAxZGIwYjZhYjM0MzQ1Nzk4In19fQ==");
        config.options().copyDefaults(true);
    }

    public ItemStack[] getHeads(){
        HeadCreator hc = new HeadCreator();
        ArrayList<ItemStack> heads = new ArrayList<ItemStack>();
        for(String key : config
                .getConfigurationSection("heads").getKeys(false)){
            ItemStack i = hc.createHead((String) config.get("heads."+key+".texture"),
                    (String) config.get("heads."+key+".title"));
            List<String> lore = new ArrayList<String>();
            ItemMeta meta = i.getItemMeta();
            meta.setLore(lore);
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Emoji Head"));
            meta.setLore(lore);
            i.setItemMeta(meta);
            heads.add(i);
        }
        int headnumber = heads.size() > 54 ? 54: heads.size();
        return heads.toArray(new ItemStack[headnumber]);
    }
}
