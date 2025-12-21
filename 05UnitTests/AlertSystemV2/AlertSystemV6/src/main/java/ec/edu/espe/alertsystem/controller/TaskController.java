package ec.edu.espe.alertsystem.controller;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import ec.edu.espe.alertsystem.model.Document;
import ec.edu.espe.alertsystem.model.Task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public List<Object[]> getTasksByAssistant(String assistantId) {

        List<Object[]> rows = new ArrayList<>();

        MongoCollection<org.bson.Document> taskCollection
                = MongoConnection.getConnection().getCollection("tasks");

        FindIterable<org.bson.Document> tasks
                = taskCollection.find(Filters.eq("assignedTo", assistantId));

        SimpleDateFormat mongoFormat
                = new SimpleDateFormat("MMM dd, yyyy, hh:mm:ss a", Locale.ENGLISH);
        SimpleDateFormat outFormat
                = new SimpleDateFormat("dd/MM/yyyy");

        for (org.bson.Document doc : tasks) {

            String id = doc.get("id").toString();
            String description = doc.getString("description");
            String status = doc.getString("status");
            String client = doc.getString("customer");

            String delivery = "";
            Object dateObj = doc.get("deliveryDate");

            try {
                if (dateObj instanceof Date) {
                    delivery = outFormat.format((Date) dateObj);

                } else if (dateObj instanceof String) {
                    String clean = dateObj.toString().replace("\u202F", " ").trim();
                    Date parsed = mongoFormat.parse(clean);
                    delivery = outFormat.format(parsed);

                } else if (doc.containsKey("document")) {
                    Document inner = (Document) doc.get("document");
                    Object reviewObj = inner.get("reviewDay");

                    if (reviewObj instanceof Date) {
                        delivery = outFormat.format((Date) reviewObj);

                    } else if (reviewObj instanceof String) {
                        String clean = reviewObj.toString().replace("\u202F", " ").trim();
                        Date parsed = mongoFormat.parse(clean);
                        delivery = outFormat.format(parsed);
                    }
                }
            } catch (Exception e) {
                delivery = "";
            }

            rows.add(new Object[]{
                id, description, status, client, delivery
            });
        }

        return rows;
    }
    public List<Task> getTasksForAssistant(String assistantId) {
    List<Task> tasks = new ArrayList<>();

    FindIterable<org.bson.Document> docs =
            getCollection().find(Filters.eq("assignedTo", assistantId));
    for (org.bson.Document doc : docs) {
        Task task = new Task();

        Integer id = doc.getInteger("id");
        task.setId(id != null ? id : 0);

        task.setDescription(doc.getString("description"));
        task.setStatus(doc.getString("status"));
        task.setCustomer(doc.getString("customer"));
        task.setAssignedTo(doc.getString("assignedTo"));

        Object creationObj = doc.get("creationDate");
        if (creationObj instanceof Date) task.setCreationDate((Date) creationObj);

        Object deliveryObj = doc.get("deliveryDate");
        if (deliveryObj instanceof Date) task.setDeliveryDate((Date) deliveryObj);

        // Documento embebido
        if (doc.containsKey("document") && doc.get("document") instanceof org.bson.Document) {
            org.bson.Document ddoc = (org.bson.Document) doc.get("document");
            Document d = new Document();
            d.setName(ddoc.getString("name"));
            d.setTypeDocument(ddoc.getString("typeDocument"));
            d.setStatus(ddoc.getString("status"));
            d.setDetails(ddoc.getString("details"));

            Object reviewObj = ddoc.get("reviewDay");
            if (reviewObj instanceof Date) d.setReviewDay((Date) reviewObj);

            task.setDocument(d);
        }

        tasks.add(task);
    }

    return tasks;
}

}
