package virtualcrm.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import virtualcrm.Configuration.SaleforceConfig;
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

@Component
public class SaleforceImpl implements CRMService {

    @Override
    public List<VirtualLeadDto> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) {
        String query = "SELECT+FirstName,LastName,AnnualRevenue,Phone,Address,Company,CreatedDate+FROM+Lead+WHERE+AnnualRevenue+>=+"+lowAnnualRevenue+"+AND+AnnualRevenue+<=+"+highAnnualRevenue;

        String response = GETRequestToSaleforce(query);

        JSONObject responseJSON = new JSONObject(response);

        return LeadConversor.JSONLeadsToVirtualLeads(responseJSON);
    }

    @Override
    public List<VirtualLeadDto> findLeadsByDate(String startDate, String endDate) {
        String query = "SELECT+FirstName,LastName,AnnualRevenue,Phone,Address,Company,CreatedDate+FROM+Lead+WHERE+CreatedDate+>+"+startDate+"T00:00:00Z+AND+CreatedDate+<+"+endDate+"T00:00:00Z";
        String response = GETRequestToSaleforce(query);

        JSONObject responseJSON = new JSONObject(response);

        return LeadConversor.JSONLeadsToVirtualLeads(responseJSON);
    }

    @Autowired
    private SaleforceConfig saleforceConfig;

    private String GETRequestToSaleforce(String query) {
        try {

            final String token = saleforceConfig.getToken();
            final String endpoint = saleforceConfig.getEndpoint();

            final String url = endpoint + "?q=" + query;

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", "Bearer "+token);
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
