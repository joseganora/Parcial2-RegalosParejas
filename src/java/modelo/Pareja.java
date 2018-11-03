/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author PEPE
 */
public class Pareja {
    private int id;
    private String nombre1;
    private String apellido1;
    private String nombre2;
    private String apellido2;
    private Date fechaCasamiento;
    private int idComercio;

    public Pareja(int id, String nombre1, String apellido1, String nombre2, String apellido2, Date fechaCasamiento, int idComercio) {
        this.id = id;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.nombre2 = nombre2;
        this.apellido2 = apellido2;
        this.fechaCasamiento = fechaCasamiento;
        this.idComercio = idComercio;
    }

    public Pareja(String nombre1, String apellido1, String nombre2, String apellido2, Date fechaCasamiento, int idComercio) {
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.nombre2 = nombre2;
        this.apellido2 = apellido2;
        this.fechaCasamiento = fechaCasamiento;
        this.idComercio = idComercio;
    }

    public Date getFechaCasamiento() {
        return fechaCasamiento;
    }
    
    public String getFechaCasamientoTexto(){
       return (new SimpleDateFormat("dd/MM/YYYY")).format(fechaCasamiento);
    }

    public void setFechaCasamiento(Date fechaCasamiento) {
        this.fechaCasamiento = fechaCasamiento;
    }

    public int getIdComercio() {
        return idComercio;
    }

    public void setIdComercio(int idComercio) {
        this.idComercio = idComercio;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    
}
