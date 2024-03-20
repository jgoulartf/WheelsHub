package com.sistemas_distribuido.pratica2.WheelsHubServiceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WheelsHubServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WheelsHubServiceRegistryApplication.class, args);
	}

}
