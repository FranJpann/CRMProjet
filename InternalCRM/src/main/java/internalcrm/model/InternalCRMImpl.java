package internalcrm.model;

import internalcrm.database.Database;
import internalcrm.thrift.InternalCRMService;
import internalcrm.thrift.InternalLeadDTO;
import org.apache.thrift.TException;

import java.text.ParseException;
import java.util.List;

public class InternalCRMImpl implements InternalCRMService.Iface {

    Database database = new Database();

    @Override
    public List<InternalLeadDTO> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) throws TException {
        List<Model> models = database.getModels(lowAnnualRevenue, highAnnualRevenue, state);
        return ModelConversor.ModelToInternalLeadDTO(models);
    }

    // TO IMPLEMENT
    @Override
    public List<InternalLeadDTO> findLeadsByDate(String startDate, String endDate) throws TException {
        List<Model> models;

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
