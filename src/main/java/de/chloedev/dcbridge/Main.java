package de.chloedev.dcbridge;

import de.chloedev.dcbridge.command.CoreCommand;
import de.chloedev.dcbridge.discord.DiscordBot;
import de.chloedev.dcbridge.event.IngameChatListener;
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
        this.config = new FileConfiguration("plugins/dc-bridge/dc-bridge");
        this.bot = new DiscordBot(this.config.getFile().getString("bot-token"));
        this.getProxy().getPluginManager().registerCommand(this, new CoreCommand());
        this.getProxy().getPluginManager().registerListener(this, new IngameChatListener());
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public DiscordBot getBot() {
        return bot;
    }
}
