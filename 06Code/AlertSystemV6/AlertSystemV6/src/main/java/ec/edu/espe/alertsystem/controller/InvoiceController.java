package ec.edu.espe.alertsystem.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author Paulo Ramos
 */
public class InvoiceController {

    public void saveInvoice(Date paymentDate, double amount, String details) {

        MongoCollection<Document> collection
                = MongoConnection.getConnection().getCollection("invoices");

        int nextInvoiceNumber = getNextInvoiceNumber();

        Document doc = new Document()
                .append("invoiceNumber", nextInvoiceNumber)
                .append("paymentDate", paymentDate)
                .append("amountPaid", amount)
                .append("details", details)
                .append("status", "Pagado");

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

}
