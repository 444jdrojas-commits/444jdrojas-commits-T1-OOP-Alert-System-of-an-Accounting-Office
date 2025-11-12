package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author JOSUE
 */

import ec.edu.espe.AlertSystem.model.*;
import java.util.*;

import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Invoice;
import ec.edu.espe.AlertSystem.model.PerformanceIndicator;
import ec.edu.espe.AlertSystem.model.Task;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskController {

    private final List<Task> tasks;


    public TaskController() {
        this.tasks = new ArrayList<>();
    }

  
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(" Tarea agregada correctamente: " + task.getDescription());
    }

    
    public List<Task> getTasksByAssistant(Assistant assistant) {
        
        return tasks;
    }

    public List<Task> getTasksByAssistantAndStatus(Assistant assistant, String status) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getStatus().equalsIgnoreCase(status)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }


    public boolean markTaskCompleted(String taskId, Assistant assistant) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(taskId)) {
                task.setStatus("completed");
                System.out.println("La tarea '" + task.getDescription() + "' fue marcada como completada.");
                return true;
            }
        }
        System.out.println("️No se encontró ninguna tarea con ese ID o descripción.");
        return false;
    }

    public Invoice getInvoiceByTaskId(String taskId) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(taskId)) {
                return new Invoice(
                    (int)(Math.random() * 10000),          
                    new Date(),                           
                    120.75f,                               
                    "Pago correspondiente a la tarea: " + task.getDescription(),
                    "Pagado"
                );
            }
        }
        System.out.println("No se encontró la tarea para generar la factura.");
        return null;
    }


    public PerformanceIndicator getPerformanceForAssistant(Assistant assistant) {
        double completionRate = 0.88;
        double averageDelay = 1.3;
        double efficiencyPerDay = 0.84;
        double finalScore = 8.2;
        String comments = "Buen desempeño general. Puede mejorar la puntualidad.";

        return new PerformanceIndicator(
            "Noviembre 2025",
            assistant,
            completionRate,
            averageDelay,
            efficiencyPerDay,
            finalScore,
            comments
        );
    }


    public List<String> getPendingAlertsForAssistant(Assistant assistant) {
        List<String> alerts = new ArrayList<>();
        Date today = new Date();

        for (Task task : tasks) {
            if (task.getStatus().equalsIgnoreCase("pending")) {
                long diffDays = (task.getDeliveryDate().getTime() - today.getTime()) / (1000 * 60 * 60 * 24);
                if (diffDays <= 2) {
                    alerts.add("La tarea '" + task.getDescription() + "' vence pronto (" + diffDays + " días restantes).");
                } else {
                    alerts.add("La tarea '" + task.getDescription() + "' sigue pendiente.");
                }
            }
        }
        return alerts;
    }


    public boolean updateTaskStatusByAssistant(String taskId, Assistant assistant, String newStatus, String note) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(taskId)) {
                task.setStatus(newStatus);
                System.out.println("Estado de la tarea '" + task.getDescription() + "' actualizado a '" + newStatus + "'.");
                System.out.println("Nota del asistente: " + note);
                return true;
            }
        }
        System.out.println("No se encontró la tarea para actualizar.");
        return false;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void printAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }

        System.out.println("\n===== LISTA DE TAREAS =====");
        for (Task task : tasks) {
            System.out.println("Descripción: " + task.getDescription());
            System.out.println("Creación: " + task.getCreationDate());
            System.out.println("Entrega: " + task.getDeliveryDate());
            System.out.println("Cliente: " + task.getCustomer());
            System.out.println("Estado: " + task.getStatus());
            System.out.println("---------------------------");
        }
    }
}
