package virtualcrm.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import virtualcrm.model.LeadConversor;
import virtualcrm.model.VirtualLeadDto;
import java.util.Collection;

@Service
public class SaleforceImpl implements CRMService {

    private WebClient saleforceWebClient;

    @Override
    public Collection<VirtualLeadDto> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) {
        String query = "SELECT+FirstName,LastName,AnnualRevenue,Phone,Address,Company,CreatedDate+FROM+Lead+WHERE+AnnualRevenue+>=+"+lowAnnualRevenue+"+AND+AnnualRevenue+<=+"+highAnnualRevenue;

        String response = GETRequestToSaleforce(query);

        JSONObject responseJSON = new JSONObject(response);

        return LeadConversor.JSONLeadsToVirtualLeads(responseJSON);
    }

    @Override
    public Collection<VirtualLeadDto> findLeadsByDate(String startDate, String endDate) {
        String query = "SELECT+FirstName,LastName,AnnualRevenue,Phone,Address,Company,CreatedDate+FROM+Lead+WHERE+CreatedDate+>+"+startDate+"T00:00:00Z+AND+CreatedDate+<+"+endDate+"T00:00:00Z";
        String response = GETRequestToSaleforce(query);

        JSONObject responseJSON = new JSONObject(response);

        return LeadConversor.JSONLeadsToVirtualLeads(responseJSON);
    }

    private String GETRequestToSaleforce(String query) {
        /*ResponseEntity<String> response = saleforceWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/query")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .toEntity(String.class)
                .block();

        return response.toString();*/
        return null;
    }
}
