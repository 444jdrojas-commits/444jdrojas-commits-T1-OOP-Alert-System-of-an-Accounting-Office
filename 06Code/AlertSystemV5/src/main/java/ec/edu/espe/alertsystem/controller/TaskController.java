package ec.edu.espe.alertsystem.controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.alertsystem.model.Document;
import ec.edu.espe.alertsystem.model.Task;
import java.util.Date;


/**
 *
 * @author Paulo Ramos
 */
public class TaskController {

    private MongoCollection<org.bson.Document> getCollection() {
        return MongoConnection.getConnection().getCollection("tasks");
    }

    private int getNextId() {
        org.bson.Document last = getCollection()
                .find()
                .sort(new org.bson.Document("id", -1))
                .first();

        if (last == null) {
            return 1;
        }
        return last.getInteger("id") + 1;
    }

    public boolean save(Task task) {
        try {
            int newId = getNextId();
            task.setId(newId);
            task.setCreationDate(new Date());
            task.setStatus("Pendiente");

            org.bson.Document doc = new org.bson.Document()
                    .append("id", task.getId())
                    .append("description", task.getDescription())
                    .append("creationDate", task.getCreationDate())
                    .append("deliveryDate", task.getDeliveryDate())
                    .append("status", task.getStatus())
                    .append("customer", task.getCustomer())
                    .append("assignedTo", task.getAssignedTo());

            if (task.getDocument() != null) {
                Document d = task.getDocument();

                org.bson.Document documentDoc = new org.bson.Document()
                        .append("name", d.getName())
                        .append("typeDocument", d.getTypeDocument())
                        .append("status", d.getStatus())
                        .append("reviewDay", d.getReviewDay())
                        .append("details", d.getDetails());

                doc.append("document", documentDoc);
            }

            getCollection().insertOne(doc);
            return true;

        } catch (Exception e) {
            System.out.println("Error MongoDB Task: " + e.getMessage());
            return false;
        }
    }

}
