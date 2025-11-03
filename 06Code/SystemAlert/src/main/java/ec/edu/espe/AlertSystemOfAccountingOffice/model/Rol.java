/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystemOfAccountingOffice.model;

/**
 *
 * @author JOSUE
 */
public class Rol {
    private String rol;
    private String name;
    private String description;

    public Rol(String rol, String name, String description) {
        this.rol = rol;
        this.name = name;
        this.description = description;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getRol() {
        return rol;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    
    
}
