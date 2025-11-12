package ec.edu.espe.AlertSystem.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Boss;
import ec.edu.espe.AlertSystem.model.Task;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * DataManager
 * Maneja lectura/escritura JSON para Boss, Assistants y Tasks.
 * Salidas en español, código en inglés.
 */
public class DataManager {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final String TASKS_FILE = "tasks.json";
    private static final String BOSS_FILE = "boss.json";
    private static final String ASSISTANTS_FILE = "assistants.json";

    // ========= TASKS =========
    public List<Task> loadTasks() {
        try (FileReader r = new FileReader(TASKS_FILE)) {
            Type type = new TypeToken<List<Task>>() {}.getType();
            List<Task> tasks = gson.fromJson(r, type);
            return tasks != null ? tasks : new ArrayList<>();
        } catch (Exception e) {
            System.out.println("⚠️ No se pudieron cargar tareas; se utilizará una lista vacía.");
            return new ArrayList<>();
        }
    }

    public void saveTasks(List<Task> tasks) {
        try (FileWriter w = new FileWriter(TASKS_FILE)) {
            gson.toJson(tasks, w);
            System.out.println("💾 Tareas guardadas en " + TASKS_FILE);
        } catch (Exception e) {
            System.out.println("⚠️ No se pudieron guardar las tareas: " + e.getMessage());
        }
    }

    // ========= BOSS =========
    public Boss loadBoss() {
        try (FileReader r = new FileReader(BOSS_FILE)) {
            return gson.fromJson(r, Boss.class);
        } catch (Exception e) {
            System.out.println("ℹ️ No existe boss.json; aún no hay jefe registrado.");
            return null;
        }
    }

    public void saveBoss(Boss boss) {
        try (FileWriter w = new FileWriter(BOSS_FILE)) {
            gson.toJson(boss, w);
            System.out.println("💾 Boss guardado en " + BOSS_FILE);
        } catch (Exception e) {
            System.out.println("⚠️ No se pudo guardar el Boss: " + e.getMessage());
        }
    }

    // ======== ASSISTANTS ========
    public List<Assistant> loadAssistants() {
        try (FileReader r = new FileReader(ASSISTANTS_FILE)) {
            Type type = new TypeToken<List<Assistant>>() {}.getType();
            List<Assistant> list = gson.fromJson(r, type);
            return list != null ? list : new ArrayList<>();
        } catch (Exception e) {
            System.out.println("ℹ️ No existe assistants.json; se utilizará una lista vacía.");
            return new ArrayList<>();
        }
    }

    public void saveAssistants(List<Assistant> assistants) {
        try (FileWriter w = new FileWriter(ASSISTANTS_FILE)) {
            gson.toJson(assistants, w);
            System.out.println("💾 Assistants guardados en " + ASSISTANTS_FILE);
        } catch (Exception e) {
            System.out.println("⚠️ No se pudieron guardar los Assistants: " + e.getMessage());
        }
    }
}
