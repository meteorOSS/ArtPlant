package com.meteor.artplant.data;

import java.util.ArrayList;
import java.util.List;

public class Furist {
    String key;
    String id;
    int data;
    String name;
    List<String> lore = new ArrayList<>();
    List<String> command = new ArrayList<>();

    public Furist(String key, String id, int data, String name, List<String> lore, List<String> command) {
        this.key = key;
        this.id = id;
        this.data = data;
        this.name = name;
        this.lore = lore;
        this.command = command;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public List<String> getCommand() {
        return command;
    }

    public void setCommand(List<String> command) {
        this.command = command;
    }
}
