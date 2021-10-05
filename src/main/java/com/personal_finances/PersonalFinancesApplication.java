package com.personal_finances;

import com.personal_finances.service.LoginService;
import com.personal_finances.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class PersonalFinancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinancesApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(RoleService roleService, LoginService loginService){
		return args -> {
			roleService.save(new Role(null, "ROLE_USER"));
			roleService.save(new Role(null, "ROLE_MANAGER"));
			roleService.save(new Role(null, "ROLE_ADMIN"));
			roleService.save(new Role(null, "ROLE_SUPER_ADMIN"));

			loginService.save(new LoginUser(null, "ryam", "1234", new ArrayList<>()));
			loginService.save(new LoginUser(null, "sousa", "123", new ArrayList<>()));
			loginService.save(new LoginUser(null, "alves", "12", new ArrayList<>()));

			loginService.addRoleToLogin("ryam", "ROLE_SUPER_ADMIN");
			loginService.addRoleToLogin("ryam", "ROLE_ADMIN");
			loginService.addRoleToLogin("ryam", "ROLE_MANAGER");
			loginService.addRoleToLogin("ryam", "ROLE_USER");

			loginService.addRoleToLogin("alves", "ROLE_USER");
			loginService.addRoleToLogin("alves", "ROLE_ADMIN");

			loginService.addRoleToLogin("sousa", "ROLE_USER");
		};
	}
}
