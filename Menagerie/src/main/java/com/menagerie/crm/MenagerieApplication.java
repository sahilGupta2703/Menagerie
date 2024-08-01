package com.menagerie.crm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MenagerieApplication {
	
	static Logger logger =LoggerFactory.getLogger(MenagerieApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MenagerieApplication.class, args);
		logger.info("application started");
	}

}
