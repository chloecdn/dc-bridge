package de.chloedev.dcbridge.spigot.util;

import de.chloedev.dcbridge.spigot.Main;

public class RedisUtil {

    public static void sendInfoMessage(String message) {
        Main.getInstance().getPlayerDataAPI().getProvider().redisPublish("dcbridge:info", message);
    }

    public static void sendEventMessage(String message) {
        Main.getInstance().getPlayerDataAPI().getProvider().redisPublish("dcbridge:event", message);
    }
}