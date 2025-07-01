package com.djabu.controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

@WebListener
public class FlywayMigrationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String URL = System.getenv("POSTGRESQL_JDBC_URL");
        String USER = System.getenv("POSTGRESQL_JDBC_USER");
        String PASSWORD = System.getenv("POSTGRESQL_JDBC_PASSWORD");

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();
        try {
            flyway.migrate();
        }catch (Exception e){
            System.out.println("flyway error" + e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Aplicación Djabu detenida. No se requiere limpieza de hilos de driver.");
    }


}
