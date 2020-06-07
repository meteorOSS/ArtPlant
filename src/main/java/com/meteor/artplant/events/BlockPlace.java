package com.meteor.artplant.events;

import com.meteor.artplant.ArtPlant;
import com.meteor.artplant.data.Seed;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlace implements Listener {
    ArtPlant plugin;
    public BlockPlace(ArtPlant plugin){
        this.plugin = plugin;
    }
    private Seed getSeed(String name){
        for (Seed seed : plugin.getSeedList()) {
            if(seed.getName().equalsIgnoreCase(name)){
                return seed;
            }
        }
        return null;
    }
    @EventHandler
    void placeBlock(BlockPlaceEvent placeEvent){
        ItemStack itemStack = placeEvent.getItemInHand();
        if(itemStack.getType()==Material.SKULL_ITEM&&itemStack.getDurability()==3&&itemStack.getItemMeta().hasDisplayName()){
            Seed seed = getSeed(itemStack.getItemMeta().getDisplayName());
            if(seed!=null){

            }
        }
    }
}
