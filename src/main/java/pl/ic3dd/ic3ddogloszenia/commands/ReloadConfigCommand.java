package pl.ic3dd.ic3ddogloszenia.commands;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.ic3dd.ic3ddogloszenia.Main;
import pl.ic3dd.ic3ddogloszenia.utils.MessageBuilder;

public class ReloadConfigCommand implements CommandExecutor {
    private final Main plugin;
    public ReloadConfigCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;
        MessageBuilder builder = new MessageBuilder();
        if(player.hasPermission("ic3ddogloszenia.reload")) {
            try {
                plugin.reloadConfig();
                builder.addBold("[OGLOSZENIA] ", NamedTextColor.RED);
                builder.add("Przeładowano plugin.", NamedTextColor.WHITE);
                builder.send(player);
            } catch (Exception e) {
                e.printStackTrace();
                builder.addBold("[OGLOSZENIA] ", NamedTextColor.RED);
                builder.add("Wystąpił błąd podczas przeładowywania pluginu.", NamedTextColor.WHITE);
                builder.send(player);
            }
        }
        return true;
    }
}
