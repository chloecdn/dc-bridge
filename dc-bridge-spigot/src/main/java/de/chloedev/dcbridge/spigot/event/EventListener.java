package de.chloedev.dcbridge.spigot.event;

import de.chloedev.dcbridge.spigot.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Main.getInstance().getPlayerDataAPI().getProvider().redisPublish("dcbridge:test", "test-message");
    }
}
