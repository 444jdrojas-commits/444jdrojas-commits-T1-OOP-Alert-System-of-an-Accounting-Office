package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.LoginController;
import ec.edu.espe.AlertSystem.model.Assistant;
import java.util.Scanner;

/**
 *
 * @author Paulo Ramos
 */
public class LoginView {
    private LoginController controller;
    private Scanner sc;

    public LoginView(LoginController controller) {
        this.controller = controller;
        this.sc = new Scanner(System.in);
    }

    public void showMenu() {
    boolean loggedIn = false;

    while (!loggedIn) {
        System.out.println("=== Inicio de sesion ===");
        System.out.print("Usuario: ");
        String user = sc.nextLine();
        System.out.print("Contrasena: ");
        String pass = sc.nextLine();

        if (controller.loginBoss(user, pass)) {

            System.out.println("\nBienvenido!");
            BossView bossView = new BossView();
            bossView.showBossMenu();
            loggedIn = true;

        } else {

            Assistant assistant = controller.loginAssistant(user, pass);

            if (assistant != null) {
                System.out.println("\nBienvenido!");
                AssistantView assistantView = new AssistantView(assistant);
                assistantView.showMenu();
                loggedIn = true;
            } else {
                System.out.println("\nCredenciales no válidas. Inténtelo de nuevo...\n");
            }
        }
    }
}

}
