/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystem.controller;

import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Boss;
import ec.edu.espe.AlertSystem.util.DataManager;
import java.util.List;

/**
 *
 * @author Paulo Ramos
 */

public class LoginController {
    private DataManager dataManager;

    public LoginController(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public boolean loginBoss(String user, String password) {
        Boss boss = dataManager.loadBoss();
        return boss != null && boss.getUser().equals(user) && boss.getPassword().equals(password);
    }

    public boolean loginAssistant(String user, String password) {
        List<Assistant> assistants = dataManager.loadAssistants();
        for (Assistant a : assistants) {
            if (a.getUser().equals(user) && a.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Método para crear datos iniciales
    public void initializeData() {
        Boss boss = new Boss("Sandra Pena", new java.util.Date(), "0999999999", "sandra@email.com", "sandrapena", "sandra1234");
        dataManager.saveBoss(boss);

        List<Assistant> assistants = List.of(
                new Assistant("Ana", new java.util.Date(), "0991111111", "ana@mail.com", "ana", "1111"),
                new Assistant("Paulo", new java.util.Date(), "0992222222", "paulo@mail.com", "paulo", "2222"),
                new Assistant("Maria", new java.util.Date(), "0993333333", "maria@mail.com", "maria", "3333")
        );
        dataManager.saveAssistants(assistants);
    }
}

