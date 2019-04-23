package com.ftlz.spigot.advancementlog;

import java.io.FileOutputStream;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.bukkit.Location;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
 
public class PlayerAdvancementListener implements Listener
{
    App _app;
    public PlayerAdvancementListener(App app)
    {
        _app = app;        
    }

    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event)
    {
        Advancement advancement = event.getAdvancement();
        String advancementString = advancement.getKey().getKey();
        if (advancementString.startsWith("recipes") == false)
        {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Location.class, new LocationAdapter());
            final Gson gson = gsonBuilder.create();
            String jsonData = gson.toJson(new AdvancementObject(event.getPlayer(), advancement));
            AppendToAdvancementLog(jsonData);
        }
    }


    public void AppendToAdvancementLog(String data)
    {
        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream("AdvancementLog.jsonl", true);

            java.nio.channels.FileLock lock = fileOutputStream.getChannel().lock();
            try
            {
                fileOutputStream.write((data + "\n").getBytes("utf-8"));
            }
            catch (Exception err)
            {
                _app.getLogger().log(Level.WARNING, err.getMessage());
            }
            finally
            {
                lock.release();
                fileOutputStream.close();
                fileOutputStream = null;
            }
        }
        catch (Exception err)
        {
            _app.getLogger().log(Level.WARNING, err.getMessage());
        }
        finally
        {
            if (fileOutputStream != null)
            {
                try
                {
                    fileOutputStream.close();
                }
                catch (Exception err)
                {
                    _app.getLogger().log(Level.WARNING, err.getMessage());
                }
            }
        }
    } 
}