package ec.edu.espe.AlertSystem.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Josue Rojas
 */
public class NaturalPerson {

    private String name;
    private String identification;
    private String nationality;
    private Date birthDate;
    private String phone;
    private String email;
    private String ocuppation;
    private String gender;
    private Address address;

    public NaturalPerson(String name, String identification, String nationality, Date birthDate, String phone, String email, String ocuppation, String gender, Address address) {
        this.name = name;
        this.identification = identification;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.ocuppation = ocuppation;
        this.gender = gender;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOcuppation() {
        return ocuppation;
    }

    public void setOcuppation(String ocuppation) {
        this.ocuppation = ocuppation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birthFormatted = (birthDate != null) ? sdf.format(birthDate) : "No registrada";
        return "\nNatural Person Information:"
                + "\n   Name: " + name
                + "\n   Identification: " + identification
                + "\n   Nationality: " + nationality
                + "\n   Birth Date: " + birthFormatted
                + "\n   Phone: " + phone
                + "\n   Email: " + email
                + "\n   Occupation: " + ocuppation
                + "\n   Gender: " + gender
                + "\n   Address: " + (address != null
                        ? ("\n      Ciudad: " + address.getCity()
                        + "\n      Calle: " + address.getStreet()
                        + "\n      Sector: " + address.getSector())
                        : "No address registered")
                + "\n-------------------------------------------";
    }

}
