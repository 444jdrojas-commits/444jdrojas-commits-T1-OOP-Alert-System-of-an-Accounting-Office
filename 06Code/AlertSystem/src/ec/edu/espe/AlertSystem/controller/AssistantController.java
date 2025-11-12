/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author Paulo Ramos
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Task;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para gestionar asistentes y sus tareas.
 */
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
    }


    public void addAssistant(Assistant assistant) {
        assistants.add(assistant);
        saveAssistants();
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

    public List<Task> getTasksForAssistant(String assistantName) {
        List<Task> assignedTasks = new ArrayList<>();
        for (Task task : tasks) {

            if (task.getCustomer().equalsIgnoreCase(assistantName)) {
                assignedTasks.add(task);
            }
        }
        return assignedTasks;
    }

    public void completeTask(int taskId) {
        for (Task task : tasks) {
            // 👇 asegúrate de que Task tenga un atributo "id"
            if (task.getId() == taskId) {
                task.setStatus("Completada");
                saveTasks();
                System.out.println("La tarea con ID " + taskId + " fue completada.");
                return;
            }
        }
        System.out.println("No se encontró la tarea con ID " + taskId);
    }

    // ---------------- PERSISTENCIA ----------------

    private List<Assistant> loadAssistants() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Assistant>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<Task> loadTasks() {
        try (FileReader reader = new FileReader(TASK_FILE_PATH)) {
            Type listType = new TypeToken<List<Task>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveAssistants() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                new Gson().toJson(assistants, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTasks() {
        try {
            File file = new File(TASK_FILE_PATH);
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                new Gson().toJson(tasks, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ensureDirectoryExists() {
        File assistantFile = new File(FILE_PATH);
        File taskFile = new File(TASK_FILE_PATH);

        if (!assistantFile.getParentFile().exists()) {
            assistantFile.getParentFile().mkdirs();
        }
        if (!taskFile.getParentFile().exists()) {
            taskFile.getParentFile().mkdirs();
        }
    }
}



