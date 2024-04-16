package com.sistemas_distribuido.pratica2.WheelsHubGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class WheelsHubGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WheelsHubGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder.
				routes()
					.route(r -> r.path("/api/carros/**").uri("lb://WheelsHubDataService0"))
					.route(r -> r.path("/api/clientes/**").uri("lb://WheelsHubDataService0"))
					.route(r -> r.path("/api/funcionarios/**").uri("lb://WheelsHubDataService0"))
				.build();
	}
}
