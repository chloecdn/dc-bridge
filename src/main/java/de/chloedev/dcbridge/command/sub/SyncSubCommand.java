package de.chloedev.dcbridge.command.sub;

import de.chloedev.dcbridge.Main;
import de.chloedev.dcbridge.command.SubCommand;
import de.chloedev.dcbridge.util.Constants;
import de.chloedev.dcbridge.util.Utils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * PLEASE NOTE:
 * I wrote most parts of this class while being both tired and confused.
 * I never did anything like this.
 * If you can give any constructive help other than "tHiS CoDe lOoKs bAd" please tell me..
 * PR's are also appreciated.
 */
public class SyncSubCommand extends SubCommand {

    public SyncSubCommand() {
        super("sync");
    }

    @Override
    public void onExecute(ProxiedPlayer sender, String[] args) {
        if (Utils.isGameUserSynced(sender.getUniqueId())) {
            sender.sendMessage(new TextComponent(Constants.PLUGIN_PREFIX + "§cYour Account is already synced!"));
            return;
        }
        if (args.length != 1) {
            sender.sendMessage(new TextComponent(Constants.MSG_INVALID_ARGS));
            return;
        }
        String userId = args[0];
        // Doing it async because `findMembers` takes quite some time, and meanwhile freezes the current task.
        Main.getInstance().getProxy().getScheduler().runAsync(Main.getInstance(), () -> {
            List<Member> members = Utils.getMainGuild().findMembers(member -> !member.getUser().isBot()).get();
            boolean b1 = false;
            for (Member m : members) {
                if (userId.equals(m.getId())) {
                    // Open Private-Channel with the user whose id was provided.
                    PrivateChannel c = m.getUser().openPrivateChannel().complete();
                    c.sendMessage("The user `" + sender.getName() + "` tried to sync their Minecraft-Account with this Discord-Account.\n" +
                            "If it isn't your account, just ignore it, It'll be invalidated after 1 Minute.\n" +
                            "If it was you, just reply with `sync`").complete();
                    // Create a message-listener for accepting the sync. will be automatically invalidated after 1 minute.
                    ListenerAdapter l;
                    Main.getInstance().getBot().getJDA().addEventListener(l = new ListenerAdapter() {
                        @Override
                        public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
                            if (event.getChannel().equals(c) && event.getMessage().getContentRaw().equals("sync")) {
                                Main.getInstance().getConfig().set("sync." + sender.getUniqueId().toString(), m.getId());
                                c.sendMessage("Synced Successfully!").queue();
                            }
                        }
                    });
                    //Invalidate Request after 1 Minute.
                    Main.getInstance().getProxy().getScheduler().schedule(Main.getInstance(), () -> Main.getInstance().getBot().getJDA().removeEventListener(l), 1, TimeUnit.MINUTES);
                    b1 = true;
                    break;
                }
            }
            if (!b1)
                sender.sendMessage(new TextComponent(Constants.PLUGIN_PREFIX + "§cCan't find a Discord-User with that User-ID."));
        });
    }
}