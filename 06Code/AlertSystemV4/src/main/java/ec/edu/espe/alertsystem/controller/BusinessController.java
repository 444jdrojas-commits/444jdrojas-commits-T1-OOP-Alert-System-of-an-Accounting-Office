package ec.edu.espe.alertsystem.controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.alertsystem.model.Address;
import ec.edu.espe.alertsystem.model.Audit;
import ec.edu.espe.alertsystem.model.Business;
import org.bson.Document;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Paulo Ramos
 */
public class BusinessController {

    public boolean addBusiness(String name, String legalRepresentative, String typeBusiness,
            Address address, String phone, String email, String ruc, Audit audit) {

        Business business = new Business(name, legalRepresentative, typeBusiness,
                address, phone, email, ruc, audit);

        return save(business);
    }

    public boolean save(Business business) {
        try {
            MongoCollection<Document> collection
                    = MongoConnection.getConnection().getCollection("businesses");

            Address a = business.getAddress();
            Document addressDoc = new Document()
                    .append("city", a.getCity())
                    .append("street", a.getStreet())
                    .append("sector", a.getSector());

            Document doc = new Document()
                    .append("nameBusiness", business.getName())
                    .append("legalRepresentative", business.getLegalRepresentative())
                    .append("typeBusiness", business.getTypeBusiness())
                    .append("ruc", business.getRuc())
                    .append("phone", business.getPhone())
                    .append("email", business.getEmail())
                    .append("address", addressDoc);

            if (business.getAudit() != null) {
                Audit au = business.getAudit();

                Document auditDoc = new Document()
                        .append("auditDate", au.getAuditDate())
                        .append("hour", au.getHour())
                        .append("description", au.getDescription());

                doc.append("audit", auditDoc);
            }

            collection.insertOne(doc);
            return true;

        } catch (Exception e) {
            System.out.println("Error MongoDB Business: " + e.getMessage());
            return false;
        }
    }

    public List<Document> getAll() {
        MongoCollection<Document> collection
                = MongoConnection.getConnection().getCollection("businesses");

        List<Document> list = new ArrayList<>();

        for (Document doc : collection.find()) {
            doc.append("type", "Empresa");
            list.add(doc);
        }

        return list;
    }

}
