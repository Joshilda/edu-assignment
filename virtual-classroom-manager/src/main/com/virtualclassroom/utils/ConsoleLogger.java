package com.virtualclassroom.utils;

import java.io.IOException;
import java.util.logging.*;

public class ConsoleLogger {
    private static final Logger LOGGER = Logger.getLogger("VirtualClassroomLogger");
    static {
        try {
            LOGGER.setUseParentHandlers(false);
            Formatter simple = new SimpleFormatter();
            Handler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(simple);
            Handler fileHandler = new FileHandler("virtual-classroom.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(simple);
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Could not initialize file logger: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
