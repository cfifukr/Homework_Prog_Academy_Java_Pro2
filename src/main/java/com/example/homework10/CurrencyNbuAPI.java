package com.example.homework10;


import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.propertyeditors.CurrencyEditor;

import java.util.Arrays;


public class CurrencyNbuAPI {

    private static final String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static Gson gson = new Gson();


    public static String getJsonNBU(String code){
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url + "&valcode=" + code.toLowerCase());

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {

                String jsonResponse = EntityUtils.toString(entity);

                return jsonResponse;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("No json response");
        return null;
    }

    public static Currency[] getCurrencyNBU(String code){
        String json = getJsonNBU(code);
        return gson.fromJson(json, Currency[].class);
    }


}
