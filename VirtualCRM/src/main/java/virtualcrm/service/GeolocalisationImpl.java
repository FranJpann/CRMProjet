package virtualcrm.service;

import virtualcrm.model.GeographicPointDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeolocalisationImpl implements GeolocalisationService{
    @Override
    public GeographicPointDto GETRequestToOpenStreetMap(String query) {

        String url = "https://nominatim.openstreetmap.org/search?";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            System.out.println(response);
            return null;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
