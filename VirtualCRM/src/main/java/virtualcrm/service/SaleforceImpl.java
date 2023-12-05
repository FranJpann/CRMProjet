package virtualcrm.service;

import org.json.JSONArray;
import org.json.JSONObject;
import virtualcrm.model.GeographicPointDto;
import virtualcrm.model.VirtualLeadDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SaleforceImpl implements CRMService {

    @Override
    public List<VirtualLeadDto> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) {
        // String query = "SELECT+FirstName,LastName,AnnualRevenue,Phone,Address,Company+FROM+Lead+WHERE+AnnualRevenue+>=+"+lowAnnualRevenue+"+AND+AnnualRevenue+<=+"+highAnnualRevenue;
        // Fake query because saleforce provide data with null AnnualRevenue (right query is above)
        String query = "SELECT+FirstName,LastName,AnnualRevenue,Phone,Address,Company+FROM+Lead";
        String response = sendGETRequest(query);

        JSONObject responseJSON = new JSONObject(response);
        JSONArray records = new JSONArray(responseJSON.get("records").toString());

        List<VirtualLeadDto> virtualLeads = new ArrayList<>();

        for(Object recordObj: records) {
            JSONObject recordJSON = new JSONObject(recordObj.toString());
            JSONObject address = new JSONObject(recordJSON.getJSONObject("Address"));

            virtualLeads.add(new VirtualLeadDto(
                    recordJSON.getString("FirstName"),
                    recordJSON.getString("LastName"),
                    recordJSON.getDouble("AnnualRevenue"),
                    recordJSON.getString("Phone"),
                    address.getString("street"),
                    address.getString("postalCode"),
                    address.getString("city"),
                    address.getString("country"),
                    null,
                    new GeographicPointDto(address.getDouble("latitude"), address.getDouble("longitude")),
                    recordJSON.getString("Company"),
                    address.getString("state")
            ));
        }

        return virtualLeads;
    }

    @Override
    public List<VirtualLeadDto> findLeadsByDate(String startDate, String endDate) {
        String query = "";
        String response = sendGETRequest(query);

        System.out.println(response);
        return null;
    }

    private String sendGETRequest(String query) {

        final String token = "Bearer 00D07000000YPGQ!AR4AQMJIDt7XN2KWJQhvroDscGQTOcDzbAaetatv78x1JxOytzfA6xNo6nfo87OSME9npBsvUWtdEbqnUXw3JUYqNzxxqQfn";
        final String endpoint = "https://univangers-dev-ed.develop.my.salesforce.com/services/data/v45.0/query/";
        final String url = endpoint + "?q=" + query;

        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", token);
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            return response.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
