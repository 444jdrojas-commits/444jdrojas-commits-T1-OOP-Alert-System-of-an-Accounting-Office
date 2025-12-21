package ec.edu.espe.AlertSystem.view;

import ec.edu.espe.AlertSystem.controller.AlertController;
import ec.edu.espe.AlertSystem.controller.DataServiceController;
import ec.edu.espe.AlertSystem.controller.Validation;
import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Invoice;
import ec.edu.espe.AlertSystem.model.Task;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Paulo Ramos
 */
public class AssistantView {

    private final Scanner scanner = new Scanner(System.in);
    private final DataServiceController dataService;
    private final Assistant loggedAssistant;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public AssistantView(Assistant assistant, DataServiceController dataService) {
        this.loggedAssistant = assistant;
        this.dataService = dataService;
    }

    public void showMenu() {
        int option;
        do {
            System.out.println("\n===== MENU ASISTENTE =====");
            System.out.println("Asistente: " + loggedAssistant.getName());
            System.out.println("1. Ver mis tareas");
            System.out.println("2. Marcar tarea como completada");
            System.out.println("3. Ver mis indicadores de rendimiento");
            System.out.println("4. Ver mis alertas");
            System.out.println("0. Cerrar sesion");
            option = Validation.readInt("Seleccione una opcion: ");

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
                    System.out.println("Cerrando sesion...");
                default ->
                    System.out.println("Opción invalida, intente nuevamente.");
            }
        } while (option != 0);
    }

    private List<Task> getMyTasks() {
        return dataService.getTasks().stream()
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
        String cliente = (task.getCustomer() != null) ? task.getCustomer().getName() : "Sin cliente";

        return index + ". " + task.getDescription()
                + " | Estado: " + estado
                + " | Cliente: " + cliente
                + " | Entrega: " + fechaEntrega;
    }

    private void viewTasks() {
        List<Task> myTasks = getMyTasks();
        AlertController.printTasks(myTasks, "MIS TAREAS");
    }

    private void completeTask() {
        List<Task> myTasks = getMyTasks();

        if (myTasks.isEmpty()) {
            System.out.println("No tienes tareas para completar.");
            return;
        }

        viewTasks();
        int id = Validation.readInt("Ingrese el ID de la tarea a completar: ");

        Task task = myTasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);

        if (task == null) {
            System.out.println("No existe una tarea con el ID " + id);
            return;
        }

        if ("Completada".equalsIgnoreCase(task.getStatus())) {
            System.out.println("La tarea '" + task.getDescription() + "' ya está marcada como completada.");
            return;
        }

        task.setStatus("Completada");
        dataService.updateTask(task);
        System.out.println("La tarea '" + task.getDescription() + "' fue marcada como completada.");

        float amountPaid = Validation.readPositiveFloat("Ingrese el monto de la factura: ");

        int invoiceNumber = dataService.getInvoices().size() + 1;
        Date paymentDate = new Date();
        String details = "Factura generada por tarea #" + task.getId()
                + " - " + task.getDescription()
                + " | Cliente: " + task.getCustomer();
        String status = Validation.confirm("¿La factura está pagada? (s/n): ") ? "Pagada" : "Pendiente";

        Invoice invoice = new Invoice(invoiceNumber, paymentDate, amountPaid, details, status);
        dataService.addInvoice(invoice);

        System.out.println("Factura generada automáticamente:");
        System.out.println(invoice);

        viewTasks();
    }

    private void viewPerformanceIndicator() {
        List<Task> myTasks = getMyTasks();
        AlertController.printPerformanceSummary(myTasks, "MIS INDICADORES DE RENDIMIENTO");
    }

    private void viewAlerts() {
        List<Task> tasks = dataService.getTasks();
        Date today = Validation.truncateToDate(new Date());

        System.out.println("\n===== Alertas de Tareas =====");

        if (tasks.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }

        tasks.sort(Comparator.comparing(Task::getDeliveryDate,
                Comparator.nullsLast(Comparator.naturalOrder())));

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

        long pending = tasks.stream()
                .filter(t -> !"Completada".equalsIgnoreCase(t.getStatus()))
                .count();

        System.out.println("Resumen: Pendientes=" + pending + " | Vencidas=" + overdue + " | Próximas a vencer=" + upcoming);

        for (Task task : tasks) {
            String deliveryStr = (task.getDeliveryDate() != null ? SDF.format(task.getDeliveryDate()) : "No registrada");
            long diffDays = Long.MIN_VALUE;

            if (task.getDeliveryDate() != null) {
                Date delivery = Validation.truncateToDate(task.getDeliveryDate());
                long diffMillis = delivery.getTime() - today.getTime();
                diffDays = diffMillis / (1000L * 60 * 60 * 24);
            }

            String assigned = (task.getAssignedTo() == null || task.getAssignedTo().isBlank())
                    ? "No asignado"
                    : dataService.findAssistantNameById(task.getAssignedTo());

            String cliente = (task.getCustomerId() != null ? task.getCustomerId() : "No asignado");
            System.out.println("\nTarea #" + task.getId() + " - " + task.getDescription());
            System.out.println("   Cliente: " + cliente);
            System.out.println("   Asistente: " + assigned);
            System.out.println("   Estado: " + task.getStatus());
            System.out.println("   Fecha de entrega: " + deliveryStr);

            if (task.getDeliveryDate() != null) {
                if (diffDays < 0) {
                    System.out.println("VENCIDA hace " + Math.abs(diffDays) + (Math.abs(diffDays) == 1 ? " día" : " días"));
                } else if (diffDays == 0) {
                    System.out.println("Es para HOY");
                } else if (diffDays <= 3) {
                    System.out.println("Proxima a vencer en " + diffDays + (diffDays == 1 ? " dia" : " dias"));
                } else {
                    System.out.println("Se entrega en " + diffDays + (diffDays == 1 ? " dia" : " dias"));
                }
            } else {
                System.out.println("   Sin fecha de entrega registrada");
            }
            System.out.println("-------------------------------------------");
        }
    }

}
