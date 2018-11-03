/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Articulo;
import modelo.Comercio;
import modelo.Comprador;
import modelo.Pareja;
import modelo.Regalo;
import modelo.TipoArticulo;
import modelo.TipoRelacion;
import modelo.Usuario;

/**
 *
 * @author PEPE
 */
public class conexion {
    private String url="jdbc:sqlserver://localhost\\SQLEXPRESSPEPE:1433;databaseName=parcial2lab4;";
    private String user="nuevoUsuario";
    private String pass="3513850319";
    private Connection con;

    
    public conexion() {
        
        
    }
    public void abrirConexion() throws SQLException{

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
            con=DriverManager.getConnection(url,user,pass);

    }
     public void cerrarConexion() throws SQLException {

            con.close();

    }
    public Usuario getUsuario(String nombre,String contra){
        Usuario user=null;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from usuario where nombre=? and contrasenia=?");
            stmt.setString(1, nombre);
            stmt.setString(2, contra);
            ResultSet rs =stmt.executeQuery();
            if(rs.next()){
                user=new Usuario(rs.getString("nombre"), rs.getString("contrasenia"), rs.getInt("id"));
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    public int countRegalosPendientes(int id){
        int cant=0;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT count(*) cant from regalo where idPareja=? and idComprador is null");
            stmt.setInt(1, id);
            ResultSet rs =stmt.executeQuery();
            if(rs.next()){
                cant=rs.getInt("cant");
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cant;
    }
    public ArrayList<Articulo> getRegalosPendientes(int idParejaSeleccionada){
        ArrayList<Articulo> art=new ArrayList<Articulo>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT a.* from Articulo a join regalo r on a.id=r.idArticulo where r.idPareja=? and r.idComprador is null");
            stmt.setInt(1, idParejaSeleccionada);
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new Articulo(rs.getInt("id"), rs.getString("codigo"), rs.getString("denominacion"), rs.getFloat("precioUnitario"), rs.getInt("idTipo")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }
    public ArrayList<Articulo> getArticulos(){
        ArrayList<Articulo> art=new ArrayList<Articulo>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from Articulo");
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new Articulo(rs.getInt("id"), rs.getString("codigo"), rs.getString("denominacion"), rs.getFloat("precioUnitario"), rs.getInt("idTipo")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }
    public ArrayList<Articulo> getArticulosSeleccionados(int idPareja) {
        ArrayList<Articulo> art=new ArrayList<Articulo>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT a.* from Articulo a join regalo r on a.id=r.idArticulo where r.idPareja=?");
            stmt.setInt(1, idPareja);
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new Articulo(rs.getInt("id"), rs.getString("codigo"), rs.getString("denominacion"), rs.getInt("precioUnitario"), rs.getInt("idTipo")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }

    public ArrayList<Articulo> getArticulosNoSeleccionados(int idPareja) {
        ArrayList<Articulo> art=new ArrayList<Articulo>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT a.* from Articulo a where not exists (SELECT ar.id from Articulo ar join regalo r on ar.id=r.idArticulo where r.idPareja=? and ar.id=a.id)");
            stmt.setInt(1, idPareja);
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new Articulo(rs.getInt("id"), rs.getString("codigo"), rs.getString("denominacion"), rs.getInt("precioUnitario"), rs.getInt("idTipo")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }
    public ArrayList<Comprador> getCompradores() {
        ArrayList<Comprador> art=new ArrayList<Comprador>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from Comprador");
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new Comprador(rs.getInt("id"), rs.getString("nombre"), rs.getInt("idPareja"), rs.getInt("idTipoRelacion")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }
     public Comprador getComprador(int id) {
        Comprador art=null;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from comprador where id=?");
            stmt.setInt(1, id);
            ResultSet rs =stmt.executeQuery();
            if(rs.next()){
                art=new Comprador(rs.getInt("id"), rs.getString("nombre"), rs.getInt("idPareja"), rs.getInt("idTipoRelacion"));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }
    public Articulo getArticulo(int id){
        Articulo art=null;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from Articulo where id=?");
            stmt.setInt(1, id);
            ResultSet rs =stmt.executeQuery();
            if(rs.next()){
                art=new Articulo(rs.getInt("id"), rs.getString("codigo"), rs.getString("denominacion"), rs.getInt("precioUnitario"), rs.getInt("idTipo"));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }
    public Pareja getPareja(int id) {
        Pareja art=null;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from pareja where id=?");
            stmt.setInt(1, id);
            ResultSet rs =stmt.executeQuery();
            if(rs.next()){
                art=new Pareja(rs.getInt("id"), rs.getString("nombre1"), rs.getString("apellido1"), rs.getString("nombre2"), rs.getString("apellido2"),rs.getDate("fechaCasamiento"),rs.getInt("idComercio"));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }
    public boolean agregarArticulo(Articulo a){
        boolean band=false;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("insert into articulo (codigo,denominacion,precioUnitario,idTipo) values (?,?,?,?)");
            stmt.setString(1, a.getCodigo());
            stmt.setString(2, a.getDenominacion());
            stmt.setFloat(3, a.getPrecioUnitario());
            stmt.setInt(4, a.getIdTipo());
            band=(stmt.executeUpdate()==1);
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return band;
    }
    public boolean agregarRegalo(Regalo regalo) {
        boolean band=false;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("insert into regalo (idPareja,idArticulo) values (?,?)");
            stmt.setInt(1, regalo.getIdPareja());
            stmt.setInt(2, regalo.getIdArticulo());
            band=(stmt.executeUpdate()==1);
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return band;
    }
    public boolean agregarComprador(Comprador comprador) {
        boolean band=false;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("insert into comprador (nombre, idPareja, idTipoRelacion) values (?,?,?)");
            stmt.setString(1, comprador.getNombre());
            stmt.setInt(2, comprador.getIdPareja());
            stmt.setInt(3, comprador.getIdTipoRelacion());
            band=(stmt.executeUpdate()==1);
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return band;
    }
    public boolean agregarPareja(Pareja pareja) {
        boolean band=false;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("insert into pareja (nombre1,apellido1,nombre2,apellido2,fechaCasamiento,idComercio) values (?,?,?,?,?,?)");
            stmt.setString(1, pareja.getNombre1());
            stmt.setString(2, pareja.getApellido1());
            stmt.setString(3, pareja.getNombre2());
            stmt.setString(4, pareja.getApellido2());
            stmt.setDate(5, pareja.getFechaCasamiento());
            stmt.setInt(6, pareja.getIdComercio());
            band=(stmt.executeUpdate()==1);
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return band;
    }
    public boolean borrarArticulo(int id){
        boolean band=false;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("delete articulo where id=?");
            stmt.setInt(1, id);
            band=(stmt.executeUpdate()==1);
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return band;
    }
    public boolean borrarRegalo(int id, int idPareja) {
        boolean band=false;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("delete regalo where idArticulo=? and idPareja=?");
            stmt.setInt(1, id);
            stmt.setInt(2, idPareja);
            band=(stmt.executeUpdate()==1);
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return band;
    }
    public boolean borrarComprador(int id) {
        boolean band=false;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("delete comprador where id=?");
            stmt.setInt(1, id);
            band=(stmt.executeUpdate()==1);
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return band;
    }
    public boolean borrarPareja(int id) {
        boolean band=false;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("delete pareja where id=?");
            stmt.setInt(1, id);
            band=(stmt.executeUpdate()==1);
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return band;
    }
    public ArrayList<TipoArticulo> getTipoArticulos(){
        ArrayList<TipoArticulo> art=new ArrayList<TipoArticulo>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from TipoArticulo");
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new TipoArticulo(rs.getInt("id"), rs.getString("denominacion")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }

    public boolean setArticulo(Articulo a) {
        boolean band=false;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("update articulo set codigo=?,denominacion=?,precioUnitario=?,idTipo=? where id=?");
            stmt.setString(1, a.getCodigo());
            stmt.setString(2, a.getDenominacion());
            stmt.setFloat(3, a.getPrecioUnitario());
            stmt.setInt(4, a.getIdTipo());
            stmt.setInt(5, a.getId());
            band=(stmt.executeUpdate()==1);
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return band;
    }
    public boolean setComprador(Comprador a) {
        boolean band=false;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("update comprador set nombre=?,idPareja=?,idTipoRelacion=? where id=?");
            stmt.setString(1, a.getNombre());
            stmt.setInt(2, a.getIdPareja());
            stmt.setInt(3, a.getIdTipoRelacion());
            stmt.setInt(4, a.getId());
            band=(stmt.executeUpdate()==1);
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return band;
    }
    public boolean setPareja(Pareja a) {
        boolean band=false;
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("update pareja set nombre1=?,apellido1=?,nombre2=?,apellido2=?,fechaCasamiento=?,idComercio=? where id=?");
            stmt.setString(1, a.getNombre1());
            stmt.setString(2, a.getApellido1());
            stmt.setString(3, a.getNombre2());
            stmt.setString(4, a.getApellido2());
            stmt.setDate(5, a.getFechaCasamiento());
            stmt.setInt(6, a.getIdComercio());
            stmt.setInt(7, a.getId());
            band=(stmt.executeUpdate()==1);
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return band;
    }

    public ArrayList<Articulo> getArticulos(String cont) {
       ArrayList<Articulo> art=new ArrayList<Articulo>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from Articulo where upper(codigo)+upper(denominacion) like upper(?)");
            stmt.setString(1, "%"+cont+"%");
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new Articulo(rs.getInt("id"), rs.getString("codigo"), rs.getString("denominacion"), rs.getInt("precioUnitario"), rs.getInt("idTipo")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }
    public ArrayList<Comprador> getCompradores(String cont) {
        ArrayList<Comprador> art=new ArrayList<Comprador>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from comprador where upper(nombre) like upper(?)");
            stmt.setString(1, "%"+cont+"%");
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new Comprador(rs.getInt("id"), rs.getString("nombre"), rs.getInt("idPareja"), rs.getInt("idTipoRelacion")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }
    public ArrayList<Pareja> getParejas(String cont) {
        ArrayList<Pareja> art=new ArrayList<Pareja>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from pareja where upper(nombre1)+upper(apellido1)+upper(nombre2)+upper(apellido2) like upper(?)");
            stmt.setString(1, "%"+cont+"%");
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new Pareja(rs.getInt("id"), rs.getString("nombre1"), rs.getString("apellido1"), rs.getString("nombre2"), rs.getString("apellido2"),rs.getDate("fechaCasamiento"),rs.getInt("idComercio")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }
    public ArrayList<Pareja> getParejas() {
        ArrayList<Pareja> art=new ArrayList<Pareja>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from pareja");
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new Pareja(rs.getInt("id"), rs.getString("nombre1"), rs.getString("apellido1"), rs.getString("nombre2"), rs.getString("apellido2"),rs.getDate("fechaCasamiento"),rs.getInt("idComercio")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }

    public ArrayList<Comercio> getComercio() {
        ArrayList<Comercio> art=new ArrayList<Comercio>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from comercio");
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new Comercio(rs.getInt("id"), rs.getString("denominacion")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }
    public ArrayList<TipoRelacion> getTipoRelacion() {
        ArrayList<TipoRelacion> art=new ArrayList<TipoRelacion>();
        try {
            abrirConexion();
            PreparedStatement stmt=con.prepareStatement("SELECT * from tipoRelacion");
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                art.add(new TipoRelacion(rs.getInt("id"), rs.getString("nombre")));
            }
            rs.close();
            stmt.close();
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return art;
    }

    

    

    

    

}
