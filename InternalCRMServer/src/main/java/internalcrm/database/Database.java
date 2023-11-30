package internalcrm.database;

import internalcrm.model.Model;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Model> models;

    public Database() {
        models = new ArrayList<>();

        models.add(new Model("Loann", "Cady", 0.0, "0749471448", "15 rue St Gilles", "49124", "Le Plessis-Grammoire", "France", "30/11/2023", "UnivAngers", "France"));
        models.add(new Model("Baptiste", "Chaussee", 1300.0 , "0749471448", "19 rue des coquelicots", "49570", "Montjean", "France", "30/11/2023", "UnivAngers", "France"));
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

    /*public List<Model> getModelsByDate(String startDate, String endDate){
        List<Model> modelResults = new ArrayList<>();
        for(Model m: models) {
            if(m.getCreationDate()){
                modelResults.add(m);
            }
        }
        return modelResults;
    }*/
}