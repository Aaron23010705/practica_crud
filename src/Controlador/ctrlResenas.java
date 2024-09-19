/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlResenas;
import Vista.resenas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Rene Garcia
 */
public class ctrlResenas implements MouseListener, KeyListener{
    
     private mdlResenas Modelo;
    private resenas vista;
    
    public ctrlResenas(mdlResenas Modelo, resenas vista) {

        this.Modelo = Modelo;
        this.vista = vista; 
         vista.btnBuscarResena.addMouseListener(this);
        vista.btnEliminarResena.addMouseListener(this);
     

        Modelo.Mostrar(vista.tbResenas);
        
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
           if (e.getSource() == vista.btnEliminarResena) {
         
                Modelo.Eliminar(vista.tbResenas);
                Modelo.Mostrar(vista.tbResenas);
                Modelo.limpiar(vista);
            }
      
           
            if (e.getSource()==vista.btnBuscarResena){
             
          Modelo.Buscar(vista.tbResenas, vista.txtBuscarResena);
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
