package ec.edu.espe.AlertSystem.view;

/**
 *
 * @author Paulo Ramos
 */
import ec.edu.espe.AlertSystem.controller.*;
import ec.edu.espe.AlertSystem.model.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BossView {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_PATTERN);
    private static final Scanner scanner = new Scanner(System.in);

    private final NaturalPersonController naturalPersonController;
    private final BusinessController businessController;
    private final TaskController taskController;
    private final InvoiceController invoiceController;
    private final AssistantController assistantController;

    public BossView() {
        this(new NaturalPersonController(), new BusinessController(), new TaskController(), new InvoiceController(), new AssistantController());
    }

    public BossView(NaturalPersonController personController,
            BusinessController businessController,
            TaskController taskController,
            InvoiceController invoiceController,
            AssistantController assistantController) {
        this.naturalPersonController = Objects.requireNonNull(personController);
        this.businessController = Objects.requireNonNull(businessController);
        this.taskController = Objects.requireNonNull(taskController);
        this.invoiceController = Objects.requireNonNull(invoiceController);
        this.assistantController = Objects.requireNonNull(assistantController);
    }

    public void showBossMenu() {
        int option;
        do {
            printMainMenu();
            option = readInt("Elige una opcion: ", i -> i >= 1 && i <= 7, "Ingrese un numero entre 1 y 7.");

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
                    listInvoices();
                case 7 ->
                    System.out.println("Sesión finalizada.");
                default ->
                    System.out.println("Opción invalida.");
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
            option = readInt("Elige una opcion: ", i -> i >= 0 && i <= 8, "Ingrese un numero valido (0-8).");

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
                    System.out.println("Opción invalida.");
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
            System.out.println("\n--- Añadir Persona Natural ---");
            String name = readStringWithPattern("Nombre: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Nombre invalido. Solo letras y espacios.");
            String id = readStringWithPattern("Cedula: ", "\\d{10}", "Cédula invalida. Debe contener 10 digitos.");
            String nationality = readNonEmpty("Nacionalidad: ");
            Date birthDate = readDate("Fecha de nacimiento (yyyy-MM-dd): ", true);
            String phone = readStringWithPattern("Número de telefono: ", "\\d{9,10}", "Telefono inválido. Debe contener 9 o 10 digitos.");
            String email = readStringWithPattern("Email: ", "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", "Email invalido. Ejemplo: usuario@dominio.com");
            String occ = readStringWithPattern("Ocupacion: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Ocupacion inválida. Solo letras y espacios.");
            String gender = readNonEmpty("Genero: ");

            System.out.println("Ingrese la direccion");
            String city = readStringWithPattern("Ciudad: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Ciudad inválida. Solo letras y espacios.");
            String street = readNonEmpty("Calle: ");
            String sector = readNonEmpty("Sector: ");

            Address address = new Address(street, city, sector);

            NaturalPerson naturalPerson = new NaturalPerson(name, id, nationality, birthDate, phone, email, occ, gender, address);

            boolean added = naturalPersonController.addNaturalPerson(naturalPerson);
            System.out.println(added ? "Persona natural añadida exitosamente!" : "No se pudo añadir la persona (ya existe o error interno).");

        } catch (Exception e) {
            System.out.println("Error inesperado al crear persona: " + e.getMessage());
        }
    }

    private void addBusiness() {
        try {
            System.out.println("\n--- Añadir Empresa ---");
            String name = readStringWithPattern("Nombre de la empresa: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Nombre inválido. Solo letras y espacios.");
            String legalRep = readStringWithPattern("Representante Legal: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Representante inválido. Solo letras y espacios.");
            String type = readNonEmpty("Tipo de empresa: ");

            System.out.println("Ingrese la dirección");
            String city = readStringWithPattern("Ciudad: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Ciudad inválida. Solo letras.");
            String street = readNonEmpty("Calle: ");
            String sector = readStringWithPattern("Sector: ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Sector inválido.");

            Address address = new Address(city, street, sector);

            String phone = readStringWithPattern("Numero de telefono: ", "\\d{9,10}", "Telefono invalido. Debe contener 9 o 10 dígitos.");
            String email = readStringWithPattern("Email: ", "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", "Email invalido. Ejemplo: usuario@dominio.com");
            String ruc = readStringWithPattern("RUC: ", "\\d{13}", "RUC inválido. Debe tener 13 dígitos.");

            System.out.print("¿Desea registrar una auditoría inicial para esta empresa? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            Audit audit = null;
            if (respuesta.equals("s")) {
                Date now = new Date();
                SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm:ss");
                String hour = sdfHour.format(now);
                String description = readNonEmpty("Descripción de la auditoría: ");

                audit = new Audit(now, hour, null, description);

            }

            Business business = new Business(name, legalRep, type, address, phone, email, ruc, audit);

            if (audit != null) {
                audit.setBusines(business);
            }

            boolean added = businessController.addBusiness(business);
            System.out.println(added ? "Empresa añadida exitosamente!" : "No se pudo añadir la empresa (ya existe o error interno).");

        } catch (Exception e) {
            System.out.println("Error inesperado al crear empresa: " + e.getMessage());
        }
    }

    public void manageTasks() {
        int option;
        do {
            printTaskMenu();
            option = readInt("Elige una opcion: ", i -> i >= 1 && i <= 4, "Ingrese un numero entre 1 y 4.");
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
                    System.out.println("Opción invalida");
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

    public void createTask() {
        System.out.println("\n===== Crear Tarea =====");

        int id = taskController.generateNextId();
        String description = readNonEmpty("Descripcion: ");

        String customer;
        while (true) {
            customer = readNonEmpty("Cliente (nombre): ").trim();
            String customerNormalized = customer.toLowerCase();

            boolean existsInBusiness = businessController.getAllBusinesses()
                    .stream()
                    .anyMatch(b -> b.getNameBusiness() != null
                    && b.getNameBusiness().trim().toLowerCase().equals(customerNormalized));

            boolean existsInNaturalPersons = naturalPersonController.getAllNaturalPersons()
                    .stream()
                    .anyMatch(p -> p.getName() != null
                    && p.getName().trim().toLowerCase().equals(customerNormalized));

            if (existsInBusiness || existsInNaturalPersons) {
                break;
            }

            System.out.println("Cliente no existe. Ingrese uno valido.");
        }

        Date deliveryDate = readDate("Fecha de entrega (yyyy-MM-dd): ", false);

        Task newTask = new Task(id, description, customer, deliveryDate);

        if (confirm("¿Asignar asistente ahora? (s/n): ")) {
            assignAssistantToTask(newTask);
        }

        boolean added = taskController.addTask(newTask);
        System.out.println(added ? "Tarea creada con ID: " + id : "No se pudo crear la tarea.");
    }

    private void assignAssistantToTask(Task task) {
        System.out.println("\n===== Asistentes Disponibles =====");

        List<Assistant> assistantList = assistantController.getAllAssistants();
        if (assistantList.isEmpty()) {
            System.out.println("No hay asistentes registrados.");
            return;
        }

        assistantList.forEach(a -> System.out.println("ID: " + a.getId() + " - " + a.getName()));

        int assistantId = readInt("Ingrese el ID del asistente: ", i -> assistantList.stream().anyMatch(a -> a.getId() == i), "ID de asistente no valido.");

        Assistant selected = assistantList.stream().filter(a -> a.getId() == assistantId).findFirst().orElse(null);
        if (selected == null) {
            System.out.println("Asistente no encontrado. La tarea se guardara sin asignar.");
            return;
        }

        task.setAssignedTo(String.valueOf(assistantId));
        System.out.println("Asignado a: " + selected.getName());
    }

    private void updateNaturalPerson() {
        String identification = readNonEmpty("Ingrese la cedula de la persona a actualizar: ");
        NaturalPerson person = naturalPersonController.findById(identification);
        if (person == null) {
            System.out.println("No se encontro la persona con esa cédula.");
            return;
        }

        System.out.println("Dejar en blanco para mantener el valor actual.");
        String name = readOptionalPattern("Nuevo nombre (" + person.getName() + "): ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Nombre inválido. Solo letras y espacios.");
        String phone = readOptionalPattern("Nuevo telefono (" + person.getPhone() + "): ", "\\d{9,10}", "Teléfono inválido. Debe contener 9 o 10 dígitos.");
        String email = readOptionalPattern("Nuevo email (" + person.getEmail() + "): ", "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", "Email inválido.");
        String occupation = readOptionalPattern("Nueva ocupación (" + person.getOcuppation() + "): ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Ocupación inválida.");

        System.out.println("Nueva dirección:");
        String city = readOptionalPattern("Ciudad (" + person.getAddress().getCity() + "): ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Ciudad inválida.");
        String street = readOptional("Calle (" + person.getAddress().getStreet() + "): ");
        String sector = readOptionalPattern("Sector (" + person.getAddress().getSector() + "): ", "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Sector inválido.");

        Address newAddress = new Address(
                city.isEmpty() ? person.getAddress().getCity() : city,
                street.isEmpty() ? person.getAddress().getStreet() : street,
                sector.isEmpty() ? person.getAddress().getSector() : sector
        );

        boolean updated = naturalPersonController.updateNaturalPerson(
                identification,
                name.isEmpty() ? person.getName() : name,
                phone.isEmpty() ? person.getPhone() : phone,
                email.isEmpty() ? person.getEmail() : email,
                occupation.isEmpty() ? person.getOcuppation() : occupation,
                newAddress
        );

        System.out.println(updated ? "Persona actualizada correctamente!" : "No se pudo actualizar la persona.");
    }

    private void updateBusiness() {
        String ruc = readNonEmpty("Ingrese el RUC de la empresa a actualizar: ");
        Business business = businessController.findByRuc(ruc);
        if (business == null) {
            System.out.println("No se encontró la empresa con ese RUC.");
            return;
        }

        System.out.println("Dejar en blanco para mantener el valor actual.");
        String name = readOptionalPattern("Nuevo nombre (" + business.getNameBusiness() + "): ",
                "^[a-zA-ZáéíóúÁÉÍÓÚñÑ .]+$", "Nombre inválido. Solo letras y espacios.");
        String phone = readOptionalPattern("Nuevo teléfono (" + business.getPhone() + "): ",
                "\\d{9,10}", "Teléfono inválido. Debe contener 9 o 10 dígitos.");
        String email = readOptionalPattern("Nuevo email (" + business.getEmail() + "): ",
                "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", "Email inválido.");
        String legalRep = readOptionalPattern("Nuevo representante legal (" + business.getLegalRepresentative() + "): ",
                "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Representante inválido.");

        boolean updated = businessController.updateBusiness(
                ruc,
                name.isEmpty() ? business.getNameBusiness() : name,
                phone.isEmpty() ? business.getPhone() : phone,
                email.isEmpty() ? business.getEmail() : email,
                legalRep.isEmpty() ? business.getLegalRepresentative() : legalRep
        );

        if (updated) {
            System.out.println("Empresa actualizada correctamente!");

            if (confirm("¿Desea registrar una auditoria para esta actualizacion? (s/n): ")) {
                Date auditDate = null;
                String hour = null;

                while (auditDate == null) {
                    String fechaStr = readNonEmpty("Ingrese la fecha de la auditoria (yyyy-MM-dd): ");
                    try {
                        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                        sdfDate.setLenient(false); // evita fechas inválidas como 2025-02-30
                        auditDate = sdfDate.parse(fechaStr);
                    } catch (Exception e) {
                        System.out.println("Fecha invalida. Use el formato yyyy-MM-dd (ejemplo: 2025-11-14).");
                    }
                }

                while (hour == null) {
                    String hourStr = readNonEmpty("Ingrese la hora de la auditoria (HH:mm): ");
                    try {
                        if (!hourStr.matches("^([01]\\d|2[0-3]):[0-5]\\d$")) {
                            throw new IllegalArgumentException();
                        }
                        hour = hourStr;
                    } catch (Exception e) {
                        System.out.println("Hora invalida. Use el formato HH:mm (ejemplo: 22:04).");
                    }
                }

                String description = readNonEmpty("Descripcion de la auditoria: ");

                Audit audit = new Audit(auditDate, hour, business, description);
                business.setAudit(audit);

                System.out.println("Auditoria registrada:");
                System.out.println("   Fecha: " +  SDF.format(audit.getAuditDate()));
                System.out.println("   Hora: " + audit.getHour());
                System.out.println("   Descripcion: " + audit.getDescription());
            }

        } else {
            System.out.println("No se pudo actualizar la empresa.");
        }
    }

    private void deleteNaturalPerson() {
        String id = readNonEmpty("Ingrese la cedula de la persona a eliminar: ");
        if (!confirm("¿Confirma eliminar la persona con cedula " + id + "? (s/n): ")) {
            return;
        }
        boolean removed = naturalPersonController.removeById(id);
        System.out.println(removed ? "Persona eliminada correctamente!" : "No se encontro la persona con esa cedula.");
    }

    private void deleteBusiness() {
        String ruc = readNonEmpty("Ingrese el RUC de la empresa a eliminar: ");
        if (!confirm("¿Confirma eliminar la empresa con RUC " + ruc + "? (s/n): ")) {
            return;
        }
        boolean removed = businessController.removeByRuc(ruc);
        System.out.println(removed ? "Empresa eliminada correctamente!" : "No se encontro la empresa con ese RUC.");
    }

    private void listNaturalPersons() {
        System.out.println("\n--- Personas Naturales ---");
        List<NaturalPerson> list = naturalPersonController.getAllNaturalPersons();
        if (list.isEmpty()) {
            System.out.println("No hay personas registradas.");
        }
        list.forEach(System.out::println);
    }

    private void listBusinesses() {
        System.out.println("\n--- Empresas ---");
        List<Business> list = businessController.getAllBusinesses();
        if (list.isEmpty()) {
            System.out.println("No hay empresas registradas.");
        }
        list.forEach(System.out::println);
    }

    private void listTasks() {
        System.out.println("\n--- Tareas ---");
        List<Task> list = taskController.getAllTasks();
        if (list.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        }
        list.forEach(System.out::println);
    }

    private void viewPerformanceIndicators() {
        List<Task> tasks = taskController.getAllTasks();

        long totalTasks = tasks.size();
        long completedTasks = tasks.stream().filter(t -> "Completada".equalsIgnoreCase(t.getStatus())).count();
        long pendingTasks = totalTasks - completedTasks;
        double completionRate = totalTasks > 0 ? (completedTasks * 100.0 / totalTasks) : 0;

        System.out.println("\n===== Indicadores de Desempeño =====");
        System.out.println("Total de tareas: " + totalTasks);
        System.out.println("Tareas completadas: " + completedTasks);
        System.out.println("Tareas pendientes: " + pendingTasks);
        System.out.printf("Porcentaje de cumplimiento: %.2f%%\n", completionRate);

        System.out.println("\n--- Desempeño por asistente ---");
        Map<String, Long> tasksByAssistant = tasks.stream()
                .filter(t -> t.getAssignedTo() != null && !t.getAssignedTo().isBlank())
                .collect(Collectors.groupingBy(Task::getAssignedTo, Collectors.counting()));

        if (tasksByAssistant.isEmpty()) {
            System.out.println("No hay tareas asignadas a asistentes.");
        }

        tasksByAssistant.forEach((assistant, count) -> {
            long completedByAssistant = tasks.stream()
                    .filter(t -> assistant.equals(t.getAssignedTo()) && "Completada".equalsIgnoreCase(t.getStatus()))
                    .count();
            System.out.println("Asistente: " + assistant
                    + " | Tareas asignadas: " + count
                    + " | Completadas: " + completedByAssistant);
        });
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
                    System.out.println("VENCIDA hace " + Math.abs(diffDays) + " dia(s)");
                } else if (diffDays == 0) {
                    System.out.println("Es para HOY");
                } else if (diffDays <= 3) {
                    System.out.println("Proxima a vencer en " + diffDays + " dia(s)");
                } else {
                    System.out.println("   Se entrega en " + diffDays + " dia(s)");
                }
            } else {
                System.out.println(" Sin fecha de entrega registrada");
            }
            System.out.println("-------------------------------------------");
        }
    }

    private void listInvoices() {
        System.out.println("\n--- Facturas ---");
        List<Invoice> invoices = invoiceController.getAllInvoices();
        if (invoices.isEmpty()) {
            System.out.println("No hay facturas registradas.");
        }
        invoices.forEach(System.out::println);
    }

    private void updateTask() {
        int id = readInt("Ingrese el ID de la tarea a actualizar: ", i -> i >= 0, "ID inválido.");
        Task task = taskController.getTaskById(id);
        if (task == null) {
            System.out.println("No se encontró la tarea con ese ID.");
            return;
        }

        System.out.println("Dejar en blanco para mantener el valor actual.");
        String description = readOptional("Nueva descripcion (" + task.getDescription() + "): ");
        Date deliveryDate = readDateOptional("Nueva fecha de entrega (yyyy-MM-dd) (actual: "
                + (task.getDeliveryDate() == null ? "none" : SDF.format(task.getDeliveryDate())) + "): ");
        String status = readOptional("Nuevo estado (" + task.getStatus() + "): ");

        System.out.println("Cliente asignado: " + task.getCustomer() + " (no editable)");

        String assignedTo = readOptional("Nuevo asistente asignado (" + task.getAssignedTo() + "): ");

        Document newDocument = null;
        if (confirm("¿Actualizar documento? (s/n): ")) {
            String docName = readNonEmpty("Nombre del documento: ");
            String typeDocument = readNonEmpty("Tipo de documento: ");
            String docStatus = readNonEmpty("Estado del documento: ");
            Date reviewDay = readDate("Dia de revision (yyyy-MM-dd): ", true);
            String details = readNonEmpty("Detalles del documento: ");
            newDocument = new Document(docName, typeDocument, docStatus, reviewDay, details);
        }

        boolean updated = taskController.updateTask(id,
                description.isEmpty() ? task.getDescription() : description,
                deliveryDate == null ? task.getDeliveryDate() : deliveryDate,
                status.isEmpty() ? task.getStatus() : status,
                task.getCustomer(),
                assignedTo.isEmpty() ? task.getAssignedTo() : assignedTo,
                newDocument == null ? task.getDocument() : newDocument
        );

        System.out.println(updated ? "Tarea actualizada correctamente!" : "No se pudo actualizar la tarea.");
    }

    private void deleteTask() {
        int id = readInt("Ingrese el ID de la tarea a eliminar: ", i -> i >= 0, "ID invalido.");
        if (!confirm("¿Confirma eliminar la tarea #" + id + "? (s/n): ")) {
            return;
        }
        boolean deleted = taskController.deleteTask(id);
        System.out.println(deleted ? "Tarea eliminada correctamente!" : "No se encontro la tarea con ese ID.");
    }

    private static String readNonEmpty(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Entrada vacía. Intente nuevamente.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static String readOptional(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static boolean confirm(String prompt) {
        String resp;
        do {
            System.out.print(prompt);
            resp = scanner.nextLine().trim().toLowerCase();
        } while (!resp.equals("s") && !resp.equals("n"));
        return resp.equals("s");
    }

    private static String readStringWithPattern(String prompt, String regex, String errorMsg) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.matches(regex)) {
                System.out.println(errorMsg);
            }
        } while (!input.matches(regex));
        return input;
    }

    private static String readOptionalPattern(String prompt, String regex, String errorMsg) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return "";
        }
        if (!input.matches(regex)) {
            System.out.println(errorMsg);
            return readOptionalPattern(prompt, regex, errorMsg);
        }
        return input;
    }

    private static Date readDate(String prompt, boolean required) {
        Date d = null;
        do {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (s.isEmpty() && !required) {
                return null;
            }
            try {
                d = SDF.parse(s);
            } catch (ParseException e) {
                System.out.println("Formato invalido. Use " + DATE_PATTERN + ".");
            }
        } while (d == null && required);
        return d;
    }

    private static Date readDateOptional(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine().trim();
        if (s.isEmpty()) {
            return null;
        }
        try {
            return SDF.parse(s);
        } catch (ParseException e) {
            System.out.println("Formato invalido. Use " + DATE_PATTERN + ".");
            return readDateOptional(prompt);
        }
    }

    private static int readInt(String prompt, Predicate<Integer> validator, String errorMsg) {
        Integer value = null;
        do {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                value = Integer.parseInt(s);
                if (!validator.test(value)) {
                    System.out.println(errorMsg);
                    value = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida, ingrese un numero.");
            }
        } while (value == null);
        return value;
    }

    private static Date truncateToDate(Date date) {
        try {
            return SDF.parse(SDF.format(date));
        } catch (ParseException e) {
            return date;
        }
    }
}
