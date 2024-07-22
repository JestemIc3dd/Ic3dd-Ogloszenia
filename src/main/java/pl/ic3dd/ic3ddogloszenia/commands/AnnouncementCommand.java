package pl.ic3dd.ic3ddogloszenia.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import pl.ic3dd.ic3ddogloszenia.Main;
import pl.ic3dd.ic3ddogloszenia.utils.ChatUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnnouncementCommand implements CommandExecutor, Listener {
    private final Main plugin;
    private final CreateAnnouncementCommand createAnnouncementCommand;

    public AnnouncementCommand(Main plugin, CreateAnnouncementCommand createAnnouncementCommand) {
        this.plugin = plugin;
        this.createAnnouncementCommand = createAnnouncementCommand;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            openAnnouncementGUI(player);
            return true;
        }
        return false;
    }

    public @NotNull ItemStack createItem(String materialPath, String namePath, String lorePath) {
        String materialString = plugin.getConfig().getString(materialPath);
        if (materialString == null) {
            plugin.getLogger().warning("Material not found for path: " + materialPath);
            return new ItemStack(Material.AIR);
        }

        Material material;
        try {
            material = Material.valueOf(materialString);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Invalid material: " + materialString);
            return new ItemStack(Material.AIR);
        }

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            plugin.getLogger().warning("ItemMeta is null for item: " + materialString);
            return item;
        }

        String name = plugin.getConfig().getString(namePath);
        if (name != null) {
            meta.displayName(ChatUtil.syntax(name));
        } else {
            plugin.getLogger().warning("Name not found for path: " + namePath);
        }

        List<Component> lore = new ArrayList<>();
        List<String> itemLore = plugin.getConfig().getStringList(lorePath);
        for (String line : itemLore) {
            line = line.replace("[TRESC]", createAnnouncementCommand.getTresc());
            lore.add(ChatUtil.syntax(line));
        }
        meta.lore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public void openAnnouncementGUI(Player player) {
        int guiSize = plugin.getConfig().getInt("gui.size");
        Component guiName = Component.text(Objects.requireNonNull(plugin.getConfig().getString("gui.name")));
        Inventory menu = Bukkit.createInventory(null, guiSize, Objects.requireNonNull(guiName));

        for (String key : Objects.requireNonNull(plugin.getConfig().getConfigurationSection("items")).getKeys(false)) {
            int slot = plugin.getConfig().getInt("items." + key + ".slot");
            String materialPath = "items." + key + ".material";
            String namePath = "items." + key + ".name";
            String lorePath = "items." + key + ".lore";

            ItemStack item = createItem(materialPath, namePath, lorePath);
            menu.setItem(slot, item);
        }

        player.openInventory(menu);
    }

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent event) {
        if (isServerSelectorMenu(event.getView().title())) {
            event.setCancelled(true);
        }
    }

    private boolean isServerSelectorMenu(@NotNull Component title) {
        String nameInventory = plugin.getConfig().getString("gui.name");
        return title.equals(Component.text(Objects.requireNonNull(nameInventory)));
    }
}
