package com.example.iidea8.k_lit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Abhigyan on 7/28/2015.
 */
public class HttpManager {
    public static String getData(String uri){
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
            return result;
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
