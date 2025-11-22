package ec.edu.espe.AlertSystem.model;

/**
 *
 * @author Josue Rojas
 */
public class Address {

    private String city;
    private String street;
    private String sector;

    public Address(String city, String street, String sector) {
        this.city = city;
        this.street = street;
        this.sector = sector;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Address{" + "city=" + city + ", street=" + street + ", sector=" + sector + '}';
    }

}
