/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.Insertar_citas1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rene Garcia
 */
public class mdlPrueba {
 private String Fecha;
    private String Motivo;
    private String Descripcion;

    public String getUUID_veterinaria() {
        return UUID_veterinaria;
    }

    public void setUUID_veterinaria(String UUID_veterinaria) {
        this.UUID_veterinaria = UUID_veterinaria;
    }

    public String getUUID_mascota() {
        return UUID_mascota;
    }

    public void setUUID_mascota(String UUID_mascota) {
        this.UUID_mascota = UUID_mascota;
    }

    public String getUUID_Usuario() {
        return UUID_Usuario;
    }

    public void setUUID_Usuario(String UUID_Usuario) {
        this.UUID_Usuario = UUID_Usuario;
    }
   private String UUID_veterinaria;
   private String UUID_mascota;
   private String UUID_Usuario;

    
    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    } 
    
     public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO tbCitas(UUID_cita, fecha_cita, motivo_cita, descripcion_motivo,  mascota, vet, usuario) VALUES (?, ?, ?, ?,?,?,?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getFecha());
            pstmt.setString(3, getMotivo());
            pstmt.setString(4, getDescripcion());
            pstmt.setString(5, getUUID_mascota());
            pstmt.setString(6, getUUID_veterinaria());
            pstmt.setString(7, getUUID_Usuario());
            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
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

    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from tbCitas where UUID_Cita = ?";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }

    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update tbCitas set fecha_cita= ?, motivo_cita = ?, descripcion_motivo = ?, mascota = ?, usuario = ?, vet = ? where UUID_cita = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getFecha());
                updateUser.setString(2, getMotivo());
                updateUser.setString(3, getDescripcion());
                updateUser.setString(4, getUUID_mascota());
                updateUser.setString(5, getUUID_Usuario());
                updateUser.setString(6, getUUID_veterinaria());
                updateUser.setString(7, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }

    public void Buscar(JTable tabla, JTextField miTextField) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_cita", "fecha_cita", "motivo_cita", "descripcion_motivo", "mascota", "vet", "usuario", "estado"});
        try {
            String sql = "SELECT * FROM tbCitas WHERE motivo_cita LIKE ? || '%'";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miTextField.getText());
            ResultSet rs = deleteEstudiante.executeQuery();

            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_cita"), rs.getString("fecha_cita"), rs.getString("motivo_cita"), rs.getString("descripcion_motivo"), rs.getString("mascota"), rs.getString("vet"), rs.getString("usuario"),rs.getString("estado")});
            }

            
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }

    public void limpiar(Insertar_citas1 Vista) {
        Vista.txtFecha.setText("");
        Vista.txtMotivo.setText("");
        Vista.txtDescripcion.setText("");
    }

    public void cargarDatosTabla(Insertar_citas1 Vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = Vista.tbPrueba.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDPrueba = Vista.tbPrueba.getValueAt(filaSeleccionada, 0).toString();
            String Fecha = Vista.tbPrueba.getValueAt(filaSeleccionada, 1).toString();
            String Motivo = Vista.tbPrueba.getValueAt(filaSeleccionada, 2).toString();
            String Descripcion = Vista.tbPrueba.getValueAt(filaSeleccionada, 3).toString();

            // Establece los valores en los campos de texto
            Vista.txtFecha.setText(Fecha);
            Vista.txtMotivo.setText(Motivo);
            Vista.txtDescripcion.setText(Descripcion);
        }
    }

}



