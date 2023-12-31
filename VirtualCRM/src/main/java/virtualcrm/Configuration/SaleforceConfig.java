package virtualcrm.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Configuration
public class SaleforceConfig {

    private final String tokenURL;
    private final String username;
    private final String password;
    private final String clientid;
    private final String clientsecret;
    private final String endpoint;

    public SaleforceConfig(@Value("${saleforce.tokenurl}") String tokenurl,
                           @Value("${saleforce.username}") String username,
                           @Value("${saleforce.password}") String password,
                           @Value("${saleforce.clientid}") String clientid,
                           @Value("${saleforce.clientsecret}") String clientsecret,
                           @Value("${saleforce.endpoint}") String endpoint) {
        this.tokenURL = tokenurl;
        this.username = username;
        this.password = password;
        this.clientid = clientid;
        this.clientsecret = clientsecret;
        this.endpoint = endpoint;
    }

    @Bean
    public String getToken() throws IOException {

        /*URL urlObj = new URL(tokenURL);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        String urlParameters = "grant_type=password" +
                "&client_id=" + clientid +
                "&client_secret=" + clientsecret +
                "&username=" + username +
                "&password=" + password;

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = urlParameters.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Lecture de la réponse
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Affichage de la réponse
            System.out.println(response.toString());
            return response.toString();
        }*/
        return "00D07000000YPGQ!AR4AQCS17AHSUAo6QYT5kVc4ZksoJ4FeEXEGZQRbhFWV8Jgo.IoUtovTszQwEryKEJ8sj5mloV_lgiL0q3Qz61DkoRXvzddL";
    }

    @Bean
    public String getEndpoint() throws IOException {
        return this.endpoint;
    }
}
