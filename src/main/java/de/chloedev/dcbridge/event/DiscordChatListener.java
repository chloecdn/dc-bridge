package de.chloedev.dcbridge.event;

import de.chloedev.dcbridge.Main;
import de.chloedev.dcbridge.Utils;
import de.chloedev.dcbridge.discord.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Collection;

public class DiscordChatListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        // replace the prefix with whatever we use, if we're going to use one at all, otherwise just remove it.
        if (e.getAuthor().isBot() || e.getMessage().getContentRaw().startsWith(";")) {
            return;
        }
        if (e.getChannel().getId().equals(TextChannel.GENERAl.getId())) { // only accept messages from general
            String message = e.getMessage().getContentRaw(); // i prefer raw, do what you gotta do.}
            Collection<ProxiedPlayer> players = Main.getInstance().getProxy().getPlayers();
            if (!players.isEmpty() && message.length() < 256) {
                players.forEach(p -> {
                    p.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(Utils.translate("&7[&9Discord&7] " + e.getAuthor().getName() + ": " + message)));
                });
            }
        }
    }
}
