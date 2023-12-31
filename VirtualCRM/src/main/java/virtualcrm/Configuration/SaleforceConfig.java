package virtualcrm.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class SaleforceConfig {

    private static final Properties properties = new Properties();

    public static String getToken() throws IOException {
        String tokenURL = properties.getProperty("saleforce.tokenurl");

        String username = properties.getProperty("saleforce.username");
        String password = properties.getProperty("saleforce.password");
        String clientid = properties.getProperty("saleforce.clientid");
        String clientsecret = properties.getProperty("saleforce.clientsecret");

        URL urlObj = new URL(tokenURL);
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
        }
    }

    public static String getEndpoint() {
        return properties.getProperty("saleforce.endpoint");
    }
}
