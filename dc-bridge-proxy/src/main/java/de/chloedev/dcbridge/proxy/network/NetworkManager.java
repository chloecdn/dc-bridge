package de.chloedev.dcbridge.proxy.network;

import com.jeff_media.playerdataapi.DataProvider;
import de.chloedev.dcbridge.proxy.network.listener.RedisListener;
import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkManager extends JedisPubSub {

    private final List<RedisListener> listeners = new ArrayList<>();

    public NetworkManager(DataProvider provider, RedisListener... listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
        this.listeners.forEach(l1 -> {
            provider.redisSubscribe(this, "dcbridge:" + l1.getChannel());
        });
    }

    @Override
    public void onMessage(String channel, String message) {
        listeners.forEach(l -> {
            if (channel.equals("dcbridge:" + l.getChannel())) {
                l.onReceive(message);
            }
        });
    }
}
