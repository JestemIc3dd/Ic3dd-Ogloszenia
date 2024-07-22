package pl.ic3dd.ic3ddogloszenia.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ChatUtil {
    public static Component syntax(String message) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(message);
    }
}
