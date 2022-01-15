package com.api.website;

import com.api.website.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@CrossOrigin(origins="*")	// http://localhost:4200
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

	@RequestMapping("/token")
	public Map<String,String> token(HttpSession session){
		return Collections.singletonMap("token", session.getId());
	}


}
