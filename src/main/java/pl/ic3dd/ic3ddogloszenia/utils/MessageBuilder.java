package pl.ic3dd.ic3ddogloszenia.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class MessageBuilder {
    private final TextComponent.Builder message;

    public MessageBuilder() {
        message = Component.text();
    }

    public void add(String text, NamedTextColor color) {
        message.append(Component.text(text, color));
    }

    public void addBold(String text, NamedTextColor color) {
        message.append(Component.text(text, color, TextDecoration.BOLD));
    }

    public void send(Player player) {
        player.sendMessage(message.build());
    }
}