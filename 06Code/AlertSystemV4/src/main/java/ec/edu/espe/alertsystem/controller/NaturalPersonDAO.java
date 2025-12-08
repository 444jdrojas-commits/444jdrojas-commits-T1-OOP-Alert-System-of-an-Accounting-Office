package ec.edu.espe.alertsystem.controller;

/**
 *
 * @author Paulo Ramos
 */
import com.mongodb.client.MongoCollection;
import ec.edu.espe.alertsystem.model.Address;
import ec.edu.espe.alertsystem.model.NaturalPerson;
import org.bson.Document;

public class NaturalPersonDAO {

    public boolean save(NaturalPerson naturalPerson) {
        try {
            MongoCollection<Document> collection =
                    MongoConnection.getConnection().getCollection("naturalPersons");

            Address a = naturalPerson.getAddress();
            Document addressDoc = new Document()
                    .append("city", a.getCity())
                    .append("street", a.getStreet())
                    .append("sector", a.getSector());

            Document doc = new Document()
                    .append("name", naturalPerson.getName())
                    .append("identification", naturalPerson.getIdentification())
                    .append("nationality", naturalPerson.getNationality())
                    .append("birthDate", naturalPerson.getBirthDate())
                    .append("phone", naturalPerson.getPhone())
                    .append("email", naturalPerson.getEmail())
                    .append("occupation", naturalPerson.getOccupation())
                    .append("gender", naturalPerson.getGender())
                    .append("address", addressDoc);  // <<<<<<<<<< IMPORTANTE

            collection.insertOne(doc);

            return true;

        } catch (Exception e) {
            System.out.println("Error MongoDB: " + e.getMessage());
            return false;
        }
    }
}
