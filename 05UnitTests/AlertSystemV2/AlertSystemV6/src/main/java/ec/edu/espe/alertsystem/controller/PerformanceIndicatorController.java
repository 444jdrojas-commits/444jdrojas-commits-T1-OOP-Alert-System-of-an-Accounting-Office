package ec.edu.espe.alertsystem.controller;

import com.mongodb.client.FindIterable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.bson.Document;

/**
 *
 * @author Paulo Ramos
 */
public class PerformanceIndicatorController {

    public Object[] calculatePerformanceRow(String label, FindIterable<Document> tasks, boolean isAssistant) {
        int total = 0;
        int completed = 0;
        int pending = 0;
        int delayed = 0;

        SimpleDateFormat mongoFormat = new SimpleDateFormat("MMM dd, yyyy, hh:mm:ss a", Locale.ENGLISH);
        Date now = new Date();

        for (Document doc : tasks) {
            total++;
            String status = doc.getString("status");
            Object dateObj = doc.get("deliveryDate");

            if ("Completada".equalsIgnoreCase(status)) {
                completed++;
            } else {
                if (!isAssistant) { 
                    pending++;
                }
            }

            if (!"Completada".equalsIgnoreCase(status) && dateObj != null) {
                try {
                    Date limitDate;
                    if (dateObj instanceof Date) {
                        limitDate = (Date) dateObj;
                    } else {
                        String clean = dateObj.toString().replace("\u202F", " ").trim();
                        limitDate = mongoFormat.parse(clean);
                    }
                    if (limitDate.before(now)) {
                        delayed++;
                    }
                } catch (Exception ex) {
                    
                }
            }
        }

        double cumplimiento = total == 0 ? 0 : (completed * 100.0) / total;
        String cumplimientoFormatted = String.format("%.1f%%", cumplimiento);

        if (isAssistant) {
            String desempeño = cumplimiento >= 90 ? "Excelente"
                    : cumplimiento >= 70 ? "Bueno"
                            : cumplimiento >= 50 ? "Regular" : "Bajo";

            return new Object[]{label, total, completed, delayed, cumplimientoFormatted, desempeño};
        } else {
            return new Object[]{label, total, completed, pending, delayed, cumplimientoFormatted};
        }
    }

}
