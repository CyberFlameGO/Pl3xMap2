package net.pl3x.map.configuration;

import java.io.File;

@SuppressWarnings("unused")
public class Config extends AbstractConfig {
    @Key("settings.language-file")
    public static String LANGUAGE_FILE = "lang-en.yml";
    @Key("settings.debug-mode")
    public static boolean DEBUG_MODE = false;

    @Key("settings.web-directory.path")
    public static String WEB_DIR = "web";
    @Key("settings.web-directory.auto-update")
    public static boolean UPDATE_WEB_DIR = true;

    private static final Config CONFIG = new Config();

    public static void reload(File dir) {
        CONFIG.reload(dir, "resources/config.yml", Config.class);
    }
}
