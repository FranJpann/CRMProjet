package virtualcrm.service;

import virtualcrm.utils.LeadConversor;
import virtualcrm.model.VirtualLeadDto;

import java.util.List;

public class VirtualCRMService implements CRMService {

    @Override
    public List<VirtualLeadDto> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) {
        InternalCRMImpl internalCRM = new InternalCRMImpl();
        SaleforceImpl saleforce = new SaleforceImpl();
        GeolocalisationImpl geolocalisation = new GeolocalisationImpl();

        List<VirtualLeadDto> virtualLeads = LeadConversor.mergeListsVirtualLeads(
                saleforce.findLeads(lowAnnualRevenue, highAnnualRevenue, state),
                internalCRM.findLeads(lowAnnualRevenue, highAnnualRevenue, state)
        );
        geolocalisation.setGeolocalisation(virtualLeads);

        return virtualLeads;
    }

    @Override
    public List<VirtualLeadDto> findLeadsByDate(String startDate, String endDate) {
        InternalCRMImpl internalCRM = new InternalCRMImpl();
        SaleforceImpl saleforce = new SaleforceImpl();
        GeolocalisationImpl geolocalisation = new GeolocalisationImpl();

        List<VirtualLeadDto> virtualLeads = LeadConversor.mergeListsVirtualLeads(
                saleforce.findLeadsByDate(startDate, endDate),
                internalCRM.findLeadsByDate(startDate, endDate)
        );
        geolocalisation.setGeolocalisation(virtualLeads);

        return virtualLeads;
    }
}
