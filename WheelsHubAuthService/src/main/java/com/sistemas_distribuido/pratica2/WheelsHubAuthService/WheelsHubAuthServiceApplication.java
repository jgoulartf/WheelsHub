package com.sistemas_distribuido.pratica2.WheelsHubAuthService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// com essa anotação executa o servidor, mas dá not found nos controller
// se tirar da erro de injeçao de dep
@ComponentScan("com.sistemas_distribuido.pratica2.WheelsHubAuthService.repository")
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class WheelsHubAuthServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(WheelsHubAuthServiceApplication.class, args);
	}
	@RestController
	public static class TestController {
		@GetMapping("/test")
		public String testEndpoint() {
			return "Test Endpoint is working!";
		}
	}

}
