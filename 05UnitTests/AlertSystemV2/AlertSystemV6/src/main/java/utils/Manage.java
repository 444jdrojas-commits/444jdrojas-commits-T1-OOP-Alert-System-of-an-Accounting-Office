package utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.alertsystem.controller.MongoConnection;
import javax.swing.JComboBox;
import org.bson.Document;

/**
 *
 * @author Paulo Ramos
 */
public class Manage {

    public static void loadCustomersComboBox(JComboBox<String> cmbCustomer, boolean includeAll) {

        cmbCustomer.removeAllItems();

        if (includeAll) {
            cmbCustomer.addItem("Todos");
        }

        MongoDatabase database = MongoConnection.getDatabase();

        MongoCollection<Document> naturalPersons
                = database.getCollection("naturalPersons");

        for (Document doc : naturalPersons.find()) {
            String name = doc.getString("name");
            if (name != null) {
                cmbCustomer.addItem(name);
            }
        }

        MongoCollection<Document> businesses
                = database.getCollection("businesses");

        for (Document doc : businesses.find()) {
            String name = doc.getString("nameBusiness");
            if (name != null) {
                cmbCustomer.addItem(name);
            }
        }
    }

}
