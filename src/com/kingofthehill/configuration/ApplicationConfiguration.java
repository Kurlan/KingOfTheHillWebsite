package com.kingofthehill.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public SessionFactory sessionFactory(org.hibernate.cfg.Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    @Bean
    public org.hibernate.cfg.Configuration hibernateConfiguration() {
        org.hibernate.cfg.Configuration configuration = (new org.hibernate.cfg.Configuration()).configure();
        configuration.setProperty("hibernate.connection.username", System.getProperty("jdbc.username"));
        configuration.setProperty("hibernate.connection.password", System.getProperty("jdbc.password"));
        configuration.setProperty("hibernate.connection.url", System.getProperty("jdbc.connectionString"));
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("show_sql", "true");
        return configuration;
    }
}
