/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystem.model;

import java.util.Date;

/**
 *
 * @author Paulo Ramos
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
    return "\nTask Details:" +
           "\n   ID: " + id +
           "\n   Description: " + description +
           "\n   Creation Date: " + creationDate +
           "\n   Delivery Date: " + deliveryDate +
           "\n   Status: " + status +
           "\n   Customer: " + customer +
           "\n   Document: " + document +
           "\n   Assigned To: " + assignedTo +
           "\n-------------------------------------------";
}

       
}
