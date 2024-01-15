package virtualcrm.service;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import virtualcrm.utils.LeadConversor;
import virtualcrm.model.VirtualLeadDto;
import virtualcrm.thrift.InternalCRMService;
import virtualcrm.thrift.InternalLeadDTO;

import java.util.List;

public class InternalCRMImpl implements CRMService{

    /*  InternalCRMImpl    */
    /*  Implémentation des services de récupération des données de l'API Apache thrift l'InternalCRM */

    private final String internalHost = "localhost";
    private final int internalPort = 9090;

    @Override
    public List<VirtualLeadDto> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) {
        List<InternalLeadDTO> internalLeads = null;

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
    public List<VirtualLeadDto> findLeadsByDate(String startDate, String endDate) {
        List<InternalLeadDTO> internalLeads = null;

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
