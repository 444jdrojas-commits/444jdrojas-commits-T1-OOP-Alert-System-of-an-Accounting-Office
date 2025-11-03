/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystemOfAccountingOffice.model;

/**
 *
 * @author JOSUE
 */
public class Boss {
    private String name;
    private Date birthdate;
    private int phone;
    private String email;
    private String user;
    private String password;

    public Boss(String name, Date birthdate, int phone, String email, String user, String password) {
        this.name = name;
        this.birthdate = birthdate;
        this.phone = phone;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    
    
    
}
