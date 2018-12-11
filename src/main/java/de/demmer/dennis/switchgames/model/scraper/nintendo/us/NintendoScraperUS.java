package de.demmer.dennis.switchgames.model.scraper.nintendo.us;

import de.demmer.dennis.switchgames.model.scraper.nintendo.Game;
import de.demmer.dennis.switchgames.model.scraper.nintendo.NintendoScraper;

import java.util.List;

public class NintendoScraperUS implements NintendoScraper {

    //URL US: https://www.nintendo.com/json/content/get/filter/game?system=switch&sort=title&direction=asc&limit=200&sale=true

    @Override
    public List<Game> getCurrentSales() {
        return null;
    }
}
