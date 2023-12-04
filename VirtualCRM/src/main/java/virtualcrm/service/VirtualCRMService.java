package virtualcrm.service;

import virtualcrm.model.VirtualLeadDto;

import java.util.Calendar;
import java.util.List;

public interface VirtualCRMService {

    List<VirtualLeadDto> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state);
    List<VirtualLeadDto> findLeadsByDate(String startDate, String endDate);
}
