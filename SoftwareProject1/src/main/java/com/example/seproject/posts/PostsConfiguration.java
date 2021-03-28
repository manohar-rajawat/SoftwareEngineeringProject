package com.example.seproject.posts;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * The accounts Spring configuration.
 * 
 * @author Paul Chapman
 */
@Configuration
@ComponentScan
@EntityScan("com.example.seproject.posts")
@EnableJpaRepositories("com.example.seproject.posts")
@PropertySource("classpath:db-config.properties")
public class PostsConfiguration {

	protected Logger logger;

	public PostsConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// accounts.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schemapost.sql")
		 				.build();

		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> posts = jdbcTemplate.queryForList("SELECT * FROM T_POST");
		logger.info("System has " + posts.size() + " posts");

		return dataSource;
	}
}