package ec.edu.espe.AlertSystem.controller;

import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Boss;

/**
 *
 * @author Paulo Ramos
 */
public class LoginController {

    private BossController bossController;
    private AssistantController assistantController;

    public LoginController(BossController bossController, AssistantController assistantController) {
        this.bossController = bossController;
        this.assistantController = assistantController;
    }

    public boolean loginBoss(String user, String password) {
        Boss boss = bossController.getBoss();
        return boss != null
                && boss.getUser().equalsIgnoreCase(user)
                && boss.getPassword().equals(password);
    }

    public Assistant loginAssistant(String user, String password) {
        return assistantController.login(user, password);
    }

}
