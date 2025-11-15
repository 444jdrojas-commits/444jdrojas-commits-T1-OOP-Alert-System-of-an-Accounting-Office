package ec.edu.espe.AlertSystem.model;

/**
 *
 * @author Josue Rojas
 */
public class Business {

    private String nameBusiness;
    private String legalRepresentative;
    private String typeBusiness;
    private Address address;
    private String phone;
    private String email;
    private String ruc;
    private Audit audit;

    public Business(String nameBusiness, String legalRepresentative, String typeBusiness, Address address, String phone, String email, String ruc, Audit audit) {
        this.nameBusiness = nameBusiness;
        this.legalRepresentative = legalRepresentative;
        this.typeBusiness = typeBusiness;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.ruc = ruc;
        this.audit = audit;
    }

    public String getNameBusiness() {
        return nameBusiness;
    }

    public void setNameBusiness(String nameBusiness) {
        this.nameBusiness = nameBusiness;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getTypeBusiness() {
        return typeBusiness;
    }

    public void setTypeBusiness(String typeBusiness) {
        this.typeBusiness = typeBusiness;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public String toString() {
        return "\nInformacion de la Empresa:"
                + "\n   Nombre: " + nameBusiness
                + "\n   Representante Legal: " + legalRepresentative
                + "\n   Tipo de Empresa: " + typeBusiness
                + "\n   Direccion: " + (address != null
                        ? ("\n      Ciudad: " + address.getCity()
                        + "\n      Calle: " + address.getStreet()
                        + "\n      Sector: " + address.getSector())
                        : "No tiene direccion registrada")
                + "\n   Telefono: " + phone
                + "\n   Correo: " + email
                + "\n   RUC: " + ruc
                + "\n   Auditoria: " + (audit != null
                        ? ("\n      Fecha: " + audit.getAuditDate()
                        + "\n      Hora: " + audit.getHour()
                        + "\n      Descripcion: " + audit.getDescription())
                        : "No tiene auditoria registrada")
                + "\n-------------------------------------------";
    }
}
