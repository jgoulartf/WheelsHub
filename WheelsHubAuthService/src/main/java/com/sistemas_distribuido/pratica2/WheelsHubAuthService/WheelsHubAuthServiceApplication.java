package com.sistemas_distribuido.pratica2.WheelsHubAuthService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class WheelsHubAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WheelsHubAuthServiceApplication.class, args);
	}

}
