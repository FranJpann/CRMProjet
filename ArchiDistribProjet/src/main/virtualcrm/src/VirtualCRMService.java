import java.util.Calendar;
import java.util.List;

public interface VirtualCRMService {

    List<VirtualLeadDto> findLeads(Double lowAnnualRevenue, Double highAnnualRevenue, String state);
    List<VirtualLeadDto> findLeadsByDate(Calendar startDate, Calendar endDate);
}
