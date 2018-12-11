package de.demmer.dennis.switchgames.scraperTest;

import de.demmer.dennis.switchgames.model.scraper.Updater;
import org.junit.Test;

public class UpdaterTest {



    @Test
    public void shouldUpdateDB(){

        Updater updater = new Updater();
        updater.updateJSONdb();
    }

}
