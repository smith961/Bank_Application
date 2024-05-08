package com.example.BankApplicationDevelopment;

import com.example.BankApplicationDevelopment.model.AccountUser;
import com.example.BankApplicationDevelopment.model.Role;
import com.example.BankApplicationDevelopment.service.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableAsync
@SpringBootApplication
public class BankApplicationDevelopmentApplication implements CommandLineRunner {
	@Autowired
	private AccountUserService accountUserService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BankApplicationDevelopmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AccountUser adminUser = new AccountUser();
		adminUser.setFirstName("Admin");
		adminUser.setLastName("Admin");
		adminUser.setRole(Role.ADMIN);
		adminUser.setUsername("admin@gmail.com");
		adminUser.setPassword(passwordEncoder.encode("password"));

		System.out.println(adminUser);

		AccountUser exist = accountUserService.getByUsername("admin@gmail.com").getBody();

		if( exist == null ){
			accountUserService.postAccountUser(adminUser);
		}

	}


}
