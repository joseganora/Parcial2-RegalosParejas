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
public class Regalo {
    private int id;
    private int idPareja;
    private int idArticulo;
    private int idComprador;
    private int cantidad;
    private boolean regalado;

    public Regalo(int id, int idPareja, int idArticulo, int idComprador, int cantidad, boolean regalado) {
        this.id = id;
        this.idPareja = idPareja;
        this.idArticulo = idArticulo;
        this.idComprador = idComprador;
        this.cantidad = cantidad;
        this.regalado = regalado;
    }
    public Regalo( int idPareja, int idArticulo, int idComprador, int cantidad, boolean regalado) {
        this.id = 0;
        this.idPareja = idPareja;
        this.idArticulo = idArticulo;
        this.idComprador = idComprador;
        this.cantidad = cantidad;
        this.regalado = regalado;
    }

    public Regalo(int idPareja, int idArticulo) {
        this.idPareja = idPareja;
        this.idArticulo = idArticulo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPareja() {
        return idPareja;
    }

    public void setIdPareja(int idPareja) {
        this.idPareja = idPareja;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isRegalado() {
        return regalado;
    }

    public void setRegalado(boolean regalado) {
        this.regalado = regalado;
    }
    
}
