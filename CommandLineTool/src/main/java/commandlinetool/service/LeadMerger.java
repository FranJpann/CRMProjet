package commandlinetool.service;

import commandlinetool.utils.LeadUtils;
import internalcrm.thrift.InternalLeadDTO;
import org.json.JSONObject;
import java.util.List;

public class LeadMerger {

    public void mergeLeads() {
        SaleforceAPIService saleforceService = new SaleforceAPIService();
        InternalCRMThriftService internalCRMThriftService = new InternalCRMThriftService();

        List<InternalLeadDTO> leads = LeadUtils.JSONFormatToListInternalLeadDTO(
                new JSONObject(saleforceService.retrieveAllLeads())
        );

        for(InternalLeadDTO lead: leads)
            internalCRMThriftService.addLead(lead);
    }
}
