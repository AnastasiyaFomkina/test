package com.fomkina.test.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 15:15
 */
@Configuration
public class FlywayRepairConfig {
    @Bean
    public FlywayMigrationStrategy repairFlywayStrategy() {
        return flyway -> {
            flyway.repair();
            flyway.migrate();
        };
    }
}
