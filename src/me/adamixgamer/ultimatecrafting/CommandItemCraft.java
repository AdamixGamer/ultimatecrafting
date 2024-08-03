package me.adamixgamer.ultimatecrafting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.adamixgamer.ultimatecrafting.getItemData.getItemStack;

public class CommandItemCraft implements CommandExecutor {


    private final UltimateCrafting plugin;

    public CommandItemCraft(UltimateCrafting plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "This command can be executed only as player");
            return true;
        }
        if (args.length == 0)
        {
            player.sendMessage(ChatColor.YELLOW+"You didn't put item name you want to craft, please try again");
            return true;
        }

        List<String> crafts = this.plugin.getConfig().getStringList("craftItems");

        for (String craft : crafts) {
            if (craft.equals(args[0])) {
                ConfigurationSection recipe = this.plugin.getConfig().getConfigurationSection(args[0]);
                if (recipe == null)
                {
                    player.sendMessage("This item has no recipe. If you are not an operator, please contact one.");
                    return true;
                }
                List<ItemStack> ingredients = getDataAsItem(recipe, false);
                ItemStack itemtocraft = getDataAsItem(recipe, true).getFirst();
                player.getInventory().setContents(craftitem(ingredients, itemtocraft, player.getInventory(), player));
                player.updateInventory();

            }
        }

        return true;
    }

    private ConfigurationSection[] getData(ConfigurationSection ingredients) {
        ConfigurationSection[] itemDataArray = new ConfigurationSection[9];
        for (int i = 0; i < 9; i++) {
            String key = String.valueOf(i + 1);
            if (ingredients.contains( key, false)) {
                itemDataArray[i] = ingredients.getConfigurationSection(key);
            }
        }
        return itemDataArray;
    }
    private List<ItemStack> getDataAsItem(ConfigurationSection recipe,boolean craftitem) {
        ConfigurationSection[] itemData = new ConfigurationSection[]{recipe.getConfigurationSection("craft")};
        if(!craftitem){
            itemData =  getData(recipe.getConfigurationSection("ingredients"));
        }

        List<ItemStack> items = new ArrayList<>();
        for (ConfigurationSection itemdata : itemData) {
                Bukkit.getConsoleSender().sendMessage(getItemStack(itemdata).toString());
                items.add(getItemStack(itemdata));
        }
        return items;
    }
    private ItemStack[] craftitem(List<ItemStack> ingredients, ItemStack itemtocraft, Inventory inventory, Player player)
    {
        int recipe_confirm = 0;
        for (ItemStack ingredient : ingredients) {
            if(ingredient.getType() == Material.AIR){
                recipe_confirm++;
            }

            if (inventory.containsAtLeast(ingredient, 1)) {

                recipe_confirm++;
            }

        }
        if(ingredients.size()==recipe_confirm)
        {
            for (ItemStack ingredient : ingredients) {
                inventory.removeItem(ingredient);
            }
            inventory.addItem(itemtocraft);
            if (Objects.requireNonNull(itemtocraft.getItemMeta()).hasDisplayName()) {
                player.sendMessage(ChatColor.GREEN + "Crafted " + itemtocraft.getAmount() + " x " + itemtocraft.getItemMeta().getDisplayName());
            } else {
                player.sendMessage(ChatColor.GREEN + "Crafted " + itemtocraft.getAmount() + " x " + itemtocraft.getType());
            }
        }
        else {
            player.sendMessage(ChatColor.RED + "You lack materials to craft this item!");
        }

        return inventory.getContents();
    }
}
