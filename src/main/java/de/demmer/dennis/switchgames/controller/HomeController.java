/*
 * Copyright 2015 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.demmer.dennis.switchgames.controller;

import com.google.gson.Gson;
import de.demmer.dennis.switchgames.Record;
import de.demmer.dennis.switchgames.model.database.RecordRepository;
import de.demmer.dennis.switchgames.model.scraper.Updater;
import de.demmer.dennis.switchgames.model.scraper.nintendo.Game;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@Controller
@RequestMapping("/")
public class HomeController {


    private String dbNaming = "src\\main\\resources\\json\\germany\\" + LocalDate.now().toString() + "-germany.json";


    public String home(ModelMap model) {
        Updater updater = new Updater();
        updater.updateJSONdb();

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

        model.addAttribute("games", gameList);


        return "games";


    }


}











//    private RecordRepository repository;

//    @Autowired
//    public HomeController(RecordRepository repository) {
//        this.repository = repository;
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String home(ModelMap model) {
//        List<Record> records = repository.findAll();
//        model.addAttribute("records", records);
//        model.addAttribute("insertRecord", new Record());
//        return "home";
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public String insertData(ModelMap model,
//                             @ModelAttribute("insertRecord") @Valid Record record,
//                             BindingResult result) {
//        if (!result.hasErrors()) {
//            repository.save(record);
//        }
//        return home(model);
//    }
//}
