package com.api.website;

import com.api.website.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class WebsiteApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserRepository userRepository;

	@Bean
	public ModelMapper modelMapper() {
		return  new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(WebsiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}


}
