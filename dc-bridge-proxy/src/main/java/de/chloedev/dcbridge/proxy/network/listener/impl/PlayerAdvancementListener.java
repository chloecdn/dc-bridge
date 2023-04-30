package de.chloedev.dcbridge.proxy.network.listener.impl;

import de.chloedev.dcbridge.proxy.Main;
import de.chloedev.dcbridge.proxy.discord.DiscordUtil;
import de.chloedev.dcbridge.proxy.discord.TextChannel;
import de.chloedev.dcbridge.proxy.network.listener.RedisListener;
import de.chloedev.dcbridge.shared.KeyValuePair;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class PlayerAdvancementListener extends RedisListener {

    @Override
    public String getChannel() {
        return "player-advancement";
    }

    @Override
    public void onReceive(String message) {
        KeyValuePair<String, String> pair = KeyValuePair.getStringPair(message);
        ProxiedPlayer player = Main.getInstance().getProxy().getPlayer(UUID.fromString(pair.key()));
        DiscordUtil.sendEmbedToChannel(TextChannel.GENERAl, player.getName() + " reached an Advancement!", pair.value(), "", "https://mc-heads.net/avatar/" + pair.key(), 0x00ff00, true);
    }
}
