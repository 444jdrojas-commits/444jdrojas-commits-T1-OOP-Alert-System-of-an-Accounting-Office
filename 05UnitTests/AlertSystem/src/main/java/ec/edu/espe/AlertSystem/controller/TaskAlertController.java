package ec.edu.espe.AlertSystem.controller;
import ec.edu.espe.AlertSystem.model.Task;
import java.util.Date;

/**
 *
 * @author Paulo Ramos
 */
public class TaskAlertController {

    public long daysRemaining(Task task, Date today) {
        if (task.getDeliveryDate() == null) {
            return Long.MIN_VALUE;
        }
        Date delivery = Validation.truncateToDate(task.getDeliveryDate());
        long diffMillis = delivery.getTime() - today.getTime();
        return diffMillis / (1000L * 60 * 60 * 24);
    }

    public String statusAccordingDays(long diffDays) {
        if (diffDays == Long.MIN_VALUE) {
            return "   Sin fecha de entrega registrada";
        } else if (diffDays < 0) {
            return "   VENCIDA hace " + Math.abs(diffDays) + " dia(s)";
        } else if (diffDays == 0) {
            return "   Es para HOY";
        } else if (diffDays <= 3) {
            return "   Proxima a vencer en " + diffDays + " dia(s)";
        } else {
            return "   Se entrega en " + diffDays + " dia(s)";
        }
    }
}
