package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author Paulo Ramos
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.AlertSystem.model.Invoice;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class InvoiceController {

    private static final String FILE_PATH = "src/ec/edu/espe/AlertSystem/util/invoices.json";
    private List<Invoice> invoices;

    public InvoiceController() {
        ensureDirectoryExists();
        invoices = loadInvoices();
    }

    public boolean addInvoice(Invoice invoice) {
        if (invoice == null) {
            System.out.println("No se puede agregar una factura nula.");
            return false;
        }
        boolean exists = invoices.stream()
                .anyMatch(inv -> inv.getInvoiceNumber() == invoice.getInvoiceNumber());
        if (exists) {
            System.out.println("Ya existe una factura con el número: " + invoice.getInvoiceNumber());
            return false;
        }
        invoices.add(invoice);
        saveInvoices();
        return true;
    }

    public List<Invoice> getAllInvoices() {
        return invoices;
    }

    public Invoice findByNumber(int number) {
        return invoices.stream()
                .filter(inv -> inv.getInvoiceNumber() == number)
                .findFirst()
                .orElse(null);
    }

    public boolean removeByNumber(int number) {
        Invoice invoice = findByNumber(number);
        if (invoice != null) {
            invoices.remove(invoice);
            saveInvoices();
            return true;
        }
        return false;
    }

    public int generateNextNumber() {
        return invoices.stream()
                .mapToInt(Invoice::getInvoiceNumber)
                .max()
                .orElse(0) + 1;
    }

    private List<Invoice> loadInvoices() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Invoice>>() {
            }.getType();
            List<Invoice> loaded = new Gson().fromJson(reader, listType);
            return (loaded != null) ? loaded : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveInvoices() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                new Gson().toJson(invoices, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ensureDirectoryExists() {
        File file = new File(FILE_PATH);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

}
