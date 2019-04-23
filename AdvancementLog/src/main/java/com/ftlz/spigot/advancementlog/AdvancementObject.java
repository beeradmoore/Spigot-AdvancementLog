package com.ftlz.spigot.advancementlog;

import com.google.gson.annotations.SerializedName;

import org.bukkit.Location;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;

public class AdvancementObject
{
    @SerializedName("name")
    private String _playerName;

    @SerializedName("location")
    private Location _location;

    @SerializedName("advancement")
    private String _advancement;

    @SerializedName("time")
    private long _time;

    public AdvancementObject(Player player, Advancement advancement)
    {
        _playerName = player.getName();
        _location = player.getLocation();
        _advancement = advancement.getKey().getKey();
        _time = (System.currentTimeMillis() / 1000L);
    }
}