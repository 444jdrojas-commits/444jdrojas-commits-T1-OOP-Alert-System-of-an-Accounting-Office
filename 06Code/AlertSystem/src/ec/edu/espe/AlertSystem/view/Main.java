/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.LoginController;
import ec.edu.espe.util.DataManager;

/**
 *
 * @author Paulo Ramos
 */

public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        LoginController controller = new LoginController(dataManager);

        // Crear datos de ejemplo (solo la primera vez)
        controller.initializeData();

        // Mostrar vista de login
        LoginView view = new LoginView(controller);
        view.showMenu();
    }
}



