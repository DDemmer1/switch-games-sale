package de.demmer.dennis.switchgames.scraperTest;

import com.google.gson.Gson;
import de.demmer.dennis.switchgames.model.scraper.metacritic.MetacriticScraper;
import de.demmer.dennis.switchgames.model.scraper.nintendo.Game;
import de.demmer.dennis.switchgames.model.scraper.nintendo.NintendoScraper;
import de.demmer.dennis.switchgames.model.scraper.nintendo.NintendoScraperFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class GameMergerTest {

    @Test
    public void testGameMerge() throws IOException {

        NintendoScraperFactory nsFactory = new NintendoScraperFactory();
        NintendoScraper germanNintendoScraper = nsFactory.getScraper(Locale.GERMANY);

        List<Game> germanGameSales = germanNintendoScraper.getCurrentSales();

        MetacriticScraper mcs = new MetacriticScraper();

        int numberOf404 = 0;
        int numberOftbd = 0;
        for (Game game: germanGameSales) {
            String mc = mcs.getMetacritic(game.getTitle());
            game.setMetacriticScore(mc);
            if(mc.equals("-1")){
                numberOf404++;
            } else if(mc.equals("0")){
                numberOftbd++;
            }
        }

        String json = new Gson().toJson(germanGameSales);

        try {
            File currentJSONFile = new File("src\\main\\resources\\json\\germany\\" + LocalDate.now().toString() + "-list.json");
            if (!currentJSONFile.exists()) {
                FileWriter fw = new FileWriter(currentJSONFile);
                fw.write(json);
                fw.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        System.out.println("***********");
        System.out.println("not found: " + numberOf404 + "/"+germanGameSales.size());
        System.out.println("tbd: " + numberOftbd + "/"+germanGameSales.size());

        Collections.sort(germanGameSales, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return Integer.valueOf(o2.getMetacriticScore()) - Integer.valueOf(o1.getMetacriticScore());
            }
        });


        for (Game game :germanGameSales) {
            System.out.println(game.getTitle()+ ": " + game.getMetacriticScore());
        }



    }
}
