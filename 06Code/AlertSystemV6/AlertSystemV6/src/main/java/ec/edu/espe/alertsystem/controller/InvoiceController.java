package ec.edu.espe.alertsystem.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Paulo Ramos
 */
public class InvoiceController {

    public void saveInvoice(double amount, String details) {

    MongoCollection<Document> collection
            = MongoConnection.getConnection().getCollection("invoices");

    int nextInvoiceNumber = getNextInvoiceNumber();

    Document doc = new Document()
            .append("invoiceNumber", nextInvoiceNumber)
            .append("amountPaid", amount)
            .append("details", details)
            .append("status", "Pendiente");

    collection.insertOne(doc);
}

    private int getNextInvoiceNumber() {

        MongoCollection<Document> collection
                = MongoConnection.getConnection().getCollection("invoices");

        Document last = collection
                .find()
                .sort(new Document("invoiceNumber", -1))
                .first();

        if (last == null) {
            return 1;
        }

        return last.getInteger("invoiceNumber") + 1;
    }

    public static List<Document> getInvoices() {

        List<Document> invoices = new ArrayList<>();

        MongoCollection<Document> collection
                = MongoConnection.getDatabase().getCollection("invoices");

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                invoices.add(cursor.next());
            }
        }

        return invoices;
    }

    public static double calcularIva(double subtotal) {
        return subtotal * 0.15;
    }

    public static double calcularTotal(double subtotal) {
        return subtotal + calcularIva(subtotal);
    }

    public static void actualizarPagoFactura(int invoiceNumber, Date paymentDate) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("invoices");

        Document filter = new Document("invoiceNumber", invoiceNumber);

        Document update = new Document("$set", new Document("status", "Pagado").append("paymentDate", paymentDate));

        collection.updateOne(filter, update);
    }
    
    

}
