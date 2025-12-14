package ec.edu.espe.alertsystem.controller;

import ec.edu.espe.alertsystem.model.User;

/**
 *
 * @author JOSUE
 */
public class UserController {

    public static boolean validateUser(User user) {

        if (user == null) {
            return false;
        }

        String username = user.getName();
        String passwordUser = user.getPassword();

        if (username == null || username.isBlank()
                || passwordUser == null || passwordUser.isBlank()) {
            return false;
        }

        return username.equals("sandra") && passwordUser.equals("sandra123")||username.equals("paulo") && passwordUser.equals("paulo123")||username.equals("josue") && passwordUser.equals("josue123");
  
    }
}
