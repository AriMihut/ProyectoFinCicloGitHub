package com.amm.finciclo.proyectofinciclo;

import dao.DAOMensaje;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContactoController extends ControladorConNavegabilidad implements Initializable{
    @FXML TextField asunto;
    @FXML TextArea mensaje;
    @FXML CheckBox checkUrgente;
    @FXML Button botonEnviar;
    
    DAOMensaje mensajeDao;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mensajeDao = new DAOMensaje();
        } catch (SQLException ex) {
            Logger.getLogger(ContactoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    public void atras() throws IOException{
        this.layout.cargarPantalla("cliente", ClienteController.class.getResource("Cliente.fxml"));
        this.layout.mostrarComoPantallaActual("cliente");
    }
    
    @FXML
    public void enviarMensaje() throws IOException{
        Usuario usuario = this.layout.getUsuario();
        mensajeDao.anadir(new Mensaje(0, usuario.getNombre(),
               asunto.getText(), mensaje.getText(), usuario.getTipoUsuario(), checkUrgente.isSelected(), usuario.getId()));
        this.layout.getCotroller("contacto");
    }
    
     @FXML
    public void verMensajes() throws IOException{
        this.layout.cargarPantalla("mensaje", MensajeController.class.getResource("Mensaje.fxml"));
        this.layout.mostrarComoPantallaActual("mensaje");
    }
    
     @FXML
    public void contactar() throws IOException{
        this.layout.cargarPantalla("contacto", ContactoController.class.getResource("Contacto.fxml"));
        this.layout.mostrarComoPantallaActual("contacto");
    }  
    
   
}
