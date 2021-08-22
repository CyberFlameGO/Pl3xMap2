package net.pl3x.map.core.configuration;

import java.io.File;

@SuppressWarnings("unused")
public class Config extends AbstractConfig {
    @Key("settings.language-file")
    public static String LANGUAGE_FILE = "lang-en.yml";
    @Key("settings.debug-mode")
    public static boolean DEBUG_MODE = false;

    private static final Config CONFIG = new Config();

    public static void reload(File dir) {
        CONFIG.reload(dir, "config.yml", Config.class);
    }
}
