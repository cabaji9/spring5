package org.hson.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.hson")
@EnableJpaRepositories("org.hson")
@EntityScan("org.hson")
public class AppConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppConfigApplication.class, args);
	}
}
