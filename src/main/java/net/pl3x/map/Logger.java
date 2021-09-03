package net.pl3x.map;

import net.pl3x.map.configuration.Config;

import java.util.logging.Level;
import java.util.logging.LogManager;

public class Logger {
    public static java.util.logging.Logger LOGGER = new Pl3xLogger();

    static {
        LogManager.getLogManager().addLogger(LOGGER);
    }

    private static class Pl3xLogger extends java.util.logging.Logger {
        private Pl3xLogger() {
            super("\u001b[36mPl3xMap\u001b[0m", null);
        }
    }

    public static void debug(String msg) {
        if (Config.DEBUG_MODE) {
            LOGGER.log(Level.INFO, "[DEBUG] " + msg);
        }
    }

    public static void debug(String msg, Throwable thrown) {
        if (Config.DEBUG_MODE) {
            LOGGER.log(Level.INFO, "[DEBUG] " + msg, thrown);
        }
    }

    public static void info(String msg) {
        LOGGER.log(Level.INFO, msg);
    }

    public static void info(String msg, Throwable thrown) {
        LOGGER.log(Level.INFO, msg, thrown);
    }

    public static void warn(String msg) {
        LOGGER.log(Level.WARNING, msg);
    }

    public static void warn(String msg, Throwable thrown) {
        LOGGER.log(Level.WARNING, msg, thrown);
    }

    public static void severe(String msg) {
        LOGGER.log(Level.SEVERE, msg);
    }

    public static void severe(String msg, Throwable thrown) {
        LOGGER.log(Level.SEVERE, msg, thrown);
    }
}
