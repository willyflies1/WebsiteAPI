package com.api.website;

import com.api.website.repositories.UserRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(WebsiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		String sql = "INSERT INTO users ( firstName, lastName, email, password ) VALUES ("
//				+ "'Maxxon', 'Jacques', 'max@email.com', 'max123')";
//		int rows = jdbcTemplate.update(sql);
//		if(rows > 0){
//			System.out.println("A new row has been inserted to 'users'.");
//		}
//		List<User> users = userRepository.listAll();
//		users.forEach(user -> {
//			System.out.println(user);
//		});
	}

	@Bean
	public ModelMapper modelMapper() {
		return  new ModelMapper();
	}

}
