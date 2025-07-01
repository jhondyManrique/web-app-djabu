package com.djabu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = System.getenv("POSTGRESQL_JDBC_URL");
    private static final String USER = System.getenv("POSTGRESQL_JDBC_USER");
    private static final String PASSWORD = System.getenv("POSTGRESQL_JDBC_PASSWORD");;
    private static Connection conexion = null;


    private Conexion() {}


    public static Connection getConexion() {
        try {
            Class.forName("org.postgresql.Driver");

            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida con PostgreSQL.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }


    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
