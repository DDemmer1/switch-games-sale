package de.demmer.dennis.switchgames.scraperTest;

import de.demmer.dennis.switchgames.model.scraper.metacritic.MetacriticScraper;
import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

public class MetacriticScraperTest {


    @Test
    public void testMCScraper(){


        try {
            MetacriticScraper mcs = new MetacriticScraper();
            String mc = mcs.getMetacritic("OVERCOOKED! 2: SURF 'N' TURF");


            assertEquals("Score has to be: 83",83,mc);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
