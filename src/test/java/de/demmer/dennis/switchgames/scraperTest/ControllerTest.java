package de.demmer.dennis.switchgames.scraperTest;

import com.google.gson.Gson;
import de.demmer.dennis.switchgames.model.scraper.Updater;
import de.demmer.dennis.switchgames.model.scraper.nintendo.Game;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControllerTest {


    private String dbNaming = "src\\main\\resources\\json\\germany\\" + LocalDate.now().toString() + "-germany.json";


    @Test
    public void shouldPrintGameTitles() {

        Updater updater = new Updater();
        updater.updateJSONdb();

        StringBuffer sb = null;
        try {
            FileReader fr = new FileReader(dbNaming);
            BufferedReader br = new BufferedReader(fr);
            sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONArray arr = new JSONArray(sb.toString());
            Gson gson = new Gson();
            for (int i = 0; i < arr.length(); i++) {

                Game game = gson.fromJson(arr.get(i).toString(),Game.class);
                System.out.println(game.getTitle());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
