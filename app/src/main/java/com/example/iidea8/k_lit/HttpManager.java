package com.example.iidea8.k_lit;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Abhigyan on 7/28/2015.
 */
public class HttpManager {
    public static JSONArray getData(String uri){
        BufferedReader reader = null;
        String result;
        try {
            URL url= new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = reader.readLine())!=null){
                sb.append(line).append("\n");
            }
            result = sb.toString();
            return new JSONArray(result);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if (reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
