package pl.ic3dd.ic3ddogloszenia.commands;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.ic3dd.ic3ddogloszenia.utils.MessageBuilder;

public class CreateAnnouncementCommand implements CommandExecutor {
    public static String tresc;
    public String getTresc() {
        return tresc;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        MessageBuilder builder = new MessageBuilder();
        Player player = (Player) sender;
        if (player.hasPermission("ic3ddogloszenia.stworz")) {
            if (args.length == 0) {
                builder.addBold("[OGLOSZENIA] ", NamedTextColor.RED);
                builder.add("Złe użycie: /stworzogloszenie [tekst].", NamedTextColor.WHITE);
                builder.send(player);
            }
            if (args.length >= 1) {
                builder.addBold("[OGLOSZENIA] ", NamedTextColor.RED);
                builder.add("Utworzono ogłoszenie.", NamedTextColor.WHITE);
                builder.send(player);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < args.length; i++)
                    stringBuilder.append(args[i]).append(" ");
                tresc = stringBuilder.toString();

            }
        }
        return true;
    }
}
