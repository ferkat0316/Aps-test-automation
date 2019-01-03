package com.aps.testing.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("jdbc")
@Data
public class DbConfiguration {
	private String driverClassName;
	private String url;
	private String userName;
	private String password;
	
}
