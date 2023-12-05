package virtualcrm.service;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import virtualcrm.model.LeadConversor;
import virtualcrm.model.VirtualLeadDto;
import virtualcrm.thrift.InternalCRMService;
import virtualcrm.thrift.InternalLeadDTO;

import java.util.Calendar;
import java.util.List;

public class VirtualCRMImpl implements CRMService {

    @Override
    public List<VirtualLeadDto> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) {
        InternalCRMImpl internalCRM = new InternalCRMImpl();
        SaleforceImpl saleforce = new SaleforceImpl();

        return LeadConversor.mergeListsVirtualLeads(
                saleforce.findLeads(lowAnnualRevenue, highAnnualRevenue, state),
                internalCRM.findLeads(lowAnnualRevenue, highAnnualRevenue, state)
        );
    }

    @Override
    public List<VirtualLeadDto> findLeadsByDate(String startDate, String endDate) {
        InternalCRMImpl internalCRM = new InternalCRMImpl();
        SaleforceImpl saleforce = new SaleforceImpl();

        return LeadConversor.mergeListsVirtualLeads(
                saleforce.findLeadsByDate(startDate, endDate),
                internalCRM.findLeadsByDate(startDate, endDate)
        );
    }
}
