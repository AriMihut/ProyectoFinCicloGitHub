package com.amm.finciclo.proyectofinciclo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class ContactoController extends ControladorConNavegabilidad implements Initializable{
    
    @FXML MenuItem menuMensajes;
    @FXML MenuItem menuContacto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }
    
    @FXML
    public void atras() throws IOException{
       this.layout.mostrarComoPantallaActual("cliente");
    }
    
    @FXML
    public void enviar() throws IOException{
       
    }
    
     @FXML
    public void abrirMensajes() throws IOException{
       
    }
    
     @FXML
    public void contactar() throws IOException{
       
    }
    
     @FXML
    public void salir() throws IOException{
       
    }
    
}
