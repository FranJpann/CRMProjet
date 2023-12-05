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
            String FirstName = recordJSON.isNull("FirstName") ? "" : recordJSON.getString("FirstName");
            String LastName = recordJSON.isNull("LastName") ? "" : recordJSON.getString("LastName");
            Double AnnualRevenue = recordJSON.isNull("AnnualRevenue") ? null : recordJSON.getDouble("AnnualRevenue");
            String Phone = recordJSON.isNull("Phone") ? "" : recordJSON.getString("Phone");
            String Company = recordJSON.isNull("Company") ? "" : recordJSON.getString("Company");

            JSONObject address = recordJSON.getJSONObject("Address");
            String street = address.isNull("street") ? "" : address.getString("street");
            String postalCode = address.isNull("postalCode") ? "" : address.getString("postalCode");
            String city = address.isNull("city") ? "" : address.getString("city");
            String country = address.isNull("country") ? "" : address.getString("country");
            String stateJSON = address.isNull("state") ? "" : address.getString("state");

            // Pas de date de création dans saleforce
            String creationDate = null;

            Double latitude = address.isNull("latitude") ? null : address.getDouble("latitude");
            Double longitude = address.isNull("longitude") ? null : address.getDouble("longitude");
            GeographicPointDto geographicPoint = new GeographicPointDto(latitude, longitude);

            virtualLeads.add(new VirtualLeadDto(
                FirstName, LastName, AnnualRevenue, Phone, street,
                    postalCode, city, country, creationDate, geographicPoint, Company, stateJSON
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

        final String token = "Bearer 00D07000000YPGQ!AR4AQErLzDcy006eeu11uFWjmE7bTJmB2MqmxdPjoOln_zlrJIKUCwoObqAnBrPHMObk3Wrx5i.oHGpa3OIcy2MQ1wScgg6N";
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
