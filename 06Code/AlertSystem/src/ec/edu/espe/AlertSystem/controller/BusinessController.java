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
import ec.edu.espe.AlertSystem.model.Business;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BusinessController {
    private static final String FILE_PATH = "src/ec/edu/espe/AlertSystem/util/businesses.json";
    private List<Business> businesses;

    public BusinessController() {
        ensureDirectoryExists();
        businesses = loadBusinesses();
    }

    /**
     * Agrega un nuevo negocio y guarda la lista en JSON.
     */
    public void addBusiness(Business business) {
        businesses.add(business);
        saveBusinesses();
    }

    /**
     * Devuelve todos los negocios cargados.
     */
    public List<Business> getAllBusinesses() {
        return businesses;
    }

    /**
     * Carga la lista de negocios desde el archivo JSON.
     */
    private List<Business> loadBusinesses() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Business>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Guarda la lista de negocios en el archivo JSON.
     */
    private void saveBusinesses() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // crea la carpeta util si no existe

            try (FileWriter writer = new FileWriter(file)) {
                new Gson().toJson(businesses, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica que la carpeta "src/ec/edu/espe/util" exista, si no la crea.
     */
    private void ensureDirectoryExists() {
        File file = new File(FILE_PATH);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
