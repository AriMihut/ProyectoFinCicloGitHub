
package com.amm.finciclo.proyectofinciclo;

import dao.DAOCliente;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClienteController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML TextField idCliente, dniCliente, nombreCliente, apellidoCliente;  
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
        } catch (SQLException ex) {
            System.out.println("ex");
            System.out.println("Error!");
        }
    }    
    
    @FXML
    public void anadir(){
        
      
    }
    
    @FXML
    public void prepararModificar(){
        
    }
    
    @FXML
    public void eliminar(){
        
    }
    
    @FXML
    public void atras() throws IOException{
       // App.setRoot("PantallaHome");
    }
    
}
