package virtualcrm.model;

import virtualcrm.thrift.InternalLeadDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LeadConversor {

    public static List<VirtualLeadDto> LeadsToVirtualLeads(List<InternalLeadDTO> internalLeads) {
        return internalLeadsToVirtualLeads(internalLeads);
    }

    private static List<VirtualLeadDto> internalLeadsToVirtualLeads(List<InternalLeadDTO> internalLeads) {
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
}
