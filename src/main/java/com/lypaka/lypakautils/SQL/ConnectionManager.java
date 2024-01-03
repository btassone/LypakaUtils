package com.lypaka.lypakautils.SQL;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {
    private static HikariDataSource dataSource;

    public static synchronized void configureDataSource(String jdbcUrl, String username, String password, int maximumPoolSize) {
        if (dataSource != null) {
            throw new IllegalStateException("DataSource has already been configured.");
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(maximumPoolSize);
        // Other configuration settings...

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        if (dataSource == null) {
            throw new IllegalStateException("DataSource has not been configured. Call configureDataSource first.");
        }
        return dataSource;
    }

    public static synchronized void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
            dataSource = null; // Reset the dataSource reference
        }
    }
}
