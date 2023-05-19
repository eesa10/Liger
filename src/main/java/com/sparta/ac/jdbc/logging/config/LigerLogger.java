package com.sparta.ac.jdbc.logging.config;

import com.sparta.ac.jdbc.Phase2;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LigerLogger {
    public static final Logger logger = Logger.getLogger(Phase2.class.getName());

    public static void ligerLogger() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        consoleHandler.setFormatter(new CustomFormatter());
        logger.setUseParentHandlers(false);
        //logger.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        logger.addHandler(FileHandlerConfig.getFileHandler()); }
    }
