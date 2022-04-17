
package com.amm.finciclo.proyectofinciclo;

import dao.DAOCliente;
import dao.DAODepartamentos;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DepartamentoController implements Initializable{
    
    @FXML TextField idDepartamentos, nombreDepartamento, idPersonal;  
    @FXML TableView<Departamento> tablaDepartamentos;
    @FXML Button anadir, prepararModificar, eliminar, atras;
    private DAODepartamentos daoDepartamentos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            daoDepartamentos = new DAODepartamentos();
        } catch (SQLException ex) {
            System.out.println("Error al instanciar el departamento");
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
