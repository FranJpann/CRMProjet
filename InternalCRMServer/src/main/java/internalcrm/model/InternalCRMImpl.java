package internalcrm.model;

import internalcrm.database.Database;
import internalcrm.servlet.InternalApp;
import internalcrm.thrift.InternalCRMService;
import internalcrm.thrift.InternalLeadDTO;
import org.apache.thrift.TException;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class InternalCRMImpl implements InternalCRMService.Iface {

    Database database;

    public InternalCRMImpl() {
        database = new Database();
    }

    @Override
    public List<InternalLeadDTO> findLeads(long lowAnnualRevenue, long highAnnualRevenue, String state) throws TException {
        List<Model> models = database.getModels(lowAnnualRevenue, highAnnualRevenue, state);
        return ModelConversor.ModelToInternalLeadDTO(models);
    }

    // TO IMPLEMENT
    @Override
    public List<InternalLeadDTO> findLeadsByDate(String startDate, String endDate) throws TException {
        return null;
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
