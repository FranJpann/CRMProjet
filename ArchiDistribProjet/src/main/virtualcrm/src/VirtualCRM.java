import java.util.Calendar;
import java.util.List;

public class VirtualCRM implements VirtualCRMService{

    @Override
    public List<VirtualLeadDto> findLeads(Double lowAnnualRevenue, Double highAnnualRevenue, String state) {
        return null;
    }

    @Override
    public List<VirtualLeadDto> findLeadsByDate(Calendar startDate, Calendar endDate) {
        return null;
    }
}
