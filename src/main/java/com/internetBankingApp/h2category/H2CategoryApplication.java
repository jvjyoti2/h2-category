package com.internetBankingApp.h2category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class H2CategoryApplication {

	private static final Logger logger = LoggerFactory.getLogger(H2CategoryApplication.class);

	public static void main(String[] args) {
		logger.info("H2CategoryApplication started.");
		SpringApplication.run(H2CategoryApplication.class, args);
	}

}
