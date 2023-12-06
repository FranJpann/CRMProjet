package virtualcrm.model;

import ch.qos.logback.classic.spi.LoggerContextVO;
import org.json.JSONArray;
import org.json.JSONObject;
import virtualcrm.service.GeolocalisationImpl;
import virtualcrm.thrift.InternalLeadDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class LeadConversor {

    public static Collection<VirtualLeadDto> internalLeadsToVirtualLeads(Collection<InternalLeadDTO> internalLeads) {
        Collection<VirtualLeadDto> virtualLeads = new ArrayList<>();

        for(InternalLeadDTO internalLead: internalLeads) {
            String[] names = internalLead.getName().split(", ");
            virtualLeads.add(new VirtualLeadDto(
                    names[1],
                    names[0],
                    internalLead.getAnnualRevenue(),
                    internalLead.getPhone(),
                    internalLead.getStreet(),
                    internalLead.getPostalCode(),
                    internalLead.getCity(),
                    internalLead.getCountry(),
                    internalLead.getCreationDate(),
                    internalLead.getCompany(),
                    internalLead.getState()));
        }

        return  virtualLeads;
    }

    public static Collection<VirtualLeadDto> JSONLeadsToVirtualLeads(JSONObject response) {
        JSONArray records = new JSONArray(response.get("records").toString());

        Collection<VirtualLeadDto> virtualLeads = new ArrayList<>();

        for(Object recordObj: records) {

            JSONObject recordJSON = new JSONObject(recordObj.toString());
            String FirstName = recordJSON.isNull("FirstName") ? "" : recordJSON.getString("FirstName");
            String LastName = recordJSON.isNull("LastName") ? "" : recordJSON.getString("LastName");
            Double AnnualRevenue = recordJSON.isNull("AnnualRevenue") ? null : recordJSON.getDouble("AnnualRevenue");
            String Phone = recordJSON.isNull("Phone") ? "" : recordJSON.getString("Phone");
            String Company = recordJSON.isNull("Company") ? "" : recordJSON.getString("Company");
            String CreatedDate = recordJSON.isNull("CreatedDate") ? "" : recordJSON.getString("CreatedDate");

            JSONObject address = recordJSON.getJSONObject("Address");
            String street = address.isNull("street") ? "" : address.getString("street");
            String postalCode = address.isNull("postalCode") ? "" : address.getString("postalCode");
            String city = address.isNull("city") ? "" : address.getString("city");
            String country = address.isNull("country") ? "" : address.getString("country");
            String stateJSON = address.isNull("state") ? "" : address.getString("state");

            virtualLeads.add(new VirtualLeadDto(
                    FirstName, LastName, AnnualRevenue, Phone, street,
                    postalCode, city, country, CreatedDate, Company, stateJSON
            ));
        }

        return virtualLeads;
    }

    public static Collection<VirtualLeadDto> mergeListsVirtualLeads(Collection<VirtualLeadDto> leads1, Collection<VirtualLeadDto> leads2){
        Collection<VirtualLeadDto> resultList = leads1;

        for(VirtualLeadDto lead: leads2) {
            if(!resultList.contains(lead)) resultList.add(lead);
        }

        return resultList;
    }
}
