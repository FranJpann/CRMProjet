package virtualcrm.service;

import org.json.JSONArray;
import org.json.JSONObject;
import virtualcrm.model.GeographicPointDto;
import virtualcrm.model.VirtualLeadDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GeolocalisationImpl implements GeolocalisationService{

    /*  GeolocalisationImpl    */
    /*  Implémentation du service de géolocalisation */

    @Override
    public GeographicPointDto GETRequestToOpenStreetMap(String query) {

        String url = "https://nominatim.openstreetmap.org/search?" + query;

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

            JSONArray array = new JSONArray(response.toString());
            Double lat = null;
            Double lon = null;

            if(!array.isNull(0)){
                JSONObject object = new JSONObject(array.get(0).toString());
                lat = object.isNull("lat") ? null : Double.valueOf(object.getString("lat"));
                lon = object.isNull("lon") ? null : Double.valueOf(object.getString("lon"));
            }

            return new GeographicPointDto(lat, lon);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setGeolocalisation(List<VirtualLeadDto> virtualLeads) {
        String query;
        for(VirtualLeadDto virtualLead: virtualLeads){
            query = "city="+virtualLead.getCity().replaceAll(" ", "+")+
                    "&country="+virtualLead.getCountry().replaceAll(" ", "+")+
                    "&postalcode="+virtualLead.getPostalCode().replaceAll(" ", "+")+
                    "&street="+virtualLead.getStreet().replaceAll(" ", "+")+
                    "&format=json&limit=1";
            virtualLead.setGeographicPointDto(GETRequestToOpenStreetMap(query));
        }
    }
}
