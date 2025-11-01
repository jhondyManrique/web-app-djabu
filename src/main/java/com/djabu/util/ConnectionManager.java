package com.djabu.util;
import com.djabu.config.DatabaseConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private static final DataSource dataSource = DatabaseConfig.getDataSource();

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e){
            throw new RuntimeException("connection has failed", e);
        }
    }
}
