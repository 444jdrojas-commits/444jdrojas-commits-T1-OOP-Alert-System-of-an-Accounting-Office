package ec.edu.espe.alertsystem.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Paulo Ramos
 */
public class Document {


    private String name;
    private String typeDocument;
    private String status;
    private Date reviewDay;
    private String details;

    public Document(String name, String typeDocument, String status, Date reviewDay, String details) {
        this.name = name;
        this.typeDocument = typeDocument;
        this.status = status;
        this.reviewDay = reviewDay;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReviewDay() {
        return reviewDay;
    }

    public void setReviewDay(Date reviewDay) {
        this.reviewDay = reviewDay;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "\n      Informacion del Documrnto:"
                + "\n         Nombre: " + name
                + "\n         Tipo de Documento: " + typeDocument
                + "\n         Estado: " + status
                + "\n         Dia de revision: " + (reviewDay != null ? sdf.format(reviewDay) : "No registrado")
                + "\n         Detalles: " + (details != null && !details.isEmpty() ? details : "Sin detalles");
    }

  
}
