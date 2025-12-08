package ec.edu.espe.alertsystem.controller;

import ec.edu.espe.alertsystem.model.Address;
import ec.edu.espe.alertsystem.model.NaturalPerson;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Paulo Ramos
 */
public class NaturalPersonController {

    private NaturalPersonDAO dao = new NaturalPersonDAO();

    public boolean addNaturalPerson(String name, String identification, String nationality, Date birthDate, String phone, String email, String occupation, String gender, Address address) {
        NaturalPerson naturalPerson = new NaturalPerson(name, identification, nationality, birthDate, phone, email, occupation, gender, address);
        return dao.save(naturalPerson);
    }

}
