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
public class Comprador {
    private int id;
    private String nombre;
    private int idPareja;
    private int idTipoRelacion;

    public Comprador(int id, String nombre, int idPareja, int idTipoRelacion) {
        this.id = id;
        this.nombre = nombre;
        this.idPareja = idPareja;
        this.idTipoRelacion = idTipoRelacion;
    }

    public Comprador(String nombre, int idPareja, int idTipoRelacion) {
        this.nombre = nombre;
        this.idPareja = idPareja;
        this.idTipoRelacion = idTipoRelacion;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdPareja() {
        return idPareja;
    }

    public void setIdPareja(int idPareja) {
        this.idPareja = idPareja;
    }

    public int getIdTipoRelacion() {
        return idTipoRelacion;
    }

    public void setIdTipoRelacion(int idTipoRelacion) {
        this.idTipoRelacion = idTipoRelacion;
    }
    
}
