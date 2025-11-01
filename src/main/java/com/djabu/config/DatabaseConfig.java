package com.djabu.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfig {

    private  static final HikariDataSource dataSource;

    private static final String JDBC_URL = System.getenv("POSTGRESQL_JDBC_URL");
    private static final String JDBC_USER = System.getenv("POSTGRESQL_JDBC_USER");
    private static final String JDBC_PASSWORD = System.getenv("POSTGRESQL_JDBC_PASSWORD");
    private static final String JDBC_DRIVER = System.getenv("POSTGRESQL_JDBC_DRIVER");

    static{
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC class not found", e);
        }

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(JDBC_URL);
        config.setUsername(JDBC_USER);
        config.setPassword(JDBC_PASSWORD);
        config.setDriverClassName(JDBC_DRIVER);
        config.setSchema("public");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(60000);

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

    public static void shutDown() {
        if(dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }




}
