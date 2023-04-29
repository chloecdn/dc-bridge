package de.chloedev.dcbridge.discord;

import de.chloedev.dcbridge.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class DiscordUtil {

    public static void sendMessageToChannel(TextChannel channel, String message, boolean queue) {
        if (queue) {
            Main.getInstance().getBot().getJDA().getTextChannelById(channel.getId()).sendMessage(message).queue();
        } else {
            Main.getInstance().getBot().getJDA().getTextChannelById(channel.getId()).sendMessage(message).complete();
        }
    }

    public static void sendEmbedToChannel(TextChannel channel, MessageEmbed embed, boolean queue) {
        if (queue) {
            Main.getInstance().getBot().getJDA().getTextChannelById(channel.getId()).sendMessageEmbeds(embed).queue();
        } else {
            Main.getInstance().getBot().getJDA().getTextChannelById(channel.getId()).sendMessageEmbeds(embed).complete();
        }
    }

    public static void sendEmbedToChannel(TextChannel channel, String embedTitle, String embedText, String embedFooter, int color, boolean queue) {
        MessageEmbed embed = new EmbedBuilder().setTitle(embedTitle).setDescription(embedText).setFooter(embedFooter).setColor(color).build();
        sendEmbedToChannel(channel, embed, queue);
    }

    public static void sendEmbedToChannel(TextChannel channel, String embedTitle, String embedText, String embedFooter, String imageUrl, int color, boolean queue) {
        MessageEmbed embed = new EmbedBuilder().setTitle(embedTitle).setDescription(embedText).setFooter(embedFooter).setThumbnail(imageUrl).setColor(color).build();
        sendEmbedToChannel(channel, embed, queue);
    }
}
