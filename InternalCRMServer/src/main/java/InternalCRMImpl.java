import internalcrm.thrift.InternalCRMService;
import internalcrm.thrift.InternalLeadDTO;
import internalcrm.thrift.calendar;
import org.apache.thrift.TException;

import java.util.List;

public class InternalCRMImpl implements InternalCRMService.Iface {

    @Override
    public List<InternalLeadDTO> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) throws TException {
        return null;
    }

    @Override
    public List<InternalLeadDTO> findLeadsByDate(String startDate, String endDate) throws TException {
        return null;
    }

    @Override
    public void deleteLead(InternalLeadDTO lead) throws TException {

    }

    @Override
    public void addLead(InternalLeadDTO lead) throws TException {

    }
}
