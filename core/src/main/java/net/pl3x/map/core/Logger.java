package net.pl3x.map.core;

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

    public static void log(Level level, String msg) {
        LOGGER.log(level, msg);
    }

    public static void log(Level level, String msg, Throwable thrown) {
        LOGGER.log(level, msg, thrown);
    }
}
