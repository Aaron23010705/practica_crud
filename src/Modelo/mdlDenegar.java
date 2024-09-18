/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.denegar_cita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rene Garcia
 */
public class mdlDenegar {
    
       private String Fecha_denegar;
    private String Motivo_denegar;
    private String Descripcion_denegar;
    private String UUID_veterinaria_denegar;
   private String UUID_mascota_denegar;
   private String UUID_Usuario_denegar;
   private String Estado_denegar;

    public String getFecha_denegar() {
        return Fecha_denegar;
    }

    public void setFecha_denegar(String Fecha_denegar) {
        this.Fecha_denegar = Fecha_denegar;
    }

    public String getMotivo_denegar() {
        return Motivo_denegar;
    }

    public void setMotivo_denegar(String Motivo_denegar) {
        this.Motivo_denegar = Motivo_denegar;
    }

    public String getDescripcion_denegar() {
        return Descripcion_denegar;
    }

    public void setDescripcion_denegar(String Descripcion_denegar) {
        this.Descripcion_denegar = Descripcion_denegar;
    }

    public String getUUID_veterinaria_denegar() {
        return UUID_veterinaria_denegar;
    }

    public void setUUID_veterinaria_denegar(String UUID_veterinaria_denegar) {
        this.UUID_veterinaria_denegar = UUID_veterinaria_denegar;
    }

    public String getUUID_mascota_denegar() {
        return UUID_mascota_denegar;
    }

    public void setUUID_mascota_denegar(String UUID_mascota_denegar) {
        this.UUID_mascota_denegar = UUID_mascota_denegar;
    }

    public String getUUID_Usuario_denegar() {
        return UUID_Usuario_denegar;
    }

    public void setUUID_Usuario_denegar(String UUID_Usuario_denegar) {
        this.UUID_Usuario_denegar = UUID_Usuario_denegar;
    }

    public String getEstado_denegar() {
        return Estado_denegar;
    }

    public void setEstado_denegar(String Estado_denegar) {
        this.Estado_denegar = Estado_denegar;
    }
    
    
    
     public void Update(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
                   
             int filaSeleccionada = tabla.getSelectedRow();

             String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
            
            //Ejecutar la consulta

             String sql_update = "Update tbCitas set estado = 'Denegado', descripcion_motivo = ?  where UUID_cita = ?";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt_update = conexion.prepareStatement(sql_update);
            //Establecer valores de la consulta SQL
            pstmt_update.setString(1,getDescripcion_denegar());
            pstmt_update.setString(2, miId);
            pstmt_update.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar_asignar " + ex);
        }
    }
     public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_cita", "fecha_cita", "motivo_cita", "descripcion_motivo", "mascota", "vet", "usuario", "estado"});
        try {
            //Consulta a ejecutar
            String query = "SELECT * FROM tbCitas";
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(query);
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_cita"), 
                    rs.getString("fecha_cita"), 
                    rs.getString("motivo_Cita"), 
                    rs.getString("descripcion_motivo"),
                rs.getString("mascota"),
                rs.getString("vet"),
                rs.getString("usuario"),
                rs.getString("estado")
                });
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }

    
      public void limpiar(denegar_cita Vista) {
        Vista.txtRazonDenegar.setText("");
    }
    
}
