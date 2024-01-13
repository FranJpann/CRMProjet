package commandlinetool.app;

import commandlinetool.service.LeadMerger;
import commandlinetool.service.VirtualCRMAPIService;

import java.io.IOException;

public class HandleArgs {

    private static final VirtualCRMAPIService virtualCRMAPIService = new VirtualCRMAPIService();
    private static final LeadMerger leadMerger = new LeadMerger();

    public static void handleArgs(String[] args) throws IOException {

        String response;

        if(args[0].equals("findLeads") && args.length == 4) {
            response = virtualCRMAPIService.findLeads(Long.parseLong(args[1]), Long.parseLong(args[2]), args[3]);
        }
        else if(args[0].equals("findLeadsByDate") && args.length == 3) {
            response = virtualCRMAPIService.findLeadsByDate(args[1], args[2]);
        }
        else if(args[0].equals("mergeLeads") && args.length == 1){
            leadMerger.mergeLeads();
            response = "Leads merged";
        }
        else {
            response = "Mauvais arguments." +
                    "\n     findLeads (lowAnnualRevenue) (highAnnualRevenue) (state)" +
                    "\n     findLeadsByDate (startDate YYYY-MM-dd) (endDate YYYY-MM-dd)";
        }

        System.out.println(response);
    }
}
