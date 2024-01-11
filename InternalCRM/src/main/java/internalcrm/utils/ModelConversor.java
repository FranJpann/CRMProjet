package internalcrm.utils;

import internalcrm.model.InternalLead;
import internalcrm.thrift.InternalLeadDTO;

import java.util.ArrayList;
import java.util.List;

public class ModelConversor {

    public static List<InternalLeadDTO> ModelToInternalLeadDTO(List<InternalLead> model) {
        List<InternalLeadDTO> listModelTO = new ArrayList<>();
        for(InternalLead m: model) {
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

    public static InternalLead InternalLeadDTOToModel(InternalLeadDTO internalLeadDTO) {
        String[] names = internalLeadDTO.getName().split(", ");
        return new InternalLead(
                names[1],
                names[0],
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
