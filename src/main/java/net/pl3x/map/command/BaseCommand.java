package net.pl3x.map.command;

import net.kyori.adventure.text.minimessage.Template;
import net.pl3x.map.Pl3xMapPlugin;
import net.pl3x.map.configuration.Lang;
import net.pl3x.map.world.MapWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public abstract class BaseCommand implements TabExecutor {
    private final Map<String, BaseCommand> subCommands = new TreeMap<>();

    private final Pl3xMapPlugin plugin;
    private final String name;
    private final String description;
    private final String permission;
    private final String usage;

    public BaseCommand(Pl3xMapPlugin plugin, String name, String description, String permission, String usage) {
        this.plugin = plugin;
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.usage = usage;
    }

    public Pl3xMapPlugin getPlugin() {
        return this.plugin;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPermission() {
        return this.permission;
    }

    public String getUsage() {
        return this.usage;
    }

    public void showHelp(CommandSender sender) {
        Lang.send(sender, Lang.COMMAND_DESCRIPTION_USAGE,
                Template.of("command", getName()),
                Template.of("description", getDescription()),
                Template.of("usage", getUsage()));
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] strings) {
        return handleTabComplete(sender, command, new LinkedList<>(Arrays.asList(strings)));
    }

    public List<String> handleTabComplete(CommandSender sender, Command command, LinkedList<String> args) {
        if (args.size() > 1) {
            BaseCommand subCmd = this.subCommands.get(args.pop().toLowerCase());
            if (subCmd != null && sender.hasPermission(subCmd.getPermission())) {
                return subCmd.handleTabComplete(sender, command, args);
            }
        } else if (args.size() == 1) {
            String arg = args.peek().toLowerCase();
            return this.subCommands.entrySet().stream()
                    .filter(cmdPair -> cmdPair.getKey().startsWith(arg))
                    .filter(cmdPair -> sender.hasPermission(cmdPair.getValue().getPermission()))
                    .map(Map.Entry::getKey).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        try {
            return handleCommand(sender, command, new LinkedList<>(Arrays.asList(args)));
        } catch (CommandException e) {
            if (e.getMessage() == null || e.getMessage().equals("")) {
                Lang.send(sender, Lang.ERROR_UNKNOWN_ERROR);
            } else {
                Lang.send(sender, e.getMessage());
            }
            return true;
        }
    }

    public boolean handleCommand(CommandSender sender, Command command, LinkedList<String> args) throws CommandException {
        if (args.size() > 0) {
            BaseCommand subCmd = this.subCommands.get(args.pop().toLowerCase());
            if (subCmd != null) {
                if ("?".equals(args.peek())) {
                    subCmd.showHelp(sender);
                    return true;
                }
                return subCmd.handleCommand(sender, command, args);
            }
            Lang.send(sender, Lang.ERROR_UNKNOWN_SUBCOMMAND);
        }
        showSubCommands(sender);
        return true;
    }

    private void showSubCommands(CommandSender sender) {
        Lang.send(sender, Lang.COMMAND_SUBCOMMANDS);
        boolean hasSubCmds = false;
        for (BaseCommand subCmd : this.subCommands.values()) {
            if (sender.hasPermission(subCmd.getPermission())) {
                Lang.send(sender, Lang.COMMAND_DESCRIPTION,
                        Template.of("command", subCmd.getName()),
                        Template.of("description", subCmd.getDescription()));
                hasSubCmds = true;
            }
        }
        if (!hasSubCmds) {
            Lang.send(sender, Bukkit.getPermissionMessage());
        }
    }

    protected void registerSubcommand(BaseCommand subCmd) {
        this.subCommands.put(subCmd.getName().toLowerCase(), subCmd);
    }

    protected Player getPlayer(CommandSender sender, LinkedList<String> args, String targetPerm) {
        String target = args.pop();
        if (target == null) {
            if (sender instanceof Player player) {
                return player;
            }
            throw new CommandException(Lang.ERROR_MUST_SPECIFY_PLAYER);
        }
        if (targetPerm != null && !sender.hasPermission(targetPerm)) {
            throw new CommandException(Bukkit.getPermissionMessage());
        }
        Player player = Bukkit.getPlayer(target);
        if (player == null) {
            throw new CommandException(Lang.ERROR_NO_SUCH_PLAYER);
        }
        return player;
    }

    public MapWorld getMapWorld(CommandSender sender, LinkedList<String> args) {
        String target = args.pop();
        World world = null;
        if (target == null) {
            if (!(sender instanceof Player player)) {
                throw new CommandException(Lang.ERROR_MUST_SPECIFY_WORLD);
            }
            world = player.getWorld();
        }
        if (world == null) {
            world = Bukkit.getWorld(target);
            if (world == null) {
                throw new CommandException(Lang.ERROR_NO_SUCH_WORLD);
            }
        }
        MapWorld mapWorld = getPlugin().getWorldManager().getMapWorld(world);
        if (mapWorld == null) {
            throw new CommandException(Lang.ERROR_WORLD_DISABLED);
        }
        return mapWorld;
    }

    public List<String> tabPlayers(String target) {
        return Bukkit.getOnlinePlayers().stream()
                .map(player -> player.getName().toLowerCase(Locale.ROOT))
                .filter(name -> name.startsWith(target.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public List<String> tabMapWorlds(String target) {
        return getPlugin().getWorldManager().getMapWorlds().stream()
                .map(world -> world.getName().toLowerCase(Locale.ROOT))
                .filter(name -> name.startsWith(target.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
}
