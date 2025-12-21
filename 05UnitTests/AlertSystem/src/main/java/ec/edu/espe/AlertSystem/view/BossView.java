package ec.edu.espe.AlertSystem.view;

/**
 *
 * @author Paulo Ramos
 */
import ec.edu.espe.AlertSystem.controller.*;
import ec.edu.espe.AlertSystem.model.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BossView {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_PATTERN);
    private static final Scanner scanner = new Scanner(System.in);

    private final DataServiceController dataServiceController;

    public BossView(DataServiceController dataServiceController) {
        this.dataServiceController = Objects.requireNonNull(dataServiceController);
    }

    public void showBossMenu() {
        int option;
        do {
            printMainMenu();
            option = Validation.readIntRange("Elige una opcion: ", 1, 7);

            switch (option) {
                case 1 ->
                    manageClient();
                case 2 ->
                    manageTasks();
                case 3 ->
                    listTasks();
                case 4 ->
                    viewPerformanceIndicators();
                case 5 ->
                    viewAlerts();
                case 6 ->
                    manageInvoices();
                case 7 ->
                    System.out.println("Sesion finalizada.");
                default ->
                    System.out.println("Opcion invalida.");
            }
        } while (option != 7);
    }

    private void printMainMenu() {
        System.out.println("\n===== Menu Jefe =====");
        System.out.println("1. Administrar Clientes");
        System.out.println("2. Administrar Tareas");
        System.out.println("3. Lista de tareas");
        System.out.println("4. Ver indicador de rendimiento");
        System.out.println("5. Ver alertas");
        System.out.println("6. Ver Facturas");
        System.out.println("7. Salir");
    }

    public void manageClient() {
        int option;
        do {
            printClientMenu();
            option = Validation.readIntRange("Elige una opcion: ", 0, 8);

            switch (option) {
                case 1 ->
                    addNaturalPerson();
                case 2 ->
                    addBusiness();
                case 3 ->
                    listNaturalPersons();
                case 4 ->
                    listBusinesses();
                case 5 ->
                    updateNaturalPerson();
                case 6 ->
                    updateBusiness();
                case 7 ->
                    deleteNaturalPerson();
                case 8 ->
                    deleteBusiness();
                case 0 ->
                    System.out.println("Saliendo del menu de clientes...");
                default ->
                    System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private void printClientMenu() {
        System.out.println("\n--- Menu Clientes ---");
        System.out.println("1. Anadir Persona Natural");
        System.out.println("2. Anadir Empresa");
        System.out.println("3. Lista de Personas Naturales");
        System.out.println("4. Lista Empresas");
        System.out.println("5. Actualizar Persona Natural");
        System.out.println("6. Actualizar Empresa");
        System.out.println("7. Eliminar Persona Natural");
        System.out.println("8. Eliminar Empresa");
        System.out.println("0. Salir");
    }

    private void addNaturalPerson() {
        try {
            System.out.println("\n--- Anadir Persona Natural ---");
            String name = Validation.readStringWithPattern("Nombre: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
                    "Nombre invalido. Solo letras y espacios.");

            String id = Validation.readStringWithPattern("Cedula: ", "\\d{10}",
                    "Cedula inválida. Debe contener 10 dígitos.");

            String nationality = Validation.readString("Nacionalidad: ");

            Date birthDate = Validation.readDateOptional("Fecha de nacimiento (yyyy-MM-dd): ");

            String phone = Validation.readStringWithPattern("Numero de telefono: ", "\\d{9,10}",
                    "Telefono invaido. Debe contener 9 o 10 dígitos.");

            String email = Validation.readStringWithPattern("Email: ", "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$",
                    "Email invalido. Ejemplo: usuario@dominio.com");

            String occ = Validation.readStringWithPattern("Ocupación: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
                    "Ocupacion invalida. Solo letras y espacios.");

            String gender;
            do {
                gender = Validation.readString("Genero (Hombre/Mujer): ");
                if (!gender.equalsIgnoreCase("Hombre") && !gender.equalsIgnoreCase("Mujer")) {
                    System.out.println("Genero invalido. Solo se permite 'Hombre' o 'Mujer'.");
                    gender = null;
                }
            } while (gender == null);

            System.out.println("Ingrese la direccion");
            String city = Validation.readStringWithPattern("Ciudad: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Ciudad inválida. Solo letras y espacios.");
            String street = Validation.readString("Calle: ");
            String sector = Validation.readString("Sector: ");

            if (dataServiceController.findNaturalById(id) != null) {
                System.out.println("Ya existe una persona con esa cedula.");
                return;
            }

            Address address = new Address(city, street, sector);
            NaturalPerson naturalPerson = new NaturalPerson(name, id, nationality, birthDate, phone, email, occ, gender, address);

            dataServiceController.addNaturalPerson(naturalPerson);

            System.out.println("Persona natural anadida exitosamente!");

        } catch (Exception e) {
            System.out.println("Error inesperado al crear persona: " + e.getMessage());
        }
    }

    private void addBusiness() {
        try {
            System.out.println("\n--- Anadir Empresa ---");
            String name = Validation.readStringWithPattern("Nombre de la empresa: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Nombre inválido. Solo letras y espacios.");
            String legalRep = Validation.readStringWithPattern("Representante Legal: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Representante inválido. Solo letras y espacios.");
            String type = Validation.readString("Tipo de empresa: ");

            System.out.println("Ingrese la direccion");
            String city = Validation.readStringWithPattern("Ciudad: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Ciudad inválida. Solo letras.");
            String street = Validation.readString("Calle: ");
            String sector = Validation.readStringWithPattern("Sector: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Sector inválido.");

            Address address = new Address(city, street, sector);

            String phone = Validation.readStringWithPattern("Numero de telefono: ", "\\d{9,10}", "Telefono invalido. Debe contener 9 o 10 dígitos.");
            String email = Validation.readStringWithPattern("Email: ", "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", "Email invalido. Ejemplo: usuario@dominio.com");
            String ruc = Validation.readStringWithPattern("RUC: ", "\\d{13}", "RUC inválido. Debe tener 13 dígitos.");

            System.out.print("¿Desea registrar una auditoria inicial para esta empresa? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            Audit audit = null;
            if (respuesta.equals("s")) {
                Date auditDate = Validation.readDateRequired("Fecha de la auditoría (yyyy-MM-dd)");

                String hour = Validation.readTimeRequired("Hora de la auditoría (HH:mm)");

                String description = Validation.readString("Descripcion de la auditoria: ");

                audit = new Audit(auditDate, hour, null, description);
            }

            Business business = new Business(name, legalRep, type, address, phone, email, ruc, audit);

            if (audit != null) {
                audit.setBusines(business);
            }

            dataServiceController.addBusiness(business);
            System.out.println("Empresa anadida exitosamente!");

        } catch (Exception e) {
            System.out.println("Error inesperado al crear empresa: " + e.getMessage());
        }
    }

    public void manageTasks() {
        int option;
        do {
            printTaskMenu();
            option = Validation.readIntRange("Elige una opción: ", 1, 4);
            switch (option) {
                case 1 ->
                    createTask();
                case 2 ->
                    updateTask();
                case 3 ->
                    deleteTask();
                case 4 ->
                    System.out.println("Volviendo...");
                default ->
                    System.out.println("Opcion invalida");
            }
        } while (option != 4);
    }

    private void printTaskMenu() {
        System.out.println("===== Administrar Tareas =====");
        System.out.println("1. Crear tarea");
        System.out.println("2. Actualizar tarea");
        System.out.println("3. Eliminar tarea");
        System.out.println("4. Volver");
    }

    public void manageInvoices() {
        int option;
        do {
            printInvoiceMenu();
            option = Validation.readInt("Elige una opcion: ");

            switch (option) {
                case 1 ->
                    listInvoices();
                case 2 ->
                    markInvoiceAsPaid();
                case 0 ->
                    System.out.println("Saliendo del menu de facturas...");
                default ->
                    System.out.println("Opcion invalida.");
            }
        } while (option != 0);
    }

    private void printInvoiceMenu() {
        System.out.println("\n===== Menu Facturas =====");
        System.out.println("1. Ver facturas");
        System.out.println("2. Marcar factura como pagada");
        System.out.println("0. Volver");
    }

    public void createTask() {
        System.out.println("\n===== Crear Tarea =====");

        int id = dataServiceController.generateNextId();
        String description = Validation.readString("Descripcion: ");

        Customer customerObj;
        while (true) {
            String customerName = Validation.readString("Cliente (nombre): ").trim();
            customerObj = dataServiceController.findCustomerByName(customerName);

            if (customerObj != null) {
                break;
            }
            System.out.println("Cliente no existe. Ingrese uno válido.");
        }

        Date deliveryDate = Validation.readDateOptional("Fecha de entrega (yyyy-MM-dd): ");

        Task newTask = new Task(id, description, new Date(), deliveryDate, "Pendiente", customerObj.getName(), customerObj, null, null);

        if (Validation.confirm("¿Desea registrar un documento para esta tarea? (s/n): ")) {
            String docName = Validation.readString("Nombre del documento: ");
            String docType = Validation.readString("Tipo de documento: ");
            String docStatus = Validation.readString("Estado del documento: ");
            Date reviewDate = Validation.readDateRequired("Dia de revision (yyyy-MM-dd): ");
            String details = Validation.readString("Detalles del documento: ");

            Document document = new Document(docName, docType, docStatus, reviewDate, details);
            newTask.setDocument(document);
        }

        if (Validation.confirm("¿Asignar asistente ahora? (s/n): ")) {
            assignAssistantToTask(newTask);
        }

        boolean added = dataServiceController.addTask(newTask);
        System.out.println(added ? "Tarea creada con ID: " + newTask.getId() : "No se pudo crear la tarea.");
    }

    private void assignAssistantToTask(Task task) {
        System.out.println("\n===== Asistentes Disponibles =====");

        List<Assistant> assistantList = dataServiceController.getAssistants();
        if (assistantList.isEmpty()) {
            System.out.println("No hay asistentes registrados.");
            return;
        }

        assistantList.forEach(a -> System.out.println("ID: " + a.getId() + " - " + a.getName()));

        Assistant selected = null;
        while (selected == null) {
            int assistantId = Validation.readInt("Ingrese el ID del asistente: ");
            for (Assistant a : assistantList) {
                if (a.getId() == assistantId) {
                    selected = a;
                    break;
                }
            }
            if (selected == null) {
                System.out.println("ID de asistente no válido. Intente de nuevo.");
            } else {
                task.setAssignedTo(String.valueOf(selected.getId()));
                System.out.println("Asignado a: " + selected.getName());
            }
        }
    }

    private void updateNaturalPerson() {
        String identification = Validation.readString("Ingrese la cedula de la persona a actualizar: ");
        NaturalPerson person = dataServiceController.findNaturalById(identification);
        if (person == null) {
            System.out.println("No se encontro la persona con esa cedula.");
            return;
        }

        System.out.println("Persona encontrada: " + person);
        System.out.println("Dejar en blanco para mantener el valor actual.");

        String newPhone = Validation.readOptionalPattern(
                "Nuevo telefono (" + person.getPhone() + "): ",
                "\\d{9,10}",
                "Telefono invalido. Debe contener 9 o 10 digitos."
        );

        String newEmail = Validation.readOptionalPattern(
                "Nuevo email (" + person.getEmail() + "): ",
                "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$",
                "Email inválido."
        );

        String newOccupation = Validation.readOptional(
                "Nueva ocupacion (" + person.getOccupation() + "): "
        );

        if (!newPhone.isEmpty()) {
            person.setPhone(newPhone);
        }
        if (!newEmail.isEmpty()) {
            person.setEmail(newEmail);
        }
        if (!newOccupation.isEmpty()) {
            person.setOccupation(newOccupation);
        }

        boolean updated = dataServiceController.updateNaturalPerson(person);
        System.out.println(updated ? "¡Persona actualizada correctamente!" : "Error al actualizar la persona.");
    }

    private void updateBusiness() {
        String ruc = Validation.readString("Ingrese el RUC de la empresa a actualizar: ");
        Business business = dataServiceController.findBusinessByRuc(ruc);

        if (business == null) {
            System.out.println("No se encontro la empresa con ese RUC.");
            return;
        }

        System.out.println("Dejar en blanco para mantener el valor actual.");
        String name = Validation.readOptionalPattern("Nuevo nombre (" + business.getName() + "): ",
                "^[a-zA-ZáéíóúÁÉÍÓÚñÑ .]+$", "Nombre invalido. Solo letras y espacios.");
        String phone = Validation.readOptionalPattern("Nuevo telefono (" + business.getPhone() + "): ",
                "\\d{9,10}", "Teléfono inválido. Debe contener 9 o 10 dígitos.");
        String email = Validation.readOptionalPattern("Nuevo email (" + business.getEmail() + "): ",
                "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", "Email invalido.");
        String legalRep = Validation.readOptionalPattern("Nuevo representante legal (" + business.getLegalRepresentative() + "): ",
                "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Representante inválido.");
        String type = Validation.readOptionalPattern("Nuevo tipo de empresa (" + business.getTypeBusiness() + "): ",
                "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Tipo invaido.");
        Address address = business.getAddress();
        Audit audit = business.getAudit();
        Business updatedBusiness = new Business(
                name.isEmpty() ? business.getName() : name,
                legalRep.isEmpty() ? business.getLegalRepresentative() : legalRep,
                type.isEmpty() ? business.getTypeBusiness() : type,
                address,
                phone.isEmpty() ? business.getPhone() : phone,
                email.isEmpty() ? business.getEmail() : email,
                ruc,
                audit
        );

        boolean updated = dataServiceController.updateBusiness(updatedBusiness);

        if (updated) {
            System.out.println("Empresa actualizada correctamente!");

            if (Validation.confirm("¿Desea registrar una auditoria para esta actualizacion? (s/n): ")) {
                Date auditDate = null;
                String hour = null;

                while (auditDate == null) {
                    String fechaStr = Validation.readString("Ingrese la fecha de la auditoria (yyyy-MM-dd): ");
                    try {
                        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

                        sdfDate.setLenient(false);
                        auditDate = sdfDate.parse(fechaStr);
                    } catch (Exception e) {
                        System.out.println("Fecha invalida. Use el formato yyyy-MM-dd (ejemplo: 2025-11-14).");
                    }
                }

                while (hour == null) {
                    String hourStr = Validation.readString("Ingrese la hora de la auditoria (HH:mm): ");
                    try {
                        if (!hourStr.matches("^([01]\\d|2[0-3]):[0-5]\\d$")) {
                            throw new IllegalArgumentException();
                        }
                        hour = hourStr;
                    } catch (Exception e) {
                        System.out.println("Hora invalida. Use el formato HH:mm (ejemplo: 22:04).");
                    }
                }

                String description = Validation.readString("Descripcion de la auditoria: ");

                Audit newAudit = new Audit(auditDate, hour, updatedBusiness, description);
                updatedBusiness.setAudit(newAudit);

                dataServiceController.updateBusiness(updatedBusiness);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("Auditoria registrada:");
                System.out.println("   Fecha: " + sdf.format(newAudit.getAuditDate()));
                System.out.println("   Hora: " + newAudit.getHour());
                System.out.println("   Descripcion: " + newAudit.getDescription());
            }

        } else {
            System.out.println("No se pudo actualizar la empresa.");
        }
    }

    private void deleteNaturalPerson() {
        String id = Validation.readString("Ingrese la cedula de la persona a eliminar: ");
        if (!Validation.confirm("¿Confirma eliminar la persona con cedula " + id + "? (s/n): ")) {
            return;
        }
        boolean removed = dataServiceController.removeNaturalById(id);
        System.out.println(removed ? "Persona eliminada correctamente!" : "No se encontro la persona con esa cedula.");
    }

    private void deleteBusiness() {
        String ruc = Validation.readString("Ingrese el RUC de la empresa a eliminar: ");
        if (!Validation.confirm("¿Confirma eliminar la empresa con RUC " + ruc + "? (s/n): ")) {
            return;
        }
        boolean removed = dataServiceController.removeBusinessByRuc(ruc);
        System.out.println(removed ? "Empresa eliminada correctamente!" : "No se encontro la empresa con ese RUC.");
    }

    private void listNaturalPersons() {
        System.out.println("\n--- Personas Naturales ---");
        List<NaturalPerson> list = dataServiceController.getNaturalPersons();
        if (list.isEmpty()) {
            System.out.println("No hay personas registradas.");
        }
        list.forEach(System.out::println);
    }

    private void listBusinesses() {
        System.out.println("\n--- Empresas ---");
        List<Business> list = dataServiceController.getBusinesses();
        if (list.isEmpty()) {
            System.out.println("No hay empresas registradas.");
        }
        list.forEach(System.out::println);
    }

    private void listTasks() {
        List<Task> allTasks = dataServiceController.getTasks();
        AlertController.printTasks(allTasks, "TODAS LAS TAREAS");
    }

    private void viewPerformanceIndicators() {
        List<Task> tasks = dataServiceController.getTasks();
        AlertController.printPerformanceSummary(tasks, "Indicadores de Desempeño");

        System.out.println("\n--- Desempeño por asistente ---");
        Map<String, Long> tasksByAssistant = tasks.stream()
                .filter(t -> t.getAssignedTo() != null && !t.getAssignedTo().isBlank())
                .collect(Collectors.groupingBy(Task::getAssignedTo, Collectors.counting()));

        tasksByAssistant.forEach((assistant, count) -> {
            long completedByAssistant = tasks.stream()
                    .filter(t -> assistant.equals(t.getAssignedTo()) && "Completada".equalsIgnoreCase(t.getStatus()))
                    .count();
            double rate = count > 0 ? (completedByAssistant * 100.0 / count) : 0;

            String performanceLabel = rate >= 76 ? "Excelente"
                    : rate >= 51 ? "Bueno"
                            : rate >= 26 ? "Regular"
                                    : "Deficiente";

            System.out.println("Asistente: " + assistant
                    + " | Tareas asignadas: " + count
                    + " | Completadas: " + completedByAssistant
                    + " | Cumplimiento: " + String.format("%.2f%%", rate)
                    + " | Desempeño: " + performanceLabel);
        });

        System.out.println("\n--- Desempeño por cliente ---");
        Map<String, List<Task>> tasksByClient = tasks.stream()
                .filter(t -> t.getCustomerId() != null && !t.getCustomerId().isBlank())
                .collect(Collectors.groupingBy(Task::getCustomerId));

        tasksByClient.forEach((clientName, clientTasks) -> {
            long completedByClient = clientTasks.stream()
                    .filter(t -> "Completada".equalsIgnoreCase(t.getStatus()))
                    .count();
            long pendingByClient = clientTasks.size() - completedByClient;
            double rate = clientTasks.isEmpty() ? 0 : (completedByClient * 100.0 / clientTasks.size());

            System.out.println("Cliente: " + clientName
                    + " | Tareas: " + clientTasks.size()
                    + " | Completadas: " + completedByClient
                    + " | Pendientes: " + pendingByClient
                    + " | Cumplimiento: " + String.format("%.2f%%", rate));
        });
    }

    private void viewAlerts() {
        List<Task> tasks = dataServiceController.getTasks();
        Date today = Validation.truncateToDate(new Date());
        TaskAlertController alertService = new TaskAlertController();

        System.out.println("\n===== Alertas de Tareas =====");

        if (tasks.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }

        tasks.sort(Comparator.comparing(task -> {
            Date delivery = task.getDeliveryDate();
            return (delivery != null) ? delivery : new Date(Long.MAX_VALUE);
        }));

        for (Task task : tasks) {
            String deliveryStr = (task.getDeliveryDate() != null ? SDF.format(task.getDeliveryDate()) : "No registrada");
            long diffDays = alertService.daysRemaining(task, today);

            String assigned = (task.getAssignedTo() == null || task.getAssignedTo().isBlank())
                    ? "No asignado"
                    : dataServiceController.findAssistantNameById(task.getAssignedTo());

            String estado = (task.getStatus() != null) ? task.getStatus() : "Sin estado";
            String cliente = (task.getCustomerId() != null ? task.getCustomerId() : "No asignado");

            System.out.println("\nTarea #" + task.getId() + " - " + task.getDescription());
            System.out.println("   Cliente: " + cliente);
            System.out.println("   Asistente: " + assigned);
            System.out.println("   Estado: " + estado);
            System.out.println("   Fecha de entrega: " + deliveryStr);

            System.out.println(alertService.statusAccordingDays(diffDays));
            System.out.println("-------------------------------------------");
        }
    }

    private void listInvoices() {
        System.out.println("\n--- Facturas ---");
        List<Invoice> invoices = dataServiceController.getInvoices();
        if (invoices.isEmpty()) {
            System.out.println("No hay facturas registradas.");
            return;
        }

        invoices.forEach(System.out::println);

        InvoiceController invoiceController = new InvoiceController();
        Map<String, List<Invoice>> facturasPorCliente = invoiceController.groupTaskCustomer(invoices);
        double totalPendiente = invoiceController.totalOutstanding(invoices);

        System.out.println("\n=== Resumen de Deuda por Cliente ===");
        for (Map.Entry<String, List<Invoice>> entry : facturasPorCliente.entrySet()) {
            String cliente = entry.getKey();
            double deudaCliente = invoiceController.debtCustomer(entry.getValue());

            System.out.println("Cliente: " + cliente + " | Total pendiente: $" + String.format("%.2f", deudaCliente));
            entry.getValue().forEach(inv
                    -> System.out.println("    - " + inv.getDetails() + ": $" + String.format("%.2f", inv.getAmountPaid()))
            );
            System.out.println();
        }

        System.out.println("-------------------------------------------");
        System.out.println("TOTAL GENERAL PENDIENTE: $" + String.format("%.2f", totalPendiente));
    }

    private void updateTask() {
        int id = Validation.readIntRange("Ingrese el ID de la tarea a actualizar: ", 0, Integer.MAX_VALUE);
        Task task = dataServiceController.findTaskById(id);

        if (task == null) {
            System.out.println("No se encontró la tarea con ese ID.");
            return;
        }

        System.out.println("Dejar en blanco para mantener el valor actual.");
        String description = Validation.readOptional("Nueva descripción (" + task.getDescription() + "): ");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date deliveryDate = Validation.readDateOptional("Nueva fecha de entrega (yyyy-MM-dd) (actual: "
                + (task.getDeliveryDate() == null ? "none" : sdf.format(task.getDeliveryDate())) + "): ");

        String status = Validation.readOptional("Nuevo estado (" + task.getStatus() + "): ");

        System.out.println("Cliente asignado: " + task.getCustomer() + " (no editable)");

        String assignedTo = Validation.readOptional("Nuevo asistente asignado (" + task.getAssignedTo() + "): ");

        Document newDocument = null;
        if (Validation.confirm("¿Actualizar documento? (s/n): ")) {
            if (task.getDocument() != null) {
                System.out.println("Documento actual: " + task.getDocument().getName());
            }
            String docName = Validation.readString("Nombre del documento: ");
            String typeDocument = Validation.readString("Tipo de documento: ");
            String docStatus = Validation.readString("Estado del documento: ");
            Date reviewDay = Validation.readDateRequired("Día de revisión (yyyy-MM-dd): ");
            String details = Validation.readString("Detalles del documento: ");
            newDocument = new Document(docName, typeDocument, docStatus, reviewDay, details);
        }

        Task updatedTask = new Task(
                task.getId(),
                description.isEmpty() ? task.getDescription() : description,
                task.getCreationDate(),
                deliveryDate == null ? task.getDeliveryDate() : deliveryDate,
                status.isEmpty() ? task.getStatus() : status,
                task.getCustomerId(),
                task.getCustomer(),
                newDocument == null ? task.getDocument() : newDocument,
                assignedTo.isEmpty() ? task.getAssignedTo() : assignedTo
        );

        boolean updated = dataServiceController.updateTask(updatedTask);
        System.out.println(updated ? "Tarea actualizada correctamente!" : "No se pudo actualizar la tarea.");
    }

    private void deleteTask() {
        int id = Validation.readIntRange("Ingrese el ID de la tarea a eliminar: ", 0, Integer.MAX_VALUE);
        if (!Validation.confirm("¿Confirma eliminar la tarea #" + id + "? (s/n): ")) {
            return;
        }
        boolean deleted = dataServiceController.removeTaskById(id);
        System.out.println(deleted ? "Tarea eliminada correctamente!" : "No se encontro la tarea con ese ID.");
    }

    private void markInvoiceAsPaid() {
        List<Invoice> invoices = dataServiceController.getInvoices();

        if (invoices.isEmpty()) {
            System.out.println("No hay facturas registradas.");
            return;
        }

        for (Invoice inv : invoices) {
            System.out.println("ID: " + inv.getInvoiceNumber()
                    + " | Estado: " + inv.getStatus()
                    + " | Detalles: " + inv.getDetails());
        }

        int id = Validation.readInt("Ingrese el número de la factura a marcar como pagada: ");

        Invoice invoice = invoices.stream()
                .filter(inv -> inv.getInvoiceNumber() == id)
                .findFirst()
                .orElse(null);

        if (invoice != null) {
            invoice.setStatus("Pagada");
            dataServiceController.updateInvoice(invoice);
            System.out.println("Factura #" + id + " marcada como pagada.");
        } else {
            System.out.println("No se encontro la factura con ese número.");
        }
    }

}
