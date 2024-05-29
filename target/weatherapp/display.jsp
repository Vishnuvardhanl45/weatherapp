<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Weather App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        .container {
            max-width: 400px;
            margin: 0 auto;
            text-align: center;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.5);
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            margin-top: 125px;
        }
        h1 {
            font-size: 24px;
        }
        .weather-info {
            text-align: center;
            margin-top: 20px;
        }
        .weather-info h2 {
            font-size: 24px;
            margin-bottom: 10px;
        }
        .weather-info p {
            font-size: 18px;
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 style="color: chocolate;">Weather App</h1>
        <div class="weather-info">
            <h2>${city}, ${country}</h2>
			<br>
            <p>${localtime}</p>
			<br>
            <p>Temperature: ${temp_c}°C</p>
			<br>
            <p>Humidity: ${humidity}%</p>
			<br>
            <p>Wind Speed: ${wind_kph} kph</p>
			<br>
            <p>Condition: ${condition}</p>
        </div>
    </div>
</body>
</html>
