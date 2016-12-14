package com.naturaliscraft.emojihead.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by EvanMerz on 12/11/16.
 */


public class HeadCreator {
    public ItemStack createHead(String texture, String title){
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile prof = new GameProfile(UUID.randomUUID(), null);
        prof.getProperties().put("textures", new Property("textures", texture));
        Field proField = null;
        try {
            proField = meta.getClass().getDeclaredField("profile");
        }catch(NoSuchFieldException e){
            e.printStackTrace();
        }
        assert proField != null;
        proField.setAccessible(true);
        try{
            proField.set(meta, prof);
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
        head.setItemMeta(meta);
        return head;
    }
}
