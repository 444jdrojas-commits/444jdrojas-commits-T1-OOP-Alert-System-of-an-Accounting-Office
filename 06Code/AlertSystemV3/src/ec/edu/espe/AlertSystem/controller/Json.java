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
import java.io.*;
import java.lang.reflect.Type;

public class Json<T> {

    private final String filePath;
    private final Type type;
    private final Gson gson = new Gson();

    public Json(String filePath, Type type) {
        this.filePath = filePath;
        this.type = type;
    }

    public T loadData() {
        File file = new File(filePath);
        if (!file.exists()) {
            return getEmptyInstance();
        }
        try (FileReader reader = new FileReader(file)) {
            T data = gson.fromJson(reader, type);
            return data != null ? data : getEmptyInstance();
        } catch (IOException e) {
            return getEmptyInstance();
        }
    }

    public void saveData(T data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, type, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private T getEmptyInstance() {
        if (type instanceof Class) {
            // Caso objeto único (ej: Boss)
            return null;
        } else {
            // Caso lista
            return gson.fromJson("[]", type);
        }
    }
}
