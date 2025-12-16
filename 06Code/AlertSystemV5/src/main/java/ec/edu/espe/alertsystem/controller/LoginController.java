package ec.edu.espe.alertsystem.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import ec.edu.espe.alertsystem.model.Assistant;
import ec.edu.espe.alertsystem.model.Boss;
import ec.edu.espe.alertsystem.model.Person;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.bson.Document;

/**
 *
 * @author JOSUE
 */
public class LoginController {

    public Person login(String username, String password) {

        MongoDatabase db = MongoConnection.getConnection();

        SimpleDateFormat mongoFormat =
                new SimpleDateFormat("MMM dd, yyyy, hh:mm:ss a", Locale.ENGLISH);

        MongoCollection<Document> assistants = db.getCollection("assistants");
        Document a = assistants.find(
                Filters.and(
                        Filters.eq("user", username),
                        Filters.eq("password", password)
                )
        ).first();

        if (a != null) {

            int id = a.getInteger("id", 0);

            Date birthDate = parseBirthDate(a.get("birthDate"), mongoFormat);

            return new Assistant(
                    id,
                    a.getString("name"),
                    birthDate,
                    a.getString("phone"),
                    a.getString("email"),
                    a.getString("user"),
                    a.getString("password")
            );
        }

        MongoCollection<Document> bosses = db.getCollection("bosses");
        Document b = bosses.find(
                Filters.and(
                        Filters.eq("user", username),
                        Filters.eq("password", password)
                )
        ).first();

        if (b != null) {

            int id = b.getInteger("id", 0);

            Date birthDate = parseBirthDate(b.get("birthDate"), mongoFormat);

            return new Boss(
                    id,
                    b.getString("name"),
                    birthDate,
                    b.getString("phone"),
                    b.getString("email"),
                    b.getString("user"),
                    b.getString("password")
            );
        }

        return null;
    }

    private Date parseBirthDate(Object birthObj, SimpleDateFormat mongoFormat) {

        if (birthObj == null) {
            return null;
        }

        try {
            if (birthObj instanceof Date) {
                return (Date) birthObj;
            } else if (birthObj instanceof String) {
                String clean = birthObj.toString()
                        .replace("\u202F", " ")
                        .trim();
                return mongoFormat.parse(clean);
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }
}
