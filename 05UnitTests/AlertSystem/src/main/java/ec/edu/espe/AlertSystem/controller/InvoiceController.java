package ec.edu.espe.AlertSystem.controller;

import ec.edu.espe.AlertSystem.model.Invoice;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Paulo Ramos
 */
public class InvoiceController {

    public Map<String, List<Invoice>> groupTaskCustomer(List<Invoice> invoices) {
        return invoices.stream()
                .filter(inv -> "Pendiente".equalsIgnoreCase(inv.getStatus()))
                .collect(Collectors.groupingBy(inv -> {
                    if (inv.getDetails() != null && inv.getDetails().contains("Cliente:")) {
                        return inv.getDetails().split("Cliente:")[1].trim();
                    }
                    return "Desconocido";
                }));
    }

    public double totalOutstanding(List<Invoice> invoices) {
        return invoices.stream()
                .filter(inv -> "Pendiente".equalsIgnoreCase(inv.getStatus()))
                .mapToDouble(Invoice::getAmountPaid)
                .sum();
    }

    public double debtCustomer(List<Invoice> facturasPendientes) {
        return facturasPendientes.stream()
                .mapToDouble(Invoice::getAmountPaid)
                .sum();
    }
}
