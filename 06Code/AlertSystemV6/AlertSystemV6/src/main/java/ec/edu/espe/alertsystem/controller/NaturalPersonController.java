package ec.edu.espe.alertsystem.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import ec.edu.espe.alertsystem.model.Address;
import ec.edu.espe.alertsystem.model.NaturalPerson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Paulo Ramos
 */
public class NaturalPersonController {

    public boolean addNaturalPerson(String name, String identification, String nationality, Date birthDate, String phone, String email, String occupation, String gender, Address address) {
        NaturalPerson naturalPerson = new NaturalPerson(name, identification, nationality, birthDate, phone, email, occupation, gender, address);
        return save(naturalPerson);
    }

    public boolean save(NaturalPerson naturalPerson) {
        try {
            MongoCollection<Document> collection
                    = MongoConnection.getConnection().getCollection("naturalPersons");

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
                    .append("address", addressDoc);

            collection.insertOne(doc);

            return true;

        } catch (Exception e) {
            System.out.println("Error MongoDB: " + e.getMessage());
            return false;
        }
    }

    public List<Document> getAll() {
        MongoCollection<Document> collection = MongoConnection.getConnection().getCollection("naturalPersons");

        List<Document> list = new ArrayList<>();

        for (Document doc : collection.find()) {
            doc.append("type", "Natural");
            list.add(doc);
        }

        return list;
    }

    public static void deleteNaturalPerson(String ci) {

        MongoCollection<Document> col
                = MongoConnection.getConnection().getCollection("naturalPersons");

        Bson filtro = Filters.eq("identification", ci);

        col.deleteOne(filtro);
    }

    public static void updateNaturalPerson(String ci, String nombre,
            String telefono, String correo, String ciudad) {

        MongoCollection<Document> col
                = MongoConnection.getConnection().getCollection("naturalPersons");

        Bson filtro = Filters.eq("identification", ci);

        Bson update = Updates.combine(
                Updates.set("name", nombre),
                Updates.set("phone", telefono),
                Updates.set("email", correo),
                Updates.set("address.city", ciudad)
        );

        col.updateOne(filtro, update);
    }
    
    

}
