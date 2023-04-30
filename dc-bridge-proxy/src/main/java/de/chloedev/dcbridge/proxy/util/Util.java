package de.chloedev.dcbridge.proxy.util;

import de.chloedev.dcbridge.proxy.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;

import java.util.UUID;

public class Util {

    private static final LegacyComponentSerializer SERIALIZER = LegacyComponentSerializer.builder().character('&')
            .hexCharacter('#').hexColors().useUnusualXRepeatedCharacterHexFormat().build();

    public static String translate(String toTranslate) {
        return (ChatColor.translateAlternateColorCodes('&', SERIALIZER.serialize(MiniMessage.miniMessage().deserialize(toTranslate))));
    }

    public static Guild getMainGuild() {
        Guild g = Main.getInstance().getBot().getJDA().getGuildById("1056451147168223232");
        if (g == null) throw new RuntimeException("Guild not found.");
        return g;
    }

    public static boolean isGameUserSynced(UUID uuid) {
        return Main.getInstance().getConfig().getFile().contains("sync." + uuid);
    }

    public static boolean isDiscordUserSynced(String memberId) {
        try {
            return Main.getInstance().getConfig().getFile().getConfigurationSection("sync").getValues(true).containsValue(memberId);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * RETURNS NULL IF NOT SYNCED!
     */
    public static Member getSyncedMember(UUID uuid) {
        if (isGameUserSynced(uuid)) {
            return getMainGuild().findMembers(m -> m.getId().equals(Main.getInstance().getConfig().getFile().getString("sync." + uuid))).get().get(0);
            //return getMainGuild().getMemberById(Main.getInstance().getConfig().getFile().getString("sync." + uuid));
        }
        return null;
    }

    public static boolean isDeveloperMode() {
        return Main.getInstance().getConfig().get(Boolean.class, "developer-mode", false);
    }

    public static boolean isInDevEnv() {
        return Main.getInstance().getConfig().get(Boolean.class, "developer-environment", false);
    }
}
