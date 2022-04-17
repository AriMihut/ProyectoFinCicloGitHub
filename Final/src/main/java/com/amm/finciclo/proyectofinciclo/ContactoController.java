package com.amm.finciclo.proyectofinciclo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ContactoController extends ControladorConNavegabilidad implements Initializable{
   
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
    public void verMensajes() throws IOException{
        this.layout.mostrarComoPantallaActual("mensaje");
    }
    
     @FXML
    public void contactar() throws IOException{
        this.layout.mostrarComoPantallaActual("contacto");
    }    
}
