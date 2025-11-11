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
    private String description;
    private Date creationDate;
    private Date deliveryDate;
    private String status;
    private String customer;
    private Document document;

    public Task(String description, Date creationDate, Date deliveryDate, String status, String customer, Document document) {
        this.description = description;
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.customer = customer;
        this.document = document;
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

    @Override
    public String toString() {
        return "Task{" + "description=" + description + ", creationDate=" + creationDate + ", deliveryDate=" + deliveryDate + ", status=" + status + ", customer=" + customer + ", document=" + document + '}';
    }


}
