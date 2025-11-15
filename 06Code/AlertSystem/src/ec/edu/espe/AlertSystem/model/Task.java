package ec.edu.espe.AlertSystem.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Josue Rojas
 */
public class Task {

    private int id;
    private String description;
    private Date creationDate;
    private Date deliveryDate;
    private String status;
    private String customer;
    private Document document;
    private String assignedTo;

    public Task(int id, String description, Date creationDate, Date deliveryDate, String status, String customer, Document document, String assignedTo) {
        this.id = id;
        this.description = description;
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.customer = customer;
        this.document = document;
        this.assignedTo = assignedTo;
    }

    public Task(int id, String description, String customer, Date deliveryDate) {
        this.id = id;
        this.description = description;
        this.customer = customer;
        this.deliveryDate = deliveryDate;

        this.creationDate = new Date();
        this.status = "Pendiente";
        this.document = null;
        this.assignedTo = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "\nTask Details:"
                + "\n   ID: " + id
                + "\n   Description: " + description
                + "\n   Created: " + (creationDate != null ? sdf.format(creationDate) : "No registrada")
                + "\n   Delivery: " + (deliveryDate != null ? sdf.format(deliveryDate) : "No registrada")
                + "\n   Status: " + status
                + "\n   Customer: " + customer
                + "\n   Assigned To: " + (assignedTo == null ? "No asignado" : assignedTo)
                + (document == null ? "\n   Document: No adjunto" : "\n   Document: " + document.toString())
                + "\n-------------------------------------------";
    }

}
