package ec.edu.espe.alertsystem.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.edu.espe.alertsystem.model.User;
import org.bson.Document;

/**
 *
 * @author JOSUE
 */
public class UserController {
        private MongoCollection<Document> getCollection() {
        return MongoConnection.getConnection().getCollection("Users");
    }

    public boolean validateUser(User user) {
        if (user == null) {
            return false;
        }

        String username = user.getName();
        String passwordUser = user.getPassword();

        if (username == null || username.isBlank()
                || passwordUser == null || passwordUser.isBlank()) {
            return false;
        }
        Document query = new Document("username", username).append("password", passwordUser);

        MongoCursor<Document> cursor = getCollection().find(query).iterator();

        boolean exists = cursor.hasNext();
        cursor.close();

        return exists;
    }

}
