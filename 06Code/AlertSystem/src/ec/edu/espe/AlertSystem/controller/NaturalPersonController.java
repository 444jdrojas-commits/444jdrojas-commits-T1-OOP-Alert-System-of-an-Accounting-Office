package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author JOSUE
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.AlertSystem.model.NaturalPerson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.AlertSystem.model.NaturalPerson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class NaturalPersonController {
    private static final String FILE_PATH = "src/ec/edu/espe/AlertSystem/util/naturalPersons.json";
    private List<NaturalPerson> naturalPersons;

    public NaturalPersonController() {
        ensureDirectoryExists();
        naturalPersons = loadNaturalPersons();
    }

    public void addNaturalPerson(NaturalPerson naturalPerson) {
        naturalPersons.add(naturalPerson);
        saveNaturalPersons();
    }

    public List<NaturalPerson> getAllNaturalPersons() {
        return naturalPersons;
    }

    private List<NaturalPerson> loadNaturalPersons() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<NaturalPerson>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveNaturalPersons() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); 

            try (FileWriter writer = new FileWriter(file)) {
                new Gson().toJson(naturalPersons, writer);
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

