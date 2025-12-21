package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.DataServiceController;
import ec.edu.espe.AlertSystem.model.Assistant;
import java.util.Scanner;

/**
 *
 * @author Paulo Ramos
 */
public class LoginView {

    private DataServiceController controller;
    private Scanner sc;

    public LoginView(DataServiceController controller) {
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

            if (controller.loginBoss(user, pass) != null) {
                System.out.println("\nBienvenido jefe!");
                BossView bossView = new BossView(controller);
                bossView.showBossMenu();
                loggedIn = true;
            } else {
                Assistant assistant = controller.loginAssistant(user, pass);
                if (assistant != null) {
                    System.out.println("\nBienvenido asistente!");
                    AssistantView assistantView = new AssistantView(assistant, controller);
                    assistantView.showMenu();
                    loggedIn = true;
                } else {
                    System.out.println("\nCredenciales no vaidas. Inténtelo de nuevo...\n");
                }
            }

        }
    }
}

