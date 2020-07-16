package fr.modcraftmc.modcraftvote.utils;

import com.google.gson.Gson;
import fr.modcraftmc.modcraftvote.vote.VoteResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public static Gson gson = new Gson();

    public static String api = "https://serveur-prive.net/api/vote/json/1273/%s";

    public static VoteResponse sendRequest(String ip) {

        HttpURLConnection connection = null;
        System.out.println(String.format(api, ip));

        try {
            //Create connection
            URL url = new URL(String.format(api, ip));
            connection = (HttpURLConnection) url.openConnection();


            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
            System.out.println(response.toString());

            connection.disconnect();
            return gson.fromJson(response.toString(), VoteResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
