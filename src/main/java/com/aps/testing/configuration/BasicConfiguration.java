package com.aps.testing.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("nifi")
@Data
public class BasicConfiguration {
	private String envName;
	private String server;
	private String user;
	private String secrets;
	private String path;

}
