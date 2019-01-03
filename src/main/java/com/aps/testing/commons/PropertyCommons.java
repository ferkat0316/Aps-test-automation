package com.aps.testing.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ComponentScan(basePackages = { "com.*" })
@PropertySource(value = { "classpath:application-aws.properties" })
public class PropertyCommons {

	@Value("jdbc.driverClassName")
	private String jdbcDriverClassName;

	private String jdbcUrl;
	private String jdbcuserName;
	private String jdbcPassword;
}
