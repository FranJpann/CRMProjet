package internalcrm.model;

import internalcrm.thrift.ModelTO;
import internalcrm.model.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelToModelTOConversor {

    static List<ModelTO> toModelTOs(List<Model> model) {
        List<ModelTO> listModelTO = new ArrayList<>();
        for(Model m: model) {
            listModelTO.add(new ModelTO(
                    m.getFirstName(),
                    m.getLastName(),
                    m.getAnnualRevenue(),
                    m.getPhone(),
                    m.getStreet(),
                    m.getPostalCode(),
                    m.getCity(),
                    m.getCountry(),
                    m.getCreationDate(),
                    m.getCompany(),
                    m.getState()
            ));
        }

        return listModelTO;
    }
}
