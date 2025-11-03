/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystemOfAccountingOffice.model;

/**
 *
 * @author JOSUE
 */
public class Document {
    private String typeDocument;
    private String status;
    private String name;
    private Date reviewDay;
    private String details;

    public Document(String typeDocument, String status, String name, Date reviewDay, String details) {
        this.typeDocument = typeDocument;
        this.status = status;
        this.name = name;
        this.reviewDay = reviewDay;
        this.details = details;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReviewDay(Date reviewDay) {
        this.reviewDay = reviewDay;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
    public String getTypeDocument() {
        return typeDocument;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public Date getReviewDay() {
        return reviewDay;
    }

    public String getDetails() {
        return details;
    }
    
    
}
