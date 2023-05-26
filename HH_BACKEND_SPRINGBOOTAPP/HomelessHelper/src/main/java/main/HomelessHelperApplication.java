package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = { "controller", "dao", "service", "model" })
@EntityScan(basePackages = { "model" })
@EnableJpaRepositories(basePackages = { "dao", "controller", "service", "model" })
@SpringBootApplication
public class HomelessHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomelessHelperApplication.class, args);
	}
}
