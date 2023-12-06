package virtualcrm.service;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import virtualcrm.model.LeadConversor;
import virtualcrm.model.VirtualLeadDto;
import virtualcrm.thrift.InternalCRMService;
import virtualcrm.thrift.InternalLeadDTO;

import java.util.Collection;
import java.util.List;

public class InternalCRMImpl implements CRMService{
    public static String internalHost = "172.17.0.1";
    public static int internalPort = 9090;

    @Override
    public Collection<VirtualLeadDto> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) {
        Collection<InternalLeadDTO> internalLeads = null;

        try {
            TTransport transport;
            transport = new TSocket(internalHost, internalPort);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            InternalCRMService.Client client = new InternalCRMService.Client(protocol);

            internalLeads = client.findLeads(lowAnnualRevenue, highAnnualRevenue, state);

            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return LeadConversor.internalLeadsToVirtualLeads(internalLeads);
    }

    @Override
    public Collection<VirtualLeadDto> findLeadsByDate(String startDate, String endDate) {
        Collection<InternalLeadDTO> internalLeads = null;

        try {
            TTransport transport;
            transport = new TSocket(internalHost, internalPort);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            InternalCRMService.Client client = new InternalCRMService.Client(protocol);

            internalLeads = client.findLeadsByDate(startDate, endDate);

            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return LeadConversor.internalLeadsToVirtualLeads(internalLeads);
    }
}
