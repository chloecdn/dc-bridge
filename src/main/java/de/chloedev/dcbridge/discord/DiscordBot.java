package de.chloedev.dcbridge.discord;

import de.chloedev.dcbridge.Main;
import de.chloedev.dcbridge.event.DiscordChatListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class DiscordBot {

    private JDA jda;

    public DiscordBot(String token) {
        if (token == null || token.isEmpty()) {
            Main.getInstance().getLogger().severe("Couldn't start Bot: value 'bot-token' not found in config.");
            //Bungee can't disable a plugin, woopeedoo, enjoy the errors my lads ._.
            return;
        }
        try {
            this.jda = JDABuilder.createDefault(token)
                    .setActivity(Activity.watching(Main.getInstance().getConfig().getFile().getString("bot-activity", "")))
                    .setStatus(OnlineStatus.fromKey(Main.getInstance().getConfig().getFile().getString("bot-status", "online")))
                    .addEventListeners(new DiscordChatListener())
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .build().awaitReady();
            Main.getInstance().getLogger().info("Sucessfully started Bot.");
        } catch (InterruptedException e) {
            Main.getInstance().getLogger().severe("Couldn't start Bot: ");
            e.printStackTrace();
            //Bungee can't disable a plugin, woopeedoo, enjoy the errors my lads ._.
        }
    }

    public JDA getJDA() {
        return jda;
    }
}