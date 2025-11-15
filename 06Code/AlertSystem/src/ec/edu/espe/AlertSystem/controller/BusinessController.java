package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author Paulo Ramos
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    private final Gson gson;

    public BusinessController() {
        ensureDirectoryExists();
        gson = new GsonBuilder().setPrettyPrinting().create();
        businesses = loadBusinesses();
    }

    public boolean addBusiness(Business business) {
        businesses.add(business);
        return true;
    }

    public List<Business> getAllBusinesses() {
        return businesses;
    }

    public Business findByRuc(String ruc) {
        for (Business b : businesses) {
            if (b.getRuc().equalsIgnoreCase(ruc)) {
                return b;
            }
        }
        return null;
    }

    public boolean removeByRuc(String ruc) {
        Business business = findByRuc(ruc);
        if (business != null) {
            businesses.remove(business);
            saveBusinesses();
            return true;
        }
        return false;
    }

    public boolean updateBusiness(String ruc, String newName, String newPhone, String newEmail, String newLegalRep) {
        Business business = findByRuc(ruc);
        if (business != null) {
            if (newName != null && !newName.isEmpty()) {
                business.setNameBusiness(newName);
            }
            if (newPhone != null && !newPhone.isEmpty()) {
                business.setPhone(newPhone);
            }
            if (newEmail != null && !newEmail.isEmpty()) {
                business.setEmail(newEmail);
            }
            if (newLegalRep != null && !newLegalRep.isEmpty()) {
                business.setLegalRepresentative(newLegalRep);
            }

            saveBusinesses();
            return true;
        }
        return false;
    }

    private List<Business> loadBusinesses() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Business>>() {
            }.getType();
            List<Business> loaded = gson.fromJson(reader, listType);
            return (loaded != null) ? loaded : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveBusinesses() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();

            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(businesses, writer);
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
