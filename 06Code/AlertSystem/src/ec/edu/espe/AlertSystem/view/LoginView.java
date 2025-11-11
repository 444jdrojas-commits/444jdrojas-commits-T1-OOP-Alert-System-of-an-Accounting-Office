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
        System.out.println("=== LOGIN SYSTEM ===");
        System.out.print("User: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (controller.loginBoss(user, pass)) {
            System.out.println("\n✅ Welcome, Boss!");
            showBossMenu();
        } else if (controller.loginAssistant(user, pass)) {
            System.out.println("\n👋 Welcome, Assistant!");
            showAssistantMenu(user);
        } else {
            System.out.println("\n❌ Invalid credentials. Try again.");
        }
    }

    private void showBossMenu() {
        int option;
        do {
            System.out.println("\n===== BOSS MENU =====");
            System.out.println("1. Manage Assistants");
            System.out.println("2. View All Tasks");
            System.out.println("3. View Performance Indicators");
            System.out.println("4. View Alerts");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> System.out.println("👤 Managing assistants (to implement)");
                case 2 -> System.out.println("📄 Viewing all tasks (to implement)");
                case 3 -> System.out.println("📈 Viewing performance indicators (to implement)");
                case 4 -> System.out.println("🚨 Viewing alerts (to implement)");
                case 5 -> System.out.println("👋 Logged out successfully.");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 5);
    }

    private void showAssistantMenu(String username) {
        int option;
        do {
            System.out.println("\n===== ASSISTANT MENU =====");
            System.out.println("1. View My Tasks");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. View My Alerts");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> System.out.println("🧾 Viewing tasks for " + username + " (to implement)");
                case 2 -> System.out.println("✅ Marking task as completed (to implement)");
                case 3 -> System.out.println("🚨 Viewing personal alerts (to implement)");
                case 4 -> System.out.println("👋 Logged out successfully.");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 4);
    }
}
