package ec.edu.espe.AlertSystem.controller;

import com.google.gson.Gson;
import ec.edu.espe.AlertSystem.model.Boss;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Paulo Ramos
 */
public class BossController {

    private static final String FILE_PATH = "src/ec/edu/espe/AlertSystem/util/boss.json";
    private Boss boss;

    public BossController() {
        ensureDirectoryExists();
        boss = loadBoss();

        if (boss == null) {
            loadDefaultBoss();
            saveBoss();
        }
    }

    public Boss login(String username, String password) {
        if (boss != null && boss.getUser().equalsIgnoreCase(username) && boss.getPassword().equals(password)) {
            return boss;
        }
        return null;
    }

    public Boss getBoss() {
        return boss;
    }

    public void updateBoss(Boss newBoss) {
        this.boss = newBoss;
        saveBoss();
    }

    public void resetBoss() {
        loadDefaultBoss();
        saveBoss();
        System.out.println("✔ Boss reinicializado y guardado en " + FILE_PATH);
    }

    private Boss loadBoss() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            return new Gson().fromJson(reader, Boss.class);
        } catch (IOException e) {
            return null;
        }
    }

    private void saveBoss() {
        saveObjectToFile(boss, FILE_PATH);
    }

    private void saveObjectToFile(Object obj, String path) {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                new Gson().toJson(obj, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ensureDirectoryExists() {
        new File(FILE_PATH).getParentFile().mkdirs();
    }

    private void loadDefaultBoss() {
        boss = new Boss("Sandra Peña", new Date(), "0999999999", "sandra@correo.com", "sandrapena", "sandra1234");
    }

}
