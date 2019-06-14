package de.demmer.dennis.switchgames;

import de.demmer.dennis.switchgames.model.scraper.Updater;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        Updater updater = new Updater();
//        updater.updateJSONdb();
    }

}
