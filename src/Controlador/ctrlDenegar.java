/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlAsignar;
import Modelo.mdlDenegar;
import Vista.denegar_cita;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Rene Garcia
 */
public class ctrlDenegar implements MouseListener, KeyListener {
    private mdlDenegar Modelo;
    private denegar_cita vista;
    
     public ctrlDenegar(mdlDenegar Modelo, denegar_cita vista) {

        this.Modelo = Modelo;
        this.vista = vista;
        
        
            vista.btnDenegar.addMouseListener(this);
        Modelo.Mostrar(vista.tbDenegar);

     
     }
       
        
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnDenegar) {
        
                try {
                    //Asignar lo de la vista al modelo
                    Modelo.setDescripcion_denegar(vista.txtRazonDenegar.getText());
                  

                    //Ejecutar el metodo 
                    Modelo.Update(vista.tbDenegar);
                    Modelo.Mostrar(vista.tbDenegar);
                    Modelo.limpiar(vista);
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

