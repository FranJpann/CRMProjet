package virtualcrm.utils;

import virtualcrm.model.VirtualLeadDto;

import java.util.ArrayList;
import java.util.List;

public class Query {

    public static List<VirtualLeadDto> QueryState(List<VirtualLeadDto> leads, String state) {
        List<VirtualLeadDto> newLeads = new ArrayList<>();
        for(VirtualLeadDto lead: leads) {
            if(lead.getState().equals(state)) newLeads.add(lead);
        }
        return newLeads;
    }
}
