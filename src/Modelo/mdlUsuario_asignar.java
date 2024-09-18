/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JComboBox;

/**
 *
 * @author Rene Garcia
 */
public class mdlUsuario_asignar {
    
     private String UUID_Usuario_asignar;
    private String Nombre_usuario_asignar;
    private String contra_usaurio_asignar;
    private String correo_usaurio_asignar;
    private String foto_usuario_asignar;
    private String rol_asignar;
    private String vet_asignar;

    public mdlUsuario_asignar() {

    }

    public String getUUID_Usuario_asignar() {
        return UUID_Usuario_asignar;
    }

    public void setUUID_Usuario_asignar(String UUID_Usuario_asignar) {
        this.UUID_Usuario_asignar = UUID_Usuario_asignar;
    }

    public String getNombre_usuario_asignar() {
        return Nombre_usuario_asignar;
    }

    public void setNombre_usuario_asignar(String Nombre_usuario_asignar) {
        this.Nombre_usuario_asignar = Nombre_usuario_asignar;
    }

    public String getContra_usaurio_asignar() {
        return contra_usaurio_asignar;
    }

    public void setContra_usaurio_asignar(String contra_usaurio_asignar) {
        this.contra_usaurio_asignar = contra_usaurio_asignar;
    }

    public String getCorreo_usaurio_asignar() {
        return correo_usaurio_asignar;
    }

    public void setCorreo_usaurio_asignar(String correo_usaurio_asignar) {
        this.correo_usaurio_asignar = correo_usaurio_asignar;
    }

    public String getFoto_usuario_asignar() {
        return foto_usuario_asignar;
    }

    public void setFoto_usuario_asignar(String foto_usuario_asignar) {
        this.foto_usuario_asignar = foto_usuario_asignar;
    }

    public String getRol_asignar() {
        return rol_asignar;
    }

    public void setRol_asignar(String rol_asignar) {
        this.rol_asignar = rol_asignar;
    }

    public String getVet_asignar() {
        return vet_asignar;
    }

    public void setVet_asignar(String vet_asignar) {
        this.vet_asignar = vet_asignar;
    }
       
    public mdlUsuario_asignar(String uuid, String nombre)
    {
        this.UUID_Usuario_asignar = uuid;
        this.Nombre_usuario_asignar = nombre;
    }
    
      @Override
    public String toString()
    {
        return Nombre_usuario_asignar;
    }
    
    
    //Metodo para cargar los valores en el ComboBox
    public void CargarComboUsuarios_asignar(JComboBox comboBox) throws SQLException{    
        Connection conexion = ClaseConexion.getConexion();
        comboBox.removeAllItems();
        
     Statement statement = conexion.createStatement();
ResultSet rs = statement.executeQuery("Select UUID_usuario, nombre_usuario from tbUsuariosOne where rol = 'Empleado'");

while (rs.next()) {
    String uuid = rs.getString("UUID_usuario");
    String nombre = rs.getString("nombre_usuario");
    comboBox.addItem(new mdlUsuario_asignar(uuid, nombre));
}
    }
}



