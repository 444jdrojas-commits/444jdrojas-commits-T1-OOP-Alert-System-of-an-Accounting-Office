package ec.edu.espe.AlertSystem.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Paulo Ramos
 */
public class Audit {

    private Date auditDate;
    private String hour;
    private  transient Business busines;
    private String description;

    public Audit(Date auditDate, String hour, Business busines, String description) {
        this.auditDate = auditDate;
        this.hour = hour;
        this.busines = busines;
        this.description = description;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Business getBusines() {
        return busines;
    }

    public void setBusines(Business busines) {
        this.busines = busines;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
public String toString() {
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd"); // solo año-mes-día
    return "Auditoría:\n" +
           "   Fecha: " + sdfDate.format(auditDate) + "\n" +
           "   Hora: " + hour + "\n" +
           "   Descripción: " + description;
}

}
