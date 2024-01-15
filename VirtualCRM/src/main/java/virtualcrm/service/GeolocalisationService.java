package virtualcrm.service;

import virtualcrm.model.GeographicPointDto;
import virtualcrm.model.VirtualLeadDto;

import java.util.List;

public interface GeolocalisationService {

    /*  GeographicService   */
    /*  Interface de service de géolocalisation */

    public GeographicPointDto GETRequestToOpenStreetMap(String query);
    public void setGeolocalisation(List<VirtualLeadDto> virtualLeads);
}
