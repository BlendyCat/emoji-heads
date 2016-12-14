package com.naturaliscraft.emojihead;

import com.naturaliscraft.emojihead.utils.HeadCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

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
        config.addDefault("heads.blendycat.title", "&bBlendyCat");
        config.addDefault("heads.blendycat.texture",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBhMThiNDQ2ODkwNjY0ZjQzNGFiNDgzOGQyM2IwN2ZhMmFhODZmM2RmZDllYjI2ZGI2NTUxZTkyMDYyNmJlIn19fQ==");
        config.options().copyDefaults(true);
    }

    public ItemStack[] getHeads(){
        HeadCreator hc = new HeadCreator();
        ArrayList<ItemStack> heads = new ArrayList<ItemStack>();
        for(String key : config
                .getConfigurationSection("heads").getKeys(false)){
            heads.add(hc.getHead((String) config.get("heads."+key+".texture"),(String) config.get("heads."+key+".title")));
        }
        int headnumber = heads.size() > 54 ? 54: heads.size();
        return heads.toArray(new ItemStack[headnumber]);
    }
}
