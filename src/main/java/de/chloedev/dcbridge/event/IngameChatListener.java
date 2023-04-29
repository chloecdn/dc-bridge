package de.chloedev.dcbridge.event;

import de.chloedev.dcbridge.Main;
import de.chloedev.dcbridge.discord.DiscordUtil;
import de.chloedev.dcbridge.discord.TextChannel;
import de.chloedev.dcbridge.util.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class IngameChatListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {
        if (e.getSender() instanceof ProxiedPlayer p && !e.getMessage().startsWith("/") && !e.getMessage().contains("<@")) {
            Main.getInstance().getProxy().getScheduler().runAsync(Main.getInstance(), () ->
                    DiscordUtil.sendMessageToChannel(
                            TextChannel.GENERAl,
                            Main.getInstance().getConfig().getFile().getString("chat-message-format")
                                    .replace("{player}", (
                                            Utils.isGameUserSynced(p.getUniqueId()) ? Utils.getSyncedMember(p.getUniqueId()).getUser().getName() : p.getName()))
                                    .replace("{message}", e.getMessage()),
                            true));
        }
    }
}