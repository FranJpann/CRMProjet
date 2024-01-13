package commandlinetool.service;

import commandlinetool.config.ConfigProperties;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class VirtualCRMAPIService {

    ConfigProperties props = new ConfigProperties();

    public String findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) throws IOException {

        String rep = "";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI(props.getConfigValue("virtualcrm.apiurl") + "/findLeads"))
                    .addParameter("lowAnnualRevenue", Long.toString(lowAnnualRevenue))
                    .addParameter("highAnnualRevenue", Long.toString(highAnnualRevenue))
                    .addParameter("state", state)
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

        return rep;
    }

    public String findLeadsByDate(String startDate, String endDate) throws IOException {

        String rep = "";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI(props.getConfigValue("virtualcrm.apiurl") + "/findLeadsByDate"))
                    .addParameter("startDate", startDate)
                    .addParameter("endDate", endDate)
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

        return rep;
    }
}
