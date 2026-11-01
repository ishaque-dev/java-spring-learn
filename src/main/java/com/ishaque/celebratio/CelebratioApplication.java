package com.ishaque.celebratio;

import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.java.Log;

@Log

@SpringBootApplication
public class CelebratioApplication {
	final DataSource dataSource;

	public CelebratioApplication(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SpringApplication.run(CelebratioApplication.class, args);
	}

}
