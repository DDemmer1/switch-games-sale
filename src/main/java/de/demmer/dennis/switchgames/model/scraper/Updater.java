package de.demmer.dennis.switchgames.model.scraper;

import com.google.gson.Gson;
import de.demmer.dennis.switchgames.model.scraper.metacritic.MetacriticScraper;
import de.demmer.dennis.switchgames.model.scraper.nintendo.Game;
import de.demmer.dennis.switchgames.model.scraper.nintendo.NintendoScraper;
import de.demmer.dennis.switchgames.model.scraper.nintendo.NintendoScraperFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Component
public class Updater {

//    private String dbNaming = "src/main/resources/json/germany/" + LocalDate.now().toString() + "-germany.json";
    private String dbNaming = LocalDate.now().toString() + "-germany.json";



    @Scheduled(fixedDelay = 1000*60*10)
    public void updateJSONdb(){
        if (isUpToDate()){
            System.out.println("DB is up to date");
            return;
        }

        //start Scrapers
        NintendoScraperFactory nsFactory = new NintendoScraperFactory();
        NintendoScraper germanNintendoScraper = nsFactory.getScraper(Locale.GERMANY);


        List<Game> germanGameSales = germanNintendoScraper.getCurrentSales();

        MetacriticScraper mcs = new MetacriticScraper();

        for (Game game: germanGameSales) {
            String mc = null;
            try {
                mc = mcs.getMetacritic(game.getTitle());
            } catch (IOException e) {
                e.printStackTrace();
            }
            game.setMetacriticScore(mc);
            game.setMetacriticURL(mcs.getMetacriticURL(game.getTitle()));
        }


        //sort by mc score
        Collections.sort(germanGameSales, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return Integer.valueOf(o2.getMetacriticScore()) - Integer.valueOf(o1.getMetacriticScore());
            }
        });



        //Transform to JSON
        String json = new Gson().toJson(germanGameSales);

        try {
            File currentJSONFile = new File(dbNaming);
            if (!currentJSONFile.exists()) {
                FileWriter fw = new FileWriter(currentJSONFile);
                fw.write(json);
                fw.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("404's: " + mcs.counter404);
        System.err.println("DB UPDATED");

    }


    private boolean isUpToDate() {
        File currentJSONFile = new File(dbNaming);

        System.out.println(currentJSONFile.getAbsolutePath());

        return currentJSONFile.exists();
    }
}
