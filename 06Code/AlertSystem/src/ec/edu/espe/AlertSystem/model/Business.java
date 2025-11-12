package ec.edu.espe.AlertSystem.model;

/**
 *
 * @author Paulo Ramos
 */

public class Business {
    private String nameBusiness;
    private String legalRepresentative;
    private String typeBusiness;
    private String address;
    private String phone;
    private String email;
    private String ruc;

    public Business(String nameBusiness, String legalRepresentative, String typeBusiness, String address, String phone, String email, String ruc) {
        this.nameBusiness = nameBusiness;
        this.legalRepresentative = legalRepresentative;
        this.typeBusiness = typeBusiness;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.ruc = ruc;
    }

    public void setNameBusiness(String nameBusiness) {
        this.nameBusiness = nameBusiness;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public void setTypeBusiness(String typeBusiness) {
        this.typeBusiness = typeBusiness;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    public String getNameBusiness() {
        return nameBusiness;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public String getTypeBusiness() {
        return typeBusiness;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getRuc() {
        return ruc;
    }   

    @Override
    public String toString() {
        return "Business{" + "nameBusiness=" + nameBusiness + ", legalRepresentative=" + legalRepresentative + ", typeBusiness=" + typeBusiness + ", address=" + address + ", phone=" + phone + ", email=" + email + ", ruc=" + ruc + '}';
    }

}
