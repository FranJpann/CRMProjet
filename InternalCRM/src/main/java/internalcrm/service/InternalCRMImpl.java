package internalcrm.service;

import internalcrm.database.Database;
import internalcrm.model.InternalLead;
import internalcrm.thrift.InternalCRMService;
import internalcrm.thrift.InternalLeadDTO;
import internalcrm.utils.ModelConversor;
import org.apache.thrift.TException;

import java.text.ParseException;
import java.util.List;

public class InternalCRMImpl implements InternalCRMService.Iface {

    Database database = new Database();

    @Override
    public List<InternalLeadDTO> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) throws TException {
        List<InternalLead> models = database.getModels(lowAnnualRevenue, highAnnualRevenue, state);
        return ModelConversor.ModelToInternalLeadDTO(models);
    }

    @Override
    public List<InternalLeadDTO> findLeadsByDate(String startDate, String endDate) throws TException {
        List<InternalLead> models;

        try {
            models = database.getModelsByDate(startDate, endDate);
        } catch (ParseException e) {
            throw new TException(e);
        }

        return ModelConversor.ModelToInternalLeadDTO(models);
    }

    @Override
    public void deleteLead(InternalLeadDTO lead) throws TException {
        database.deleteModel(ModelConversor.InternalLeadDTOToModel(lead));
    }

    @Override
    public void addLead(InternalLeadDTO lead) throws TException {
        database.addModel(ModelConversor.InternalLeadDTOToModel(lead));
    }
}
