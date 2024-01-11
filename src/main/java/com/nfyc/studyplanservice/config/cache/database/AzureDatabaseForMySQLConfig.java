package com.nfyc.studyplanservice.config.cache.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "nfyc.azure.datasource")
@Getter
@Setter
public class AzureDatabaseForMySQLConfig {
    private String url;
    private String username;
    private String driverClassName;

}
