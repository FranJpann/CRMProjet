package commandlinetool.utils;

import internalcrm.thrift.InternalLeadDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LeadUtils {

    public static List<InternalLeadDTO> JSONFormatToListInternalLeadDTO(JSONObject response) {
        JSONArray records = new JSONArray(response.get("records").toString());

        List<InternalLeadDTO> internalLeadDTOS = new ArrayList<>();

        for(Object recordObj: records) {

            JSONObject recordJSON = new JSONObject(recordObj.toString());
            String FirstName = recordJSON.isNull("FirstName") ? "" : recordJSON.getString("FirstName");
            String LastName = recordJSON.isNull("LastName") ? "" : recordJSON.getString("LastName");
            Double AnnualRevenue = recordJSON.isNull("AnnualRevenue") ? 0 : recordJSON.getDouble("AnnualRevenue");
            String Phone = recordJSON.isNull("Phone") ? "" : recordJSON.getString("Phone");
            String Company = recordJSON.isNull("Company") ? "" : recordJSON.getString("Company");
            String CreatedDate = recordJSON.isNull("CreatedDate") ? "" : recordJSON.getString("CreatedDate");

            JSONObject address = recordJSON.getJSONObject("Address");
            String street = address.isNull("street") ? "" : address.getString("street");
            String postalCode = address.isNull("postalCode") ? "" : address.getString("postalCode");
            String city = address.isNull("city") ? "" : address.getString("city");
            String country = address.isNull("country") ? "" : address.getString("country");
            String stateJSON = address.isNull("state") ? "" : address.getString("state");

            internalLeadDTOS.add(new InternalLeadDTO(
                    FirstName + ", " + LastName, AnnualRevenue, Phone, street,
                    postalCode, city, country, CreatedDate, Company, stateJSON
            ));
        }
        return internalLeadDTOS;
    }
}
