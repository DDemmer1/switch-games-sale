package de.demmer.dennis.switchgames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SwitchGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwitchGamesApplication.class, args);
	}
}
