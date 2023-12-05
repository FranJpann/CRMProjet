package virtualcrm.service;

import org.json.JSONArray;
import org.json.JSONObject;
import virtualcrm.model.GeographicPointDto;
import virtualcrm.model.LeadConversor;
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
        String query = "SELECT+FirstName,LastName,AnnualRevenue,Phone,Address,Company,CreatedDate+FROM+Lead+WHERE+AnnualRevenue+>=+"+lowAnnualRevenue+"+AND+AnnualRevenue+<=+"+highAnnualRevenue;

        String response = sendGETRequest(query);

        JSONObject responseJSON = new JSONObject(response);

        return LeadConversor.JSONLeadsToVirtualLeads(responseJSON);
    }

    @Override
    public List<VirtualLeadDto> findLeadsByDate(String startDate, String endDate) {
        String query = "SELECT+FirstName,LastName,AnnualRevenue,Phone,Address,Company,CreatedDate+FROM+Lead+WHERE+CreatedDate+>+"+startDate+"+AND+CreatedDate+<+"+endDate;
        String response = sendGETRequest(query);

        JSONObject responseJSON = new JSONObject(response);

        return LeadConversor.JSONLeadsToVirtualLeads(responseJSON);
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
