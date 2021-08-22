package net.pl3x.map.core.configuration;

import java.io.File;

public final class Lang extends AbstractConfig {
    @Key("command.generic.available-subcommands")
    public static String AVAILABLE_SUBCOMMANDS = "<green>Available Subcommands:";
    @Key("command.generic.command-description")
    public static String COMMAND_DESCRIPTION = "  &3{command} &e- &7{description}";

    @Key("command.error.unknown-subcommand")
    public static String UNKNOWN_SUBCOMMAND = "<red>Unknown subcommand";

    @Key("command.error.world-disabled")
    public static String WORLD_DISABLED = "<red>Pl3xMap is disabled in this world";
    @Key("command.error.no-such-world")
    public static String NO_SUCH_WORLD = "<red>No such world";
    @Key("command.error.must-specify-world")
    public static String MUST_SPECIFY_WORLD = "<red>You must specify the world";

    @Key("command.error.no-such-player")
    public static String NO_SUCH_PLAYER = "<red>No such player";
    @Key("command.error.must-specify-player")
    public static String MUST_SPECIFY_PLAYER = "<red>You must specify the player";

    @Key("command.error.player-not-hidden")
    public static String PLAYER_NOT_HIDDEN = "<red><player> is not hidden from map";
    @Key("command.error.player-already-hidden")
    public static String PLAYER_ALREADY_HIDDEN = "<red><player> is already hidden from map";

    @Key("command.error.render-in-progress")
    public static String RENDER_IN_PROGRESS = "<red>A render is already in progress on <world>";
    @Key("command.error.render-not-in-progress")
    public static String RENDER_NOT_IN_PROGRESS = "<red>No renders running for <world>";

    @Key("click-for-help")
    public static String CLICK_FOR_HELP = "Click for help";

    @Key("command.prefix")
    public static String COMMAND_PREFIX = "<white>[<gradient:#C028FF:#5B00FF>Pl3xMap</gradient>]</white> ";

    @Key("command.description.help")
    public static String HELP_COMMAND_DESCRIPTION = "Get help for Pl3xmap commands";
    @Key("command.description.reload")
    public static String RELOAD_COMMAND_DESCRIPTION = "Reloads the plugin";

    @Key("command.hide.success")
    public static String HIDE_PLAYER_SUCCESS = "<green><player> is now hidden from map";
    @Key("command.show.success")
    public static String SHOW_PLAYER_SUCCESS = "<green><player> is no longer hidden from map";

    @Key("command.cancelrender.success")
    public static String RENDER_CANCELLED = "<green>Render cancelled for <world>";
    @Key("command.fullrender.success")
    public static String FULLRENDER_STARTED = "<dark_aqua>Started full map render for <yellow><world>";

    @Key("command.reload.success")
    public static String PLUGIN_RELOADED = "<green><name> v<version> reloaded";

    private static final Lang CONFIG = new Lang();

    public static void reload(File dir) {
        CONFIG.reload(dir, Config.LANGUAGE_FILE, Lang.class);
    }
}
