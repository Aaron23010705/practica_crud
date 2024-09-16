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
public class mdlVet {

    
        private String UUID_veterinaria;
    private String Nombre_veterinaria;
    private String Ubicacion_veterinaria;
    private String NIT;
    private String contacto_veterinaria;
    private String correo_veterinaria;
    private String descripcion_servicio;

    public mdlVet() {
    }
    
    
    public String getUUID_veterinaria() {
        return UUID_veterinaria;
    }

    public void setUUID_veterinaria(String UUID_veterinaria) {
        this.UUID_veterinaria = UUID_veterinaria;
    }

    public String getNombre_veterinaria() {
        return Nombre_veterinaria;
    }

    public void setNombre_veterinaria(String Nombre_veterinaria) {
        this.Nombre_veterinaria = Nombre_veterinaria;
    }

    public String getUbicacion_veterinaria() {
        return Ubicacion_veterinaria;
    }

    public void setUbicacion_veterinaria(String Ubicacion_veterinaria) {
        this.Ubicacion_veterinaria = Ubicacion_veterinaria;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getContacto_veterinaria() {
        return contacto_veterinaria;
    }

    public void setContacto_veterinaria(String contacto_veterinaria) {
        this.contacto_veterinaria = contacto_veterinaria;
    }

    public String getCorreo_veterinaria() {
        return correo_veterinaria;
    }

    public void setCorreo_veterinaria(String correo_veterinaria) {
        this.correo_veterinaria = correo_veterinaria;
    }

    public String getDescripcion_servicio() {
        return descripcion_servicio;
    }

    public void setDescripcion_servicio(String descripcion_servicio) {
        this.descripcion_servicio = descripcion_servicio;
    }
    
     public mdlVet(String uuid, String nombre)
    {
        this.UUID_veterinaria = uuid;
        this.Nombre_veterinaria = nombre;
    }
    
      @Override
    public String toString()
    {
        return Nombre_veterinaria;
    }
    
    
    //Metodo para cargar los valores en el ComboBox
    public void cargarComboVet(JComboBox comboBox){    
        Connection conexion = ClaseConexion.getConexion();
        comboBox.removeAllItems();
        try{
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from tbVeterinarias");
            while (rs.next()) {
                String uuid = rs.getString("UUID_veterinaria");
                String nombre = rs.getString("nombre_veterinaria");
                comboBox.addItem(new mdlVet(uuid,nombre));                
            }
        }
        catch(SQLException ex)
        {
            
        }
    }
    
}
