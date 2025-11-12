package ec.edu.espe.AlertSystem.view;

/**
 *
 * @author JOSUE
 */

import ec.edu.espe.AlertSystem.controller.BusinessController;
import ec.edu.espe.AlertSystem.controller.NaturalPersonController;
import ec.edu.espe.AlertSystem.controller.TaskController;
import ec.edu.espe.AlertSystem.model.Business;
import ec.edu.espe.AlertSystem.model.Document;
import ec.edu.espe.AlertSystem.model.NaturalPerson;
import ec.edu.espe.AlertSystem.model.Task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import ec.edu.espe.AlertSystem.controller.BusinessController;
import ec.edu.espe.AlertSystem.controller.NaturalPersonController;
import ec.edu.espe.AlertSystem.controller.TaskController;
import ec.edu.espe.AlertSystem.model.Business;
import ec.edu.espe.AlertSystem.model.Document;
import ec.edu.espe.AlertSystem.model.NaturalPerson;
import ec.edu.espe.AlertSystem.model.Task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BossView {
    private NaturalPersonController personController;
    private BusinessController businessController;
    private TaskController taskController = new TaskController();

    private Scanner sc;

    public BossView() {
        personController = new NaturalPersonController();
        businessController = new BusinessController();
        sc = new Scanner(System.in);
    }
    
public void showBossMenu() {
    int option;
    do {
        System.out.println("\n===== BOSS MENU =====");
        System.out.println("1. Manage Clients");
        System.out.println("2. Create Task");
        System.out.println("3. Create Task"); // 👈 nueva opción
        System.out.println("4. View Performance Indicators");
        System.out.println("5. View Alerts");
        System.out.println("6. Logout");
        System.out.print("Choose an option: ");
        option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1 -> manageClient();
            case 2 -> addTask(); // mostrar todas las tareas
            case 3 -> listTasks();   // 👈 crear nueva tarea
            case 4 -> System.out.println("Viewing performance indicators (to implement)");
            case 5 -> System.out.println("Viewing alerts (to implement)");
            case 6 -> System.out.println("Logged out successfully.");
            default -> System.out.println("Invalid option.");
        }
    } while (option != 6);
}


    public void manageClient() {
        int option;
        do {
            System.out.println("\n--- Boss Menu ---");
            System.out.println("1. Add Natural Person");
            System.out.println("2. Add Business");
            System.out.println("3. List Natural Persons");
            System.out.println("4. List Businesses");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
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
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Identification: "); String id = sc.nextLine();
            System.out.print("Nationality: "); String nationality = sc.nextLine();
            System.out.print("Birth Date (yyyy-MM-dd): "); String birthStr = sc.nextLine();
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthStr);
            System.out.print("Phone: "); String phone = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Occupation: "); String occ = sc.nextLine();
            System.out.print("Gender: "); String gender = sc.nextLine();

            NaturalPerson p = new NaturalPerson(name, id, nationality, birthDate, phone, email, occ, gender);
            personController.addNaturalPerson(p);
            System.out.println(" Natural person added successfully!");
        } catch (ParseException e) {
            System.out.println(" Invalid date format. Use yyyy-MM-dd");
        }
    }

    private void addBusiness() {
        System.out.print("Business Name: "); String name = sc.nextLine();
        System.out.print("Legal Representative: "); String legalRep = sc.nextLine();
        System.out.print("Type of Business: "); String type = sc.nextLine();
        System.out.print("Address: "); String address = sc.nextLine();
        System.out.print("Phone: "); String phone = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("RUC: "); String ruc = sc.nextLine();

        Business b = new Business(name, legalRep, type, address, phone, email, ruc);
        businessController.addBusiness(b);
        System.out.println(" Business added successfully!");
    }

    private void addTask() {
    try {
        System.out.print("Description: ");
        String description = sc.nextLine();

        System.out.print("Creation Date (yyyy-MM-dd): ");
        String creationStr = sc.nextLine();
        Date creationDate = new SimpleDateFormat("yyyy-MM-dd").parse(creationStr);

        System.out.print("Delivery Date (yyyy-MM-dd): ");
        String deliveryStr = sc.nextLine();
        Date deliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(deliveryStr);

        System.out.print("Status: ");
        String statusTask = sc.nextLine(); // 👈 status de la tarea

        System.out.print("Customer: ");
        String customer = sc.nextLine();

        // Datos del documento
        System.out.print("Document Name: ");
        String docName = sc.nextLine();

        System.out.print("Document Type: ");
        String typeDocument = sc.nextLine();

        System.out.print("Document Status: ");
        String docStatus = sc.nextLine();

        System.out.print("Review Day (yyyy-MM-dd): ");
        String reviewStr = sc.nextLine();
        Date reviewDay = new SimpleDateFormat("yyyy-MM-dd").parse(reviewStr);

        System.out.print("Document Details: ");
        String details = sc.nextLine();

        // Crear el documento con todos los atributos
        Document document = new Document(typeDocument, docStatus, docName, reviewDay, details);

        // Crear la tarea asociada al documento
        Task task = new Task(description, creationDate, deliveryDate, statusTask, customer, document);

        taskController.addTask(task);
        System.out.println(" Task created successfully!");
    } catch (ParseException e) {
        System.out.println(" Invalid date format. Use yyyy-MM-dd");
    }
}


    private void listNaturalPersons() {
        System.out.println("\n--- Natural Persons ---");
        personController.getAllNaturalPersons().forEach(System.out::println);
    }

    private void listBusinesses() {
        System.out.println("\n--- Businesses ---");
        businessController.getAllBusinesses().forEach(System.out::println);
    }
    private void listTasks() {
    System.out.println("\n--- Tasks ---");
    taskController.getAllTasks().forEach(System.out::println);
}

}

