package de.demmer.dennis.switchgames.model.scraper.nintendo.de;

import de.demmer.dennis.switchgames.model.scraper.nintendo.Game;
import de.demmer.dennis.switchgames.model.scraper.nintendo.NintendoScraper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NintendoScraperGermany implements NintendoScraper {


    @Override
    public List<Game> getCurrentSales() {

        List<Game> games = new ArrayList<>();

        JSONObject obj = null;
        try {
            obj = getJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray arr = obj.getJSONObject("response").getJSONArray("docs");

        for (int i = 0; i < arr.length(); i++) {

            String title = arr.getJSONObject(i).getString("title");
            String publisher = arr.getJSONObject(i).getString("publisher");
            String url = arr.getJSONObject(i).getString("url");
            boolean nintendoClub = arr.getJSONObject(i).getBoolean("club_nintendo");
            String releaseDay = arr.getJSONObject(i).getString("pretty_date_s");
            String saleUntil = arr.getJSONObject(i).getString("change_date");
            String imageURL = arr.getJSONObject(i).getString("image_url_sq_s");
//            String priceDiscountpercentage = arr.getJSONObject(i).getString("price_discount_percentage_f");
            String priceDiscountpercentage = String.valueOf(arr.getJSONObject(i).getDouble("price_discount_percentage_f"));

            List<String> categories = new ArrayList<>();
            for (int j = 0; j < arr.getJSONObject(i).getJSONArray("pretty_game_categories_txt").length(); j++) {
                String categorie = arr.getJSONObject(i).getJSONArray("pretty_game_categories_txt").get(j).toString();
                categories.add(categorie);
            }

            boolean digitalVersion = arr.getJSONObject(i).getBoolean("digital_version_b");
            boolean physicalVersion = arr.getJSONObject(i).getBoolean("physical_version_b");

            String numberOfPlayers = arr.getJSONObject(i).getString("players_to");
            String ageRating = arr.getJSONObject(i).getString("pretty_agerating_s");
            String excerpt = arr.getJSONObject(i).getString("excerpt");
            float price = Float.valueOf(arr.getJSONObject(i).getString("price_lowest_f"));

            Game game = new Game(title,publisher,url,nintendoClub,releaseDay,saleUntil,imageURL,priceDiscountpercentage,categories,digitalVersion,physicalVersion,numberOfPlayers,ageRating,excerpt,price);

            games.add(game);
        }

        saveJSON(obj);

        return games;
    }

    private void saveJSON(JSONObject object) {
        try {
            File currentJSONFile = new File("src\\main\\resources\\json\\germany\\" + LocalDate.now().toString() + "-RAW.json");
            if (!currentJSONFile.exists()) {
                FileWriter fw = new FileWriter(currentJSONFile);
                fw.write(object.toString());
                fw.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private JSONObject getJson() throws IOException {


        String germanyURL = "http://search.nintendo-europe.com/de/select?q=*&sort=sorting_title%20asc&rows=100&wt=json&fq=type:GAME%20AND%20system_type:nintendoswitch*%20AND%20product_code_txt:*%20AND%20price_has_discount_b:true";

        URL url = new URL(germanyURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

        int status = con.getResponseCode();
        System.out.println("Status: " + status);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        String siteContent = content.toString();

        return new JSONObject(siteContent);
    }
}
