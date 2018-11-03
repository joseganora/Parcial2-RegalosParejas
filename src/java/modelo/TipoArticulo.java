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
public class TipoArticulo {
private int id;
private String denominacion;

    public TipoArticulo(int id, String denominacion) {
        this.id = id;
        this.denominacion = denominacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String nombre) {
        this.denominacion = nombre;
    }

}
