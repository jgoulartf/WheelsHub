package com.sistemas_distribuidos.pratica2.net_wheels_hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class WheelsHubDataService3 {

	public static void main(String[] args) {
		SpringApplication.run(WheelsHubDataService3.class, args);
	}
	
}
