package ec.edu.espe.AlertSystem.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Josue Rojas
 */
public class Invoice {

    private int invoiceNumber;
    private Date paymentDate;
    private float amountPaid;
    private String details;
    private String status;

    public Invoice(int invoiceNumber, Date paymentDate, float amountPaid, String details, String status) {
        this.invoiceNumber = invoiceNumber;
        this.paymentDate = paymentDate;
        this.amountPaid = amountPaid;
        this.details = details;
        this.status = status;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "\nInvoice Details:"
                + "\n   Invoice Number: " + invoiceNumber
                + "\n   Payment Date: " + (paymentDate != null ? sdf.format(paymentDate) : "No registrada")
                + "\n   Amount Paid: $" + amountPaid
                + "\n   Details: " + (details != null && !details.isEmpty() ? details : "Sin detalles")
                + "\n   Status: " + status
                + "\n-------------------------------------------";
    }
}
