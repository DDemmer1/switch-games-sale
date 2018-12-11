package de.demmer.dennis.switchgames.model.scraper.nintendo;

import de.demmer.dennis.switchgames.model.scraper.nintendo.de.NintendoScraperGermany;
import de.demmer.dennis.switchgames.model.scraper.nintendo.us.NintendoScraperUS;

import java.util.Locale;

public class NintendoScraperFactory{

    public NintendoScraper getScraper(Locale locale){

        if(locale.equals(Locale.GERMANY)){
            return new NintendoScraperGermany();
        } else if(locale.equals(Locale.US)){
            return new NintendoScraperUS();
        }

        else throw new IllegalArgumentException("Non existent NintendoScraper for locale: "+ locale.toString());

    }



}
