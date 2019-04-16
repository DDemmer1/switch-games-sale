package de.demmer.dennis.switchgames.scraperTest;

import de.demmer.dennis.switchgames.model.scraper.GameListParser;
import de.demmer.dennis.switchgames.model.scraper.metacritic.MetacriticScraper;
import de.demmer.dennis.switchgames.model.scraper.nintendo.Game;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QueryTest {

    @Test
    public void testQueryNormalization(){

        MetacriticScraper metacriticScraper = new MetacriticScraper();

        System.out.println(metacriticScraper.normalizeQuery("EA SPORTS™ FIFA 19"));
        System.out.println(metacriticScraper.normalizeQuery("L.A. Noire"));

    }

    @Test
    public void getAll404s(){

        List<Game> gameList = GameListParser.parseGameList();

        List<Game> games404 = new ArrayList<>();
        for (Game game: gameList) {
            if(game.getMetacriticScore() == -1){
                games404.add(game);
            }
        }

        List<String> gameTitel = new ArrayList<>();

        System.out.println("404's: " + games404.size());
        games404.forEach(game -> System.out.println(game.getTitle()));
        System.out.println("************");
        games404.forEach(game -> gameTitel.add(game.getTitle()));

        MetacriticScraper metacriticScraper = new MetacriticScraper();

        List<String> normalizedGameTitle = new ArrayList<>();
        gameTitel.forEach(gameTitle -> normalizedGameTitle.add(metacriticScraper.normalizeQuery(gameTitle)));

        normalizedGameTitle.forEach(game -> System.out.println(game));

//88-heroes-98-heroes-edition
//        holy-potatoes!-a-weapon-shop!

    }


    @Test
    public void printMeta(){


        for ( Game game:GameListParser.parseGameList()) {
            System.out.println(game.getTitle()+ ": " + game.getMetacriticScore());
        }

    }


}
