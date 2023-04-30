package de.chloedev.dcbridge.proxy.command;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public abstract class SubCommand {

    private final String name;

    public SubCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void onExecute(ProxiedPlayer sender, String[] args);
}