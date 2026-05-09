import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherFetcher {
    private static final String API_KEY = "YOUR_API_KEY";  // <-- Replace with your key

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String city = sc.nextLine();
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) response.append(line);
            reader.close();
            String json = response.toString();
            String temp = extractValue(json, "\"temp\":", ",");
            String humidity = extractValue(json, "\"humidity\":", ",");
            String description = extractValue(json, "\"description\":\"", "\"");
            System.out.println("City: " + city);
            System.out.println("Temperature: " + temp + "°C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Condition: " + description);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }

    private static String extractValue(String json, String key, String delimiter) {
        int start = json.indexOf(key);
        if (start == -1) return "N/A";
        start += key.length();
        if (delimiter.equals(",")) {
            int end = json.indexOf(",", start);
            return json.substring(start, end).replace("\"", "").trim();
        } else {
            int end = json.indexOf(delimiter, start);
            return json.substring(start, end);
        }
    }
}