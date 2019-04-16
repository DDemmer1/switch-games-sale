package de.demmer.dennis.switchgames.model.scraper.metacritic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetacriticScraper {

    public int counter404 = 0;

    public String getMetacritic(String gameName) throws IOException {

        System.out.println("GameName: " + gameName);
        String query = normalizeQuery(gameName);
        System.out.println("Query: " + query);

        URL url = new URL("https://www.metacritic.com/game/switch/" + query);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

        int status = con.getResponseCode();
        System.out.println("Status: " + status);
        if(status== 404){
            counter404++;
            return "-1";
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        String siteContent = content.toString();
        Document doc = Jsoup.parse(siteContent);

        String mcScore = getMCScore(doc,query);

        return mcScore;
    }

    public String getMetacriticURL(String gameName){


        return "https://www.metacritic.com/game/switch/"+normalizeQuery(gameName);

    }

    private String getMCScore(Document doc, String query){

        for (Element e: doc.body().getElementsByAttribute("itemprop")) {
            String itempropVal = e.attr("itemprop").toString();

            if(itempropVal.equals("ratingValue")){
                System.out.println("Rating for " + query  + ": " + e.text());
                return e.text();
            }
        }

        return "0";
    }



    public String normalizeQuery(String query){

        Pattern pattern = Pattern.compile("[A-Z][.]");
        Matcher matcher = pattern.matcher(query);

        while (matcher.find()){
//            System.out.println(matcher.start());
//            System.out.println(matcher.end());
//            System.out.println(query.substring(matcher.start(),matcher.end()));
            StringBuilder stringBuilder = new StringBuilder(query);
            stringBuilder.replace(matcher.start(),matcher.start()+1,(query.charAt(matcher.start())+"").toLowerCase());
            stringBuilder.replace(matcher.end()-1,matcher.end(),"");
            query = stringBuilder.toString();
//            System.out.println(query);
            matcher = pattern.matcher(query);

        }

        query = query.toLowerCase().replace("&", "-");
        query = query.toLowerCase().replace(" - ", "-");
        query = query.toLowerCase().replace(": ", "-");
        query = query.replace(". ", "-");
        query = query.replace(", ", "-");
        query = query.replace("'", "");
        query = query.replace(" ", "-");
        query = query.replace(".","-");
        query = query.replace("™","");
        query = query.replace("®","");
        query = query.replace(":","-");
        query = query.replace("?","");
        query = query.replace("ō","o");

        return query;
    }





}
