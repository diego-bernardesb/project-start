package com.tecnologydbb.appstartAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//TODO: configuracao para ignorar autenticacao do spring security
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AppStartApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppStartApiApplication.class, args);
	}

}
