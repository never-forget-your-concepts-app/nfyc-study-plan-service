package com.nfyc.studyplanservice.config.cache.database;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Profile("azure")
@Configuration
@RequiredArgsConstructor
public class AzureMySQLConfiguration {

    private final AzureDatabaseForMySQLConfig azureDatabaseForMySQLConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(AzureMySQLConfiguration.class);

    @Bean(name = "azureMySQLDataSource")
    public DataSource dataSource() {
        DataSourceProperties dataSourceProperties = dataSourceProperties();
        return DataSourceBuilder.create()
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .driverClassName(dataSourceProperties.getDriverClassName())
                .build();

    }

    @Bean (name = "azureMySQLDataSourceProperties")
    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl(azureDatabaseForMySQLConfig.getUrl());
        dataSourceProperties.setUsername(azureDatabaseForMySQLConfig.getUsername());
        dataSourceProperties.setDriverClassName(azureDatabaseForMySQLConfig.getDriverClassName());
        return dataSourceProperties;
    }
}