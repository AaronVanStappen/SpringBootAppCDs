package be.qnh.hobby.cds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.HashMap;
import java.util.Map;

@EnableScheduling
@SpringBootApplication
public class HobbyApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(HobbyApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(HobbyApplication.class, args);
		LOGGER.info("Spring Boot started");
	}
}
