package com.ishaque.celebratio.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseConfig {
    @Bean
    public JdbcTemplate jdJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
