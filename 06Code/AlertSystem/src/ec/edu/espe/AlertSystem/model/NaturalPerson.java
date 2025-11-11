/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystem.model;

import java.util.Date;

/**
 *
 * @author Paulo Ramos
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

    public NaturalPerson(String name, String identification, String nationality, Date birthDate, String phone, String email, String ocuppation, String gender) {
        this.name = name;
        this.identification = identification;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.ocuppation = ocuppation;
        this.gender = gender;
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

    @Override
    public String toString() {
        return "NaturalPerson{" + "name=" + name + ", identification=" + identification + ", nationality=" + nationality + ", birthDate=" + birthDate + ", phone=" + phone + ", email=" + email + ", ocuppation=" + ocuppation + ", gender=" + gender + '}';
    }
    
}
