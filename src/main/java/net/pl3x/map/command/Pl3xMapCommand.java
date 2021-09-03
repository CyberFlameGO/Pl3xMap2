package net.pl3x.map.command;

import net.pl3x.map.Pl3xMapPlugin;
import net.pl3x.map.command.commands.HelpCommand;
import net.pl3x.map.command.commands.HideCommand;
import net.pl3x.map.command.commands.ReloadCommand;
import net.pl3x.map.command.commands.ShowCommand;

public class Pl3xMapCommand extends BaseCommand {
    public Pl3xMapCommand(Pl3xMapPlugin plugin) {
        super(plugin, "map", "Controls the Pl3xMap plugin", "pl3xmap.command.map", "/<command> help");
        registerSubcommand(new HelpCommand(plugin));
        registerSubcommand(new HideCommand(plugin));
        registerSubcommand(new ShowCommand(plugin));
        registerSubcommand(new ReloadCommand(plugin));
    }
}
