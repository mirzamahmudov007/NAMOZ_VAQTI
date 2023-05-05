package com.example.demo.service;

import lombok.Value;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;
@Service
public class TemperatureService {

        private final String apiKey = "b28bf1739b8c0e84a536751ae4ac1300";
        private final String apiUrlPattern = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric";

        public String getTemperature(double lat,double lon) throws IOException {
            String apiUrl = String.format(apiUrlPattern, lat, lon, apiKey);
            URL url = new URL(apiUrl);
            InputStream inputStream = url.openConnection().getInputStream();
            String json = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
            JSONObject jsonObject = new JSONObject(json);
            double temperature = jsonObject.getJSONObject(apiKey).getDouble(apiKey);
            return "The temperature in your location is " + temperature + "C";
        }
    }

