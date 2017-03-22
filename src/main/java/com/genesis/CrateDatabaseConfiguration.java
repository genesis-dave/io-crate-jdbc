package com.genesis;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = { "com.genesis" })
public class CrateDatabaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrateDatabaseConfiguration.class);

    @Value("${database.host}")
    private String host;
    @Value("${database.port}")
    private int port;
    @Value("${database.schema}")
    private String schema;
    @Value("${database.driverClassName}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(String.format("crate://%s:%d/", host, port));
        // dataSource.setSchema(schema);

        LOGGER.info("datasource url: {}", dataSource.getUrl());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        LOGGER.info("creating instance of jdbc template with the specified datasource settings");
        return new JdbcTemplate(dataSource());
    }

}
