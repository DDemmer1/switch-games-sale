package de.demmer.dennis.switchgames.model.scraper;

import com.google.gson.Gson;
import de.demmer.dennis.switchgames.model.scraper.nintendo.Game;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameListParser {

    private static String dbNaming = LocalDate.now().toString() + "-germany.json";

    public static List<Game> parseGameList(){

        StringBuffer sb = null;
        try {
            FileReader fr = new FileReader(dbNaming);
            BufferedReader br = new BufferedReader(fr);
            sb = new StringBuffer();

            String line = "";
            while( (line = br.readLine()) != null){
                sb.append(line);
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Game> gameList = new ArrayList<>();

        try {
            JSONArray arr = new JSONArray(sb.toString());
            Gson gson = new Gson();
            for (int i = 0; i < arr.length(); i++) {

                Game game = gson.fromJson(arr.get(i).toString(),Game.class);
                gameList.add(game);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return gameList;
    }
}
