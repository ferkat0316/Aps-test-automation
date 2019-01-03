package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ApsTestAutomationApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(ApsTestAutomationApplication.class, args);

		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}

	@Profile("aws")
	@Bean
	public String awsBean() {
		return "aws";
	}

	@Profile("sit")
	@Bean
	public String sitBean() {
		return "sit";
	}
}
