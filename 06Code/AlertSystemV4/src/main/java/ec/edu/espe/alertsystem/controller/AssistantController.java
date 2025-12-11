package ec.edu.espe.alertsystem.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Paulo Ramos
 */
public class AssistantController {

    public int generateUniqueId() {
        MongoCollection<Document> collection = MongoConnection.getConnection().getCollection("assistants");

        Document lastDoc = collection.find()
                .sort(Sorts.descending("id"))
                .first();

        if (lastDoc == null) {
            return 1;
        } else {
            return lastDoc.getInteger("id") + 1;
        }
    }

    public boolean addAssistant(String name, Date birthDate, String phone, String email, String user, String password) {
        int newId = generateUniqueId();
        return save(newId, name, birthDate, phone, email, user, password);
    }

    public boolean save(int id, String name, Date birthDate, String phone, String email, String user, String password) {
        try {
            MongoCollection<Document> collection = MongoConnection.getConnection().getCollection("assistants");

            Document doc = new Document()
                    .append("id", id)
                    .append("name", name)
                    .append("birthDate", birthDate)
                    .append("phone", phone)
                    .append("email", email)
                    .append("user", user)
                    .append("password", password);

            collection.insertOne(doc);

            return true;

        } catch (Exception e) {
            System.out.println("Error MongoDB: " + e.getMessage());
            return false;
        }
    }

    public List<Document> getAll() {
        MongoCollection<Document> collection = MongoConnection.getConnection().getCollection("assistants");

        List<Document> list = new ArrayList<>();

        for (Document doc : collection.find()) {
            doc.append("type", "Assistant");
            list.add(doc);
        }

        return list;
    }
}


