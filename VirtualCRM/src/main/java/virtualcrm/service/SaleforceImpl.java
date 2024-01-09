package virtualcrm.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import virtualcrm.configuration.ConfigProperties;
import virtualcrm.model.LeadConversor;
import virtualcrm.model.VirtualLeadDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    private String GETRequestToSaleforce(String query) {
        try {

            ConfigProperties props = new ConfigProperties();

            final String token = saleforceGetToken();
            final String endpoint = props.getConfigValue("saleforce.endpoint");

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

    private String saleforceGetToken() throws IOException {

        ConfigProperties props = new ConfigProperties();

        String rep = "";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI(props.getConfigValue("saleforce.tokenurl")))
                    .addParameter("grant_type", "password")
                    .addParameter("username", props.getConfigValue("saleforce.username"))
                    .addParameter("password", props.getConfigValue("saleforce.password"))
                    .addParameter("client_id", props.getConfigValue("saleforce.clientid"))
                    .addParameter("client_secret", props.getConfigValue("saleforce.clientsecret"))
                    .build();

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                rep = EntityUtils.toString(response.getEntity());
            } finally {
                response.close();
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            httpclient.close();
        }

        rep = new JSONObject(rep).get("access_token").toString();
        return rep;
    }
}
