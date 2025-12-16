package ec.edu.espe.alertsystem.model;

/**
 *
 * @author Paulo Ramos
 */
public abstract class Customer {

    protected String name;
    protected String phone;
    protected String email;
    protected Address address;

    public abstract String getCustomerType();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    
    public String getContactInfo() {
        return name + " | Tel: " + phone + " | Email: " + email;
    }

}
