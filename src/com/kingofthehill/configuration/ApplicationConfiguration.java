package com.kingofthehill.configuration;

import java.util.concurrent.TimeUnit;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.kingofthehill.cache.LatestGrafitiCacheLoader;
import com.kingofthehill.cache.QueueLengthCacheLoader;
import com.kingofthehill.model.Grafiti;
import com.kingofthehill.repository.GetLatestGrafitiRepository;
import com.kingofthehill.repository.QueueSizeRepository;

@Configuration
@EnableWebMvc
public class ApplicationConfiguration {

    @Autowired
    private GetLatestGrafitiRepository getLatestGrafitiRepository;

    @Autowired
    private QueueSizeRepository queueSizeRepository;

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
        configuration.setProperty("jadira.usertype.autoRegisterUserTypes", "true");

        configuration.setProperty("hibernate.c3p0.min_size", "1");
        configuration.setProperty("hibernate.c3p0.max_size", "2");
        configuration.setProperty("hibernate.c3p0.timeout", "300");
        configuration.setProperty("hibernate.c3p0.idle_test_period", "3000");

        return configuration;
    }

    @Bean
    public LoadingCache<String, Grafiti> configureLatestGrafitiCache() {
        return CacheBuilder.newBuilder().maximumSize(10).expireAfterWrite(5, TimeUnit.SECONDS)
                .build(new LatestGrafitiCacheLoader(getLatestGrafitiRepository));
    }

    @Bean
    public LoadingCache<String, Long> configureQueueSizeCache() {
        return CacheBuilder.newBuilder().maximumSize(10).expireAfterWrite(5, TimeUnit.SECONDS)
                .build(new QueueLengthCacheLoader(queueSizeRepository));
    }
}
