/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 * @author Rene Garcia
 */
public class mdlUsuario {
     private String UUID_Usuario;
    private String Nombre_usuario;
    private String contra_usaurio;
    private String correo_usaurio;
    private String foto_usuario;
    private String rol;
    private String vet;

    public mdlUsuario() {
    }

    public String getUUID_Usuario() {
        return UUID_Usuario;
    }

    public void setUUID_Usuario(String UUID_Usuario) {
        this.UUID_Usuario = UUID_Usuario;
    }

    public String getNombre_usuario() {
        return Nombre_usuario;
    }

    public void setNombre_usuario(String Nombre_usuario) {
        this.Nombre_usuario = Nombre_usuario;
    }

    public String getContra_usaurio() {
        return contra_usaurio;
    }

    public void setContra_usaurio(String contra_usaurio) {
        this.contra_usaurio = contra_usaurio;
    }

    public String getCorreo_usaurio() {
        return correo_usaurio;
    }

    public void setCorreo_usaurio(String correo_usaurio) {
        this.correo_usaurio = correo_usaurio;
    }

    public String getFoto_usuario() {
        return foto_usuario;
    }

    public void setFoto_usuario(String foto_usuario) {
        this.foto_usuario = foto_usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getVet() {
        return vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }

 public mdlUsuario(String uuid, String nombre)
    {
        this.UUID_Usuario = uuid;
        this.Nombre_usuario = nombre;
    }
    
      @Override
    public String toString()
    {
        return Nombre_usuario;
    }
    
    
    //Metodo para cargar los valores en el ComboBox
    public void CargarComboUsuarios(JComboBox comboBox){    
        Connection conexion = ClaseConexion.getConexion();
        comboBox.removeAllItems();
        try{
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from tbUsuariosOne");
            while (rs.next()) {
                String uuid = rs.getString("UUID_Usuario");
                String nombre = rs.getString("nombre_usuario");
                comboBox.addItem(new mdlUsuario(uuid,nombre));                
            }
        }
        catch(SQLException ex)
        {
            
        }
    }
    
}



