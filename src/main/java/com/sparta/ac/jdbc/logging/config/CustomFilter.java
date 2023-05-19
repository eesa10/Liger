package com.sparta.ac.jdbc.logging.config;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class CustomFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        if (record.getMessage().contains("Security")) {
            return true;
        } else  {
            return false;
        }
    }
}
