package virtualcrm.service;

import virtualcrm.model.VirtualLeadDto;

import java.util.List;

public interface CRMService {

    /*  CRMService    */
    /*  Interface pour les services */

    List<VirtualLeadDto> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state);
    List<VirtualLeadDto> findLeadsByDate(String startDate, String endDate);
}
