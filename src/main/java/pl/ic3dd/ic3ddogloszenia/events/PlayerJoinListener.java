package pl.ic3dd.ic3ddogloszenia.events;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.ic3dd.ic3ddogloszenia.Main;
import pl.ic3dd.ic3ddogloszenia.utils.MessageBuilder;

public class PlayerJoinListener implements Listener {
    private final Main plugin;
    public PlayerJoinListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJoinAnnounce(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            boolean config = plugin.getConfig().getBoolean("announce.enabled");
            if (config) {
                MessageBuilder builder = new MessageBuilder();
                builder.addBold("[OGŁOSZENIA] ", NamedTextColor.RED);
                builder.add("Plugin został napisany przez ic3dd_. ", NamedTextColor.GREEN);
                builder.add("Wersja pluginu to: 1.0.0 ", NamedTextColor.YELLOW);
                builder.add("dsc.gg/ic3dd-development | Aby wyłączyć to powiadomienie ustaw opcje annouce.enabled na false", NamedTextColor.GRAY);
                builder.send(player);
            }
        }
    }
}