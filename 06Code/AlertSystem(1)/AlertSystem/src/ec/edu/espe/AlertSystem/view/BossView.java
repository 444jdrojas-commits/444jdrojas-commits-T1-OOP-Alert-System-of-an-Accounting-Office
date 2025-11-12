/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.util.Scanner;

public class BossView {
    private NaturalPersonController personController;
    private BusinessController businessController;
    private TaskController taskController = new TaskController();
private final InvoiceController invoiceController = new InvoiceController(); 
    private Scanner sc;

    public BossView() {
        personController = new NaturalPersonController();
        businessController = new BusinessController();
        sc = new Scanner(System.in);
    }
    
            

             public void showBossMenu() {
    int option;
    do {
        System.out.println("\n===== Menu Jefe =====");
        System.out.println("1. Administrar Clientes");
        System.out.println("2. Crear tarea");
        System.out.println("3. Lista de tareas");
        System.out.println("4. Ver indicador de rendimiento");
        System.out.println("5. Ver alertas");
        System.out.println("6. Ver Facturas");
        System.out.println("7. Salir");
        System.out.print("Elige una opcion: ");

        String input = sc.nextLine();
        try {
            option = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida, ingrese un número.");
            option = -1;
        }
        
        
 switch (option) {
                case 1 -> manageClient();
                case 2 -> addTask(); 
                case 3 -> listTasks();   
                case 4 -> viewPerformanceIndicators();
                case 5 -> viewAlerts();
                case 6 -> listInvoices();
                case 7 -> System.out.println("Logged out successfully.");
                default -> System.out.println("Invalid option.");
            }
        
    } while (option != 7);
}

    public void manageClient() {
        int option;
        do {
            System.out.println("\n--- Menu Jefe ---");
            System.out.println("1. Añadir Persona Natural");
            System.out.println("2. Añadir Empresa");
            System.out.println("3. Lista de Personas Naturales");
            System.out.println("4. Lista Empresas");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> addNaturalPerson();
                case 2 -> addBusiness();
                case 3 -> listNaturalPersons();
                case 4 -> listBusinesses();
            }
        } while (option != 0);
    }

    private void addNaturalPerson() {
        try {
            System.out.print("Nombre: "); String name = sc.nextLine();
            System.out.print("Cedula: "); String id = sc.nextLine();
            System.out.print("Nacionalidad: "); String nationality = sc.nextLine();
            System.out.print("Fecha de nacimiento (yyyy-MM-dd): "); String birthStr = sc.nextLine();
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthStr);
            System.out.print("Numero de telefono: "); String phone = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Ocupacion: "); String occ = sc.nextLine();
            System.out.print("Genero: "); String gender = sc.nextLine();

            NaturalPerson p = new NaturalPerson(name, id, nationality, birthDate, phone, email, occ, gender);
            personController.addNaturalPerson(p);
            System.out.println(" Persona natural añadida!");
        } catch (ParseException e) {
            System.out.println(" Formato inválido. Use yyyy-MM-dd");
        }
    }

    private void addBusiness() {
        System.out.print("Nombre de la empresa: "); String name = sc.nextLine();
        System.out.print("Representante Legal: "); String legalRep = sc.nextLine();
        System.out.print("Tipo de empresa: "); String type = sc.nextLine();
        System.out.print("Direccion: "); String address = sc.nextLine();
        System.out.print("Numero de telefono: "); String phone = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("RUC: "); String ruc = sc.nextLine();

        Business b = new Business(name, legalRep, type, address, phone, email, ruc);
        businessController.addBusiness(b);
        System.out.println(" Empresa añadida exitosamente!");
    }

    private void addTask() {
        try {
            System.out.print("Descripción: ");
            String description = sc.nextLine();

            System.out.print("Fecha de creación (yyyy-MM-dd): ");
            Date creationDate = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());

            System.out.print("Fecha de entrega (yyyy-MM-dd): ");
            Date deliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());

            System.out.print("Estado de la tarea: ");
            String statusTask = sc.nextLine();

            System.out.print("Cliente: ");
            String customer = sc.nextLine();

            // ====== Datos del documento ======
            System.out.print("Nombre del documento: ");
            String docName = sc.nextLine();

            System.out.print("Tipo de documento: ");
            String typeDocument = sc.nextLine();

            System.out.print("Estado del documento: ");
            String docStatus = sc.nextLine();

            System.out.print("Día de revisión (yyyy-MM-dd): ");
            Date reviewDay = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());

            System.out.print("Detalles del documento: ");
            String details = sc.nextLine();

            Document document = new Document(typeDocument, docStatus, docName, reviewDay, details);

            // ====== Asignación ======
            System.out.print("Nombre del asistente asignado: ");
            String assignedTo = sc.nextLine();

            int id = taskController.generateNextId();

            Task task = new Task(id, description, creationDate, deliveryDate, statusTask, customer, document, assignedTo);

            taskController.addTask(task);
            System.out.println("\n Tarea #" + id + " creada correctamente y asignada a " + assignedTo + "!\n");

        } catch (ParseException e) {
            System.out.println(" Formato inválido de fecha. Use yyyy-MM-dd");
        }
    }

    private void listNaturalPersons() {
        System.out.println("\n--- Personas Naturales ---");
        personController.getAllNaturalPersons().forEach(System.out::println);
    }

    private void listBusinesses() {
        System.out.println("\n--- Empresas ---");
        businessController.getAllBusinesses().forEach(System.out::println);
    }

    private void listTasks() {
        System.out.println("\n--- Tareas ---");
        taskController.getAllTasks().forEach(System.out::println);
    }

    private void viewPerformanceIndicators() {
        long total = taskController.getAllTasks().size();
        long completed = taskController.getAllTasks().stream()
                .filter(t -> "Completada".equalsIgnoreCase(t.getStatus()))
                .count();

        System.out.println("\n--- Indicadores de Rendimiento ---");
        System.out.println("Total de tareas: " + total);
        System.out.println("Tareas completadas: " + completed);
        System.out.println("Tareas pendientes: " + (total - completed));
    }

    private void viewAlerts() {
        System.out.println("\n--- Alertas ---");
        taskController.getAllTasks().stream()
                .filter(t -> !"Completada".equalsIgnoreCase(t.getStatus()))
                .forEach(t -> System.out.println("️ Tarea pendiente: " + t.getDescription() +
                        " | Asignada a: " + t.getAssignedTo() +
                        " | Fecha de entrega: " + t.getDeliveryDate()));
    }
    
private void listInvoices() {
    System.out.println("\n--- Facturas ---");
    if (invoiceController.getAllInvoices().isEmpty()) {
        System.out.println("No hay facturas registradas.");
    } else {
        invoiceController.getAllInvoices().forEach(System.out::println);
    }
}


}
