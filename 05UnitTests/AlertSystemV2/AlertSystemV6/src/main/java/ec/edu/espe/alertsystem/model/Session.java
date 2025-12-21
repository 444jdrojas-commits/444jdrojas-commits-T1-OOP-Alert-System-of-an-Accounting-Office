package ec.edu.espe.alertsystem.model;

/**
 *
 * @author JOSUE
 */

public class Session {

    private static Person loggedUser;

    public static void startSession(Person user) {
        loggedUser = user;
    }

    public static Person getUser() {
        return loggedUser;
    }

    public static String getAssistantId() {
    if (loggedUser instanceof Assistant assistant) {
        return String.valueOf(assistant.getId());
    }
    return null;
}


    public static void closeSession() {
        loggedUser = null;
    }
}
