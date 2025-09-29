package com.designpatterns.creational.singleton;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
public final class AppLogger {
    private static volatile Logger instance;
    private AppLogger() {}
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (AppLogger.class) {
                if (instance == null) {
                    Logger logger = Logger.getLogger("AppLogger");
                    logger.setUseParentHandlers(false);
                    ConsoleHandler handler = new ConsoleHandler();
                    handler.setLevel(Level.ALL);
                    logger.addHandler(handler);
                    logger.setLevel(Level.INFO);
                    instance = logger;
                }
            }
        }
        return instance;
    }
}
