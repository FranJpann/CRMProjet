package internalcrm.database;

import internalcrm.model.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {

    private List<Model> models;

    public Database() {
        models = new ArrayList<>();

        models.add(new Model("Loann", "Cady", 0.0, "0749471448", "15 rue St Gilles", "49124", "Le Plessis-Grammoire", "France", "2023-11-30", "UnivAngers", "France"));
        models.add(new Model("Baptiste", "Chaussee", 1300.0 , "0749471448", "19 rue des coquelicots", "49570", "Montjean", "France", "2023-11-30", "UnivAngers", "France"));
    }

    public void addModel(Model model) {
        this.models.add(model);
    }

    public void deleteModel(Model model) {
        this.models.remove(model);
    }

    public List<Model> getModels(long lowAnnualRevenue, long highAnnualRevenue, String state) {
        List<Model> modelResults = new ArrayList<>();
        for(Model m: models) {
            if(m.getAnnualRevenue() >= lowAnnualRevenue && m.getAnnualRevenue() <= highAnnualRevenue && m.getState().equals(state)){
                modelResults.add(m);
            }
        }
        return modelResults;
    }

    // Format de date (dd-MM-yyyy)
    public List<Model> getModelsByDate(String startDate, String endDate) throws ParseException {
        List<Model> modelResults = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startD = simpleDateFormat.parse(startDate);
        Date endD = simpleDateFormat.parse(endDate);

        for(Model m: models) {
            Date modelDate = simpleDateFormat.parse(m.getCreationDate());
            if(modelDate.after(startD) && modelDate.before(endD)){
                modelResults.add(m);
            }
        }
        return modelResults;
    }
}