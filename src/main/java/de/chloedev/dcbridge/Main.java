package de.chloedev.dcbridge;

import de.chloedev.dcbridge.discord.DiscordBot;
import de.chloedev.dcbridge.io.FileConfiguration;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    private static Main INSTANCE;
    private FileConfiguration config;
    private DiscordBot bot;

    public static Main getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.config = new FileConfiguration("dc-bridge");
        this.bot = new DiscordBot(this.config.getFile().getString("bot-token"));
    }

    @Override
    public void onDisable() {

    }

    public FileConfiguration getConfig() {
        return config;
    }
}
