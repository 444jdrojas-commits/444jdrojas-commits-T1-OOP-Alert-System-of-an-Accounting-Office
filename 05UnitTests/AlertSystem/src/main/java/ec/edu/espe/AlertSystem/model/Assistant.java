package ec.edu.espe.AlertSystem.model;

import java.util.Date;

/**
 *
 * @author Josue Rojas
 */
public class Assistant extends Person {

    private int id;
    private String user;
    private String password;

    public Assistant() {
    }

    public Assistant(int id, String name, Date birthDate, String phone, String email, String user, String password) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Assistant{" + "id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", phone=" + phone + ", email=" + email + ", user=" + user + ", password=" + password + '}';
    }

    @Override
    public String getRole() {
        return "Asistente";
    }

}
