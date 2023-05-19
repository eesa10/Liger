package com.sparta.ac.jdbc.logging.config;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class FileHandlerConfig {
    public static FileHandler getFileHandler() {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("src/main/resources/logFile.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new CustomFormatter());
        } catch (IllegalArgumentException | SecurityException | IOException e) {
            e.printStackTrace();
        }
        return fileHandler;
    }
}
