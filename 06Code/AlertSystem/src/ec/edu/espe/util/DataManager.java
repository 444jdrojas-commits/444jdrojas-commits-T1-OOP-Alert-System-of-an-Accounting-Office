/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.util;

/**
 *
 * @author Paulo Ramos
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Boss;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class DataManager {
    private static final String BOSS_FILE = "boss.json";
    private static final String ASSISTANTS_FILE = "assistants.json";
    private Gson gson = new Gson();

    // Guardar un solo Boss
    public void saveBoss(Boss boss) {
        try (FileWriter writer = new FileWriter(BOSS_FILE)) {
            gson.toJson(boss, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Leer el Boss desde JSON
    public Boss loadBoss() {
        try (FileReader reader = new FileReader(BOSS_FILE)) {
            return gson.fromJson(reader, Boss.class);
        } catch (IOException e) {
            return null;
        }
    }

    // Guardar lista de asistentes
    public void saveAssistants(List<Assistant> assistants) {
        try (FileWriter writer = new FileWriter(ASSISTANTS_FILE)) {
            gson.toJson(assistants, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar lista de asistentes
    public List<Assistant> loadAssistants() {
        try (FileReader reader = new FileReader(ASSISTANTS_FILE)) {
            Type listType = new TypeToken<ArrayList<Assistant>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}

