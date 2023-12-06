package virtualcrm.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import virtualcrm.model.VirtualLeadDto;
import virtualcrm.service.VirtualCRMImpl;

import java.util.Collection;
import java.util.List;

@RestController
public class ApiController {

    VirtualCRMImpl virtualCRM = new VirtualCRMImpl();
    @RequestMapping("/findLeads")
    public String getLead(@RequestParam(value = "lowAnnualRevenue")long lowAnnualRevenue, @RequestParam(value = "highAnnualRevenue")long highAnnualRevenue,
                        @RequestParam(value = "state")String state) {
        Collection<VirtualLeadDto> virtualLeads = virtualCRM.findLeads(lowAnnualRevenue, highAnnualRevenue, state);
        String response = "{";
        for(VirtualLeadDto lead: virtualLeads){
            response += "{"+lead.toString()+"},";
        }
        return response + "}";
    }

    @RequestMapping("/findLeadsByDate")
    public String getLeadByDate(@RequestParam(value = "startDate")String startDate, @RequestParam(value = "endDate")String endDate) {
        Collection<VirtualLeadDto> virtualLeads = virtualCRM.findLeadsByDate(startDate, endDate);
        String response = "{";
        for(VirtualLeadDto lead: virtualLeads){
            response += "{"+lead.toString()+"},";
        }
        return response + "}";
    }
}