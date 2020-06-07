package com.meteor.artplant;

import com.meteor.artplant.commands.Commands;
import com.meteor.artplant.data.Furist;
import com.meteor.artplant.data.Seed;
import com.meteor.artplant.events.BlockPlace;
import com.meteor.artplant.tools.MessageManager;
import com.meteor.artplant.tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ArtPlant extends JavaPlugin {
    HashMap<String, Furist> furistHashMap = new HashMap<>();
    List<Seed> seedList = new ArrayList<>();
    public static ArtPlant plugin;
    Tools tools;
    MessageManager lang;
    YamlConfiguration data;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getLogger().info("------------");
        getServer().getLogger().info("种植插件已载入,meteor");
        getServer().getLogger().info("联系qq2260483272,接插件定制");
        getServer().getLogger().info("------------");
        {
            loadFile();
            tools = new Tools(this);
            plugin = this;
            reloadFile();
            registerCmd();
            registerEvents();
            autoSave();
        }
    }
    void autoSave(){
        Bukkit.getScheduler().runTaskLaterAsynchronously(this,()->{
            save();
            getLogger().info("已自动保存数据....");
            autoSave();
        },getConfig().getInt("setting.auto-save-time")*60*20);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        save();
        getServer().getLogger().info("------------");
        getServer().getLogger().info("种植插件已卸载,meteor");
        getServer().getLogger().info("联系qq2260483272,接插件定制");
        getServer().getLogger().info("------------");
    }
    void save(){
        try {
            data.save(new File(getDataFolder()+"/data.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HashMap<String, Furist> getFuristHashMap() {
        return furistHashMap;
    }

    public MessageManager getLang() {
        return lang;
    }

    public static ArtPlant getPlugin() {
        return plugin;
    }

    public List<Seed> getSeedList() {
        return seedList;
    }

    private void loadFile(){
        saveDefaultConfig();
        String[] strings = {"furist.yml","lang.yml", "seeds.yml","data.yml"};
        for(String string :strings){
            File file = new File(getDataFolder()+"/"+string);
            if(!file.exists()){
                saveResource(string,false);
            }
        }
    }
    private void registerCmd(){
        getServer().getPluginCommand("apt").setExecutor(new Commands(this));
    }
    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new BlockPlace(this),this);
    }

    public Tools getTools() {
        return tools;
    }

    public YamlConfiguration getData() {
        return data;
    }

    public void reloadFile(){
        lang = new MessageManager(YamlConfiguration.loadConfiguration(new File(getDataFolder()+"/lang.yml")));
        data = YamlConfiguration.loadConfiguration(new File(getDataFolder()+"/data.yml"));
        getServer().getLogger().info("------------");
        tools.loadFurist(furistHashMap);
        tools.loadSeed(seedList);
        getServer().getLogger().info("------------");
    }
}
