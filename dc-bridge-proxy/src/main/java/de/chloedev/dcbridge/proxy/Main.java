package de.chloedev.dcbridge.proxy;

import com.jeff_media.playerdataapi.PlayerDataAPI;
import de.chloedev.dcbridge.proxy.command.CoreCommand;
import de.chloedev.dcbridge.proxy.discord.DiscordBot;
import de.chloedev.dcbridge.proxy.event.IngameListener;
import de.chloedev.dcbridge.proxy.io.FileConfiguration;
import de.chloedev.dcbridge.proxy.network.NetworkManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    private static Main INSTANCE;
    private FileConfiguration config;
    private DiscordBot bot;
    private PlayerDataAPI playerDataAPI;
    private NetworkManager networkManager;

    public static Main getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.config = new FileConfiguration("plugins/dc-bridge/dc-bridge");
        this.bot = new DiscordBot(this.config.get(String.class, "bot-token", null));
        this.playerDataAPI = (PlayerDataAPI) ProxyServer.getInstance().getPluginManager().getPlugin("PlayerDataAPI");
        this.getProxy().getPluginManager().registerCommand(this, new CoreCommand());
        this.getProxy().getPluginManager().registerListener(this, new IngameListener());
        this.networkManager = new NetworkManager();
        this.playerDataAPI.getProvider().redisSubscribe(this.networkManager, "dcbridge:test");
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public DiscordBot getBot() {
        return bot;
    }

    public PlayerDataAPI getPlayerDataAPI() {
        return playerDataAPI;
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }
}
