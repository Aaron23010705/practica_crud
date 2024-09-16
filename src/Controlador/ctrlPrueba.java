/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlPrueba;
import Vista.Insertar_citas;
import Vista.Insertar_citas1;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


/**
 *
 * @author Rene Garcia
 */
public class ctrlPrueba implements MouseListener, KeyListener {
    
   private mdlPrueba Modelo;
    private Insertar_citas1 vista;


public ctrlPrueba (mdlPrueba Modelo, Insertar_citas1 vista) {
    
    
    this.Modelo = Modelo;
    this.vista = vista;
    
    vista.btnGuardar.addMouseListener(this);
    vista.btnActualizar.addMouseListener(this);
    vista.btnEliminar.addMouseListener(this);
    vista.btnLimpiar.addMouseListener(this);
    vista.txtBuscar.addMouseListener(this);
    vista.tbPrueba.addMouseListener(this);

    Modelo.Mostrar(vista.tbPrueba);
    
}
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
         if (e.getSource() == vista.btnGuardar) {
            if (vista.txtFecha.getText().isEmpty() || vista.txtMotivo.getText().isEmpty() || vista.txtDescripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    Modelo.setFecha(vista.txtFecha.getText());
                    Modelo.setMotivo((vista.txtMotivo.getText()));
                    Modelo.setDescripcion(vista.txtDescripcion.getText());
                    //Ejecutar el metodo 
                    Modelo.Guardar();
                    Modelo.Mostrar(vista.tbPrueba);
                    Modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtFecha.getText().isEmpty() || vista.txtMotivo.getText().isEmpty() || vista.txtDescripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Modelo.Eliminar(vista.tbPrueba);
                Modelo.Mostrar(vista.tbPrueba);
                Modelo.limpiar(vista);
            }
        }

        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtFecha.getText().isEmpty() || vista.txtMotivo.getText().isEmpty() || vista.txtDescripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    Modelo.setFecha(vista.txtFecha.getText());
                    Modelo.setMotivo((vista.txtMotivo.getText()));
                    Modelo.setDescripcion(vista.txtDescripcion.getText());

                    //Ejecutar el método    
                    Modelo.Actualizar(vista.tbPrueba);
                    Modelo.Mostrar(vista.tbPrueba);
                    Modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            Modelo.limpiar(vista);
        }

        if (e.getSource() == vista.tbPrueba) {
            Modelo.cargarDatosTabla(vista);
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
