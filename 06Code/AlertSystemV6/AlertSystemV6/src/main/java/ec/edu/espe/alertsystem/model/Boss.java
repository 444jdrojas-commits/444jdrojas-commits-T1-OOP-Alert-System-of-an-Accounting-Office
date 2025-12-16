package ec.edu.espe.alertsystem.model;

import java.util.Date;

/**
 *
 * @author Paulo Ramos
 */

public class Boss extends Person {

    private int id;
    private String user;
    private String password;

    public Boss() {
    }

    public Boss(int id, String name, Date birthDate, String phone,
                String email, String user, String password) {
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
    public String getRole() {
        return "Jefe";
    }

    @Override
    public String toString() {
        return "Boss{" +
                "id=" + id +
                ", name=" + name +
                ", birthDate=" + birthDate +
                ", phone=" + phone +
                ", email=" + email +
                ", user=" + user +
                '}';
    }
}
    
