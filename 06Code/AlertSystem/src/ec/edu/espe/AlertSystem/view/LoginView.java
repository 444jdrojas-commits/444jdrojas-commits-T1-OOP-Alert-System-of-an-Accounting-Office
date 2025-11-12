/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.LoginController;
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
            System.out.println("=== Inicio de sesión ===");
            System.out.print("Usuario: ");
            String user = sc.nextLine();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine();

            if (controller.loginBoss(user, pass)) {
                System.out.println("\nBienvenido!");
                BossView bossView = new BossView();
                bossView.showBossMenu();
                loggedIn = true; // salir del bucle
            } else if (controller.loginAssistant(user, pass)) {
                System.out.println("\nBienvenido!");
                AssistantView assistantView = new AssistantView(user);
                assistantView.showMenu();
                loggedIn = true; // salir del bucle
            } else {
                System.out.println("\nCredenciales no válidas. Inténtelo de nuevo...\n");
            }
        }
    }
}
