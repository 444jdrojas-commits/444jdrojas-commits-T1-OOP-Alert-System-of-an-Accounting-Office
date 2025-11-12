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
import ec.edu.espe.AlertSystem.model.Task;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
      private static final String FILE_PATH = "src/ec/edu/espe/AlertSystem/util/tasks.json";
    private List<Task> tasks;
    public TaskController() {
        ensureDirectoryExists();
        tasks = loadTasks();
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    private List<Task> loadTasks() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Task>>() {}.getType();
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
        return 1; // si no hay tareas, empezamos desde 1
    } else {
        int maxId = tasks.stream()
                         .mapToInt(Task::getId)
                         .max()
                         .orElse(0);
        return maxId + 1; // siguiente número
    }
}

}

