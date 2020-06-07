package com.meteor.artplant.data;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Seed {
    String id;
    String name;
    List<String> lore = new ArrayList<>();
    int bumptime;
    String exay;
    Furist furist;

    public Seed(String id, List<String> lore, int bumptime, String exay, Furist furist) {
        this.id = id;
        this.lore = lore;
        this.bumptime =  bumptime;
        this.exay = exay;
        this.furist = furist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public int getBumptime() {
        return bumptime;
    }

    public void setBumptime(int bumptime) {
        this.bumptime = bumptime;
    }

    public String getExay() {
        return exay;
    }

    public void setExay(String exay) {
        this.exay = exay;
    }

    public Furist getFurist() {
        return furist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFurist(Furist furist) {
        this.furist = furist;
    }
}
