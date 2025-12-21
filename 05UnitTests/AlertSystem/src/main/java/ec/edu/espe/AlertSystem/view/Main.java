package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.DataServiceController;


/**
 *
 * @author Paulo Ramos
 */
public class Main {
    public static void main(String[] args) {
        DataServiceController controller = new DataServiceController();

        LoginView view = new LoginView(controller);
        view.showMenu();
    }
}






