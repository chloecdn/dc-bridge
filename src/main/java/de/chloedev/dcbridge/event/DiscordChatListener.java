package de.chloedev.dcbridge.event;

import de.chloedev.dcbridge.discord.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordChatListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        // replace the prefix with whatever we use, if we're going to use one at all, otherwise just remove it.
        if (e.getAuthor().isBot() || e.getMessage().getContentRaw().startsWith(";")) {
            return;
        }
        if (e.getChannel().getId().equals(TextChannel.GENERAl.getId())) { // only accept messages from general
            String message = e.getMessage().getContentRaw(); // i prefer raw, do what you gotta do.}
            // sendMessageToServer(message);
        }
    }
}
