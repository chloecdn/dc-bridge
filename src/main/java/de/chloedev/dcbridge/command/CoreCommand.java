package de.chloedev.dcbridge.command;

import de.chloedev.dcbridge.Main;
import de.chloedev.dcbridge.command.sub.SyncSubCommand;
import de.chloedev.dcbridge.util.Constants;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

public class CoreCommand extends Command {

    private final List<SubCommand> subCommands;

    public CoreCommand() {
        super("dcbridge");
        this.subCommands = List.of(new SyncSubCommand());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        //if (sender.hasPermission("")) {
        if (args.length < 1) {
            sender.sendMessage(new TextComponent(Constants.MSG_INVALID_ARGS));
            return;
        }
        switch (args[0]) {
            case "reload" -> {
                Main.getInstance().getProxy().getScheduler().runAsync(Main.getInstance(), () -> {
                    Main.getInstance().getConfig().reload();
                    sender.sendMessage(new TextComponent(Constants.PLUGIN_PREFIX + "Reloaded Config."));
                });
            }
            case "sync" -> {
                if (sender instanceof ProxiedPlayer p) {
                    for (SubCommand subCommand : this.subCommands) {
                        if (subCommand.getName().equals(args[0])) {
                            subCommand.onExecute(p, ArrayUtils.remove(args, 0));
                            break;
                        }
                    }
                }
            }
            default -> {
                sender.sendMessage(Constants.MSG_INVALID_ARGS);
            }
        }
        //}
    }
}
