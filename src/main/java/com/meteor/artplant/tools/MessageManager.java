package com.meteor.artplant.tools;

import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    YamlConfiguration mes;
    public MessageManager(YamlConfiguration yaml){
        this.mes = yaml;
    }
    public String getString(String path){
        if(mes.getString(path)==null){
            return "消息文件获取失败...检查路径";
        }
        return mes.getString(path).replace("&","§");
    }
    public List<String> getStringList(String path){
        List<String> rnlist = new ArrayList<>();
        mes.getStringList(path).forEach((a)->{
            a = a.replace("&","§");
            rnlist.add(a);
        });
        return rnlist;
    }
    public YamlConfiguration getYaml(){
        return mes;
    }
}
