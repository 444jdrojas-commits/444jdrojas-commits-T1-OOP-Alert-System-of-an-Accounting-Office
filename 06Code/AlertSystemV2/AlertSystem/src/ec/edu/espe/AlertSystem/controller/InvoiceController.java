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
package ec.edu.espe.AlertSystem.controller;

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

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
        saveInvoices();
    }

    public List<Invoice> getAllInvoices() {
        return invoices;
    }

    public int generateNextNumber() {
        return invoices.isEmpty() ? 1 : invoices.get(invoices.size() - 1).getInvoiceNumber() + 1;
    }

    // 👇 Cargar facturas desde JSON
    private List<Invoice> loadInvoices() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Invoice>>() {}.getType();
            List<Invoice> loaded = new Gson().fromJson(reader, listType);
            return (loaded != null) ? loaded : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    // 👇 Guardar facturas en JSON
    private void saveInvoices() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // crea la carpeta util si no existe

            try (FileWriter writer = new FileWriter(file)) {
                new Gson().toJson(invoices, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 👇 Asegurar que el directorio exista
    private void ensureDirectoryExists() {
        File file = new File(FILE_PATH);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
