package ec.edu.espe.AlertSystem.model;

import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;

/**
 *
 * @author Josue Rojas
 */
public class Business extends Customer {

    @SerializedName("nameBusiness")
    private String name;

    private String legalRepresentative;
    private String typeBusiness;
    private String ruc;
    private Audit audit;

    public Business(String name, String legalRepresentative, String typeBusiness,
            Address address, String phone, String email, String ruc, Audit audit) {
        this.name = name;
        this.legalRepresentative = legalRepresentative;
        this.typeBusiness = typeBusiness;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.ruc = ruc;
        this.audit = audit;
    }

    // getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        return "\nInformacion de la Empresa:"
                + "\n   Nombre: " + name
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
                        ? ("\n      Fecha: " + sdfDate.format(audit.getAuditDate())
                        + "\n      Hora: " + audit.getHour()
                        + "\n      Descripcion: " + audit.getDescription())
                        : "No tiene auditoria registrada")
                + "\n-------------------------------------------";
    }

    @Override
    public String getCustomerType() {
        return "Empresa";
    }
}
