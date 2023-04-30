package de.chloedev.dcbridge.proxy.event;

import de.chloedev.dcbridge.proxy.Main;
import de.chloedev.dcbridge.proxy.discord.DiscordUtil;
import de.chloedev.dcbridge.proxy.discord.TextChannel;
import de.chloedev.dcbridge.proxy.util.Util;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.net.InetSocketAddress;

public class IngameListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {
        if (e.getSender() instanceof ProxiedPlayer p && !e.getMessage().startsWith("/") && !e.getMessage().contains("<@")) {
            Main.getInstance().getProxy().getScheduler().runAsync(Main.getInstance(), () ->
                    DiscordUtil.sendMessageToChannel(
                            TextChannel.GENERAl,
                            Main.getInstance().getConfig().getFile().getString("chat-message-format")
                                    .replace("{player}", (
                                            Util.isGameUserSynced(p.getUniqueId()) ? Util.getSyncedMember(p.getUniqueId()).getUser().getName() : p.getName()))
                                    .replace("{message}", e.getMessage()),
                            true));
        }
    }

    @EventHandler
    public void onConnect(ServerConnectedEvent e) {
        if (!Util.isDeveloperMode() && e.getServer().getSocketAddress() instanceof InetSocketAddress i && i.getPort() == 30001)
            DiscordUtil.sendEmbedToChannel(TextChannel.GENERAl, "INFO", e.getPlayer().getName() + " joined the Server.", "", "https://mc-heads.net/avatar/" + e.getPlayer().getUniqueId().toString(), 0x00ff00, true);
    }

    @EventHandler
    public void onDisconnect(ServerDisconnectEvent e) {
        if (!Util.isDeveloperMode())
            DiscordUtil.sendEmbedToChannel(TextChannel.GENERAl, "INFO", e.getPlayer().getName() + " left the Server.", "", "https://mc-heads.net/avatar/" + e.getPlayer().getUniqueId().toString(), 0xff0000, true);
    }
}