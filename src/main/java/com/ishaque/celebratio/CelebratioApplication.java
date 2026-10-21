package com.ishaque.celebratio;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.java.Log;

@Log

@SpringBootApplication
public class CelebratioApplication implements CommandLineRunner {
	final DataSource dataSource;

	public CelebratioApplication(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(CelebratioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("JDBC" + dataSource.toString());

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("SELECT 1");

	}

}
