package net.pl3x.map.command.commands;

import net.pl3x.map.Pl3xMapPlugin;
import net.pl3x.map.command.BaseCommand;
import net.pl3x.map.configuration.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HelpCommand extends BaseCommand {
    public HelpCommand(Pl3xMapPlugin plugin) {
        super(plugin, "help", Lang.CMD_HELP_DESCRIPTION, "pl3xmap.command.help", "/<command> help (subcommand)");
    }

    @Override
    public List<String> handleTabComplete(CommandSender sender, Command command, LinkedList<String> args) {
        return Collections.emptyList();
    }

    @Override
    public boolean handleCommand(CommandSender sender, Command command, LinkedList<String> args) throws CommandException {
        Lang.send(sender, "todo"); // TODO
        return true;
    }
}
