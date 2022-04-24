
package com.amm.finciclo.proyectofinciclo;

import dao.DAOProveedor;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProveedorController extends ControladorConNavegabilidad implements Initializable {
    
    @FXML TextField idproveedor, nombreProveedor, apellidoProveedor;  
    @FXML TableView<Proveedor> tablaProveedores;
    @FXML Button anadir;
    @FXML Button prepararModificar;
    @FXML Button eliminar;
    @FXML Button atras;
    DAOProveedor daoProveedores;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            daoProveedores = new DAOProveedor();
        } catch (SQLException ex) {
            System.out.println("ex");
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
