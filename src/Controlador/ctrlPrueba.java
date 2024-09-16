/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlMascota;
import Modelo.mdlPrueba;
import Modelo.mdlUsuario;
import Modelo.mdlVet;
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
    private mdlUsuario modelo_user;
    private mdlMascota modelo_mascota;
    private mdlVet modelo_vet;

    public ctrlPrueba(mdlPrueba Modelo, Insertar_citas1 vista, mdlUsuario modelo_usuario, mdlMascota modelo_pet, mdlVet modelo_veterinaria) {

        this.Modelo = Modelo;
        this.vista = vista;
        this.modelo_user = modelo_usuario;
        this.modelo_mascota = modelo_pet;
        this.modelo_vet = modelo_veterinaria;

        this.modelo_user.CargarComboUsuarios(vista.cmbUsuarios);
        this.modelo_mascota.cargarComboMascotas(vista.cmbMascota);
        this.modelo_vet.cargarComboVet(vista.cmbVeterinaria);

        vista.cmbUsuarios.addActionListener(e -> {
            if (e.getSource() == vista.cmbUsuarios) {
                mdlUsuario selectedItem = (mdlUsuario) vista.cmbUsuarios.getSelectedItem();
                if (selectedItem != null) {
                    String UUID = selectedItem.getUUID_Usuario();
                    modelo_user.setUUID_Usuario(UUID);
                    System.err.println("ESTE ES EL VALOR SELECCIONADO" + UUID);
                }
            }
        });

        vista.cmbMascota.addActionListener(e -> {
            if (e.getSource() == vista.cmbMascota) {
                mdlMascota selectedItem = (mdlMascota) vista.cmbMascota.getSelectedItem();
                if (selectedItem != null) {
                    String UUID = selectedItem.getUUID_mascota();

                    modelo_mascota.setUUID_mascota(UUID);
                }
            }
        });

        vista.cmbVeterinaria.addActionListener(e -> {
            if (e.getSource() == vista.cmbVeterinaria) {
                mdlVet selectedItem = (mdlVet) vista.cmbVeterinaria.getSelectedItem();
                if (selectedItem != null) {
                    String UUID = selectedItem.getUUID_veterinaria();
                    modelo_vet.setUUID_veterinaria(UUID);
                }
            }
        });

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
                    Modelo.setUUID_mascota(modelo_mascota.getUUID_mascota());
                    Modelo.setUUID_veterinaria(modelo_vet.getUUID_veterinaria());
                    Modelo.setUUID_Usuario(modelo_user.getUUID_Usuario());

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
                    Modelo.setUUID_mascota(modelo_mascota.getUUID_mascota());
                    Modelo.setUUID_veterinaria(modelo_vet.getUUID_veterinaria());
                    Modelo.setUUID_Usuario(modelo_user.getUUID_Usuario());

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
