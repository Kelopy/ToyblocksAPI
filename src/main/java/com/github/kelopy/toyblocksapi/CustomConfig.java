package com.github.kelopy.toyblocksapi;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CustomConfig {

    private File customFile;
    private YamlConfiguration customConfig;

    public CustomConfig(JavaPlugin plugin, String name){
        customFile = new File(plugin.getDataFolder(), name);
        if (!customFile.exists()){
            plugin.saveResource(name, false);
        }

        customConfig = YamlConfiguration.loadConfiguration(customFile);
    }

    public void addDefaults(Map<String,Object> defaults) {
        for (String s : defaults.keySet()) {
            customConfig.set(s, defaults.get(s));

            try {
                customConfig.save(customFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addDefault(String key, String value) {
        if (customConfig.getString(key) == null) {

            customConfig.set(key, value);

            try {
                customConfig.save(customFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public YamlConfiguration get() {
        return customConfig;
    }

    public void save() {
        try {
            customConfig.save(customFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reload() {
        customConfig = YamlConfiguration.loadConfiguration(customFile);
    }

}
