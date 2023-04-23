package de.chloedev.dcbridge.event;

import de.chloedev.dcbridge.Main;
import de.chloedev.dcbridge.discord.DiscordUtil;
import de.chloedev.dcbridge.discord.TextChannel;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {
        if (e.getSender() instanceof ProxiedPlayer p) {
            DiscordUtil.sendMessageToChannel(TextChannel.GENERAl, Main.getInstance().getConfig().getFile().getString("chat-message-format".replace("{player}", p.getName()).replace("{message}", e.getMessage())), false);
        }
    }
}