package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author Paulo Ramos
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.AlertSystem.model.Address;
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

    public boolean addNaturalPerson(NaturalPerson naturalPerson) {
        naturalPersons.add(naturalPerson);
        saveNaturalPersons();
        return true;
    }

    public List<NaturalPerson> getAllNaturalPersons() {
        return naturalPersons;
    }

    public NaturalPerson findById(String identification) {
        for (NaturalPerson p : naturalPersons) {
            if (p.getIdentification().equalsIgnoreCase(identification)) {
                return p;
            }
        }
        return null;
    }

    public boolean removeById(String identification) {
        NaturalPerson person = findById(identification);
        if (person != null) {
            naturalPersons.remove(person);
            saveNaturalPersons();
            return true;
        }
        return false;
    }

    public boolean updateNaturalPerson(String identification, String newName, String newPhone, String newEmail, String newOcuppation, Address newAddress) {
        NaturalPerson person = findById(identification);
        if (person != null) {
            if (newName != null && !newName.isEmpty()) {
                person.setName(newName);
            }
            if (newPhone != null && !newPhone.isEmpty()) {
                person.setPhone(newPhone);
            }
            if (newEmail != null && !newEmail.isEmpty()) {
                person.setEmail(newEmail);
            }
            if (newOcuppation != null && !newOcuppation.isEmpty()) {
                person.setOcuppation(newOcuppation);
            }
            if (newAddress != null) {
                person.setAddress(newAddress);
            }
            saveNaturalPersons();
            return true;
        }
        return false;
    }

    private List<NaturalPerson> loadNaturalPersons() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<NaturalPerson>>() {
            }.getType();
            List<NaturalPerson> loaded = new Gson().fromJson(reader, listType);
            return (loaded != null) ? loaded : new ArrayList<>();
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
