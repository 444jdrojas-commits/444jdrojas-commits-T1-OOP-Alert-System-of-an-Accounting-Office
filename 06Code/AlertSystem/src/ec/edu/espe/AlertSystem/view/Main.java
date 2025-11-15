package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.AssistantController;
import ec.edu.espe.AlertSystem.controller.BossController;
import ec.edu.espe.AlertSystem.controller.LoginController;

/**
 *
 * @author Paulo Ramos
 */

public class Main {
    public static void main(String[] args) {

        BossController bossController = new BossController();
        AssistantController assistantController = new AssistantController();

        LoginController controller = new LoginController(bossController, assistantController);

        LoginView view = new LoginView(controller);
        view.showMenu();
    }
}





