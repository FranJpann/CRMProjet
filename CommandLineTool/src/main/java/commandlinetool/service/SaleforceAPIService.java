package commandlinetool.service;

import commandlinetool.config.ConfigProperties;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class SaleforceAPIService {

    ConfigProperties props = new ConfigProperties();

    public String retrieveAllLeads() {
        String query = "SELECT+FirstName,LastName,AnnualRevenue,Phone,Address,Company,CreatedDate+FROM+Lead";
        return GETRequestToSaleforce(query);
    }

    private String GETRequestToSaleforce(String query) {
        try {

            final String token = getToken();
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

    private String getToken() throws IOException {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            httpclient.close();
        }

        rep = new JSONObject(rep).get("access_token").toString();
        return rep;
    }
}
