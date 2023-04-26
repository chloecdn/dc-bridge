package de.chloedev.dcbridge;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;

public class Utils {

    private static final LegacyComponentSerializer SERIALIZER = LegacyComponentSerializer.builder().character('&')
            .hexCharacter('#').hexColors().useUnusualXRepeatedCharacterHexFormat().build();

    public static String translate(String toTranslate) {
        return (ChatColor.translateAlternateColorCodes('&', SERIALIZER.serialize(MiniMessage.miniMessage().deserialize(toTranslate))));
    }
}
