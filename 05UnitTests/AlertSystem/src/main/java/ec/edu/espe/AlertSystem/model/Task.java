package ec.edu.espe.AlertSystem.model;

/**
 *
 * @author Josue Rojas
 */
import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private int id;
    private String description;
    private Date creationDate;
    private Date deliveryDate;
    private String status;

    @SerializedName("customer")
    private String customerId;

    private transient Customer customer;
    private Document document;
    private String assignedTo;

    public Task(int id, String description, Date creationDate, Date deliveryDate,
            String status, String customerId, Customer customer,
            Document document, String assignedTo) {
        this.id = id;
        this.description = description;
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.customerId = customerId;
        this.customer = customer;
        this.document = document;
        this.assignedTo = assignedTo;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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
        return "\nDetalles de la Tarea:"
                + "\n   ID: " + id
                + "\n   Descripción: " + description
                + "\n   Creado: " + (creationDate != null ? sdf.format(creationDate) : "No registrada")
                + "\n   Fecha de Entrega: " + (deliveryDate != null ? sdf.format(deliveryDate) : "No registrada")
                + "\n   Estado: " + status
                + "\n   Cliente: " + (customerId != null ? customerId : "No asignado")
                + "\n   Asignado a: " + (assignedTo == null ? "No asignado" : assignedTo)
                + (document == null ? "\n   Documento: No adjunto" : "\n   Documento: " + document.toString())
                + "\n-------------------------------------------";
    }
}
