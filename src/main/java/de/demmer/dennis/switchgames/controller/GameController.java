package de.demmer.dennis.switchgames.controller;

import com.google.gson.Gson;
import de.demmer.dennis.switchgames.model.scraper.GameListParser;
import de.demmer.dennis.switchgames.model.scraper.Updater;
import de.demmer.dennis.switchgames.model.scraper.nintendo.Game;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class GameController {

    private String dbNaming = LocalDate.now().toString() + "-germany.json";

    @GetMapping(value = "/")
    public String home(ModelMap model) {
        model.addAttribute("games", GameListParser.parseGameList());
        return "games";
    }


    @GetMapping(value = "/price")
    public String sortedAfterPrice(ModelMap model) {

        List<Game> sortedAfterPrice = GameListParser.parseGameList();
        Collections.sort(sortedAfterPrice, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {

                if(o1.getPrice()>o2.getPrice()){
                    return 1;
                } else if (o1.getPrice() == o2.getPrice()){
                    return 0;
                } else{
                    return -1;
                }
            }
        });
        model.addAttribute("games", sortedAfterPrice);
        return "games";
    }



    @GetMapping(value = "/percentage")
    public String sortedAfterPercentage(ModelMap model) {

        List<Game> sortedAfterPercentage = GameListParser.parseGameList();
        Collections.sort(sortedAfterPercentage, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o2.getPriceDiscountpercentage()-o1.getPriceDiscountpercentage();
            }
        });
        model.addAttribute("games", sortedAfterPercentage);
        return "games";
    }





}
