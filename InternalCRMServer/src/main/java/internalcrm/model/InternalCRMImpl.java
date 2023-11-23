package internalcrm.model;

import internalcrm.thrift.InternalCRMService;
import internalcrm.thrift.InternalLeadDTO;
import org.apache.thrift.TException;

import java.util.List;

public class InternalCRMImpl implements InternalCRMService.Iface {

    private final String token="Bearer 00D07000000YPGQ!AR4AQL.4g9_6qsJw1pfW40cr.dKC.kOmIOqnETQYX0Mvrop2ur4kttCVf3ulroaPk9wQzFOjkFmj0G5ifzUbtveTXWwUDJBK";
    private final String endpoint="https://univangers-dev-ed.develop.my.salesforce.com/services/data/v45.0/query/";

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
