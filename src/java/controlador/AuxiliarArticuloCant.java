/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.TipoArticulo;

/**
 *
 * @author PEPE
 */
public class AuxiliarArticuloCant {
    private TipoArticulo a;
    private int cant;

    public AuxiliarArticuloCant(TipoArticulo a, int cant) {
        this.a = a;
        this.cant = cant;
    }
    public AuxiliarArticuloCant(TipoArticulo a) {
        this.a = a;
        this.cant = 0;
    }
    
    public TipoArticulo getA() {
        return a;
    }

    public void setA(TipoArticulo a) {
        this.a = a;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
    
}
