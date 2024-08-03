package me.adamixgamer.ultimatecrafting;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class getItemData {
    public static ItemMeta addMeta(ConfigurationSection meta, ItemMeta itemmeta) {
        if (meta.contains("DisplayName")) {
            itemmeta.setDisplayName(meta.getString("DisplayName"));
        }
        if (meta.contains("CustomModelData")) {
            itemmeta.setCustomModelData(meta.getInt("CustomModelData"));
        }
        if (meta.contains("Lore")) {
            itemmeta.setLore(meta.getStringList("Lore"));
        }
        if (meta.contains("unbreakable")) {
            itemmeta.setUnbreakable(meta.getBoolean("unbreakable"));
        }
        return itemmeta;
    }

    public static ItemStack getItemStack(ConfigurationSection itemData) {
        if (itemData != null) {
            if (!itemData.contains("Material")){
                return new ItemStack(Material.AIR, 1);
            }
            ItemStack item = new ItemStack(Objects.requireNonNull(Material
                    .getMaterial(Objects.requireNonNull(itemData.getString("Material")))));
            if(!itemData.contains("Amount")){
                item.setAmount(1);
            }
            else{
                item.setAmount(itemData.getInt("Amount"));
            }
            if (itemData.contains("ItemMeta")) {
                ConfigurationSection meta = itemData.getConfigurationSection("ItemMeta");
                assert meta != null;
                ItemMeta itemmeta = item.getItemMeta();
                item.setItemMeta(addMeta(meta, itemmeta));
            }
            return item;
        }
        return new ItemStack(Material.AIR, 1);
    }
}
