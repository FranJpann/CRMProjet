package virtualcrm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.reactive.function.client.WebClient;
import virtualcrm.thrift.InternalCRMService;

import java.util.HashMap;
import java.util.Objects;

@Configuration
public class SaleforceConfig {

    private static final String SALESFORCE_CLIENT_NAME = "salesforce";
    private static final String CLIENT_PROPERTY_KEY = "spring.datasource.";

    @Qualifier
    private Environment env;

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService authorizedClientService) {
        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .password()
                        .build();

        // Using AuthorizedClientServiceOAuth2AuthorizedClientManager instead of the DefaultOAuth2AuthorizedClientManager
        // to support asynchrone execution through the @Async annotation
        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        authorizedClientManager.setContextAttributesMapper(oAuth2AuthorizeRequest -> {
                    if (SALESFORCE_CLIENT_NAME.equals(oAuth2AuthorizeRequest.getClientRegistrationId())) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put(OAuth2AuthorizationContext.USERNAME_ATTRIBUTE_NAME, getProperty("username"));
                        map.put(OAuth2AuthorizationContext.PASSWORD_ATTRIBUTE_NAME, getProperty("password"));
                        return map;
                    }
                    return null;
                }
        );

        return authorizedClientManager;
    }

    @Bean
    public WebClient salesforceWebClient(OAuth2AuthorizedClientManager authorizedClientManager) {
        // May use a ServerAuth2AuthorizedClientExchangeFilterFunction in a reactive stack
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth2Client.setDefaultClientRegistrationId(SALESFORCE_CLIENT_NAME);
        return WebClient.builder()
                .baseUrl(Objects.requireNonNull(env.getProperty("spring.datasource.salesforce.base-path")))
                .apply(oauth2Client.oauth2Configuration())
                .build();
    }

    private String getProperty(String property) {
        return env.getProperty(CLIENT_PROPERTY_KEY + SALESFORCE_CLIENT_NAME + "." + property);
    }
}