package internalcrm.model;

import internalcrm.thrift.InternalLeadDTO;

import java.util.ArrayList;
import java.util.List;

public class ModelConversor {

    static List<InternalLeadDTO> ModelToInternalLeadDTO(List<Model> model) {
        List<InternalLeadDTO> listModelTO = new ArrayList<>();
        for(Model m: model) {
            listModelTO.add(new InternalLeadDTO(
                    m.getLastName() + ", " + m.getFirstName(),
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

    static Model InternalLeadDTOToModel(InternalLeadDTO internalLeadDTO) {
        String[] names = internalLeadDTO.getName().split(", ");
        return new Model(
                names[0],
                names[1],
                internalLeadDTO.getAnnualRevenue(),
                internalLeadDTO.getPhone(),
                internalLeadDTO.getStreet(),
                internalLeadDTO.getPostalCode(),
                internalLeadDTO.getCity(),
                internalLeadDTO.getCountry(),
                internalLeadDTO.getCreationDate(),
                internalLeadDTO.getCompany(),
                internalLeadDTO.getState()
            );
    }
}
