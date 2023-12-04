package virtualcrm.model;

public class GeographicPointDto {
    private Double latitude;
    private Double longitude;

    public String toString() {
        return latitude+":"+longitude;
    }
}
