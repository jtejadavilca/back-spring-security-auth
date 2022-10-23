package com.portfolio.jtvdev.springsecurity;

import com.portfolio.jtvdev.springsecurity.application.port.out.RolePort;
import com.portfolio.jtvdev.springsecurity.application.shared.Roles;
import com.portfolio.jtvdev.springsecurity.infrastructure.db.entity.RoleEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {
// https://www.youtube.com/watch?v=xnGJchfeI88
// https://github.com/SaulM12/LastSpringSecurityJWT
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Autowired
	private RolePort rolePort;

	@Override
	public void run(String... args) throws Exception {
		var roles = this.rolePort.findAll();
		if(roles.isEmpty()) {
			this.rolePort.save(new RoleEntity(Roles.ROLE_USER));
			this.rolePort.save(new RoleEntity(Roles.ROLE_ADMIN));
		}
	}
}
