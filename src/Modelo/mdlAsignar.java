/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.asignar_cita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rene Garcia
 */
public class mdlAsignar {
    
   private String Fecha_asignar;
    private String Motivo_asignar;
    private String Descripcion_asignar;
    private String UUID_veterinaria_asignar;
   private String UUID_mascota_asignar;
   private String UUID_Usuario_asignar;
   private String Estado_asignar;

    public String getFecha_asignar() {
        return Fecha_asignar;
    }

    public void setFecha_asignar(String Fecha_asignar) {
        this.Fecha_asignar = Fecha_asignar;
    }

    public String getMotivo_asignar() {
        return Motivo_asignar;
    }

    public void setMotivo_asignar(String Motivo_asignar) {
        this.Motivo_asignar = Motivo_asignar;
    }

    public String getDescripcion_asignar() {
        return Descripcion_asignar;
    }

    public void setDescripcion_asignar(String Descripcion_asignar) {
        this.Descripcion_asignar = Descripcion_asignar;
    }

    public String getUUID_veterinaria_asignar() {
        return UUID_veterinaria_asignar;
    }

    public void setUUID_veterinaria_asignar(String UUID_veterinaria_asignar) {
        this.UUID_veterinaria_asignar = UUID_veterinaria_asignar;
    }

    public String getUUID_mascota_asignar() {
        return UUID_mascota_asignar;
    }

    public void setUUID_mascota_asignar(String UUID_mascota_asignar) {
        this.UUID_mascota_asignar = UUID_mascota_asignar;
    }

    public String getUUID_Usuario_asignar() {
        return UUID_Usuario_asignar;
    }

    public void setUUID_Usuario_asignar(String UUID_Usuario_asignar) {
        this.UUID_Usuario_asignar = UUID_Usuario_asignar;
    }

    public String getEstado_asignar() {
        return Estado_asignar;
    }

    public void setEstado_asignar(String Estado_asignar) {
        this.Estado_asignar = Estado_asignar;
    }

    
  
    
     public void Guardar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
                    int filaSeleccionada = tabla.getSelectedRow();

             String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
                                 System.err.println("ESTE ES EL VALOR SELECCIONADO" + miId);

            String sql = "INSERT INTO tbAsignaciones(UUID_asignacion, citas, empleado) VALUES (?, ?, ?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, miId);
            pstmt.setString(3,getUUID_Usuario_asignar());
                             
            System.err.println("ESTE ES EL VALOR SELECCIONADO" + getUUID_Usuario_asignar());

            
            //Ejecutar la consulta
            pstmt.executeUpdate();

             String sql_update = "UPDATE tbCitas set estado = 'Asignada' where UUID_cita = ? ";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt_update = conexion.prepareStatement(sql_update);
            //Establecer valores de la consulta SQL
            pstmt_update.setString(1, miId);
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


}
  
    
    

