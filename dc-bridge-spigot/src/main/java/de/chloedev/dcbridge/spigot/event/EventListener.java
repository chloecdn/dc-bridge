package de.chloedev.dcbridge.spigot.event;

import de.chloedev.dcbridge.spigot.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerAdvancementDoneEvent e) {
        Main.getInstance().getPlayerDataAPI().getProvider().redisPublish("dcbridge:player-advancement", e.getPlayer().getUniqueId() + ";;" + e.getAdvancement().getDisplay().getTitle());
    }
}
