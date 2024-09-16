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
public class mdlMascota {
    
       private String UUID_mascota;
    private String Nombre_mascota;
    private String raza;
    private String sexo;
    private String procesos_previos;
    private String alergias;
    private String enfermedades_cronicas;
    private String peso;
    private String especie;
    private String foto_perfil;
    private String dueno;

    public mdlMascota() {
    }

    public String getUUID_mascota() {
        return UUID_mascota;
    }

    public void setUUID_mascota(String UUID_mascota) {
        this.UUID_mascota = UUID_mascota;
    }

    public String getNombre_mascota() {
        return Nombre_mascota;
    }

    public void setNombre_mascota(String Nombre_mascota) {
        this.Nombre_mascota = Nombre_mascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getProcesos_previos() {
        return procesos_previos;
    }

    public void setProcesos_previos(String procesos_previos) {
        this.procesos_previos = procesos_previos;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getEnfermedades_cronicas() {
        return enfermedades_cronicas;
    }

    public void setEnfermedades_cronicas(String enfermedades_cronicas) {
        this.enfermedades_cronicas = enfermedades_cronicas;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }
    
  public mdlMascota(String uuid, String nombre)
    {
        this.UUID_mascota = uuid;
        this.Nombre_mascota = nombre;
    }
    
      @Override
    public String toString()
    {
        return Nombre_mascota;
    }
    
    
    //Metodo para cargar los valores en el ComboBox
    public void cargarComboMascotas(JComboBox comboBox){    
        Connection conexion = ClaseConexion.getConexion();
        comboBox.removeAllItems();
        try{
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from tbMascotas");
            while (rs.next()) {
                String uuid = rs.getString("UUID_mascota");
                String nombre = rs.getString("nombre_mascota");
                comboBox.addItem(new mdlMascota(uuid,nombre));                
            }
        }
        catch(SQLException ex)
        {
            
        }
    }
    
}

    

