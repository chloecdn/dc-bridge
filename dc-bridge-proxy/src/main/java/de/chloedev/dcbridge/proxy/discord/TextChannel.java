package de.chloedev.dcbridge.proxy.discord;

public enum TextChannel {
    MC_BRIDGE("1099723097281659034"),
    GENERAl("1056451147168223235");

    private String id;

    TextChannel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
