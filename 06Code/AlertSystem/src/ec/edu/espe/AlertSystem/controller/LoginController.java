package ec.edu.espe.AlertSystem.controller;

import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Boss;
import ec.edu.espe.AlertSystem.util.DataManager;
import java.util.ArrayList;
import java.util.List;


public class LoginController {

    private final DataManager dataManager;

    public LoginController() {
        this.dataManager = new DataManager();
    }

    public LoginController(DataManager dataManager) {
        this.dataManager = dataManager;
    }

 
    public boolean loginBoss(String user, String password) {
        Boss boss = dataManager.loadBoss();
        return boss != null && boss.getUser().equals(user) && boss.getPassword().equals(password);
    }

    public Boss getBossIfValid(String user, String password) {
        Boss boss = dataManager.loadBoss();
        if (boss != null && boss.getUser().equals(user) && boss.getPassword().equals(password)) {
            return boss;
        }
        return null;
    }

    public boolean loginAssistant(String user, String password) {
        return getAssistantIfValid(user, password) != null;
    }

    public Assistant getAssistantIfValid(String user, String password) {
        List<Assistant> assistants = dataManager.loadAssistants();
        for (Assistant a : assistants) {
            if (a.getUser().equals(user) && a.getPassword().equals(password)) {
                return a;
            }
        }
        return null;
    }

    public void initializeData() {
        // Boss por defecto
        if (dataManager.loadBoss() == null) {
            Boss boss = new Boss(
                    "Sandra Pena",
                    new java.util.Date(),
                    "0999999999",
                    "sandra@email.com",
                    "sandrapena",
                    "sandra1234"
            );
            dataManager.saveBoss(boss);
        }

        List<Assistant> assistants = dataManager.loadAssistants();
        if (assistants.isEmpty()) {
            assistants = new ArrayList<>();
            assistants.add(new Assistant("Ana", new java.util.Date(), "0991111111", "ana@mail.com", "ana", "1111"));
            assistants.add(new Assistant("Luis", new java.util.Date(), "0992222222", "luis@mail.com", "luis", "2222"));
            assistants.add(new Assistant("Maria", new java.util.Date(), "0993333333", "maria@mail.com", "maria", "3333"));
            dataManager.saveAssistants(assistants);
        }
    }
}
