package de.chloedev.dcbridge.spigot;

import com.jeff_media.playerdataapi.PlayerDataAPI;
import de.chloedev.dcbridge.spigot.event.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main INSTANCE;
    private PlayerDataAPI playerDataAPI;

    public static Main getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.playerDataAPI = (PlayerDataAPI) Bukkit.getPluginManager().getPlugin("PlayerDataAPI");
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }

    public PlayerDataAPI getPlayerDataAPI() {
        return playerDataAPI;
    }
}
