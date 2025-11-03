/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystemOfAccountingOffice.model;

import java.util.Date;

/**
 *
 * @author Paulo Ramos
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
        return "Invoice{" + "invoiceNumber=" + invoiceNumber + ", paymentDate=" + paymentDate + ", amountPaid=" + amountPaid + ", details=" + details + ", status=" + status + '}';
    }
    
    
}
