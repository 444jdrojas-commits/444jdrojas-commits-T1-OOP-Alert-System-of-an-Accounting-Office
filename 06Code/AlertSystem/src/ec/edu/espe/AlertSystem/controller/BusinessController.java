package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author JOSUE
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

    public void addBusiness(Business business) {
        businesses.add(business);
        saveBusinesses();
    }

    public List<Business> getAllBusinesses() {
        return businesses;
    }

    private List<Business> loadBusinesses() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Business>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


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

    private void ensureDirectoryExists() {
        File file = new File(FILE_PATH);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
