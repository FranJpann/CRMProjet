package virtualcrm.service;

import virtualcrm.model.VirtualLeadDto;

import java.util.Collection;
import java.util.List;

public interface CRMService {

    Collection<VirtualLeadDto> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state);
    Collection<VirtualLeadDto> findLeadsByDate(String startDate, String endDate);
}
