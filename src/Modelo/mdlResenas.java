/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.resenas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rene Garcia
 */
public class mdlResenas {
      private String UUID_resena;
    private String calificacion;
    private String comentarios;
    private String resenador;
    private String vet;


    public String getUUID_resena() {
        return UUID_resena;
    }

    public void setUUID_resena(String UUID_resena) {
        this.UUID_resena = UUID_resena;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getResenador() {
        return resenador;
    }

    public void setResenador(String resenador) {
        this.resenador = resenador;
    }

    public String getVet() {
        return vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }
  
    
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_resena", "calificacion", "comentarios", "nombre_usuario", "nombre_veterinaria"});
        try {
            //Consulta a ejecutar
            String query = "SELECT re.uuid_resena, re.calificacion, re.comentarios,u.nombre_usuario, nombre_veterinaria\n" +
"FROM tbResenas re\n" +
"Right Join tbUsuariosOne u\n" +
"ON re.resenador = u.uuid_usuario\n" +
"Left JOIN tbVeterinarias v\n" +
"ON re.vet = v.uuid_veterinaria";
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(query);
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_resena"), 
                    rs.getString("calificacion"), 
                    rs.getString("comentarios"), 
                    rs.getString("usuario"),
                rs.getString("veterinaria")
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
            String sql = "delete from tbResenas where UUID_resena = ?";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
 public void Buscar(JTable tabla, JTextField miTextField) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_resena", "calificacion", "comentarios", "resenador", "vet"});
        try {
         String sql = "SELECT * FROM tbResenas WHERE comentarios LIKE ? || '%'";

        modelo.setColumnIdentifiers(new Object[]{"UUID_resena", "calificacion", "comentarios", "resenador", "vet"});
            PreparedStatement buscarResena = conexion.prepareStatement(sql);
            buscarResena.setString(1, miTextField.getText());
            ResultSet rs = buscarResena.executeQuery();

            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_resena"), rs.getString("calificacion"), rs.getString("comentarios"), rs.getString("resenador"), rs.getString("vet")});
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

    public void limpiar(resenas Vista) {
        Vista.txtBuscarResena.setText("");
      
    }
}
