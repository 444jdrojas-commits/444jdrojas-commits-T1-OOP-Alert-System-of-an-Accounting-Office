package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.LoginController;
import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Boss;
import java.util.Scanner;

public class LoginView {
    private final LoginController loginController;
    private final Scanner sc;

    public LoginView() {
        this.loginController = new LoginController();
        this.sc = new Scanner(System.in);
        loginController.initializeData();
    }

    public void showLogin() {
        System.out.println("=======================================");
        System.out.println("  SISTEMA DE ALERTAS - CONTABILIDAD");
        System.out.println("=======================================");

        while (true) {
            System.out.print("\nIngrese su rol (boss/assistant) o 0 para salir: ");
            String role = sc.nextLine().trim();
            if (role.equals("0")) { System.out.println("Saliendo..."); break; }

            System.out.print("Usuario: ");
            String user = sc.nextLine().trim();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine().trim();

            switch (role.toLowerCase()) {
                case "boss" -> handleBoss(user, pass);
                case "assistant" -> handleAssistant(user, pass);
                default -> System.out.println("Rol inválido.");
            }
        }
    }

    private void handleBoss(String user, String pass) {
        Boss boss = loginController.getBossIfValid(user, pass);
        if (boss != null) {
            System.out.println("Bienvenido, " + boss.getName());
            new BossView().showBossMenu();
        } else System.out.println("Credenciales incorrectas (Boss).");
    }

    private void handleAssistant(String user, String pass) {
        Assistant a = loginController.getAssistantIfValid(user, pass);
        if (a != null) {
            System.out.println("Bienvenido, " + a.getName());
            new AsistantView(a).showAssistantMenu(); // nombre de tu clase vista
        } else System.out.println("Credenciales incorrectas (Assistant).");
    }
}
