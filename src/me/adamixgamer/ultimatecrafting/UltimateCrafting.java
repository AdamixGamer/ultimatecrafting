package me.adamixgamer.ultimatecrafting;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class UltimateCrafting extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("itemcraft").setExecutor(new CommandItemCraft(this));
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Ultimate Crafting " + this.getDescription().getVersion() + " has been enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Ultimate Crafting " + this.getDescription().getVersion() + " has been disabled");
    }
}