package virtualcrm.service;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import virtualcrm.model.LeadConversor;
import virtualcrm.model.VirtualLeadDto;
import virtualcrm.thrift.InternalCRMService;
import virtualcrm.thrift.InternalLeadDTO;

import java.util.*;

public class VirtualCRMImpl implements CRMService {

    @Override
    public Collection<VirtualLeadDto> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) {
        Collection<CRMService> services = new ArrayList<>();
        services.add(new InternalCRMImpl());
        services.add(new SaleforceImpl());
        GeolocalisationImpl geolocalisation = new GeolocalisationImpl();

        Collection<VirtualLeadDto> virtualLeads = new ArrayList<>();
        for(CRMService service: services){
            virtualLeads = LeadConversor.mergeListsVirtualLeads(virtualLeads, service.findLeads(lowAnnualRevenue, highAnnualRevenue, state));
        }
        geolocalisation.setGeolocalisation(virtualLeads);

        return virtualLeads;
    }

    @Override
    public Collection<VirtualLeadDto> findLeadsByDate(String startDate, String endDate) {
        Collection<CRMService> services = new ArrayList<>();
        services.add(new InternalCRMImpl());
        services.add(new SaleforceImpl());
        GeolocalisationImpl geolocalisation = new GeolocalisationImpl();

        Collection<VirtualLeadDto> virtualLeads = new ArrayList<>();
        for(CRMService service: services){
            virtualLeads = LeadConversor.mergeListsVirtualLeads(virtualLeads, service.findLeadsByDate(startDate, endDate));
        }
        geolocalisation.setGeolocalisation(virtualLeads);

        return virtualLeads;
    }
}
