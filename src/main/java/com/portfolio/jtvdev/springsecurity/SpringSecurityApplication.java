package com.portfolio.jtvdev.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class SpringSecurityApplication {
// https://www.youtube.com/watch?v=xnGJchfeI88
// https://github.com/SaulM12/LastSpringSecurityJWT
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
