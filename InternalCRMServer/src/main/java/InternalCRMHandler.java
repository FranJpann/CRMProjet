import internalcrm.services.thrift.impl.InternalCRMService;
import internalcrm.services.thrift.impl.InternalLeadDTO;
import internalcrm.services.thrift.impl.calendar;
import org.apache.thrift.TException;

import java.util.List;

public class InternalCRMHandler implements InternalCRMService.Iface {

    @Override
    public List<InternalLeadDTO> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) throws TException {
        return null;
    }

    @Override
    public List<InternalLeadDTO> findLeadsByDate(calendar startDate, calendar endDate) throws TException {
        return null;
    }

    @Override
    public void deleteLead(InternalLeadDTO lead) throws TException {

    }

    @Override
    public void addLead(InternalLeadDTO lead) throws TException {

    }
}
