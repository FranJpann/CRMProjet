package virtualcrm.model;

public class GeographicPointDto {
    private Double latitude;
    private Double longitude;

    public GeographicPointDto(Double latitude, Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String toString() {
        return latitude+":"+longitude;
    }
}
