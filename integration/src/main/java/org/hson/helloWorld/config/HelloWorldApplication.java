package org.hson.helloWorld.config;

import lombok.extern.slf4j.Slf4j;
import org.hson.helloWorld.integration.FileWriterGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;

@Slf4j
@SpringBootApplication(scanBasePackages = "org.hson")
@EnableJpaRepositories("org.hson")
@EntityScan("org.hson")
@IntegrationComponentScan("org.hson")
public class HelloWorldApplication {

	public static void main(String[] args) {


		ConfigurableApplicationContext ctx = SpringApplication.run(HelloWorldApplication.class, args);

		FileWriterGateway fileWriterGateway = ctx.getBean(FileWriterGateway.class);


	fileWriterGateway.writeToFile("lala","uno");
	}
}
