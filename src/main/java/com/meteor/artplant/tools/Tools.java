package com.meteor.artplant.tools;

import com.meteor.artplant.ArtPlant;
import com.meteor.artplant.data.Furist;
import com.meteor.artplant.data.Seed;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Tools {
    ArtPlant plugin;
    MessageManager fur;
    MessageManager se;
    public Tools(ArtPlant plugin){
        this.plugin = plugin;
        fur= new MessageManager(YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder()+"/furist.yml")));
        se= new MessageManager(YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder()+"/seeds.yml")));
    }
    public Seed getSeedKey(String key){
        for(Seed seed : plugin.getSeedList()){
            if(seed.getId().equalsIgnoreCase(key)){
                return seed;
            }
        }
        return null;
    }
    public ItemStack getFur(String key){
        Furist furist = plugin.getFuristHashMap().get(key);
        ItemStack itemStack = new ItemStack(Material.valueOf(furist.getId()),1,(short)furist.getData());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(fur.getStringList(key+".lore"));
        itemMeta.setDisplayName(fur.getString(key+".name"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public ItemStack getSeed(String key){
        Seed seed = getSeedKey(key);
        if(seed!=null){
            ItemStack itemStack = new ItemStack(Material.SKULL_ITEM);
            itemStack.setDurability((short)3);
            SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
            skullMeta.setDisplayName(seed.getName());
            skullMeta.setLore(seed.getLore());
            GameProfile gf = new GameProfile(UUID.randomUUID(),null);
            gf.getProperties().put("textures",new Property("textures",seed.getExay()));
            try{
                Field f = skullMeta.getClass().getDeclaredField("profile");
                f.setAccessible(true);
                f.set(skullMeta,gf);
                itemStack.setItemMeta((ItemMeta)skullMeta);
            }catch (Exception e){
                plugin.getLogger().info("反射出错,无法获取头颅.....");
            }
            return itemStack;
        }
        return null;
    }
    public void loadFurist(HashMap<String, Furist> Furists){
        Furists.clear();
        for(String string : fur.getYaml().getKeys(false)){
            Furists.put(string,new Furist(string,fur.getString(string+".id"),fur.getYaml().getInt(string+".data"),fur.getString(string+".name")
            ,fur.getStringList(string+".lore"),fur.getStringList(string+".cmd")));
        }
        plugin.getServer().getLogger().info("§b本次载入了 §6"+Furists.size()+" §b个果实");
    }
    public void loadSeed(List<Seed> seeds){
        seeds.clear();
        for(String string : se.getYaml().getKeys(false)){
            Seed seed = new Seed(string,se.getStringList(string+".lore"),se.getYaml().getInt(string+".time"),se.getYaml().getString(string+".exay")
                    ,plugin.getFuristHashMap().get(string));
            seed.setName(se.getString(string+".name"));
            seeds.add(seed);
        }
        plugin.getServer().getLogger().info("§b本次载入了 §6"+seeds.size()+" §b个种子");
    }
}
