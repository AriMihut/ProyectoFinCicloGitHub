
package com.amm.finciclo.proyectofinciclo;

import dao.DAOCliente;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClienteController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML TextField id, dni, nombre, telefono, importeTotal; 
    @FXML private ComboBox<Servicio> servicios;
    @FXML private ComboBox<String> comboboxSexo;
    @FXML TextArea reclamarOContactar;
    @FXML TableView<Cliente> tablaClientes;
    @FXML Button anadir;
    @FXML Button prepararModificar;
    @FXML Button eliminar;
    @FXML Button atras;
    DAOCliente daoClientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            daoClientes = new DAOCliente();
            configurarComboBox();
        } catch (SQLException ex) {
            System.out.println("Error initialize DaoCliente " + ex.getMessage());
        }
    }    
    
    private void configurarServicios(){
        servicios.getSelectionModel().selectFirst();      
    }
     
    private void configurarComboBox(){
        comboboxSexo.getItems().addAll("Femenino", "Masculino");
        comboboxSexo.getSelectionModel().selectFirst();      
    }
    
    @FXML
    public void comprar(){
      
    }
    
    @FXML
    public void devolver(){
        
    }
    
    @FXML
    public void contactar(){
        this.layout.mostrarComoPantallaActual("contacto");
    }
    
    @FXML
    public void anadir(){
      
    }
    
    @FXML
    public void editar(){
      
    }
    
    @FXML
    public void eliminar(){
      
    }
    
    @FXML
    public void volverAtras() throws IOException{
       this.layout.mostrarComoPantallaActual("autentificacion");
    }
    
}
