package de.demmer.dennis.switchgames.scraperTest;

import de.demmer.dennis.switchgames.model.scraper.nintendo.Game;
import de.demmer.dennis.switchgames.model.scraper.nintendo.NintendoScraper;
import de.demmer.dennis.switchgames.model.scraper.nintendo.NintendoScraperFactory;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;


public class NintendoScraperTest {

    @Test
    public void nintendoScraperTestGermany(){

        NintendoScraperFactory nsFactory = new NintendoScraperFactory();
        NintendoScraper germanNintendoScraper = nsFactory.getScraper(Locale.GERMANY);

        List<Game> germanNintendoSales = germanNintendoScraper.getCurrentSales();

        assertNotNull("List of current sales should not be null", germanNintendoSales);

        for (Game obj: germanNintendoSales) {
            System.out.println(obj.toString());
        }

    }


}
