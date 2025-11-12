package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.TaskController;
import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Invoice;
import ec.edu.espe.AlertSystem.model.PerformanceIndicator;
import ec.edu.espe.AlertSystem.model.Task;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Josue Rojas
 */
public class AsistantView {
    private final TaskController taskController = new TaskController();
    private final Scanner sc = new Scanner(System.in);
    private Assistant loggedAssistant;

    public AsistantView() {}
    public AsistantView(Assistant assistant) { this.loggedAssistant = assistant; }
    public void setLoggedAssistant(Assistant assistant) { this.loggedAssistant = assistant; }

    public void showAssistantMenu() {
        if (loggedAssistant == null) {
            System.out.println("No hay asistente autenticado. Regrese al login.");
            return;
        }
        int option;
        do {
            System.out.println("\n===== ASSISTANT MENU =====");
            System.out.println("Bienvenido, " + loggedAssistant.getName());
            System.out.println("1. Ver tareas");
            System.out.println("2. Completar tarea");
            System.out.println("3. Ver factura por tarea");
            System.out.println("4. Ver indicador de desempeño");
            System.out.println("5. Ver alertas pendientes");
            System.out.println("6. Actualizar estado/progreso");
            System.out.println("0. Cerrar sesión");
            System.out.print("Elija una opción: ");
            String input = sc.nextLine().trim();
            option = input.isEmpty() ? -1 : parseIntSafe(input);

            switch (option) {
                case 1 -> handleViewTasks();
                case 2 -> handleCompleteTask();
                case 3 -> handleViewInvoice();
                case 4 -> handleViewPerformance();
                case 5 -> handleReceiveAlerts();
                case 6 -> handleUpdateStatus();
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private void handleViewTasks() {
        System.out.println("\n--- Ver tareas ---");
        System.out.print("Filtrar por estado (pending, in_progress, completed) o Enter para todas: ");
        String status = sc.nextLine().trim();
        List<Task> tasks = status.isEmpty()
                ? taskController.getTasksByAssistant(loggedAssistant)
                : taskController.getTasksByAssistantAndStatus(loggedAssistant, status);

        if (tasks == null || tasks.isEmpty()) {
            System.out.println("No hay tareas para mostrar.");
        } else {
            printTasksTable(tasks);
        }
        pause();
    }

    private void handleCompleteTask() {
        System.out.println("\n--- Completar tarea ---");
        System.out.print("ID/Descripción de la tarea a completar: ");
        String id = sc.nextLine().trim();
        boolean ok = taskController.markTaskCompleted(id, loggedAssistant);
        System.out.println(ok ? "Tarea marcada como COMPLETADA." : "No se pudo completar (verifique ID).");
        pause();
    }

    private void handleViewInvoice() {
        System.out.println("\n--- Ver factura por tarea ---");
        System.out.print("ID/Descripción de la tarea: ");
        String id = sc.nextLine().trim();
        Invoice invoice = taskController.getInvoiceByTaskId(id);
        if (invoice == null) System.out.println("No hay factura para esa tarea.");
        else printInvoice(invoice);
        pause();
    }

    private void handleViewPerformance() {
        System.out.println("\n--- Indicador de desempeño ---");
        PerformanceIndicator kpi = taskController.getPerformanceForAssistant(loggedAssistant);
        if (kpi == null) System.out.println("No hay indicadores disponibles.");
        else printPerformance(kpi);
        pause();
    }

    private void handleReceiveAlerts() {
        System.out.println("\n--- Alertas pendientes ---");
        List<String> alerts = taskController.getPendingAlertsForAssistant(loggedAssistant);
        if (alerts == null || alerts.isEmpty()) System.out.println("Sin alertas.");
        else alerts.forEach(a -> System.out.println("- " + a));
        pause();
    }

    private void handleUpdateStatus() {
        System.out.println("\n--- Actualizar estado/progreso ---");
        System.out.print("ID/Descripción de la tarea: ");
        String id = sc.nextLine().trim();
        System.out.print("Nuevo estado (pending/in_progress/completed): ");
        String newStatus = sc.nextLine().trim();
        System.out.print("Nota/avance (opcional): ");
        String note = sc.nextLine().trim();
        boolean ok = taskController.updateTaskStatusByAssistant(id, loggedAssistant, newStatus, note);
        System.out.println(ok ? "Estado actualizado." : "No se pudo actualizar (verifique ID).");
        pause();
    }

    // helpers
    private void printTasksTable(List<Task> tasks) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        System.out.printf("%-20s %-12s %-12s %-12s %-12s%n",
                "Descripción/ID", "Creación", "Entrega", "Cliente", "Estado");
        for (Task t : tasks) {
            System.out.printf("%-20s %-12s %-12s %-12s %-12s%n",
                    safe(t.getDescription()),
                    t.getCreationDate()==null?"-":fmt.format(t.getCreationDate()),
                    t.getDeliveryDate()==null?"-":fmt.format(t.getDeliveryDate()),
                    safe(t.getCustomer()),
                    safe(t.getStatus()));
        }
    }

    private void printInvoice(Invoice inv) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Factura #" + inv.getInvoiceNumber());
        System.out.println("Monto      : " + inv.getAmountPaid());
        System.out.println("Fecha pago : " + (inv.getPaymentDate()==null?"-":fmt.format(inv.getPaymentDate())));
        System.out.println("Estado     : " + inv.getStatus());
        System.out.println("Detalles   : " + inv.getDetails());
    }

    private void printPerformance(PerformanceIndicator kpi) {
        System.out.println("Periodo           : " + kpi.getPeriod());
        System.out.println("Completion Rate   : " + kpi.getCompletionRate());
        System.out.println("Average Delay     : " + kpi.getAverageDelay());
        System.out.println("Efficiency/Day    : " + kpi.getEfficiencyPerDay());
        System.out.println("Final Score       : " + kpi.getFinalScore());
        System.out.println("Comentarios       : " + kpi.getComments());
    }

    private String safe(Object o){ return o==null? "-" : o.toString(); }
    private int parseIntSafe(String s){ try{ return Integer.parseInt(s);}catch(Exception e){ return -1;} }
    private void pause(){ System.out.print("\nPresione Enter para continuar..."); sc.nextLine(); }
}

package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.TaskController;
import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Invoice;
import ec.edu.espe.AlertSystem.model.PerformanceIndicator;
import ec.edu.espe.AlertSystem.model.Task;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Josue Rojas
 */
public class AsistantView {
    private final TaskController taskController = new TaskController();
    private final Scanner sc = new Scanner(System.in);
    private Assistant loggedAssistant;

    public AsistantView() {}
    public AsistantView(Assistant assistant) { this.loggedAssistant = assistant; }
    public void setLoggedAssistant(Assistant assistant) { this.loggedAssistant = assistant; }

    public void showAssistantMenu() {
        if (loggedAssistant == null) {
            System.out.println("No hay asistente autenticado. Regrese al login.");
            return;
        }
        int option;
        do {
            System.out.println("\n===== ASSISTANT MENU =====");
            System.out.println("Bienvenido, " + loggedAssistant.getName());
            System.out.println("1. Ver tareas");
            System.out.println("2. Completar tarea");
            System.out.println("3. Ver factura por tarea");
            System.out.println("4. Ver indicador de desempeño");
            System.out.println("5. Ver alertas pendientes");
            System.out.println("6. Actualizar estado/progreso");
            System.out.println("0. Cerrar sesión");
            System.out.print("Elija una opción: ");
            String input = sc.nextLine().trim();
            option = input.isEmpty() ? -1 : parseIntSafe(input);

            switch (option) {
                case 1 -> handleViewTasks();
                case 2 -> handleCompleteTask();
                case 3 -> handleViewInvoice();
                case 4 -> handleViewPerformance();
                case 5 -> handleReceiveAlerts();
                case 6 -> handleUpdateStatus();
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private void handleViewTasks() {
        System.out.println("\n--- Ver tareas ---");
        System.out.print("Filtrar por estado (pending, in_progress, completed) o Enter para todas: ");
        String status = sc.nextLine().trim();
        List<Task> tasks = status.isEmpty()
                ? taskController.getTasksByAssistant(loggedAssistant)
                : taskController.getTasksByAssistantAndStatus(loggedAssistant, status);

        if (tasks == null || tasks.isEmpty()) {
            System.out.println("No hay tareas para mostrar.");
        } else {
            printTasksTable(tasks);
        }
        pause();
    }

    private void handleCompleteTask() {
        System.out.println("\n--- Completar tarea ---");
        System.out.print("ID/Descripción de la tarea a completar: ");
        String id = sc.nextLine().trim();
        boolean ok = taskController.markTaskCompleted(id, loggedAssistant);
        System.out.println(ok ? "Tarea marcada como COMPLETADA." : "No se pudo completar (verifique ID).");
        pause();
    }

    private void handleViewInvoice() {
        System.out.println("\n--- Ver factura por tarea ---");
        System.out.print("ID/Descripción de la tarea: ");
        String id = sc.nextLine().trim();
        Invoice invoice = taskController.getInvoiceByTaskId(id);
        if (invoice == null) System.out.println("No hay factura para esa tarea.");
        else printInvoice(invoice);
        pause();
    }

    private void handleViewPerformance() {
        System.out.println("\n--- Indicador de desempeño ---");
        PerformanceIndicator kpi = taskController.getPerformanceForAssistant(loggedAssistant);
        if (kpi == null) System.out.println("No hay indicadores disponibles.");
        else printPerformance(kpi);
        pause();
    }

    private void handleReceiveAlerts() {
        System.out.println("\n--- Alertas pendientes ---");
        List<String> alerts = taskController.getPendingAlertsForAssistant(loggedAssistant);
        if (alerts == null || alerts.isEmpty()) System.out.println("Sin alertas.");
        else alerts.forEach(a -> System.out.println("- " + a));
        pause();
    }

    private void handleUpdateStatus() {
        System.out.println("\n--- Actualizar estado/progreso ---");
        System.out.print("ID/Descripción de la tarea: ");
        String id = sc.nextLine().trim();
        System.out.print("Nuevo estado (pending/in_progress/completed): ");
        String newStatus = sc.nextLine().trim();
        System.out.print("Nota/avance (opcional): ");
        String note = sc.nextLine().trim();
        boolean ok = taskController.updateTaskStatusByAssistant(id, loggedAssistant, newStatus, note);
        System.out.println(ok ? "Estado actualizado." : "No se pudo actualizar (verifique ID).");
        pause();
    }

    // helpers
    private void printTasksTable(List<Task> tasks) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        System.out.printf("%-20s %-12s %-12s %-12s %-12s%n",
                "Descripción/ID", "Creación", "Entrega", "Cliente", "Estado");
        for (Task t : tasks) {
            System.out.printf("%-20s %-12s %-12s %-12s %-12s%n",
                    safe(t.getDescription()),
                    t.getCreationDate()==null?"-":fmt.format(t.getCreationDate()),
                    t.getDeliveryDate()==null?"-":fmt.format(t.getDeliveryDate()),
                    safe(t.getCustomer()),
                    safe(t.getStatus()));
        }
    }

    private void printInvoice(Invoice inv) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Factura #" + inv.getInvoiceNumber());
        System.out.println("Monto      : " + inv.getAmountPaid());
        System.out.println("Fecha pago : " + (inv.getPaymentDate()==null?"-":fmt.format(inv.getPaymentDate())));
        System.out.println("Estado     : " + inv.getStatus());
        System.out.println("Detalles   : " + inv.getDetails());
    }

    private void printPerformance(PerformanceIndicator kpi) {
        System.out.println("Periodo           : " + kpi.getPeriod());
        System.out.println("Completion Rate   : " + kpi.getCompletionRate());
        System.out.println("Average Delay     : " + kpi.getAverageDelay());
        System.out.println("Efficiency/Day    : " + kpi.getEfficiencyPerDay());
        System.out.println("Final Score       : " + kpi.getFinalScore());
        System.out.println("Comentarios       : " + kpi.getComments());
    }

    private String safe(Object o){ return o==null? "-" : o.toString(); }
    private int parseIntSafe(String s){ try{ return Integer.parseInt(s);}catch(Exception e){ return -1;} }
    private void pause(){ System.out.print("\nPresione Enter para continuar..."); sc.nextLine(); }
}
