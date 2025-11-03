/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystemOfAccountingOffice.model;

/**
 *
 * @author Paulo Ramos
 */
public class Addres {
    
    private String country;
    private String city;
    private String Street;

    public Addres(String country, String city, String Street) {
        this.country = country;
        this.city = city;
        this.Street = Street;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    @Override
    public String toString() {
        return "Addres{" + "country=" + country + ", city=" + city + ", Street=" + Street + '}';
    }
}
