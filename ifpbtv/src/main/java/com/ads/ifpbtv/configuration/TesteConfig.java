package com.ads.ifpbtv.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ads.ifpbtv.service.DBService;

@Configuration
public class TesteConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() {
		
		dbService.instantiateTestDatabase();
		return true;
	}
}
