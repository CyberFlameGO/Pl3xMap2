package net.pl3x.map.command.commands;

import net.kyori.adventure.text.minimessage.Template;
import net.pl3x.map.Pl3xMapPlugin;
import net.pl3x.map.command.BaseCommand;
import net.pl3x.map.configuration.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReloadCommand extends BaseCommand {
    public ReloadCommand(Pl3xMapPlugin plugin) {
        super(plugin, "reload", Lang.CMD_RELOAD_DESCRIPTION, "pl3xmap.command.reload", "/<command> reload");
    }

    @Override
    public List<String> handleTabComplete(CommandSender sender, Command command, LinkedList<String> args) {
        return Collections.emptyList();
    }

    @Override
    public boolean handleCommand(CommandSender sender, Command command, LinkedList<String> args) throws CommandException {
        getPlugin().reload();
        Lang.send(sender, Lang.CMD_RELOAD_SUCCESS,
                Template.of("name", getPlugin().getName()),
                Template.of("version", getPlugin().getDescription().getVersion()));
        return true;
    }
}
