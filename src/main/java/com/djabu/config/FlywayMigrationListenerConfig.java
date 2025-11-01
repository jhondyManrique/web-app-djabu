package com.djabu.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

@WebListener
public class FlywayMigrationListenerConfig implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSource dataSource = DatabaseConfig.getDataSource();

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

        DatabaseConfig.shutDown();
        System.out.println("Aplicación Djabu detenida. No se requiere limpieza de hilos de driver.");
    }


}
