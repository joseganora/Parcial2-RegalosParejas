/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author PEPE
 */
public class Usuario {
private String nombre;
private String contra;
private int id;
private boolean autenticado;

    public Usuario(String nombre, String contra, int id) {
        this.nombre = nombre;
        this.contra = contra;
        this.id = id;
        autenticado=false;
    }
    public Usuario(String nombre, String contra) {
        this.nombre = nombre;
        this.contra = contra;
        this.id = -1;
        autenticado=false;
    }

    public void autenticar(){
        autenticado=true;

    }

    public boolean isAutenticado() {
        return autenticado;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
