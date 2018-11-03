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
public class Articulo {
    private int id;
    private String codigo;
    private String denominacion;
    private float precioUnitario;
    private int idTipo;

    public Articulo(int id, String codigo, String denominacion, float precioUnitario, int idTipo) {
        this.id = id;
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.precioUnitario = precioUnitario;
        this.idTipo = idTipo;
    }

    public Articulo(String codigo, String denominacion, float precioUnitario, int idTipo) {
        this.id=0;
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.precioUnitario = precioUnitario;
        this.idTipo = idTipo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
    
}
