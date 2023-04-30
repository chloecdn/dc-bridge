package de.chloedev.dcbridge.proxy.network.listener;

public abstract class RedisListener {

    public abstract String getChannel();

    public abstract void onReceive(String message);
}
