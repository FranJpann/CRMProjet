package virtualcrm.model;

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

    public static List<VirtualLeadDto> mergeListsVirtualLeads(List<VirtualLeadDto> leads1, List<VirtualLeadDto> leads2){
        List<VirtualLeadDto> resultList = leads1;

        for(VirtualLeadDto lead: leads2) {
            if(!resultList.contains(lead)) resultList.add(lead);
        }

        return resultList;
    }
}
