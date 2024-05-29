package com.weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String API_KEY = "1a5ed811c2314e94b0f91534242305";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the index.jsp page
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the city name from the form input
        String city = request.getParameter("city");

        String urlString = BASE_URL + "?key=" + API_KEY + "&q=" + city;

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        reader.close();

        JSONObject jsonObject = new JSONObject(responseContent.toString());
        JSONObject location = jsonObject.getJSONObject("location");
        JSONObject current = jsonObject.getJSONObject("current");

        String cityName = location.getString("name");
        String country = location.getString("country");
        String region = location.getString("region");
        String localtime = location.getString("localtime");
        double temp_c = current.getDouble("temp_c");
        int humidity = current.getInt("humidity");
        double wind_kph = current.getDouble("wind_kph");

        String condition;
        Object conditionObj = current.get("condition");
        if (conditionObj instanceof JSONObject) {
            condition = ((JSONObject) conditionObj).getString("text");
        } else {
            condition = conditionObj.toString();
        }

        request.setAttribute("city", cityName);
        request.setAttribute("country", country);
        request.setAttribute("region", region);
        request.setAttribute("localtime", localtime);
        request.setAttribute("temp_c", temp_c);
        request.setAttribute("humidity", humidity);
        request.setAttribute("wind_kph", wind_kph);
        request.setAttribute("condition", condition);

        // Forward the request to the display.jsp page for rendering
        request.getRequestDispatcher("display.jsp").forward(request, response);

        connection.disconnect();
    }
}
