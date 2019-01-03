package com.aps.testing.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jms.annotation.EnableJms;

import com.aps.testing.configuration.BasicConfiguration;
import com.aps.testing.configuration.DbConfiguration;

@Configuration
@EnableJms
@ComponentScan(basePackages = { "com.*" })
@PropertySource(value = { "classpath:application-aws.properties" })
public class DbConfig {

	@Autowired
	private DbConfiguration dbConfig;

	@Autowired
	private BasicConfiguration baseConfig;

	@Bean
	@Primary
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbConfig.getDriverClassName());
		dataSource.setUrl(dbConfig.getUrl());
		dataSource.setUsername(dbConfig.getUserName());
		dataSource.setPassword(dbConfig.getPassword());
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		System.out.println("Connecting to data base in: " + baseConfig.getEnvName() + " environment.");
		return jdbcTemplate;
	}
}