package net.pl3x.map.configuration;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.Template;
import org.bukkit.command.CommandSender;

import java.io.File;

public final class Lang extends AbstractConfig {
    @Key("command.generic.prefix")
    public static String COMMAND_PREFIX = "<white>[<gradient:#C028FF:#5B00FF>Pl3xMap</gradient>]</white> ";

    @Key("command.generic.subcommands")
    public static String COMMAND_SUBCOMMANDS = "<green>Available Subcommands:";
    @Key("command.generic.description")
    public static String COMMAND_DESCRIPTION = "  <dark_aqua><command> <yellow>- <grey><description>";
    @Key("command.generic.description-and-usage")
    public static String COMMAND_DESCRIPTION_USAGE = "<light_purple><description>:\\n   <yellow>/<grey><usage>";

    @Key("command.error.unknown-subcommand")
    public static String ERROR_UNKNOWN_SUBCOMMAND = "<red>Unknown subcommand";
    @Key("command.error.unknown-error")
    public static String ERROR_UNKNOWN_ERROR = "<red>Unknown error";

    @Key("command.error.world-disabled")
    public static String ERROR_WORLD_DISABLED = "<red>Pl3xMap is disabled in this world";
    @Key("command.error.no-such-world")
    public static String ERROR_NO_SUCH_WORLD = "<red>No such world";
    @Key("command.error.must-specify-world")
    public static String ERROR_MUST_SPECIFY_WORLD = "<red>You must specify the world";

    @Key("command.error.no-such-player")
    public static String ERROR_NO_SUCH_PLAYER = "<red>No such player";
    @Key("command.error.must-specify-player")
    public static String ERROR_MUST_SPECIFY_PLAYER = "<red>You must specify the player";

    @Key("command.cancelrender.render-not-in-progress")
    public static String CMD_CANCELRENDER_RENDER_NOT_IN_PROGRESS = "<red>No renders running for <world>";
    @Key("command.cancelrender.success")
    public static String CMD_CANCELRENDER_SUCCESS = "<green>Render cancelled for <world>";

    @Key("command.fullrender.render-in-progress")
    public static String CMD_FULLRENDER_RENDER_IN_PROGRESS = "<red>A render is already in progress on <world>";
    @Key("command.fullrender.success")
    public static String CMD_FULLRENDER_SUCCESS = "<dark_aqua>Started full map render for <yellow><world>";

    @Key("command.description.help")
    public static String CMD_HELP_DESCRIPTION = "Get help for Pl3xmap commands";

    @Key("command.hide.description")
    public static String CMD_HIDE_DESCRIPTION = "Hide a player from the map";
    @Key("command.hide.already-hidden")
    public static String CMD_HIDE_ALREADY_HIDDEN = "<red><player> is already hidden from map";
    @Key("command.hide.success")
    public static String CMD_HIDE_SUCCESS = "<green><player> is now hidden from map";

    @Key("command.reload.description")
    public static String CMD_RELOAD_DESCRIPTION = "Reloads the plugin";
    @Key("command.reload.success")
    public static String CMD_RELOAD_SUCCESS = "<green><name> v<version> reloaded";

    @Key("command.show.description")
    public static String CMD_SHOW_DESCRIPTION = "Show a player on the map";
    @Key("command.show.already-hidden")
    public static String CMD_SHOW_NOT_HIDDEN = "<red><player> is not hidden from map";
    @Key("command.show.success")
    public static String CMD_SHOW_SUCCESS = "<green><player> is no longer hidden from map";

    private static final Lang CONFIG = new Lang();

    public static void reload(File dir) {
        CONFIG.reload(dir, Config.LANGUAGE_FILE, Lang.class);
    }

    public static void send(CommandSender sender, String message) {
        for (String part : message.split("\\n")) {
            sender.sendMessage(MiniMessage.get().parse(Lang.COMMAND_PREFIX + part));
        }
    }

    public static void send(CommandSender sender, String message, Template... templates) {
        for (String part : message.split("\\n")) {
            sender.sendMessage(MiniMessage.get().parse(Lang.COMMAND_PREFIX + part, templates));
        }
    }
}
