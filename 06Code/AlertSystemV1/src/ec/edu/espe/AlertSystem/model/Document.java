package ec.edu.espe.AlertSystem.model;

import java.util.Date;

/**
 *
 * @author Josue Rojas
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

 @Override
public String toString() {
    return "\nDocument Information:" +
           "\n   Tipo: " + typeDocument +
           "\n   Estado: " + status +
           "\n   Nombre: " + name +
           "\n   Dia de revision: " + reviewDay +
           "\n   Detalles: " + details +
           "\n-------------------------------------------";
}
    
   
}
