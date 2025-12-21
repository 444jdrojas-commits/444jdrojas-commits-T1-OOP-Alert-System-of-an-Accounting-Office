package ec.edu.espe.AlertSystem.model;

import java.util.Date;

/**
 *
 * @author Paulo Ramos
 */
public abstract class Person {
   protected String name;
   protected Date birthDate;
   protected String phone;
   protected String email;

   
    public abstract String getRole();

    public String getBasicInfo() {
        return name + " | Tel: " + phone + " | Email: " + email;
    }
}

