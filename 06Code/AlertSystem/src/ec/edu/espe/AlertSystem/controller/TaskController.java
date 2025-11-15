package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author Paulo Ramos
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.AlertSystem.model.Document;
import ec.edu.espe.AlertSystem.model.Task;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskController {

    private static final String FILE_PATH = "src/ec/edu/espe/AlertSystem/util/tasks.json";
    private List<Task> tasks;

    public TaskController() {
        ensureDirectoryExists();
        tasks = loadTasks();
    }

    public boolean addTask(Task task) {
        tasks.add(task);
        return true;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public boolean deleteTask(int taskId) {
        boolean removed = tasks.removeIf(task -> task.getId() == taskId);
        if (removed) {
            saveTasks();
        }
        return removed;
    }

    public Task getTaskById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void assignTask(int taskId, String assistantName) {
        Task task = getTaskById(taskId);
        if (task != null) {
            task.setAssignedTo(assistantName);
            saveTasks();
        }
    }

    public void attachDocument(int taskId, Document document) {
        Task task = getTaskById(taskId);
        if (task != null) {
            task.setDocument(document);
            saveTasks();
        }
    }

    public void updateStatus(int taskId, String newStatus) {
        Task task = getTaskById(taskId);
        if (task != null) {
            task.setStatus(newStatus);
            saveTasks();
        }
    }

    private List<Task> loadTasks() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Task>>() {
            }.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveTasks() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                new Gson().toJson(tasks, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ensureDirectoryExists() {
        File file = new File(FILE_PATH);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public int generateNextId() {
        if (tasks.isEmpty()) {
            return 1;
        } else {
            int maxId = tasks.stream()
                    .mapToInt(Task::getId)
                    .max()
                    .orElse(0);
            return maxId + 1;
        }
    }

    public boolean updateTask(int id, String newDescription, Date newDeliveryDate, String newStatus,
            String newCustomer, String newAssignedTo, Document newDocument) {
        Task task = getTaskById(id);
        if (task != null) {
            if (newDescription != null && !newDescription.isEmpty()) {
                task.setDescription(newDescription);
            }
            if (newDeliveryDate != null) {
                task.setDeliveryDate(newDeliveryDate);
            }
            if (newStatus != null && !newStatus.isEmpty()) {
                task.setStatus(newStatus);
            }
            if (newCustomer != null && !newCustomer.isEmpty()) {
                task.setCustomer(newCustomer);
            }
            if (newAssignedTo != null && !newAssignedTo.isEmpty()) {
                task.setAssignedTo(newAssignedTo);
            }
            if (newDocument != null) {
                task.setDocument(newDocument);
            }

            saveTasks();
            return true;
        }
        return false;
    }

}
