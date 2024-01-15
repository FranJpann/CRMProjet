package internalcrm.database;

import internalcrm.model.InternalLead;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {

    /*  Database    */
    /*  Met à disposition une base de donnée contenant une liste d'InternalLead */

    private List<InternalLead> models;

    public Database() {
        models = new ArrayList<>();

        models.add(new InternalLead("Loann", "Cady", 0.0, "0749471448", "15 rue St Gilles", "49124", "Le Plessis-Grammoire", "France", "2023-11-30", "UnivAngers", "France"));
        models.add(new InternalLead("Baptiste", "Chaussee", 1300.0 , "0749471448", "19 rue des coquelicots", "49570", "Montjean", "France", "2023-11-30", "UnivAngers", "France"));
    }

    public void addModel(InternalLead model) {
        this.models.add(model);
    }

    public void deleteModel(InternalLead model) {
        this.models.remove(model);
    }

    public List<InternalLead> getModels(long lowAnnualRevenue, long highAnnualRevenue, String state) {
        List<InternalLead> modelResults = new ArrayList<>();
        for(InternalLead m: models) {
            if(m.getAnnualRevenue() >= lowAnnualRevenue && m.getAnnualRevenue() <= highAnnualRevenue && m.getState().equals(state)){
                modelResults.add(m);
            }
        }
        return modelResults;
    }

    // Format de date (dd-MM-yyyy)
    public List<InternalLead> getModelsByDate(String startDate, String endDate) throws ParseException {
        List<InternalLead> modelResults = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startD = simpleDateFormat.parse(startDate);
        Date endD = simpleDateFormat.parse(endDate);

        for(InternalLead m: models) {
            Date modelDate = simpleDateFormat.parse(m.getCreationDate());
            if(modelDate.after(startD) && modelDate.before(endD)){
                modelResults.add(m);
            }
        }
        return modelResults;
    }
}