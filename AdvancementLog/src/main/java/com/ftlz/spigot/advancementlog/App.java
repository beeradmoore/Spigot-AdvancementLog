package com.ftlz.spigot.advancementlog;

import java.util.Iterator;
import java.util.logging.Level;

import org.bukkit.advancement.Advancement;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerAdvancementListener(this), this);

        /*
        // Used to output all current known advancements
        Iterator<Advancement> iterator = getServer().advancementIterator();
        while (iterator.hasNext())
        {
            Advancement advancement = iterator.next();
            getLogger().log(Level.INFO, advancement.getKey().getKey());
        }
        */
    }
}
