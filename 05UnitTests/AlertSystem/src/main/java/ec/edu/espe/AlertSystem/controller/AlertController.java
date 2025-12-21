package ec.edu.espe.AlertSystem.controller;

import ec.edu.espe.AlertSystem.model.Task;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 *
 * @author Paulo Ramos
 */
public class AlertController {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");


    public static void printPerformanceSummary(List<Task> tasks, String title) {
        Date today = Validation.truncateToDate(new Date());

        long total = tasks.size();
        long completed = tasks.stream()
                .filter(t -> "Completada".equalsIgnoreCase(t.getStatus()))
                .count();
        long pending = total - completed;

        long overdue = tasks.stream()
                .filter(t -> t.getDeliveryDate() != null
                && t.getDeliveryDate().before(today)
                && !"Completada".equalsIgnoreCase(t.getStatus()))
                .count();

        long upcoming = tasks.stream()
                .filter(t -> t.getDeliveryDate() != null
                && !"Completada".equalsIgnoreCase(t.getStatus())
                && t.getDeliveryDate().after(today)
                && t.getDeliveryDate().before(new Date(today.getTime() + 3L * 24 * 60 * 60 * 1000)))
                .count();

        System.out.println("\n===== " + title + " =====");
        System.out.println("Total de tareas: " + total);
        System.out.println("Tareas completadas: " + completed);
        System.out.println("Tareas pendientes: " + pending);
        System.out.println("Tareas vencidas: " + overdue);
        System.out.println("Tareas proximas a vencer (<=3 dias): " + upcoming);

        if (total > 0) {
            double completionRate = (completed * 100.0) / total;
            System.out.printf("Porcentaje de cumplimiento: %.2f%%\n", completionRate);

            String performanceLabel = completionRate >= 76 ? "Excelente"
                    : completionRate >= 51 ? "Bueno"
                            : completionRate >= 26 ? "Regular"
                                    : "Deficiente";

            System.out.println("Desempeno: " + performanceLabel);
        }
    }

    public static void printTasks(List<Task> tasks, String title) {
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("              " + title);
        System.out.println("========================================");
        System.out.printf("%-3s | %-25s | %-10s | %-20s | %-12s%n",
                "ID", "Descripción", "Estado", "Cliente", "Entrega");
        System.out.println("---+---------------------------+------------+----------------------+------------");

        for (Task task : tasks) {
            String entrega = (task.getDeliveryDate() != null ? SDF.format(task.getDeliveryDate()) : "No registrada");
            System.out.printf("%-3d | %-25s | %-10s | %-20s | %-12s%n",
                    task.getId(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getCustomerId() != null ? task.getCustomerId() : "No asignado",
                    entrega);
        }

        System.out.println("========================================");
    }

}
