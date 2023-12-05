package virtualcrm.service;

import virtualcrm.model.GeographicPointDto;

public interface GeolocalisationService {

    public GeographicPointDto GETRequestToOpenStreetMap(String query);
}
