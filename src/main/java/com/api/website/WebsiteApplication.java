package com.api.website;

import com.api.website.repository.AwsCloudFilesRepository;
import com.api.website.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")	// http://localhost:4200
@SpringBootApplication(scanBasePackages = {"com.api.website"})
public class WebsiteApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AwsCloudFilesRepository awsCloudFilesRepository;

	@Autowired
	private UserRepository userRepository;

	@Bean
	public ModelMapper modelMapper() {
		return  new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	public static void main(String[] args) {
		SpringApplication.run(WebsiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

//	@RequestMapping("/token")
//	public Map<String,String> token(HttpSession session){
//		return Collections.singletonMap("token", session.getId());
//	}


}
