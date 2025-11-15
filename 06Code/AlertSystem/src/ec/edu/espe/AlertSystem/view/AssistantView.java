package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.InvoiceController;
import ec.edu.espe.AlertSystem.controller.TaskController;
import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Invoice;
import ec.edu.espe.AlertSystem.model.Task;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Paulo Ramos
 */
public class AssistantView {

    private final Scanner scanner = new Scanner(System.in);
    private final TaskController taskController = new TaskController();
    private final Assistant loggedAssistant;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private final InvoiceController invoiceController = new InvoiceController();

    private Date truncateToDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public AssistantView(Assistant assistant) {
        this.loggedAssistant = assistant;
    }

    public void showMenu() {
        int option;
        do {
            System.out.println("\n===== MENÚ ASISTENTE =====");
            System.out.println("Asistente: " + loggedAssistant.getName());
            System.out.println("1. Ver mis tareas");
            System.out.println("2. Marcar tarea como completada");
            System.out.println("3. Ver mis indicadores de rendimiento");
            System.out.println("4. Ver mis alertas");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            option = readInt();

            switch (option) {
                case 1 ->
                    viewTasks();
                case 2 ->
                    completeTask();
                case 3 ->
                    viewPerformanceIndicator();
                case 4 ->
                    viewAlerts();
                case 0 ->
                    System.out.println("Cerrando sesión...");
                default ->
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (option != 0);
    }

    private List<Task> getMyTasks() {
        return taskController.getAllTasks().stream()
                .filter(t -> t.getAssignedTo() != null
                && t.getAssignedTo().equals(String.valueOf(loggedAssistant.getId())))
                .toList();
    }

    private String formatTask(Task task, int index) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaEntrega = (task.getDeliveryDate() != null)
                ? sdf.format(task.getDeliveryDate())
                : "Sin fecha";
        String estado = (task.getStatus() != null) ? task.getStatus() : "Sin estado";
        String cliente = (task.getCustomer() != null) ? task.getCustomer() : "Sin cliente";

        return index + ". " + task.getDescription()
                + " | Estado: " + estado
                + " | Cliente: " + cliente
                + " | Entrega: " + fechaEntrega;
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Ingrese un número: ");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private void viewTasks() {
        List<Task> myTasks = getMyTasks();

        if (myTasks.isEmpty()) {
            System.out.println("No tienes tareas asignadas.");
            return;
        }

        System.out.println("\n--- MIS TAREAS ---");
        for (int i = 0; i < myTasks.size(); i++) {
            System.out.println(formatTask(myTasks.get(i), i + 1));
        }
    }

    private void completeTask() {
    List<Task> myTasks = getMyTasks();

    if (myTasks.isEmpty()) {
        System.out.println("No tienes tareas para completar.");
        return;
    }

    viewTasks();
    System.out.print("Seleccione el número de la tarea a completar: ");
    int index = readInt();

    if (index < 1 || index > myTasks.size()) {
        System.out.println("Número inválido.");
        return;
    }

    Task task = myTasks.get(index - 1);

    if ("Completada".equalsIgnoreCase(task.getStatus())) {
        System.out.println("La tarea '" + task.getDescription() + "' ya está marcada como completada.");
        return;
    }

    task.setStatus("Completada");
    taskController.saveTasks();
    System.out.println("La tarea '" + task.getDescription() + "' fue marcada como completada.");

    float amountPaid = readPositiveFloat("Ingrese el monto de la factura: ");

    int invoiceNumber = invoiceController.generateNextNumber();
    Date paymentDate = new Date(); // fecha actual
    String details = "Factura generada por tarea #" + task.getId() + " - " + task.getDescription();
    String status = "Pagada";

    Invoice invoice = new Invoice(invoiceNumber, paymentDate, amountPaid, details, status);
    invoiceController.addInvoice(invoice);

    System.out.println("Factura generada automáticamente:");
    System.out.println(invoice);
}

    private void viewPerformanceIndicator() {
        List<Task> myTasks = getMyTasks();

        long total = myTasks.size();
        long completed = myTasks.stream()
                .filter(t -> t.getStatus() != null && "Completada".equalsIgnoreCase(t.getStatus()))
                .count();

        System.out.println("\n--- MIS INDICADORES DE RENDIMIENTO ---");
        System.out.println("Total de tareas: " + total);
        System.out.println("Tareas completadas: " + completed);
        System.out.println("Tareas pendientes: " + (total - completed));

        if (total > 0) {
            double completionRate = (completed * 100.0) / total;
            System.out.printf("Porcentaje completado: %.2f%%\n", completionRate);
        }
    }

    private void viewAlerts() {
        List<Task> tasks = taskController.getAllTasks();
        Date today = truncateToDate(new Date());

        System.out.println("\n===== Alertas de Tareas =====");

        if (tasks.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }

        for (Task task : tasks) {
            String deliveryStr = (task.getDeliveryDate() != null ? SDF.format(task.getDeliveryDate()) : "No registrada");
            long diffDays = Long.MIN_VALUE;

            if (task.getDeliveryDate() != null) {
                Date delivery = truncateToDate(task.getDeliveryDate());
                long diffMillis = delivery.getTime() - today.getTime();
                diffDays = diffMillis / (1000L * 60 * 60 * 24);
            }

            String assigned = (task.getAssignedTo() == null || task.getAssignedTo().isBlank()) ? "No asignado" : task.getAssignedTo();

            System.out.println("\nTarea #" + task.getId() + " - " + task.getDescription());
            System.out.println("   Cliente: " + task.getCustomer());
            System.out.println("   Asistente: " + assigned);
            System.out.println("   Fecha de entrega: " + deliveryStr);

            if (task.getDeliveryDate() != null) {
                if (diffDays < 0) {
                    System.out.println("   VENCIDA hace " + Math.abs(diffDays) + " día(s)");
                } else if (diffDays == 0) {
                    System.out.println("   Es para HOY");
                } else if (diffDays <= 3) {
                    System.out.println("   Próxima a vencer en " + diffDays + " día(s)");
                } else {
                    System.out.println("   Se entrega en " + diffDays + " día(s)");
                }
            } else {
                System.out.println("   Sin fecha de entrega registrada");
            }
            System.out.println("-------------------------------------------");
        }
    }
    
    private float readPositiveFloat(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        try {
            float value = Float.parseFloat(input);
            if (value <= 0) {
                System.out.println("El monto debe ser mayor que 0. Intente nuevamente.");
            } else {
                return value;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese un número válido (ejemplo: 120.50).");
        }
    }
}


}
