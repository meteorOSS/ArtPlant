package com.meteor.artplant.data;

public class SpeedPros {
    String key;
    String id;
    int data;
    String name;
    int speedtime;

    public SpeedPros(String key, String id, int data, String name, int speedtime) {
        this.key = key;
        this.id = id;
        this.data = data;
        this.name = name;
        this.speedtime = speedtime;
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

    public int getSpeedtime() {
        return speedtime;
    }

    public void setSpeedtime(int speedtime) {
        this.speedtime = speedtime;
    }
}
