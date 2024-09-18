/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlAsignar;
import Modelo.mdlUsuario_asignar;
import Vista.asignar_cita;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Rene Garcia
 */
public class ctrlAsignar implements MouseListener, KeyListener {
    private mdlAsignar Modelo;
    private asignar_cita vista;
    private mdlUsuario_asignar modelo_user;
    
    
     public ctrlAsignar(mdlAsignar Modelo, asignar_cita vista, mdlUsuario_asignar modelo_usuario) {

        this.Modelo = Modelo;
        this.vista = vista;
        this.modelo_user = modelo_usuario;
       

        this.modelo_user.CargarComboUsuarios_asignar(vista.cmbEmpleados);
        
           vista.cmbEmpleados.addActionListener(e -> {
            if (e.getSource() == vista.cmbEmpleados) {
                mdlUsuario_asignar selectedItem = (mdlUsuario_asignar) vista.cmbEmpleados.getSelectedItem();
                if (selectedItem != null) {
                    String UUID_asignar = selectedItem.getUUID_Usuario_asignar();
                    modelo_user.setUUID_Usuario_asignar(UUID_asignar);
                    System.err.println("ESTE ES EL VALOR SELECCIONADO" + UUID_asignar);
                }
            }
        });
     
             vista.btnAsignar.addMouseListener(this);
        Modelo.Mostrar(vista.tbAsignacion);

        
     }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == vista.btnAsignar) {
        
                try {
                    
                  

                    //Asignar lo de la vista al modelo
                    Modelo.setUUID_Usuario_asignar(modelo_user.getUUID_Usuario_asignar());
                 

                    //Ejecutar el metodo 
                    Modelo.Guardar(vista.tbAsignacion);
                    Modelo.Mostrar(vista.tbAsignacion);
                } catch (Exception ex) {
                }
            }
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
