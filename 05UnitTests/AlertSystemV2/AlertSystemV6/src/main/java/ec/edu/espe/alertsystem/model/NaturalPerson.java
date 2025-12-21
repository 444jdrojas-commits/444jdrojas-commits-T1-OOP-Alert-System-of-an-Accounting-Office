package ec.edu.espe.alertsystem.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Paulo Ramos
 */
public class NaturalPerson extends Customer {

    private String identification;
    private String nationality;
    private Date birthDate;
    private String occupation;
    private String gender;

    public NaturalPerson(String name, String identification, String nationality, Date birthDate, String phone, String email, String occupation, String gender, Address address) {
        this.name = name;
        this.identification = identification;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.occupation = occupation;
        this.gender = gender;
        this.address = address; 
    }
    
    public NaturalPerson(){
        
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birthFormatted = (birthDate != null) ? sdf.format(birthDate) : "No registrada";
        return "\nInformacion de Persona Natural:"
                + "\n   Nombre: " + name
                + "\n   Identificacion: " + identification
                + "\n   Nacionalidad: " + nationality
                + "\n   Fecha de nacimiento: " + birthFormatted
                + "\n   Telefono: " + phone
                + "\n   Correo: " + email
                + "\n   Ocupacion: " + occupation
                + "\n   Genero: " + gender
                + "\n   Direccion: " + (address != null
                        ? ("\n      Ciudad: " + address.getCity()
                        + "\n      Calle: " + address.getStreet()
                        + "\n      Sector: " + address.getSector())
                        : "No tiene dirección registrada")
                + "\n-------------------------------------------";
    }

    @Override
    public String getCustomerType() {
        return "Persona Natural";
    }
}
