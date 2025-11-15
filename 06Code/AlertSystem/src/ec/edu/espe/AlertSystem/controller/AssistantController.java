package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author Paulo Ramos
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Invoice;
import ec.edu.espe.AlertSystem.model.Task;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssistantController {

    private static final String FILE_PATH = "src/ec/edu/espe/AlertSystem/util/assistants.json";
    private static final String TASK_FILE_PATH = "src/ec/edu/espe/AlertSystem/util/tasks.json";

    private List<Assistant> assistants;
    private List<Task> tasks;
    private Assistant loggedAssistant;

    public AssistantController() {
        ensureDirectoryExists();
        assistants = loadAssistants();
        tasks = loadTasks();

        if (assistants.isEmpty()) {
            loadDefaultAssistants();
            saveAssistants();
        }
    }

    public Assistant login(String username, String password) {
        for (Assistant a : assistants) {
            if (a.getUser().equalsIgnoreCase(username) && a.getPassword().equals(password)) {
                loggedAssistant = a;
                return a;
            }
        }
        return null;
    }

    public boolean addAssistant(Assistant assistant) {
        boolean added = assistants.add(assistant);
        saveAssistants();
        return added;
    }

    public List<Assistant> getAllAssistants() {
        return assistants;
    }

    public Assistant findAssistantByUser(String username) {
        for (Assistant a : assistants) {
            if (a.getUser().equalsIgnoreCase(username)) {
                return a;
            }
        }
        return null;
    }

    public Assistant findAssistantById(int id) {
        for (Assistant a : assistants) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public List<Task> getTasksForAssistant(String assistantName) {
        List<Task> assignedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getCustomer().equalsIgnoreCase(assistantName)) {
                assignedTasks.add(task);
            }
        }
        return assignedTasks;
    }

    public void completeTask(int taskId, InvoiceController invoiceController) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setStatus("Completada");
                saveTasks();

                int nextInvoiceNumber = invoiceController.getAllInvoices().size() + 1;
                Date paymentDate = new Date();
                float amountPaid = 100.0f;
                String details = "Factura generada por tarea: " + task.getDescription();
                String status = "Pendiente";

                Invoice invoice = new Invoice(nextInvoiceNumber, paymentDate, amountPaid, details, status);
                invoiceController.addInvoice(invoice);

                System.out.println("La tarea con ID " + taskId + " fue completada.");
                System.out.println("Factura #" + nextInvoiceNumber + " generada automáticamente.");
                return;
            }
        }
        System.out.println("No se encontró la tarea con ID " + taskId);
    }

    private List<Assistant> loadAssistants() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Assistant>>() {
            }.getType();
            List<Assistant> list = new Gson().fromJson(reader, listType);
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<Task> loadTasks() {
        try (FileReader reader = new FileReader(TASK_FILE_PATH)) {
            Type listType = new TypeToken<List<Task>>() {
            }.getType();
            List<Task> list = new Gson().fromJson(reader, listType);
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveAssistants() {
        saveListToFile(assistants, FILE_PATH);
    }

    private void saveTasks() {
        saveListToFile(tasks, TASK_FILE_PATH);
    }

    private void saveListToFile(Object list, String path) {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                new Gson().toJson(list, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ensureDirectoryExists() {
        new File(FILE_PATH).getParentFile().mkdirs();
        new File(TASK_FILE_PATH).getParentFile().mkdirs();
    }

    private void loadDefaultAssistants() {
        assistants.add(new Assistant(1, "Paulo Ramos", new Date(), "0987654321", "paulo@correo.com", "paulo", "paulo123"));
        assistants.add(new Assistant(2, "Josue Rojas", new Date(), "0991112233", "josue@correo.com", "josue", "josue123"));
        assistants.add(new Assistant(3, "Thais Santorum", new Date(), "0975556677", "thais@correo.com", "thais", "thais123"));
    }

    public void resetAssistants() {
        assistants.clear();
        loadDefaultAssistants();
        saveAssistants();
        System.out.println("Asistentes reinicializados y guardados en " + FILE_PATH);
    }

}
