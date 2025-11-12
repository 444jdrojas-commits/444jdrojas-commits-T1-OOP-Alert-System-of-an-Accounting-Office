/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.TaskController;
import ec.edu.espe.AlertSystem.model.Task;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Paulo Ramos
 */

public class AssistantView {
    
    private final Scanner scanner = new Scanner(System.in);
    private final TaskController taskController = new TaskController();
    private final String assistantName; // 👈 nombre del asistente logueado

    // Constructor recibe el nombre/usuario del asistente
    public AssistantView(String assistantName) {
        this.assistantName = assistantName;
    }

    // 👇 Este reemplaza al showAssistantMenu del primer código
    public void showMenu() {
        int option;
        do {
            System.out.println("\n===== MENÚ ASISTENTE =====");
            System.out.println("1. Ver mis tareas");
            System.out.println("2. Marcar tarea como completada");
            System.out.println("3. Ver mis indicadores de rendimiento");
            System.out.println("4. Ver mis alertas");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (option) {
                case 1 -> viewTasks();
                case 2 -> completeTask();
                case 3 -> viewPerformanceIndicator();
                case 4 -> viewAlerts();
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (option != 0);
    }

    // 👇 Opción 1: Ver solo las tareas asignadas a este asistente
    private void viewTasks() {
        List<Task> tasks = taskController.getAllTasks();
        List<Task> myTasks = tasks.stream()
                                  .filter(t -> t.getAssignedTo().equalsIgnoreCase(assistantName))
                                  .toList();

        if (myTasks.isEmpty()) {
            System.out.println("No tienes tareas asignadas.");
        } else {
            System.out.println("\n--- MIS TAREAS ---");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int i = 1;
            for (Task task : myTasks) {
                System.out.println(i++ + ". " + task.getDescription() +
                        " | Estado: " + task.getStatus() +
                        " | Cliente: " + task.getCustomer() +
                        " | Entrega: " + sdf.format(task.getDeliveryDate()));
            }
        }
    }

    // 👇 Opción 2: Completar solo tareas propias
    private void completeTask() {
        List<Task> tasks = taskController.getAllTasks();
        List<Task> myTasks = tasks.stream()
                                  .filter(t -> t.getAssignedTo().equalsIgnoreCase(assistantName))
                                  .toList();

        if (myTasks.isEmpty()) {
            System.out.println("No tienes tareas para completar.");
            return;
        }

        viewTasks();
        System.out.print("Seleccione el número de la tarea a completar: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > myTasks.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Task task = myTasks.get(index - 1);
        task.setStatus("Completada");
        taskController.saveTasks(); // 👈 guarda cambios en JSON
        System.out.println("La tarea '" + task.getDescription() + "' fue marcada como completada.");
    }

    // 👇 Opción 3: Indicadores personales
    private void viewPerformanceIndicator() {
        List<Task> tasks = taskController.getAllTasks();
        List<Task> myTasks = tasks.stream()
                                  .filter(t -> t.getAssignedTo().equalsIgnoreCase(assistantName))
                                  .toList();

        long total = myTasks.size();
        long completed = myTasks.stream().filter(t -> "Completada".equalsIgnoreCase(t.getStatus())).count();

        System.out.println("\n--- MIS INDICADORES DE RENDIMIENTO ---");
        System.out.println("Total de tareas: " + total);
        System.out.println("Tareas completadas: " + completed);
        System.out.println("Tareas pendientes: " + (total - completed));
    }

    // 👇 Opción 4: Alertas personales
    private void viewAlerts() {
        List<Task> tasks = taskController.getAllTasks();
        List<Task> myTasks = tasks.stream()
                                  .filter(t -> t.getAssignedTo().equalsIgnoreCase(assistantName))
                                  .toList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("\n--- MIS ALERTAS ---");

        boolean hasAlerts = false;
        for (Task task : myTasks) {
            if (!"Completada".equalsIgnoreCase(task.getStatus())) {
                System.out.println("⚠️ Tarea pendiente: " + task.getDescription() +
                        " | Fecha de entrega: " + sdf.format(task.getDeliveryDate()));
                hasAlerts = true;
            }
        }

        if (!hasAlerts) {
            System.out.println("No tienes alertas, todas tus tareas están completadas.");
        }
    }
}
