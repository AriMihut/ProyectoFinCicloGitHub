package com.amm.finciclo.proyectofinciclo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

public class MensajeController extends ControladorConNavegabilidad implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    public void anadirModificar(){ 
    }
    
    @FXML
    public void prepararModificar(){
       /* Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        dni.setText(cliente.getDni());
        nombre.setText(cliente.getNombre());
        comboboxSexo.setValue(cliente.getSexo());
        telefono.setText(String.valueOf(cliente.getTelefono()));
        email.setText(cliente.getEmail());
        clientesAModificar = cliente;
        id = cliente.getId();*/
    
    }
    
    @FXML
    public void eliminar(){
        /*Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        daoCliente.eliminar(cliente);
        mostrar();*/
    }
    
    @FXML
    public void contactar(){
        /*Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        daoCliente.eliminar(cliente);
        mostrar();*/
    }
    
    @FXML
    public void atras(){
        /*Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        daoCliente.eliminar(cliente);
        mostrar();*/
    }
    
}
