package virtualcrm.model;

import org.json.JSONArray;
import org.json.JSONObject;
import virtualcrm.thrift.InternalLeadDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LeadConversor {

    public static List<VirtualLeadDto> internalLeadsToVirtualLeads(List<InternalLeadDTO> internalLeads) {
        List<VirtualLeadDto> virtualLeads = new ArrayList<>();

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

    public static List<VirtualLeadDto> JSONLeadsToVirtualLeads(JSONObject response) {
        JSONArray records = new JSONArray(response.get("records").toString());

        List<VirtualLeadDto> virtualLeads = new ArrayList<>();

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

            Double latitude = address.isNull("latitude") ? null : address.getDouble("latitude");
            Double longitude = address.isNull("longitude") ? null : address.getDouble("longitude");
            GeographicPointDto geographicPoint = new GeographicPointDto(latitude, longitude);

            virtualLeads.add(new VirtualLeadDto(
                    FirstName, LastName, AnnualRevenue, Phone, street,
                    postalCode, city, country, CreatedDate, geographicPoint, Company, stateJSON
            ));
        }

        return virtualLeads;
    }

    public static List<VirtualLeadDto> mergeListsVirtualLeads(List<VirtualLeadDto> leads1, List<VirtualLeadDto> leads2){
        List<VirtualLeadDto> resultList = leads1;

        for(VirtualLeadDto lead: leads2) {
            if(!resultList.contains(lead)) resultList.add(lead);
        }

        return resultList;
    }
}
